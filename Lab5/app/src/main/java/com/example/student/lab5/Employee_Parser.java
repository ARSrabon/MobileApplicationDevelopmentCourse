package com.example.student.lab5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 11/9/16.
 */

public class Employee_Parser {
    String getResponse;
    List<Employee> myEmployees;

    public List<Employee> getMyEmployees() {
        return myEmployees;
    }

    public void setMyEmployees(List<Employee> myEmployees) {
        this.myEmployees = myEmployees;
    }

    public String getGetResponse() {
        return getResponse;
    }

    public void setGetResponse(String getResponse) {
        this.getResponse = getResponse;
    }


    void parse_emploee(){
        try {
            List<Employee> Employees = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(getGetResponse());
            JSONArray jsonArray = new JSONArray(jsonObject.getString("employees"));
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Employee e = new Employee(object.getString("firstname"),object.getString("lastname"));
                Employees.add(e);
            }
            setMyEmployees(Employees);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String parse_employee_toString(){
        List<Employee> employeeList = getMyEmployees();
        String parsed = "";
        for (Employee e: employeeList) {
            parsed += e.getfName() + " " + e.getlName() + " \n";
        }
        return parsed;
    }

    public Employee_Parser(String getResponse) {
        this.getResponse = getResponse;
    }


}
