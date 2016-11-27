package com.example.student.lab3.models;

/**
 * Created by student on 10/26/16.
 */

public class Product {
    private String productName;
    private int productStock;

    public Product(String productName, int productStock) {
        this.productName = productName;
        this.productStock = productStock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
}
