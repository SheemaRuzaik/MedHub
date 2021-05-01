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

public class PatientProfile extends AppCompatActivity {

    // Title bar
    TextView title;
    // Form details
    TextView first_name, last_name, nic, email, contact, dob, gender;
    // Button
    TextView edit, change, delete;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Profile");

        // Form details
        first_name = findViewById(R.id.get_first_name);
        last_name = findViewById(R.id.get_last_name);
        nic = findViewById(R.id.get_nic);
        email = findViewById(R.id.get_patient_email);
        contact = findViewById(R.id.get_patient_contact);
        dob = findViewById(R.id.get_dob);
        gender = findViewById(R.id.get_gender);

        // button
        edit = findViewById(R.id.edit_profile_patient);
        change = findViewById(R.id.change_patient);
        delete = findViewById(R.id.delete_patient);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfile.this, PatientEditProfile.class);
                startActivity(intent);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfile.this, PatientUpdatePassword.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pp);
        navigationView = findViewById(R.id.Navigation_view_pp);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.pat_prof_to_home);
        profile = findViewById(R.id.pat_prof);
        appointment = findViewById(R.id.pat_prof_to_appointment);
        my_book = findViewById(R.id.pat_prof_to_my_book);
        logout = findViewById(R.id.pat_prof_to_logout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfile.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        my_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}