package com.example.msrabon.activeandroidtest.activities;

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
import com.example.msrabon.activeandroidtest.R;
import com.example.msrabon.activeandroidtest.adapters.ProductAdapter;
import com.example.msrabon.activeandroidtest.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Product Stock");

//        ArrayList<Category> category = new ArrayList<Category>();
//        category.add(new Category("NoteBook")) ;
//        category.add(new Category("iPad")) ;
//        category.add(new Category("MacBook")) ;
//        category.add(new Category("Laptop")) ;
//
//        for (Category category1 : category) {
//            category1.save();
//        }
//
//
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.add(new Product("Acer","lp-1",65,199.99,category.get(3)));
//        products.add(new Product("ASUS","lp-2",15,399.99,category.get(3)));
//        products.add(new Product("HP","lp-3",45,299.90,category.get(3)));
//        products.add(new Product("Lennovo","lp-4",5,500.99,category.get(3)));
//        products.add(new Product("MacBook15'", "mac_15p", 10, 2099.9, category.get(2)));
//        products.add(new Product("iPad 9'", "tab_9", 25, 1899.0, category.get(1)));
//        products.add(new Product("ASUS","lp-2",15,399.99,category.get(0)));
//
//        for (Product product: products) {
//            try {
//                product.save();
//            }catch (SQLException q){
//                q.printStackTrace();
//            }
//        }


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            List<Product> productList = new Select().from(Product.class).execute();
            Log.d("mylist", productList.toString());
            productAdapter = new ProductAdapter((ArrayList<Product>) productList, MainActivity.this);
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
                intent = new Intent(MainActivity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(MainActivity.this, Product_Stock_Updater_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_logout:
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.My_sharedPrefs_file_name), Context.MODE_PRIVATE).edit();
                editor.putBoolean("loggedin",false);
                editor.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
