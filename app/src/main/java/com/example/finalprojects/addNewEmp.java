package com.example.finalprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class addNewEmp extends AppCompatActivity {
    EditText names;
    EditText userName;
    EditText pasword;
    EditText sallarys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_emp);
        setUpView();
    }

    private void setUpView() {
        names = findViewById(R.id.editTextName);
        userName =findViewById(R.id.editTextEmpUserName);
        pasword = findViewById(R.id.editTextempPassword);
        sallarys = findViewById(R.id.editTextempSalary);
    }


    public void addemployees(View view) {
        String name = names.getText().toString().trim();
        String username = userName.getText().toString().trim();
        String password = pasword.getText().toString().trim();
        String sallary = sallarys.getText().toString().trim();


        if(!name.equals("") && !username.equals("") && !password.equals("") && !sallary.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:80/hotelSystem/addNewEmp.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("tea","test resonse");
                    if (response.equals("success")) {
                        Toast.makeText(view.getContext(), "New Employess Was Added Sucssfully.", Toast.LENGTH_SHORT).show();
                        clearText();

                    } else if (response.equals("failure")) {
                        Toast.makeText(view.getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                private void clearText() {
                    names.getText().clear();
                    pasword.getText().clear();
                    sallarys.getText().clear();
                    userName.getText().clear();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("err","here is error");
                    Toast.makeText(view.getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    Log.d("err",view.getContext()+"\n"+error.toString().trim());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("username", username);
                    data.put("password", password);
                    data.put("sallary", sallary);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
            requestQueue.add(stringRequest);
        }



    }
}