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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientLogin extends AppCompatActivity {

    // Title bar
    TextView title;
    // Patient object
    Patient patient;
    // Form details
    EditText pwd,nic;
    // Register link
    TextView sign_up;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    //Button
    TextView sign_in;

    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Patient Login");

        // form details
        nic = findViewById(R.id.login_nic);
        pwd = findViewById(R.id.login_pwd);

        //Sign Up Link
        sign_up = findViewById(R.id.patient_login_to_sign_un);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(intent);
            }
        });

        patient = new Patient();

        // Login
        sign_in = findViewById(R.id.patient_login_to_profile);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nic.getText().toString())){
                    Toast.makeText(getApplicationContext(),"NIC cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                } else {
                    String un=nic.getText().toString().trim();
                    String pw=pwd.getText().toString().trim();
                    patient.setNic(nic.getText().toString().trim());

                    dbref= FirebaseDatabase.getInstance().getReference().child("Patient");
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if((snapshot.hasChild(un))&&(snapshot.child(un).child("password").getValue().toString().equals(pw))){
                                SessionManagement sessionManagement=new SessionManagement(PatientLogin.this);
                                sessionManagement.saveSession(patient);
                                Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid NIC or Password",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
        });


        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pl);
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