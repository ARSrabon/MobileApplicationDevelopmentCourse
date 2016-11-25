package com.example.msrabon.productinventory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

public class UserSignIn_Activity extends AppCompatActivity {

    EditText edt_username;
    EditText edt_password;

    Button btn_signin;
    Button btn_signup;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        sharedPreferences = getSharedPreferences(getString(R.string.My_sharedPrefs_file_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean("loggedin",false)){
            Intent intent = new Intent(UserSignIn_Activity.this,DefaultView_Activity.class);
            startActivity(intent);
            finish();
        }

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        edt_username = (EditText) findViewById(R.id.edit_username);
        edt_password = (EditText) findViewById(R.id.edit_user_password);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = (User) new Select().from(User.class)
                                                .where("username=?",edt_username.getText().toString())
                                                .and("password=?",edt_password.getText().toString()).execute();

                Toast.makeText(UserSignIn_Activity.this,user.toString(),Toast.LENGTH_LONG).show();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignIn_Activity.this,UserSignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
