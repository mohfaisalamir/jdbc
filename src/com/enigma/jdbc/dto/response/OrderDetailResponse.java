package com.enigma.jdbc.dto.response;

public class OrderDetailResponse {
    private String productName;
    private Long productPrice;
    private Integer quantity;
    private Long subTotal;

    public OrderDetailResponse(String productName, Long productPrice, Integer quantity, Long subTotal) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public OrderDetailResponse() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String  toString() {
        return "\n\tOrderDetailResponse : " +
                "\n\tproductName  : " + productName  +
                "\n\tproductPrice : " + productPrice +
                "\n\tquantity     : " + quantity +
                "\n\tsubTotal     : " + subTotal +
                "\n\t" + "_".repeat(30) ;
    }
}
