package com.enigma.jdbc.repository.impl;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.dto.request.OrderDetailRequest;
import com.enigma.jdbc.dto.request.OrderRequest;
import com.enigma.jdbc.dto.response.OrderDetailResponse;
import com.enigma.jdbc.dto.response.OrderResponse;
import com.enigma.jdbc.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderReporsitoryImpl implements OrderRepository {
    @Override
    public void save(OrderRequest request) {
        try(Connection connection = DBConnector.connection()) {
            connection.setAutoCommit(false);
            // 1. INSERT ORDER
            PreparedStatement orderStatement = connection.prepareStatement(
                    "INSERT into t_order (trans_date, customer_id, table_id) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            orderStatement.setDate(1, new Date(System.currentTimeMillis()));
            orderStatement.setInt(2,request.getCustomerId());
            orderStatement.setInt(3,request.getTabelId());
            orderStatement.executeUpdate();
            // 2. GET ID FROM ORDER
            int orderId = 0;
            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }else {
                connection.rollback();
            }

            orderStatement.close();
            // 3. INSERT ORDER DETAIL
            PreparedStatement orderDetailStatement = connection.prepareStatement(
                    "insert into t_order_detail (order_id, product_id,quantity) VALUES (?,?,?)");
            for (OrderDetailRequest orderDetail : request.getOrderDetails()) {
                orderDetailStatement.clearParameters();
                orderDetailStatement.setInt(1, orderId);
                orderDetailStatement.setInt(2,orderDetail.getProductId());
                orderDetailStatement.setInt(3,orderDetail.getQuantity());
                orderDetailStatement.addBatch();
            }
            orderDetailStatement.executeBatch();
            orderDetailStatement.close();
            connection.commit();
            System.out.println("Order details inserted and SUCCESS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderResponse  findById(Integer id) {
        if(id == null) {
            throw new RuntimeException/*IllegalArgumentException*/("id cannot be null");
        }
        try(Connection connection = DBConnector.connection()) {
            String query = " select                \n" +
                    "t.id order_id,\n" +
                    "t.trans_date trans_date,\n" +
                    "mc.customer_name customer_name,\n" +
                    "mt.name table,\n" +
                    "mp.name product_name,\n" +
                    "mp.price product_price,\n" +
                    "tod.quantity quantity,\n" +
                    "mp.price * quantity sub_total\n" +
                    "from t_order t\n" +
                    "join m_customer mc on t.customer_id = mc.id\n" +
                    "join m_table mt on t.table_id = mt.id\n" +
                    "join t_order_detail tod on t.id = tod.order_id\n" +
                    "join m_product mp on mp.id = tod.product_id WHERE t.id =?;\n" +
                    "\n";

            PreparedStatement orderStatement = connection.prepareStatement(query);
            orderStatement.setInt(1,id);
            ResultSet resultSet = orderStatement.executeQuery();
            Map<Integer, OrderResponse> orderMap = getOrderMap(resultSet);
            resultSet.close();
            orderStatement.close();

            return orderMap.get(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderResponse> findAll() {
        try(Connection connection = DBConnector.connection()) {
            String query = " select                \n" +
                    "t.id order_id,\n" +
                    "t.trans_date trans_date,\n" +
                    "mc.customer_name customer_name,\n" +
                    "mt.name table,\n" +
                    "mp.name product_name,\n" +
                    "mp.price product_price,\n" +
                    "tod.quantity quantity,\n" +
                    "mp.price * quantity sub_total\n" +
                    "from t_order t\n" +
                    "join m_customer mc on t.customer_id = mc.id\n" +
                    "join m_table mt on t.table_id = mt.id\n" +
                    "join t_order_detail tod on t.id = tod.order_id\n" +
                    "join m_product mp on mp.id = tod.product_id;\n" +
                    "\n";

            PreparedStatement orderStatement = connection.prepareStatement(query);
            ResultSet resultSet = orderStatement.executeQuery();
            Map<Integer, OrderResponse> orderMap = getOrderMap(resultSet);
            resultSet.close();
            orderStatement.close();

            return new ArrayList<>(orderMap.values());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private @NotNull Map<Integer, OrderResponse> getOrderMap(ResultSet resultSet) throws SQLException {
        Map<Integer, OrderResponse> orderMap = new HashMap<>();

        while (resultSet.next()) {
            int orderId = resultSet.getInt("order_id");
            Date transDate = resultSet.getDate("trans_date");
            String customerName = resultSet.getString("customer_name");
            String table = resultSet.getString("table");
            String productName = resultSet.getString("product_name");
            long price = resultSet.getLong("product_price");
            int quantity = resultSet.getInt("quantity");
            long subTotal = resultSet.getLong("sub_total");

            OrderResponse orderResponse = orderMap.get(orderId);
            if (orderResponse == null) {
                orderResponse = new OrderResponse(
                        orderId,
                        transDate,
                        customerName,
                        table
                );
                orderMap.put(orderId, orderResponse);
            }
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse(productName,price,quantity,subTotal);
            orderResponse.addOrderDetail(orderDetailResponse);

        }
        return orderMap;
    }

}
