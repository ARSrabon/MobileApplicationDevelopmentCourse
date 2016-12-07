package com.example.student.lab6.Models;

/**
 * Created by student on 12/7/16.
 */

public class Product {

    public double price;
    public String productName;

    public Product() {
    }

    public Product(double price, String productName) {
        this.price = price;
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return productName + price;
    }
}
