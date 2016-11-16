package com.example.student.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText st_Name;
    EditText st_Id;
    EditText st_Dept;

    Button btn_Save;
    Button btn_Show;

    TextView txt_Show_All;

    String list="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<Student> tempList = new Select().from(Student.class).execute();
//        for (Student student : tempList){
//            student.delete();
//        }

        Student student_1 = new Student("Abir","038","CSE");
        Student student_2 = new Student("Galib","062","EEE");
        Student student_3 = new Student("Tuhin","068","EEE");

        student_1.save();
        student_2.save();

        st_Name = (EditText) findViewById(R.id.edit_st_name);
        st_Id = (EditText) findViewById(R.id.edit_st_id);
        st_Dept = (EditText) findViewById(R.id.edit_st_dept);

        btn_Save = (Button) findViewById(R.id.btn_save);
        btn_Show = (Button) findViewById(R.id.btn_show);

        txt_Show_All = (TextView) findViewById(R.id.txt_showall);

        List<Student> studentList = new Select().from(Student.class).execute();

        for (Student student : studentList){
            list += student.student_Name + " " + student.student_Id + " " + student.student_Dept + "\n";
            student.student_Id = "1111";
            Log.d("Name: ",student.student_Name);
            student.save();
        }

        btn_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_Show_All.setText(list);
                Log.d("String Val",list);
            }
        });

    }
}
