package com.example.msrabon.activeandroidtest.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.msrabon.activeandroidtest.R;
import com.example.msrabon.activeandroidtest.models.Product;


import java.util.ArrayList;

public class ProductDetailsView extends AppCompatActivity {

    Button btn_stckupdate;
    Button btn_seller;
    Button btn_delete;

    TextView txt_productName;
    TextView txt_productId;
    TextView txt_productPrice;
    TextView txt_productStock;
    private SharedPreferences.Editor editor;

    int product_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_view_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ArrayList<Product> productList = (ArrayList) new Select().from(Product.class).execute();
        final Intent intent = getIntent();
        product_id = intent.getIntExtra("product_id",1);
        Log.d("details",productList.get(intent.getIntExtra("product_id",1)).getProduct_name());

        btn_stckupdate = (Button) findViewById(R.id.btn_stockupdater);
        btn_seller = (Button) findViewById(R.id.btn_product_sell);
        btn_delete = (Button) findViewById(R.id.btn_product_detete);

        txt_productId = (TextView) findViewById(R.id.view_productid);
        txt_productName = (TextView) findViewById(R.id.view_productname);
        txt_productStock = (TextView) findViewById(R.id.view_productstock);
        txt_productPrice = (TextView) findViewById(R.id.view_productprice);

        txt_productId.setText(productList.get(intent.getIntExtra("product_id",1)).getProduct_id());
        txt_productName.setText(productList.get(intent.getIntExtra("product_id",1)).getProduct_name());
        txt_productStock.setText(String.valueOf(productList.get(intent.getIntExtra("product_id",1)).getProduct_stock()));
        txt_productPrice.setText(String.valueOf(productList.get(intent.getIntExtra("product_id",1)).getProduct_price()));

        btn_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProductDetailsView.this,Product_Sell_Activity.class);
                intent1.putExtra("product_id",product_id);
                startActivity(intent1);
            }
        });

        btn_stckupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProductDetailsView.this,Product_Stock_Updater_Activity.class);
                intent1.putExtra("product_id",product_id);
                startActivity(intent1);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = productList.get(product_id);
                product.delete(Product.class,product_id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_prouct:
                intent = new Intent(ProductDetailsView.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(ProductDetailsView.this, Product_Stock_Updater_Activity.class);
                break;
            case R.id.action_logout:
                editor = getSharedPreferences(getString(R.string.My_sharedPrefs_file_name), Context.MODE_PRIVATE).edit();
                editor.putBoolean("loggedin",false);
                editor.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
