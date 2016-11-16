package com.example.student.lab5;

import java.security.PublicKey;

/**
 * Created by student on 11/9/16.
 */

public class Employee {
    public String fName;
    public String lName;

    public Employee(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
