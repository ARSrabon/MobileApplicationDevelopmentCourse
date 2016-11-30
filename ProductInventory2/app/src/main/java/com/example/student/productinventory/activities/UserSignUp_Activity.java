package com.example.student.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.student.productinventory.R;
import com.example.student.productinventory.models.Product;
import com.example.student.productinventory.models.User;

import java.util.ArrayList;


public class UserSignUp_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edit_newUsername;
    EditText edit_newEmail;
    EditText edit_new_password;
    EditText edit_new_passwordConfirm;

    Spinner userType;
    String userlvl;
    ArrayList<String> usertypes;

    Button btn_newSignUp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        btn_newSignUp = (Button) findViewById(R.id.btn_new_signup);

        edit_newUsername = (EditText) findViewById(R.id.edit_new_username);
        edit_newEmail = (EditText) findViewById(R.id.edit_new_email);
        edit_new_password = (EditText) findViewById(R.id.edit_new_password);
        edit_new_passwordConfirm = (EditText) findViewById(R.id.edit_new_password_confirm);

        userType = (Spinner) findViewById(R.id.spinner_usertype);


        usertypes = new ArrayList<String>();
        usertypes.add("Admin");
        usertypes.add("Sales");
        ArrayAdapter<String> userTypesAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,usertypes);
        userTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(userTypesAdapter);

        userType.setOnItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(getString(R.string.My_sharedPrefs_file_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_newSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_newUsername.getText().toString().length() > 0
                        && edit_newEmail.getText().toString().length() > 0
                        && edit_new_password.getText().toString().length() > 0
                        && edit_new_password.getText().toString().equals(edit_new_passwordConfirm.getText().toString())) {

                    User usernew = new User(edit_newUsername.getText().toString(),
                                            edit_newEmail.getText().toString(),
                                            edit_new_password.getText().toString(),userlvl);
                    usernew.save();

                    Intent intent = new Intent(UserSignUp_Activity.this,DefaultView_Activity.class);
                    // putting data to sharedpref
                    editor.putBoolean("loggedin",true);
                    editor.putLong("userId",usernew.getId());
                    editor.commit();
                    // transioning to new activity.
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userlvl = usertypes.get(parent.getSelectedItemPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        userlvl = usertypes.get(0);
    }
}
