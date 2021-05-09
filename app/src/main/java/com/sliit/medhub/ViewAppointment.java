package com.sliit.medhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAppointment extends AppCompatActivity {

    // Title bar
    TextView title;

    // Form details
    TextView txtPaymentNo, txtPatientName, TxtPhoneNumber, txtNIC,txtAge;
   //Button
    Button bttnedit,bttndelete,bttnback;

    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private ConstraintLayout constraintLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    DatabaseReference dbref,delref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);


        // Tool bar
        title = findViewById(R.id.head);
        title.setText("My Appointments");

        Intent intent=getIntent();
        String Pay_ID=intent.getStringExtra("Pay_ID");

        // Form details
        txtPaymentNo = findViewById(R.id.getpayNo);
        txtPatientName = findViewById(R.id.getPatientName);
        TxtPhoneNumber = findViewById(R.id.getPhoneNumber);
        txtNIC = findViewById(R.id.getNIC);
        txtAge = findViewById(R.id.getAge);



        // button
        bttnback = findViewById(R.id.cancel_button);
        bttndelete = findViewById(R.id.Delete);
        bttnedit = findViewById(R.id.edit);

//        final SessionManagement sessionManagement=new SessionManagement(ViewAppointment.this);
//        Integer un=sessionManagement.getSession();

        dbref= FirebaseDatabase.getInstance().getReference().child("Payment").child(Pay_ID);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String PayID=snapshot.child("payID").getValue().toString();
                String Patient_Name=snapshot.child("patientName").getValue().toString();
                String Phone_Number=snapshot.child("phoneNumber").getValue().toString();
                String NIC=snapshot.child("nic").getValue().toString();
                String Age=snapshot.child("age").getValue().toString();
                String Card_type=snapshot.child("cardType").getValue().toString();
                String Card_number=snapshot.child("cardNumber").getValue().toString();
                String Card_Name=snapshot.child("nameOnCard").getValue().toString();
                String Exp_date=snapshot.child("expDate").getValue().toString();
                String CVC=snapshot.child("cvc").getValue().toString();


                txtPaymentNo.setText(PayID);
                txtPatientName.setText(Patient_Name);
                TxtPhoneNumber.setText(Phone_Number);
                txtNIC.setText(NIC);
                txtAge.setText(Age);


            }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });



                bttnedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), EditPayment_Info.class);
                        intent.putExtra("getPay_ID",Pay_ID);
                        v.getContext().startActivity(intent);
                        startActivity(intent);
                    }
                });

                bttnback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewAppointment.this, AddInfo_Payment.class);
                        startActivity(intent);
                    }
                });

                bttndelete.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        delref= FirebaseDatabase.getInstance().getReference().child("Payment");
                        delref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(Pay_ID)){
                                    delref=FirebaseDatabase.getInstance().getReference().child("Payment").child(Pay_ID);
                                    delref.removeValue();
                                    Intent intent2 = new Intent(ViewAppointment.this,AddInfo_Payment.class);

                                    startActivity(intent2);
                                    Toast.makeText(getBaseContext(), "Your Appointment has been Deleted", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getBaseContext(), "No Source to delete", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });





        // navigation implement


        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.view_app_to_home);
        profile = findViewById(R.id.view_app_to_pat_prof);
        appointment = findViewById(R.id.view_app_to_make_app);
        my_book = findViewById(R.id.view_app);
        logout = findViewById(R.id.view_app_logout);

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
                Intent intent = new Intent(ViewAppointment.this, ViewAppointment.class);
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
                //Intent intent = new Intent(PatientEditProfile.this, MyBooking.class);
                //startActivity(intent);
            }
        });
            }


}
