package com.example.student.lab6.Models;

import java.util.List;

/**
 * Created by student on 12/7/16.
 */

public class ProductList {
    List<Product> productList;

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addToList(Product product){
        productList.add(product);
    }

}
