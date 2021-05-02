package com.example.medhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class PatientUpdatePassword extends AppCompatActivity {

    // Title bar
    TextView title;
    // form details
    EditText current_pwd, new_pwd, con_pwd;
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
        title.setText("Change Password");

        // form details
        current_pwd = findViewById(R.id.pat_current_pwd);
        new_pwd = findViewById(R.id.pat_new_pwd);
        con_pwd = findViewById(R.id.pat_new_cpwd);

        // Button
        update = findViewById(R.id.update_to_profile);

//        final SessionManagement sessionManagement=new SessionManagement(PatientUpdatePassword.this);
//        String un=sessionManagement.getSession();
//
//        dbref= FirebaseDatabase.getInstance().getReference().child("Patient").child(un);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                upRef= FirebaseDatabase.getInstance().getReference().child("Civilian").child(nic.getText().toString());
//                upRef.child("name").setValue(name.getText().toString().trim());
//                upRef.child("email").setValue(mail.getText().toString().trim());
//                upRef.child("phone").setValue(phone.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PatientUpdatePassword.this, PatientProfile.class);
                startActivity(intent);
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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUpdatePassword.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        my_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientUpdatePassword.this, MyBooking.class);
                //startActivity(intent);
            }
        });


    }
}