package com.example.msrabon.productinventory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.msrabon.productinventory.R;
import com.example.msrabon.productinventory.models.User;

public class UserSignUp_Activity extends AppCompatActivity {

    EditText edit_newUsername;
    EditText edit_newEmail;
    EditText edit_new_password;
    EditText edit_new_passwordConfirm;

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
                                            edit_new_password.getText().toString());
                    usernew.save();

                    Intent intent = new Intent(UserSignUp_Activity.this,DefaultView_Activity.class);
                    // putting data to sharedpref
                    editor.putBoolean("loggedin",true);
                    editor.putString("username",edit_newUsername.getText().toString());
                    editor.commit();
                    // transioning to new activity.
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
