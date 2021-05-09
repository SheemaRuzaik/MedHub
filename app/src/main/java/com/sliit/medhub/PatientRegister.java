package com.sliit.medhub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientRegister extends AppCompatActivity {

    // Title bar
    TextView title;
    // Patient class object
    Patient patient;
    // Form details
    EditText full_name, nic, pwd, cpwd, email, contact;
    private DatePickerDialog datePickerDialog;
    //private Button datebtn;
    Spinner spinner;
    // login link
    TextView sign_in;
    // button
    TextView sign_up;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    FirebaseAuthException firebaseAuthException;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Patient Register");

        // Form details
        full_name = findViewById(R.id.full_name);
        nic = findViewById(R.id.Nic);
        pwd = findViewById(R.id.Patient_Pwd);
        cpwd = findViewById(R.id.Patient_Cpwd);
        //datebtn = findViewById(R.id.Date);

        //Intent intent = getIntent();
        email = findViewById(R.id.Patient_Email);
        contact = findViewById(R.id.Patient_Contact);

        //datebtn.setText(getTodaysDate());

        spinner = findViewById(R.id.Gender);

        List<String> gender = new ArrayList();
        gender.add(0,"Choose Gender");
        gender.add("Male");
        gender.add("Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Gender")){
                    // nothing
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // login link
        sign_in = findViewById(R.id.Patient_register_to_sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientRegister.this, PatientLogin.class);
                startActivity(intent);
            }
        });
        // Button
        sign_up = findViewById(R.id.Register_to_profile);


        patient = new Patient();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(full_name.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Full name cannot be empty",Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(nic.getText().toString())){
                    Toast.makeText(getApplicationContext(),"NIC no cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(contact.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Contact No cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(cpwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Confirm Password cannot be empty",Toast.LENGTH_SHORT).show();
                }else {
                    if (patient.isNICValid(nic.getText().toString().trim())){
                        if (pwd.getText().toString().trim().equals(cpwd.getText().toString().trim())){

                            dbref = FirebaseDatabase.getInstance().getReference().child("Patient");

                            patient.setFull_name(full_name.getText().toString().trim());
                            patient.setEmail(email.getText().toString().trim());
                            patient.setNic(nic.getText().toString().trim());
                            patient.setGender(spinner.getSelectedItem().toString().trim());
                            patient.setContact(Integer.parseInt(contact.getText().toString().trim()));
                            patient.setPassword(pwd.getText().toString().trim());

                            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if(snapshot.hasChild(patient.getNic())){
                                        Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                                    }else{
                                        dbref.child(patient.getNic()).setValue(patient);
                                        Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), PatientLogin.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {

                                }
                            });
                        }else {
                            Toast.makeText(getApplicationContext(), "Confirm Password is not match", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid NIC number Format", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pr);
        navigationView = findViewById(R.id.Navigation_view_pr);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

    }
    
}