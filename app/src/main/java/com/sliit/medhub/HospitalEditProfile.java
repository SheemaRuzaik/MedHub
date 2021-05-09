package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalEditProfile extends AppCompatActivity {

    // Title bar
    TextView title;
    // Form details
    EditText name, email, address, phone;
    TextView register;
    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;
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
        setContentView(R.layout.activity_hospital_edit_profile);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Edit Profile");

        // Form details
        name = findViewById(R.id.edit_hospital_name);
        email = findViewById(R.id.edit_hospital_email);
        address = findViewById(R.id.edit_address);
        register = findViewById(R.id.edit_register_no);
        phone = findViewById(R.id.edit_hospital_phone_no);

        // Button
        save = findViewById(R.id.edit_to_profile_hospital);

        final SessionManagement sessionManagement=new SessionManagement(HospitalEditProfile.this);
        String un=sessionManagement.getHospitalSession();

        dbref= FirebaseDatabase.getInstance().getReference().child("Hospital").child(un);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                register.setText(snapshot.child("register_no").getValue().toString());
                address.setText(snapshot.child("address").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upref= FirebaseDatabase.getInstance().getReference().child("Hospital").child(register.getText().toString());
                upref.child("name").setValue(name.getText().toString().trim());
                upref.child("email").setValue(email.getText().toString().trim());
                upref.child("register_no").setValue(register.getText().toString().trim());
                upref.child("address").setValue(address.getText().toString().trim());
                upref.child("phone").setValue(phone.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(HospitalEditProfile.this, HospitalProfile.class);
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
                Intent intent = new Intent(HospitalEditProfile.this, WelcomePage.class);
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