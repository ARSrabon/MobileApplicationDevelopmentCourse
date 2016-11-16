package com.example.student.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button btn_plus;
    Button btn_sub;
    Button btn_div;
    Button btn_multi;

    EditText editText_1;
    EditText editText_2;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_multi = (Button) findViewById(R.id.btn_multi);

        editText_1 = (EditText) findViewById(R.id.edtxt_1);
        editText_2 = (EditText) findViewById(R.id.edtxt_2);

//        results = (TextView) findViewById(R.id.result);


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.valueOf(editText_1.getText().toString()) + Double.valueOf(editText_2.getText().toString());
                results.setText(String.valueOf(result));
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.valueOf(editText_1.getText().toString()) - Double.valueOf(editText_2.getText().toString());
                results.setText(String.valueOf(result));
            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.valueOf(editText_1.getText().toString()) / Double.valueOf(editText_2.getText().toString());
                results.setText(String.valueOf(result));
            }
        });
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.valueOf(editText_1.getText().toString()) * Double.valueOf(editText_2.getText().toString());
                results.setText(String.valueOf(result));
            }
        });


    }
}
