package com.example.student.lab4;

import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by student on 11/2/16.
 */

public class StudentData {

    public StudentData() {
        Student student_1 = new Student("Abir","038","CSE");
        Student student_2 = new Student("Galib","062","EEE");

        student_1.save();
        student_2.save();
    }

    public String getStudentData(){
        List<Student> studentList = new Select().from(Student.class).execute();

        String list = "";

        for (Student student : studentList){
            list = student.student_Name + " " + student.student_Id + " " + student.student_Dept + "\n";
        }
        return list;
    }


}
