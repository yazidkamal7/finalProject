package com.android.hotelbooking.HotelRooms.SingelBedRooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.LoginActivity;
import com.android.hotelbooking.R;
import com.android.hotelbooking.User.MainPageActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ReserveSingleBedRoom extends AppCompatActivity {
    TextView room_name;
    Button resBTN;
    LoginActivity logAct = new LoginActivity();
    String rooms,users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_single_room);
        resBTN = findViewById(R.id.buttonReserve);
        Intent in = getIntent();
         rooms = in.getStringExtra("roomName");
         users = logAct.LoggedUser;
        room_name = findViewById(R.id.bookingRoomName);
        room_name.setText(rooms);
        resBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showPopupWindowSingle popClass = new showPopupWindowSingle();
                popClass.showPopupWindow(v);
            }
        });

    }

    public void ReserveRoom(View view) {

        String url = "http://"+getResources().getString(R.string.databaseIp)+"/login/reserveSingleRoom.php?roomName = "+rooms+" & user = "+users+"";

            StringRequest str_req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("DONE")) {
                        Toast.makeText(view.getContext(), "Booked Successfully ^_^", Toast.LENGTH_SHORT).show();


                    } else if (response.equals("ERROR")) {
                        Toast.makeText(view.getContext(), "Booked failed , try again!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    Toast.makeText(view.getContext(), e.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("roomName", rooms);
                    data.put("user", users);
                    return data;
                }
            };

            RequestQueue req_Q = Volley.newRequestQueue(view.getContext());
            req_Q.add(str_req);
        backToMain();
    }
    private void backToMain() {
        Intent in = new Intent(this, MainPageActivity.class);
        startActivity(in);
        finish();
    }



    }

