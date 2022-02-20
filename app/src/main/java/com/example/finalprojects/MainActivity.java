package com.example.finalprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void Emploess(View view) {
        Intent intent = new Intent(this,LogInEmp.class);
        startActivity(intent);
    }

    public void user(View view) {

    }

    public void AdminDashbord(View view) {
        Intent intent = new Intent(this , Admin.class);
        startActivity(intent);

    }
}