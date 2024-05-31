package com.enigma.jdbc.repository.impl;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.entity.Customer;
import com.enigma.jdbc.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public void save(Customer customer) {
        try (Connection connection = DBConnector.connection()) {
            String query = "insert into customer_table values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setBoolean(4,customer.isMember());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            System.out.println("CustomerRepositoryImpl save");
        }
    }

        @Override
    public Customer findById(int id) {
            Customer customer;
            try (Connection connection = DBConnector.connection()) {
                String query = "select * from customer_table where id_customer = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                String name = null, phone = null;
                boolean member = false;


                while (resultSet.next()) {
                    //customer_name | customer_phone | member_status //
                    // ingnat ya, resultSet ini merujuk pada database, sehingga resultSet.gerData(column_name) wajib di samakan dengan nama column pada tabel
                    name = resultSet.getString("customer_name");
                    phone = resultSet.getString("customer_phone");
                    member = resultSet.getBoolean("member_status");

                    //System.out.println("=".repeat(20)+"\nNama    : " + name + "\nPhone : " + phone+"\nMember : " + member);
                }
                customer = new Customer(id, name, phone, member);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return customer;
        }

    @Override
    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnector.connection()) {
            String query = "select * from customer_table";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id =0;
            String name = null, phone = null;
            boolean member = false;

            while (resultSet.next()) {
                //customer_name | customer_phone | member_status //
                // ingnat ya, resultSet ini merujuk pada database, sehingga resultSet.gerData(column_name) wajib di samakan dengan nama column pada tabel
                id  = resultSet.getInt("id_customer");
                name = resultSet.getString("customer_name");
                phone = resultSet.getString("customer_phone");
                member = resultSet.getBoolean("member_status");

               // System.out.println("=".repeat(20)+"\nNama    : " + name + "\nPhone : " + phone+"\nMember : " + member);
                Customer customer = new Customer(id, name, phone, member);
                customers.add(customer);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return customers;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnector.connection()) {
            String query = "delete from customer_table where id_customer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            System.out.println("CustomerRepositoryImpl delete");
        }

    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DBConnector.connection()) {
            String query = "update customer_table set customer_name=?,customer_phone=?,member_status=? where id_customer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setBoolean(3, customer.isMember());
            preparedStatement.setInt(4, customer.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            System.out.println("CustomerRepositoryImpl save");
        }


    }
}
