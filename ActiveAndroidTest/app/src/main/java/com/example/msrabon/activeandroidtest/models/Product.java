package com.example.msrabon.activeandroidtest.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by msrabon on 11/16/16.
 */

@Table(name = "products")
public class Product extends Model {

    @Column(name = "p_name")
    private String product_name;

    @Column(name = "p_id", unique = true)
    private String product_id;

    @Column(name = "p_stock")
    private int product_stock;

    @Column(name = "p_price")
    private double product_price;

    @Column(name = "p_category")
    private Category category;

    public Product() {
        super();
    }

    public Product(String product_name, String product_id, int product_stock, double product_price, Category category) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.product_stock = product_stock;
        this.product_price = product_price;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return product_name + " " + product_id + " " + product_price + " " + product_stock;
    }
}
