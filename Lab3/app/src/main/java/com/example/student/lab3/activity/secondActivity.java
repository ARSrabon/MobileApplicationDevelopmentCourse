package com.example.student.lab3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.student.lab3.R;

public class secondActivity extends AppCompatActivity {


    TextView txt_view_1;
    TextView txt_view_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        txt_view_1 = (TextView) findViewById(R.id.txt_view_1);
        txt_view_2 = (TextView) findViewById(R.id.txt_view_2);

        txt_view_1.setTextSize(30); // changes TextView text Size. unit 'sp' (maybe).

        String name = intent.getStringExtra("Name");
        double version = intent.getDoubleExtra("Version",0);
        String hello = intent.getStringExtra("Hello");

        txt_view_1.setText(name + " " + version);
    }
}
