package com.example.student.productinventory.activities;

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
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.student.productinventory.R;
import com.example.student.productinventory.models.Product;
import com.example.student.productinventory.models.SellHistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Product_Sell_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_sell;
    Button btn_billgen;

    EditText edit_sellsquantity;
    EditText edit_CustomerId;
    EditText edit_total_dues;

    TextView txt_productStock;

    List<Product> productList;
    Spinner product_spinner;
    Product products;

    Date date;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sell);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Product Stock");


        date = new Date();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a");

        txt_productStock = (TextView) findViewById(R.id.lbl_pstock);

        edit_sellsquantity = (EditText) findViewById(R.id.edit_sellStocks);
        edit_CustomerId = (EditText) findViewById(R.id.edit_customer_id);
        edit_total_dues = (EditText) findViewById(R.id.edit_sales_due);

        btn_sell = (Button) findViewById(R.id.btn_sellProduct);
//        btn_billgen = (Button) findViewById(R.id.btn_billgenerator);

        productSpinnerInit();

        final Intent intent = getIntent();
        if (intent.getBooleanExtra("selectedProduct", false)) {
            long id = intent.getLongExtra("selectedProductId", -1);
            if (id != -1) {
                int count = 0;
                for (Product product : productList) {
                    if (product.getId() == id) {
                        product_spinner.setSelection(count);
                        break;
                    }
                    count++;
                }
                Log.d("The loop", "Completed");
            }
        }

        Log.d("The loop", "Completed");

        product_spinner.setOnItemSelectedListener(this);

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStamp = simpleDateFormat.format(date);
                Log.d("timestamp ", timeStamp);
                long quantity = Long.parseLong(edit_sellsquantity.getText().toString().trim(), 10);
                if (quantity != 0 && edit_CustomerId.getText().toString().length()>0) {
                    if (quantity <= products.getProduct_stock()) {
                        double dues = Double.parseDouble(edit_total_dues.getText().toString().trim());
                        String nid = edit_CustomerId.getText().toString();
                        SellHistory sellHistory = new SellHistory(products.getId(), products.getProduct_id(), nid ,quantity,products.getProduct_price()*quantity,dues,timeStamp);
                        products.setProduct_stock(products.getProduct_stock()-quantity);
                        products.save();
                        sellHistory.save();
                        Toast.makeText(Product_Sell_Activity.this, "Sell success !!!", Toast.LENGTH_SHORT).show();
                        productSpinnerInit();
                    } else {
                        Toast.makeText(Product_Sell_Activity.this, "Stock Limit exceeded!!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Product_Sell_Activity.this, "Enter some Quantity Or NID First !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        btn_billgen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void productSpinnerInit(){
        product_spinner = (Spinner) findViewById(R.id.spinner_products);
        productList = new Select().from(Product.class).execute();

        ArrayAdapter<Product> productArrayAdapter = new ArrayAdapter<Product>(this, R.layout.spinner_item, productList);
        productArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product_spinner.setAdapter(productArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        products = productList.get(adapterView.getSelectedItemPosition());
        txt_productStock.setText("Product Stock: " + products.getProduct_stock());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        products = productList.get(0);
        txt_productStock.setText("Product Stock: " + products.getProduct_stock());
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
                intent = new Intent(Product_Sell_Activity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(Product_Sell_Activity.this, Product_Stock_Updater_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(Product_Sell_Activity.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(Product_Sell_Activity.this,SellHistoryViewActivity.class);
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
        Intent intent1 = new Intent(Product_Sell_Activity.this, DefaultView_Activity.class);
        startActivity(intent1);
        finish();
    }

}
