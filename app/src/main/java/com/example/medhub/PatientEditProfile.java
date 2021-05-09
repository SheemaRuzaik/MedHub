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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientEditProfile extends AppCompatActivity {

    // Title bar
    TextView title;
    // Form details
    EditText full_name, email, contact;
    TextView nic, gender;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Button
    TextView save;

    DatabaseReference dbref, upref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_profile);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Edit Profile");

        // Form details
        full_name = findViewById(R.id.edit_full_name);
        nic = findViewById(R.id.edit_nic);
        email = findViewById(R.id.edit_patient_email);
        contact = findViewById(R.id.edit_patient_contact_no);
        //dob = findViewById(R.id.edit_dob);
        gender = findViewById(R.id.edit_gender);

        // button
        save = findViewById(R.id.edit_to_profile);

        final SessionManagement sessionManagement=new SessionManagement(PatientEditProfile.this);
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upref= FirebaseDatabase.getInstance().getReference().child("Patient").child(nic.getText().toString());
                upref.child("full_name").setValue(full_name.getText().toString().trim());
                upref.child("email").setValue(email.getText().toString().trim());
                upref.child("nic").setValue(nic.getText().toString().trim());
                upref.child("gender").setValue(gender.getText().toString().trim());
                upref.child("contact").setValue(contact.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(PatientEditProfile.this, PatientProfile.class);
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
                Intent intent = new Intent(PatientEditProfile.this, WelcomePage.class);
                startActivity(intent);
            }
        });
    }
}