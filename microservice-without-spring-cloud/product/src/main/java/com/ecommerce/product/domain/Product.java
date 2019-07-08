package com.ecommerce.product.domain;

import java.util.Objects;

public class Product {

    private String category;
    private String productName;
    private double productUnitPrice;
    private int productAmount; //total inventory amount of the product

    public Product() {
    }

    public Product(String category, String name, double unitPrice, int availableAmount) {
        this.category = category;
        this.productName = name;
        this.productUnitPrice = unitPrice;
        this.productAmount = availableAmount;
    }

    public Product(String category, String productName) {
        this.category = category;
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getProductName(), product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getProductName());
    }
}
