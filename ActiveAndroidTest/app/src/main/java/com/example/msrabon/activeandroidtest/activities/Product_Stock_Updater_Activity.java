package com.example.msrabon.activeandroidtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.msrabon.activeandroidtest.R;
import com.example.msrabon.activeandroidtest.models.Product;

import java.util.List;


public class Product_Stock_Updater_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner product_spinner;

    EditText edit_ProductStock;
    EditText edit_ProductPrice;

    Button btn_StockUpdate;
    Button btn_Discard;

    Product products;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__stock__updater);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Add Product Stock");

        btn_StockUpdate = (Button) findViewById(R.id.btn_updateStock);
        btn_Discard = (Button) findViewById(R.id.btn_discardUpdate);

        product_spinner = (Spinner) findViewById(R.id.spinner_products);

        edit_ProductPrice = (EditText) findViewById(R.id.edit_productPrice);
        edit_ProductStock = (EditText) findViewById(R.id.edit_productStock);


        productList = new Select().from(Product.class).execute();

        ArrayAdapter<Product> productArrayAdapter = new ArrayAdapter<Product>(this,R.layout.spinner_item,productList);
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
//                Log.d("The loop","Completed");
            }
        }

//        Log.d("The loop !!!!","Completed");

        product_spinner.setOnItemSelectedListener(this);

        btn_StockUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.setProduct_stock(Integer.parseInt(edit_ProductStock.getText().toString()));
                products.setProduct_price(Double.parseDouble(edit_ProductPrice.getText().toString()));
                products.save();

                Toast.makeText(Product_Stock_Updater_Activity.this, "Product Stock Updated.", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(Product_Stock_Updater_Activity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        btn_Discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Product_Stock_Updater_Activity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Product_Stock_Updater_Activity.this,MainActivity.class);
        startActivity(intent1);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        products = productList.get(adapterView.getSelectedItemPosition());
        edit_ProductPrice.setText(String.valueOf(products.getProduct_price()));
        edit_ProductStock.setText(String.valueOf(products.getProduct_stock()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        products = productList.get(0);
        edit_ProductPrice.setText(String.valueOf(products.getProduct_price()));
        edit_ProductStock.setText(String.valueOf(products.getProduct_stock()));
    }
}
