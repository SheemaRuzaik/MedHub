package com.example.medhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalLogin extends AppCompatActivity {

    // Hospital object
    Hospital hospital;
    // Form details
    EditText pwd,register;
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
        setContentView(R.layout.activity_hospital_login);

        // form details
        register = findViewById(R.id.login_reg_no);
        pwd = findViewById(R.id.login_hos_pwd);

        //Sign Up Link
        sign_up = findViewById(R.id.hospital_login_to_sign_un);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalLogin.this, HospitalProfile.class);
                startActivity(intent);

            }
        });

        // Login
        sign_in = findViewById(R.id.hospital_login_to_profile);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(register.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Register No cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                } else {
                    final String un=register.getText().toString().trim();
                    final String pw=pwd.getText().toString().trim();
                    hospital.setRegister_no(register.getText().toString().trim());

                    dbref= FirebaseDatabase.getInstance().getReference().child("Hospital");
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if((snapshot.hasChild(un))&&(snapshot.child(un).child("pwd").getValue().toString().equals(pw))){

                                // Toast.makeText(getApplicationContext(),un,Toast.LENGTH_SHORT).show();
                                SessionManagement sessionManagement=new SessionManagement(HospitalLogin.this);
                                sessionManagement.saveHospitalSession(hospital);
                                Intent intent = new Intent(getApplicationContext(), HospitalProfile.class);
                                startActivity(intent);
                            }else{
                                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getApplicationContext());
                                alertDialog.setMessage("Invalid register No or Password").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                });
                                //   AlertDialog dialog=alertDialog.create();
                                // dialog.show();

                                Toast.makeText(getApplicationContext(),"Invalid NIC or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_hl);
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