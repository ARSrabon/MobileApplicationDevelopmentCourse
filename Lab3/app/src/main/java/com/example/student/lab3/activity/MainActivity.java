package com.example.student.lab3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.student.lab3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickhere = (Button) findViewById(R.id.btn_click);

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecycleView.class);
                intent.putExtra("Hello","it's working !!!");
                intent.putExtra("Name","Android");
                intent.putExtra("Version",6.0);
                startActivity(intent);            }
        });

    }
}
