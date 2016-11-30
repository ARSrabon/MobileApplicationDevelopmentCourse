package com.example.msrabon.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.msrabon.productinventory.adapters.SaleHistoryAdapter;
import com.example.msrabon.productinventory.models.Product;
import com.example.msrabon.productinventory.models.SellHistory;

import java.util.ArrayList;
import java.util.List;

public class SellHistoryViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_history_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recyle_view_sales);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            List<SellHistory> sellHistories = new Select().from(SellHistory.class).execute();
            Log.d("mylist", sellHistories.toString());
            SaleHistoryAdapter saleHistoryAdapter = new SaleHistoryAdapter((ArrayList<SellHistory>) sellHistories,SellHistoryViewActivity.this);
            recyclerView.setAdapter(saleHistoryAdapter);
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
                intent = new Intent(SellHistoryViewActivity.this, Add_New_Product_Activity.class);
                startActivity(intent);
                break;
            case R.id.stock_updater:
                intent = new Intent(SellHistoryViewActivity.this, Product_Stock_Updater_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sell_product: intent = new Intent(SellHistoryViewActivity.this,Product_Sell_Activity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.add_category:
                break;

            case R.id.sale_history: intent = new Intent(SellHistoryViewActivity.this,SellHistoryViewActivity.class);
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
        Intent intent1 = new Intent(SellHistoryViewActivity.this,DefaultView_Activity.class);
        startActivity(intent1);
        finish();
    }
}
