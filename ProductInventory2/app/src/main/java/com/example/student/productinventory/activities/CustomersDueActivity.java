package com.example.student.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.example.student.productinventory.R;
import com.example.student.productinventory.models.SellHistory;

import java.util.List;

public class CustomersDueActivity extends AppCompatActivity {

    EditText edit_customerId;

    TextView txt_TextView;

    Button btn_showdues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_due);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        edit_customerId = (EditText) findViewById(R.id.edit_customer_id);
        btn_showdues = (Button) findViewById(R.id.btn_showdues);
        txt_TextView = (TextView) findViewById(R.id.txt_showcustomerdues);

        btn_showdues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_customerId.getText().toString().length() > 0){
                    List<SellHistory> sellHistories = new Select().from(SellHistory.class).execute();
                    String nid = edit_customerId.getText().toString();
                    long total =0;
                    for (SellHistory history : sellHistories) {
                        if(nid.equals(history.getNID())){
                            total += history.getDue();
                        }
                    }

                    txt_TextView.setText("Total Due's: " + total);

                }
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
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_prouct:
                intent = new Intent(CustomersDueActivity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(CustomersDueActivity.this, Product_Stock_Updater_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(CustomersDueActivity.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(CustomersDueActivity.this,SellHistoryViewActivity.class);
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
        Intent intent1 = new Intent(CustomersDueActivity.this,DefaultView_Activity.class);
        startActivity(intent1);
        finish();
    }
}
