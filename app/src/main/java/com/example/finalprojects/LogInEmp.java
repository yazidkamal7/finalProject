package com.example.finalprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogInEmp extends AppCompatActivity {
        EditText username ;
        EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_emp);
        username = findViewById(R.id.edtTextlogUserNameEmp);
        password = findViewById(R.id.editTextempPassword);

    }
       static String LoggedUser;
    public void loginEmp(View view) {
        RequestQueue queue;



            String userName =username.getText().toString();
            String passWord = password.getText().toString();
            queue = Volley.newRequestQueue(this);

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http:127.0.2.2/hotelSystem/loginEmp.php?name="+userName+"&password="+passWord+"",
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if(Integer.parseInt(response.toString())!=1){
                        Toast.makeText(view.getContext(), "Faillur log in  ", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), EmpDashbord.class);
                        startActivity(intent);
                    }







                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", userName);
                    data.put("password", passWord);
                    return data;
                }
            };

            queue.add(request);


        }


}