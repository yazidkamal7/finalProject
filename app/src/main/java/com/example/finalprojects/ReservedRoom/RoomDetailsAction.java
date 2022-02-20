package com.android.hotelbooking.ReservedRoom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.LoginActivity;
import com.android.hotelbooking.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RoomDetailsAction extends AppCompatActivity {
    TextView room_Name;
    Button resBTN;
    LoginActivity logACT = new LoginActivity();
    String rooms,users,type,URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_details);
        resBTN = findViewById(R.id.buttonUnReserve);
        Intent in = getIntent();
        rooms = in.getStringExtra("roomName");
        users = logACT.LoggedUser;
        type=in.getStringExtra("type");
        room_Name = findViewById(R.id.SelctedRoomName);
        room_Name.setText(rooms);
        resBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupWindowUnreserved popClass = new showPopupWindowUnreserved();
                popClass.showPopupWindow(v);
            }
        });

    }

    public void UnReserveRoom(View v) {

        if(type.equals("Single")){
            URL  = "http://"+getString(R.string.databaseIp)+"/login/UnReserveSingleBedRoom.php";
        }


        StringRequest str_req = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("DONE")) {
                    Toast.makeText(v.getContext(), "UnBooked Successfully ^_^", Toast.LENGTH_SHORT).show();


                } else if (response.equals("ERROR")) {
                    Toast.makeText(v.getContext(), "UnBooked failed , try again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(v.getContext(), e.toString().trim(), Toast.LENGTH_SHORT).show();
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

        RequestQueue req_Q = Volley.newRequestQueue(v.getContext());
        req_Q.add(str_req);
        backToMain();
    }

    private void backToMain() {
        Intent in = new Intent(this, ForBookingRooms.class);
        startActivity(in);
        finish();
    }

}
