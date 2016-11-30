package com.example.msrabon.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.msrabon.productinventory.R;
import com.example.msrabon.productinventory.adapters.ProductAdapter;
import com.example.msrabon.productinventory.models.Category;
import com.example.msrabon.productinventory.models.Product;

import java.util.ArrayList;
import java.util.List;

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
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("loggedin", false)) {
            Toast.makeText(DefaultView_Activity.this, "Welcome, " + sharedPreferences.getString("username", ""), Toast.LENGTH_LONG).show();
        }

        try {
            Log.d("dbempty", String.valueOf(sharedPreferences.getString("dbempty", "")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sharedPreferences.getString("dbempty", "true").equals("true")) {
            ArrayList<Category> category = new ArrayList<Category>();
            category.add(new Category("NoteBook"));
            category.add(new Category("iPad"));
            category.add(new Category("MacBook"));
            category.add(new Category("Laptop"));

            for (Category category1 : category) {
                category1.save();
            }


            ArrayList<Product> products = new ArrayList<Product>();
            products.add(new Product("Acer", "lp-1", 65, 199.99, category.get(3)));
            products.add(new Product("ASUS", "lp-2", 15, 399.99, category.get(3)));
            products.add(new Product("HP", "lp-3", 45, 299.90, category.get(3)));
            products.add(new Product("Lennovo", "lp-4", 5, 500.99, category.get(3)));
            products.add(new Product("MacBook15'", "mac_15p", 10, 2099.9, category.get(2)));
            products.add(new Product("iPad 9'", "tab_9", 25, 1899.0, category.get(1)));
            products.add(new Product("ASUS", "lp-2", 15, 399.99, category.get(0)));

            for (Product product : products) {
                try {
                    product.save();
                } catch (SQLException q) {
                    q.printStackTrace();
                }
            }

            editor.putString("dbempty", "false");
            editor.commit();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            List<Product> productList = new Select().from(Product.class).execute();
            Log.d("mylist", productList.toString());
            productAdapter = new ProductAdapter((ArrayList<Product>) productList, DefaultView_Activity.this);
            recyclerView.setAdapter(productAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(DefaultView_Activity.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(DefaultView_Activity.this,SellHistoryViewActivity.class);
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
}
