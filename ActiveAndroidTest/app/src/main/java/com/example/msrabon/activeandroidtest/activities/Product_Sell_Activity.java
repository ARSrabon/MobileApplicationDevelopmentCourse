package com.example.msrabon.activeandroidtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.activeandroid.query.Select;
import com.example.msrabon.activeandroidtest.R;
import com.example.msrabon.activeandroidtest.models.Product;

import java.util.List;


public class Product_Sell_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Product> productList;
    Spinner product_spinner;
    Product products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sell);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Product Stock");


        product_spinner = (Spinner) findViewById(R.id.spinner_products);
        productList = new Select().from(Product.class).execute();

        ArrayAdapter<Product> productArrayAdapter = new ArrayAdapter<Product>(this,android.R.layout.simple_spinner_item,productList);
        productArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product_spinner.setAdapter(productArrayAdapter);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("selectedProduct",false)){
            long id = intent.getLongExtra("selectedProductId",-1);
            if(id != -1){
                int count=0;
                for (Product product: productList) {
                    if(product.getId() == id){
                        product_spinner.setSelection(count);
                        break;
                    }
                    count++;
                }
                Log.d("The loop","Completed");
            }
        }

        Log.d("The loop","Completed");

        product_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        products = productList.get(adapterView.getSelectedItemPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        products = productList.get(0);
    }
}
