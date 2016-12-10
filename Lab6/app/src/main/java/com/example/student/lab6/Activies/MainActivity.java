package com.example.student.lab6.Activies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.lab6.Models.Product;
import com.example.student.lab6.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    EditText edit_username;
    EditText edit_password;

    TextView txt_viewDatabase;

    Button btn_login;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
    DatabaseReference productReference;

    String dataNew;
    String jdata = null;
    DatabaseReference productUpdateDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_viewDatabase = (TextView) findViewById(R.id.txt_showData);
        edit_username = (EditText) findViewById(R.id.edt_username);
        edit_password = (EditText) findViewById(R.id.edt_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance(); // Firebase Authentication object init.

        /**
         * Firebase database Connection
         * Create a connection to Firebase Database
         *  firebaseDatabase = FirebaseDatabase.getInstance();
         *
         *  Create a Reference to the point where we will be starting to read from
         *  databaseReference = firebaseDatabase.getReference( ReadStartPoint );
         *
         */
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Season"); // creating a ref for starting from 'Season'
        /**
         * To Update data in the Firebase Database
         *
         * databaseReference.setValue(object);
         * databaseReference.setValue(new object);
         *
         */
        databaseReference.setValue("Winter");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //dataNew = dataSnapshot.getValue(String.class);
//                //txt_viewDatabase.setText(dataNew);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        productReference = firebaseDatabase.getReference("ProductList");



        productReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot temp : dataSnapshot.getChildren()) {
                    Product product = temp.getValue(Product.class); // getting the product object From snapshot
//                    product.setPrice(product.getPrice() + 100); // updating the product price
//                    productUpdateDatabaseReference = temp.getRef(); // getting the current product object reference
//                    productUpdateDatabaseReference.setValue(product); // saving the updated values
                    dataNew += product.getProductName() + " " + product.getPrice() + "\n";
                }
                txt_viewDatabase.setText(dataNew);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**
         * To save new data to Firebase Database
         * productReference.push().setValue(new Object);
         * Example:
         * productReference.push().setValue(new Product(455.5,"HP ProBook 450 G1"));
         */
//        productReference.push().setValue(new Product(455.5,"HP ProBook 450 G1"));

        //txt_viewDatabase.setText(dataNew);

        if(auth.getCurrentUser() != null){
            FirebaseUser userInfo = auth.getCurrentUser();
            userInfo.getDisplayName();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                Log.d("TAG", username + "       " + password);

                auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "success");
                            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
