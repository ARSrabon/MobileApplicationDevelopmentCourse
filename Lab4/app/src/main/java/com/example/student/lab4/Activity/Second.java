package com.example.student.lab4.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.student.lab4.R;

public class Second extends AppCompatActivity {

    EditText st_Name;
    EditText st_Id;
    EditText st_Dept;

    Button btn_Save;
    Button btn_Show;

    TextView txt_Show_All;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        st_Name = (EditText) findViewById(R.id.edit_st_name);
        st_Id = (EditText) findViewById(R.id.edit_st_id);
        st_Dept = (EditText) findViewById(R.id.edit_st_dept);

        btn_Save = (Button) findViewById(R.id.btn_save);

        txt_Show_All = (TextView) findViewById(R.id.txt_showall);

        sharedPreferences = getSharedPreferences("My_Pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        txt_Show_All.setText(sharedPreferences.getString("student_name",""));

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = st_Name.getText().toString();
                editor.putString("student_name",name);
                editor.commit();
            }
        });

        sharedPreferences.edit().clear();

    }
}
