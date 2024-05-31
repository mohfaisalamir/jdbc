package com.enigma.jdbc.dto.response;

import com.enigma.jdbc.entity.OrderDetail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private Integer orderId;
    private Date orderDate;
    private String customerName;
    private String table;
    private List<OrderDetailResponse> orderDetails;

    public OrderResponse(Integer orderId, Date orderDate, String customerName, String table/*, List<OrderDetail> orderDetails*/) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.table = table;
        this.orderDetails = new ArrayList<>();
    }

    public OrderResponse() {
    }

    public void addOrderDetail(OrderDetailResponse orderDetailResponse) {
        this.orderDetails.add(orderDetailResponse);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponse > orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "\nOrder : " +
                "\norderId        : " + orderId +
                "\norderDate    : " + orderDate +
                "\ncustomerName : " + customerName +
                "\ntable        : " + table +
                "\n" + "=".repeat(30)+
                "\norderDetails : " + orderDetails ;
    }
}
