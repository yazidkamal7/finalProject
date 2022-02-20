package com.example.finalprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddRoom extends AppCompatActivity {
    EditText edtName;
    EditText edtPrice;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        edtName = findViewById(R.id.editTextRoomName);
        spinner = (Spinner) findViewById(R.id.spinnerRoom);
        edtPrice = findViewById(R.id.editTextPriceRoom);
    }

    public void AddRoom(View view) {
        String nameRoom = edtName.getText().toString();
        String priceRoom = edtPrice.getText().toString();
        String typeRoom = spinner.getSelectedItem().toString();


        if (!nameRoom.equals("") && !priceRoom.equals("") && !typeRoom.equals("")) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:80/hotelSystem/AddRoom.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Successfully, New Room Added.", Toast.LENGTH_SHORT).show();


                    } else if (response.equals("failure")) {
                        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("NameRoom", nameRoom);
                    data.put("PriceRoom", priceRoom);
                    data.put("TypeRoom", typeRoom);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
}

