package com.example.student.productinventory.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.student.productinventory.models.Product;

import java.util.List;

/**
 * Created by msrabon on 11/29/16.
 */

@Table(name = "categories")
public class Category extends Model {

    @Column(name = "c_name")
    private String category_name;

    public Category() {
        super();
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    @Override
    public String toString() {
        return category_name;
    }

    public List<Product> GetProductList(){
        return getMany(Product.class,"p_category");
    }

}
