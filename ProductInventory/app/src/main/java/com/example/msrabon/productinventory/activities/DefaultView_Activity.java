package com.example.msrabon.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.msrabon.productinventory.models.Product;
import com.example.msrabon.productinventory.adapters.ProductAdapter;
import com.example.msrabon.productinventory.R;

import java.util.ArrayList;

public class DefaultView_Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defaultview_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        sharedPreferences = getSharedPreferences("Product_inventory_prefs", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("loggedin", false)) {
            Toast.makeText(DefaultView_Activity.this, "Welcome, " + sharedPreferences.getString("username", ""), Toast.LENGTH_LONG).show();
        }

        ArrayList <Product> products = new ArrayList<Product>();
        products.add(new Product("Acer",25,199.99));
        products.add(new Product("ASUS",25,399.99));
        products.add(new Product("HP",25,299.90));
        products.add(new Product("Lennovo",25,500.99));

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter(products);
        recyclerView.setAdapter(productAdapter);




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
                intent = new Intent(DefaultView_Activity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(DefaultView_Activity.this, Product_Stock_Updater_Activity.class);
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
