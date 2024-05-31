package com.enigma.jdbc.dto.request;

public class OrderDetailRequest {
   // private int orderId; karena otomatis terisi maka dihapus
    private int productId;
    private int quantity;
    public OrderDetailRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetailRequest() {
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\n"+
                "\nOrderDetailRequest : " +
                "\nproductId= " + productId +
                "\nquantity = " + quantity ;
    }
}
