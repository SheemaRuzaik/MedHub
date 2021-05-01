package com.example.medhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HospitalUpdatePassword extends AppCompatActivity {

    // form details
    EditText current_pwd, new_pwd, con_pwd;
    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Button
    TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_update_password);

        // form details
        current_pwd = findViewById(R.id.hos_current_pwd);
        new_pwd = findViewById(R.id.hos_new_pwd);
        con_pwd = findViewById(R.id.hos_new_cpwd);

        // Button
        update = findViewById(R.id.update_to_profile_hospital);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalUpdatePassword.this, HospitalProfile.class);
                startActivity(intent);
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_hup);
        navigationView = findViewById(R.id.Navigation_view_hup);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.hos_chan_to_home);
        doctor = findViewById(R.id.hos_chan_to_doctor);
        profile = findViewById(R.id.hos_chan_to_prof);
        appointment = findViewById(R.id.hos_chan_to_appointment);
        extra = findViewById(R.id.hos_chan_to_extra);
        logout = findViewById(R.id.hos_chan_to_logout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalUpdatePassword.this, HospitalProfile.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}