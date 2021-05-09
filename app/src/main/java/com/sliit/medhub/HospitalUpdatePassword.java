package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HospitalUpdatePassword extends AppCompatActivity {

    // Title bar
    TextView title;
    // form details
    EditText current_pwd, new_pwd, con_pwd;
    TextView register;
    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;
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
        setContentView(R.layout.activity_hospital_update_password);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Update Password");

        // form details
        //current_pwd = findViewById(R.id.hos_current_pwd);
        new_pwd = findViewById(R.id.hos_new_pwd);
        con_pwd = findViewById(R.id.hos_new_cpwd);
        //register = findViewById(R.id.get_upreg);

        // Button
        update = findViewById(R.id.update_to_profile_hospital);

        final SessionManagement sessionManagement=new SessionManagement(HospitalUpdatePassword.this);
        String un=sessionManagement.getHospitalSession();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(new_pwd.getText().toString())){
                    Toast.makeText(HospitalUpdatePassword.this, "New Password cannot Empty", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(con_pwd.getText().toString())){
                    Toast.makeText(HospitalUpdatePassword.this, "Confirm Password cannot Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if (new_pwd.getText().toString().equals(con_pwd.getText().toString().trim())){
                        upref = FirebaseDatabase.getInstance().getReference().child("Hospital").child(un);
                        upref.child("password").setValue(new_pwd.getText().toString().trim());

                        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(HospitalUpdatePassword.this, HospitalProfile.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Confirm Password is not Match", Toast.LENGTH_SHORT).show();

                    }
                }
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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalUpdatePassword.this, HospitalProfile.class);
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

        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalUpdatePassword.this, WelcomePage.class);
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