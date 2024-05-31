package com.enigma.jdbc.entity;

import java.sql.Date;

public class Order {
    private Integer orderId;
    private Integer tableId;
    private Integer customerId;
    private Date orderDate;

    public Order(Integer orderId, Integer tableId, Integer customerId, Date orderDate) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }
    public Order() {

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", tableId=" + tableId +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                '}';
    }
}
