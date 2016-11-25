package com.example.msrabon.productinventory;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by msrabon on 11/16/16.
 */

@Table(name = "products")
public class Product extends Model {

    @Column(name = "name")
    private String product_name;

    @Column(name = "product_id")
    private String product_id;

    @Column(name = "stock")
    private long product_stock;

    @Column(name = "price")
    private double product_price;

    public Product(String product_name, String product_id, long product_stock, double product_price) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.product_stock = product_stock;
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public long getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(long product_stock) {
        this.product_stock = product_stock;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
