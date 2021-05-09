package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class WelcomePage extends AppCompatActivity {

    //title bar
    TextView title;
    //Button
    TextView patient, hospital;
    //Toolbar
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        title = findViewById(R.id.head);
        title.setText("WelCome Page");

        patient = findViewById(R.id.pat_login);
        hospital = findViewById(R.id.hos_login);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.medhub.WelcomePage.this, com.example.medhub.PatientLogin.class);
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.medhub.WelcomePage.this, com.example.medhub.HospitalLogin.class);
                startActivity(intent);
            }
        });

        //Toolbar
        drawerLayout = findViewById(R.id.drawer_wp);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
    }
}