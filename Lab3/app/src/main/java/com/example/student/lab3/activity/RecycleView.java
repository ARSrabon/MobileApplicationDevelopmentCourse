package com.example.student.lab3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.student.lab3.R;
import com.example.student.lab3.models.Product;
import com.example.student.lab3.adapters.ProductAdapter;

import java.util.ArrayList;

public class RecycleView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        ArrayList <Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop Acer",25));
        products.add(new Product("Laptop HP",20));
        products.add(new Product("Apple Mac",15));
        products.add(new Product("Laptop Lennovo",67));

        ProductAdapter productAdapter = new ProductAdapter(products);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);

    }

}
