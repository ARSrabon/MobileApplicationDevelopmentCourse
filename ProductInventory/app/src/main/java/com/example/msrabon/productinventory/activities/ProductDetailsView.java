package com.example.msrabon.productinventory.activities;

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

import com.example.msrabon.productinventory.R;
import com.example.msrabon.productinventory.models.Product;

import java.util.ArrayList;

public class ProductDetailsView extends AppCompatActivity {

    Button btn_stckupdate;
    Button btn_seller;
    Button btn_delete;

    TextView txt_productName;
    TextView txt_productId;
    TextView txt_productPrice;
    TextView txt_productStock;
    TextView txt_productCategory;
    private SharedPreferences.Editor editor;

    long product_id;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_view_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final Intent intent = getIntent();
        product_id = intent.getLongExtra("product_id",-1);
        Log.d("details",String.valueOf(intent.getLongExtra("product_id",-1)));

        product = Product.load(Product.class,product_id);

        btn_stckupdate = (Button) findViewById(R.id.btn_stockupdater);
        btn_seller = (Button) findViewById(R.id.btn_product_sell);
        btn_delete = (Button) findViewById(R.id.btn_product_detete);

        txt_productId = (TextView) findViewById(R.id.view_productid);
        txt_productName = (TextView) findViewById(R.id.view_productname);
        txt_productStock = (TextView) findViewById(R.id.view_productstock);
        txt_productPrice = (TextView) findViewById(R.id.view_productprice);

        txt_productId.setText(String.valueOf(product.getProduct_id()));
        txt_productName.setText(product.getProduct_name());
        txt_productStock.setText(String.valueOf(product.getProduct_stock()));
        txt_productPrice.setText(String.valueOf(product.getProduct_price()));

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
                intent1.putExtra("selectedProduct",true);
                intent1.putExtra("selectedProductId",product_id);
                startActivity(intent1);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.delete(Product.class,product_id);
                Intent intent1 = new Intent(ProductDetailsView.this,DefaultView_Activity.class);
                startActivity(intent1);
                finish();
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
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(ProductDetailsView.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(ProductDetailsView.this,SellHistoryViewActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_logout:
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.My_sharedPrefs_file_name), Context.MODE_PRIVATE).edit();
                editor.putBoolean("loggedin", false);
                editor.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(ProductDetailsView.this,DefaultView_Activity.class);
        startActivity(intent1);
        finish();
    }

}
