package com.example.medhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HospitalRegister extends AppCompatActivity {

    // Hospital object
    Hospital hospital;
    // Form details
    EditText name, address, phone, pwd, cpwd, email, register;
    // login link
    TextView sign_in;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    //Button
    TextView back, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        // Form details
        name = findViewById(R.id.hospital_name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.hospital_phone_no);
        email = findViewById(R.id.hospital_email);
        register = findViewById(R.id.hospital_register_no);
        pwd = findViewById(R.id.hospital_pwd);
        cpwd = findViewById(R.id.hospital_cpwd);

        //Intent intent = getIntent();
        hospital = new Hospital();
        // login link
        sign_in = findViewById(R.id.hospital_register_to_sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Button
        back = findViewById(R.id.profile_contact_hospital);
        sign_up = findViewById(R.id.register_to_profile_hospital);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(HospitalRegister.this, HospitalRegisterContact.class);
                //startActivity(intent);
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_hr);
        navigationView = findViewById(R.id.Navigation_view_hr);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

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