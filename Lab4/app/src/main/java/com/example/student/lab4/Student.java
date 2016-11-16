package com.example.student.lab4;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by student on 11/2/16.
 */

@Table(name = "studentTable")
public class Student extends Model {

    @Column(name = "studentName")
    public String student_Name;

    @Column(name = "studentId")
    public String student_Id;

    @Column(name = "department")
    public String student_Dept;

    public Student(){

    }

    public Student(String student_Name, String student_Id, String student_Dept) {
        this.student_Name = student_Name;
        this.student_Id = student_Id;
        this.student_Dept = student_Dept;
    }
}
