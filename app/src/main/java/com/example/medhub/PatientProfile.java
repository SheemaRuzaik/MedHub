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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfile extends AppCompatActivity {

    // Title bar
    TextView title;
    // Form details
    TextView full_name, nic, email, contact, dob, gender;
    // Button
    TextView edit, change, delete, feedback;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    DatabaseReference dbref,delref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Profile");

        // Form details
        full_name = findViewById(R.id.get_full_name);
        nic = findViewById(R.id.get_nic);
        email = findViewById(R.id.get_patient_email);
        contact = findViewById(R.id.get_patient_contact);
        //dob = findViewById(R.id.get_dob);
        gender = findViewById(R.id.get_gender);

        // button
        edit = findViewById(R.id.edit_profile_patient);
        change = findViewById(R.id.change_patient);
        delete = findViewById(R.id.delete_patient);
        feedback = findViewById(R.id.feedback);

        final SessionManagement sessionManagement=new SessionManagement(PatientProfile.this);
        String un=sessionManagement.getSession();

        dbref= FirebaseDatabase.getInstance().getReference().child("Patient").child(un);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                full_name.setText(snapshot.child("full_name").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                nic.setText(snapshot.child("nic").getValue().toString());
                gender.setText(snapshot.child("gender").getValue().toString());
                contact.setText(snapshot.child("contact").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

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
                delref=FirebaseDatabase.getInstance().getReference().child("Patient").child(nic.getText().toString());
                delref.removeValue();
                sessionManagement.removeSession();

                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),WelcomePage.class);
                startActivity(intent);

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfile.this, FeedBack.class);
                startActivity(intent);
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
                Intent intent = new Intent(PatientProfile.this, PatientProfile.class);
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
                Intent intent = new Intent(PatientProfile.this, WelcomePage.class);
                startActivity(intent);
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