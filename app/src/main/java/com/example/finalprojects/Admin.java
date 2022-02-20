package com.example.finalprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void AddNewEmployee(View view) {
        Intent intent = new Intent(this , addNewEmp.class);
        startActivity(intent);
    }

    public void AddnewRoom(View view) {
        Intent intent = new Intent(this, AddRoom.class);
        startActivity(intent);
    }


}