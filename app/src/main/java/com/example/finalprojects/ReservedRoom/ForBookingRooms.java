package com.android.hotelbooking.ReservedRoom;

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

import com.android.hotelbooking.LoginActivity;
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

public class ForBookingRooms extends AppCompatActivity {
    String user = LoginActivity.LoggedUser;

    ListView lst_SingleBed;
    private RequestQueue req_Q;
    ArrayList<String> SingBedRooms= new ArrayList<>();
    TextView label_S,emptyLabel;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserved_rooms);
        label_S=findViewById(R.id.signleRoomTitle);
        emptyLabel=findViewById(R.id.emptyRooms);
        lst_SingleBed=findViewById(R.id.myListSingle);
        emptyLabel.setVisibility(View.GONE);
        String urlSingle = "http://"+getString(R.string.databaseIp)+"/login/MySingleBedRooms.php?cat="+user;
        loadData(urlSingle,lst_SingleBed,SingBedRooms,label_S);


        lst_SingleBed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg2) {
                Toast.makeText(getApplicationContext(), "" +  SingBedRooms.get(pos), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), RoomDetailsAction.class);
                in.putExtra("roomName",SingBedRooms.get(pos));
                in.putExtra("type","Single");
                startActivity(in);
                finish();
            }
        });

    }



    public void loadData(String URl, ListView lst, ArrayList<String> lstRooms, TextView label){
        req_Q = Volley.newRequestQueue(this);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, URl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        lstRooms.add( obj.getString("roomName"));

                    }catch(JSONException exp){
                        Log.d("Error", exp.toString());
                    }
                }
                String[] arr = new String[lstRooms.size()];
                if (arr.length==0){
                label.setVisibility(View.GONE);
                    lst.setVisibility(View.GONE);
                }else {
                    flag=true;
                    arr = lstRooms.toArray(arr);

                    ArrayAdapter<String> adapt = new ArrayAdapter<>(
                            getApplicationContext(), android.R.layout.simple_list_item_1,
                            arr);
                    lst.setAdapter(adapt);

                }
                if (flag==false){
                    emptyLabel.setVisibility(View.VISIBLE);
                }
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
