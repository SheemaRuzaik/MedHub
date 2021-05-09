package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalRegister extends AppCompatActivity {

    // Title bar
    TextView title;
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
    TextView sign_up;

    FirebaseAuthException firebaseAuthException;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Hospital Register");

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
                Intent intent = new Intent(HospitalRegister.this, HospitalLogin.class);
                startActivity(intent);
            }
        });

        // Button
        sign_up = findViewById(R.id.register_to_profile_hospital);

        hospital = new Hospital();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Hospital name cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(register.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Registration No cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(address.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Address cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Phone No cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(cpwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Confirm Password cannot be empty",Toast.LENGTH_SHORT).show();
                }else {
                    if (pwd.getText().toString().trim().equals(cpwd.getText().toString().trim())){

                        dbref = FirebaseDatabase.getInstance().getReference().child("Hospital");

                        hospital.setName(name.getText().toString().trim());
                        hospital.setAddress(address.getText().toString().trim());
                        hospital.setEmail(email.getText().toString().trim());
                        hospital.setRegister_no(register.getText().toString().trim());
                        hospital.setPhone(Integer.parseInt(phone.getText().toString().trim()));
                        hospital.setPassword(pwd.getText().toString().trim());

                        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if(snapshot.hasChild(hospital.getRegister_no())){
                                    Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                                }else{
                                    dbref.child(hospital.getRegister_no()).setValue(hospital);
                                    Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HospitalLogin.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled( DatabaseError error) {

                            }

                        });

                    }else {
                        Toast.makeText(getApplicationContext(), "Confirm Password is not match", Toast.LENGTH_SHORT).show();
                    }
                }

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