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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HospitalEditProfile extends AppCompatActivity {

    // Form details
    TextView name, email, address, register, phone;
    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Button
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_edit_profile);

        // Form details
        name = findViewById(R.id.edit_hospital_name);
        email = findViewById(R.id.edit_hospital_email);
        address = findViewById(R.id.edit_address);
        register = findViewById(R.id.edit_register_no);
        phone = findViewById(R.id.edit_hospital_phone_no);

        // Button
        save = findViewById(R.id.edit_to_profile_hospital);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalEditProfile.this, HospitalProfile.class);
                startActivity(intent);
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_hep);
        navigationView = findViewById(R.id.Navigation_view_hep);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.hos_edit_pro_to_home);
        doctor = findViewById(R.id.hos_edit_pro_to_doctor);
        profile = findViewById(R.id.hos_edit_pro_to_prof);
        appointment = findViewById(R.id.hos_edit_pro_to_appointment);
        extra = findViewById(R.id.hos_edit_pro_to_extra);
        logout = findViewById(R.id.hos_edit_pro_to_logout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalEditProfile.this, HospitalProfile.class);
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