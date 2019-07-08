package com.ecommerce.shoppingportal.domain;

import java.sql.Timestamp;

public class Order {

    private String orderId;
    private String productName;
    private double productUnitPrice;
    private int productAmount;
    private double taxRate;
    private double totalPrice;
    private Timestamp OrderDate;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public Timestamp getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        OrderDate = orderDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", productUnitPrice=" + productUnitPrice +
                ", productAmount=" + productAmount +
                ", taxRate=" + taxRate +
                ", totalPrice=" + totalPrice +
                ", OrderDate=" + OrderDate +
                '}';
    }
}
