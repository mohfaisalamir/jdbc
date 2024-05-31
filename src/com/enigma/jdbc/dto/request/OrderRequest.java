package com.enigma.jdbc.dto.request;

import java.util.List;

public class OrderRequest {
    private Integer customerId;
    private Integer tabelId;
//    private Date date; karena date telah dilakukan perintah otomatis "new Date(System.currentTimeMillis())" maka dihapus saja ini
    private List<OrderDetailRequest> orderDetails;

    public OrderRequest(Integer customerId, Integer tabelId,  List<OrderDetailRequest> orderDetails) {
        this.customerId = customerId;
        this.tabelId = tabelId;
        this.orderDetails = orderDetails;
    }

    public OrderRequest() {
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "customerId=" + customerId +
                ", tabelId=" + tabelId +
                ", orderDetails=" + orderDetails +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTabelId() {
        return tabelId;
    }

    public void setTabelId(Integer tabelId) {
        this.tabelId = tabelId;
    }


    public List<OrderDetailRequest> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
