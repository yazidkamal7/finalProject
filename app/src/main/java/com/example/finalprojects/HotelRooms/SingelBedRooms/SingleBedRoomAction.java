package com.android.hotelbooking.HotelRooms.SingelBedRooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleBedRoomAction extends AppCompatActivity {

    ListView lst;
    TextView NO_rooms;
    private RequestQueue req_Q;
    ArrayList<String> Rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);
        lst = findViewById(R.id.singleRoomList);
        NO_rooms=findViewById(R.id.numOfRooms);
        String url = "http://"+getString(R.string.databaseIp)+"/login/info_json.php";
        loadData(url);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg) {
                Toast.makeText(getApplicationContext(), "" +  Rooms.get(pos), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), ReserveSingleBedRoom.class);
                in.putExtra("roomName",Rooms.get(pos));
                startActivity(in);
            }
        });

    }
    public void loadData(String url){
        req_Q = Volley.newRequestQueue(this);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                 Rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Rooms.add( obj.getString("roomName"));
                    }catch(JSONException exp){
                        Log.d("Error", exp.toString());
                    }
                }
                String[] arr = new String[Rooms.size()];

                arr = Rooms.toArray(arr);

                ArrayAdapter<String> adapt = new ArrayAdapter<>(
                        SingleBedRoomAction.this, android.R.layout.simple_list_item_1,
                        arr);
                lst.setAdapter(adapt);
               int size=Rooms.size();
                NO_rooms.setText(size+"");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e) {

                Toast.makeText(getApplicationContext(), e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        req_Q.add(req);


    }
}



