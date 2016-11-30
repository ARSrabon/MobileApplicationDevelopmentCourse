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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.msrabon.productinventory.R;
import com.example.msrabon.productinventory.models.Category;
import com.example.msrabon.productinventory.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Add_New_Product_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edit_productName;
    EditText edit_productId;

    Spinner spinner_category;

    Button btn_addProduct;
    Button btn_discard;

    List<Category> categoryList;
    ArrayList<String> categories;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Add New Product");

        btn_addProduct = (Button) findViewById(R.id.btn_addNewProduct);
        btn_discard = (Button) findViewById(R.id.btn_discard);

        edit_productName = (EditText) findViewById(R.id.edit_new_product_name);
        edit_productId = (EditText) findViewById(R.id.edit_new_product_id);

        spinner_category = (Spinner) findViewById(R.id.spin_product_category);

        categoryList = new Select().from(Category.class).execute();

//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        ArrayAdapter<Category> dataAdapter = new ArrayAdapter<Category>(this,R.layout.spinner_item,categoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(dataAdapter);
        spinner_category.setOnItemSelectedListener(this);

        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = edit_productName.getText().toString();
                String pId = edit_productId.getText().toString();
                Product product = new Product(pName,pId,0,0,category);
                product.save();

                Toast.makeText(Add_New_Product_Activity.this, "New Product Added.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Add_New_Product_Activity.this,Product_Stock_Updater_Activity.class);
                intent.putExtra("selectedProduct",true);
                intent.putExtra("selectedProductId",product.getId());

                startActivity(intent);
                finish();

            }
        });

        btn_discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_New_Product_Activity.this,DefaultView_Activity.class);
                startActivity(intent);
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
                intent = new Intent(Add_New_Product_Activity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(Add_New_Product_Activity.this, Product_Stock_Updater_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(Add_New_Product_Activity.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(Add_New_Product_Activity.this,SellHistoryViewActivity.class);
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category = categoryList.get(adapterView.getSelectedItemPosition());
        Log.d("onSelected: ",category.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        category = categoryList.get(0);
        Log.d("onNothingSelected: ",category.toString());
    }
}
