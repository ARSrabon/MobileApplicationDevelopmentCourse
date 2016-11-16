package com.example.student.lab5;

/**
 * Created by student on 11/9/16.
 */

public class MenueItem {

    String value;
    String onClick;

    public MenueItem(String value, String onClick) {
        this.value = value;
        this.onClick = onClick;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }
}
