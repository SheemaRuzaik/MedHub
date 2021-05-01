package com.example.medhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class PatientEditProfile extends AppCompatActivity {

    // Title bar
    TextView title;
    // Form details
    TextView first_name, last_name, nic, email, contact, dob, gender;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Button
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_profile);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Edit Profile");

        // Form details
        first_name = findViewById(R.id.edit_first_name);
        last_name = findViewById(R.id.edit_last_name);
        nic = findViewById(R.id.edit_nic);
        email = findViewById(R.id.edit_patient_email);
        contact = findViewById(R.id.edit_patient_contact_no);
        dob = findViewById(R.id.edit_dob);
        gender = findViewById(R.id.edit_gender);

        // button
        save = findViewById(R.id.edit_to_profile);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientEditProfile.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pep);
        navigationView = findViewById(R.id.Navigation_view_pep);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.pat_edit_prof_to_home);
        profile = findViewById(R.id.pat_edit_prof_to_prof);
        appointment = findViewById(R.id.pat_edit_prof_to_appointment);
        my_book = findViewById(R.id.pat_edit_prof_to_my_book);
        logout = findViewById(R.id.pat_edit_prof_to_logout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientEditProfile.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        my_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

    }
}