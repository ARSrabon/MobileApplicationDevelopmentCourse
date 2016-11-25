package com.example.student.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView txt_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txt_hello = (TextView) findViewById(R.id.txt_hello);

//        String url = "http://www.mocky.io/v2/5822fc701000004f0dccfe40" ; //"http://www.mocky.io/v2/5822f4e71000009a0cccfe1f" ; //"http://www.mocky.io/v2/5822e943100000100bccfde8"; //"http://www.mocky.io/v2/5822e3c41000007a0accfdd5"

        String url = "http://api.androidhive.info/contacts/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txt_hello.setText(response);
//                Gson gson = new Gson();
//                Contacts[] contacts = gson.fromJson(response,Contacts[].class);
//                Log.d("jsons", contacts.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response).getJSONObject("menu");
//
//                    String text = "";
//                    text += "Id: " + jsonObject.getString("id") + "\n";
//                    text += "Value: " + jsonObject.getString("value") + "\n";
//                    JSONObject object = new JSONObject(jsonObject.getString("popup"));
//                    JSONArray jsonArray = new JSONArray(object.getString("menuitem"));
//                    Log.d("TEST", jsonArray.toString());
//                    for (int i = 0; i < jsonArray.length() ; i++) {
//
//                    }
//                    txt_hello.setText(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

//        url = "https://posttestserver.com/post.php";
//        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("TEST",response);
//                txt_hello.setText(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put("userid","Cyborn");
//                map.put("userpass","Cyborn13x");
//                return map;
//            }
//        };



        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        requestQueue.add(stringRequest);

    }
}
