package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientUpdatePassword extends AppCompatActivity {

    // Title bar
    TextView title;
    // form details
    EditText current_pwd, new_pwd, con_pwd;
    TextView nic;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Button
    TextView update;

    DatabaseReference upref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update_password);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Update Password");

        // form details
        //current_pwd = findViewById(R.id.pat_current_pwd);
        new_pwd = findViewById(R.id.pat_new_pwd);
        con_pwd = findViewById(R.id.pat_new_cpwd);
        //nic = findViewById(R.id.get_upnic);

        // Button
        update = findViewById(R.id.update_to_profile);

        final SessionManagement sessionManagement=new SessionManagement(PatientUpdatePassword.this);
        String un=sessionManagement.getSession();

        upref= FirebaseDatabase.getInstance().getReference().child("Patient").child(un);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(new_pwd.getText().toString())){
                    Toast.makeText(PatientUpdatePassword.this, "New Password cannot Empty", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(con_pwd.getText().toString())){
                    Toast.makeText(PatientUpdatePassword.this, "Confirm Password cannot Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if (new_pwd.getText().toString().equals(con_pwd.getText().toString().trim())){
                        upref = FirebaseDatabase.getInstance().getReference().child("Patient").child(un);
                        upref.child("password").setValue(new_pwd.getText().toString().trim());

                        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(PatientUpdatePassword.this, PatientProfile.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Confirm Password is not Match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pup);
        navigationView = findViewById(R.id.Navigation_view_pup);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.pat_chan_to_home);
        profile = findViewById(R.id.pat_chan_to_prof);
        appointment = findViewById(R.id.pat_chan_to_appointment);
        my_book = findViewById(R.id.pat_chan_to_my_book);
        logout = findViewById(R.id.pat_chan_to_logout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUpdatePassword.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        my_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUpdatePassword.this, WelcomePage.class);
                startActivity(intent);
            }
        });
    }
}