package com.sliit.medhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddInfo_Payment extends AppCompatActivity {

    // Title bar
    TextView title;

    //Payment Object
    Payment payment;

    //Form input
    EditText txtPatientName, txtPhoneNumber, txtNIC, txtAge, txtCardType, txtCardNumber, txtNameOnCard, txtExpDate, txtCVC;

    //Button object
    Button bttnback, bttnPay;

    // navigation details
    private ConstraintLayout constraintLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    FirebaseAuthException firebaseAuthException;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info__payment);


        //Tool bar
        title = findViewById(R.id.head);
        title.setText("Make Your Payment");

        txtPatientName = findViewById(R.id.patientFullName);
        txtPhoneNumber = findViewById(R.id.phoneNumber);
        txtNIC = findViewById(R.id.NIC);
        txtAge = findViewById(R.id.patientAge);
        txtCardType = findViewById(R.id.cardtype);
        txtCardNumber = findViewById(R.id.cardnumber);
        txtNameOnCard = findViewById(R.id.nameoncard);
        txtExpDate = findViewById(R.id.expdate);
        txtCVC = findViewById(R.id.CVC);

        bttnback = findViewById(R.id.Cancel_pay);
        bttnPay = findViewById(R.id.Pay_Now);

        //Intent intent = getIntent();
        payment = new Payment();

        bttnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent = new Intent(AddInfo_Payment.this, FindDoctor.class);
                startActivity(intent);
            }
        });


        bttnPay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtPatientName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Patient name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtPhoneNumber.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtNIC.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "NIC number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtAge.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Age  cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtCardType.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Card Type  cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtCardNumber.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Card Number  cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtNameOnCard.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Name on card  cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtExpDate.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Expiry Date  cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtCVC.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "CVC  cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    dbref = FirebaseDatabase.getInstance().getReference().child("Payment");

                    payment.setPatientName(txtPatientName.getText().toString().trim());
                    payment.setPhoneNumber(txtPhoneNumber.getText().toString().trim());
                    payment.setNIC(txtNIC.getText().toString().trim());
                    payment.setAge(txtAge.getText().toString().trim());
                    payment.setCardType(txtCardType.getText().toString().trim());
                    payment.setCardNumber(txtCardNumber.getText().toString().trim());
                    payment.setNameOnCard(txtNameOnCard.getText().toString().trim());
                    payment.setExpDate(txtExpDate.getText().toString().trim());
                    payment.setCVC(txtCVC.getText().toString().trim());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(payment.getPayID().toString())){
                                Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
                            }else {
                                dbref.child(payment.getPayID().toString()).setValue(payment);
                                Toast.makeText(getApplicationContext(), "Successfully made your appointment", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AddInfo_Payment.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });



                         //   SessionManagement sessionManagement=new SessionManagement(AddInfo_Payment.this);
                         //   sessionManagement.saveSession(payment);

                                //Toast.makeText(getApplicationContext(), "Successfully placed your appointment", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), ViewAppointment.class);
                               // intent.putExtra("paymentID",payID);
                               // intent.putExtra("patientname",txtPatientName.getText().toString());
                                //intent.putExtra("phonenumber",Integer.parseInt(txtPhoneNumber.getText().toString()));
                              //  intent.putExtra("NIC",txtNIC.getText().toString());
                               // intent.putExtra("Age",Integer.parseInt(txtAge.getText().toString()));


                               // startActivity(intent);








        // navigation implement
       // constraintLayout = findViewById(R.id.drawer_hr);
        //navigationView = findViewById(R.id.Navigation_view_hr);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

    }




    private void clearControls() {
        txtPatientName.setText("");
        txtPhoneNumber.setText("");
        txtNIC.setText("");
        txtAge.setText("");
        txtCardType.setText("");
        txtCardNumber.setText("");
        txtNameOnCard.setText("");
        txtExpDate.setText("");
        txtCVC.setText("");
    }
}


