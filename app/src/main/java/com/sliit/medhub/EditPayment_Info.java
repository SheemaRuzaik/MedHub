package com.sliit.medhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EditPayment_Info extends AppCompatActivity {


    // Title bar
    TextView title;

    //Form input
    EditText txtPatientName, txtPhoneNumber, txtNIC, txtAge;
    TextView txtpayID;


    //declare not needed variables
    String Card_type;
    String Card_number;
    String Card_Name;
    String Exp_date;
    String CVC;

    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;

    // navigation details
    private ConstraintLayout constraintLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    // Button
    Button bttnSave, bttnCancel;

    DatabaseReference dbref, upref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment__info);




        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Edit My Appointment");

        Intent intent=getIntent();
        String Pay_ID=intent.getStringExtra("getPay_ID");

        txtPatientName = findViewById(R.id.editPatientName);
        txtPhoneNumber = findViewById(R.id.editPhoneNumber);
        txtNIC = findViewById(R.id.editNIC);
        txtAge = findViewById(R.id.editAge);
        txtpayID=findViewById(R.id.viewpayID);

        //Payment Object

        Payment getpayment=new Payment();

        //Button
        bttnSave = findViewById(R.id.Save);
        bttnCancel=findViewById(R.id.Cancel);

//     final SessionManagement sessionManagement = new SessionManagement(EditPayment_Info.this);
//        Integer un = sessionManagement.getSession();

        dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child(Pay_ID);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String PayID=snapshot.child("payID").getValue().toString();
                String Patient_Name=snapshot.child("patientName").getValue().toString();
                String Phone_Number=snapshot.child("phoneNumber").getValue().toString();
                String NIC=snapshot.child("nic").getValue().toString();
                String Age=snapshot.child("age").getValue().toString();
                Card_type=snapshot.child("cardType").getValue().toString();
                 Card_number=snapshot.child("cardNumber").getValue().toString();
                 Card_Name=snapshot.child("nameOnCard").getValue().toString();
                 Exp_date=snapshot.child("expDate").getValue().toString();
                 CVC=snapshot.child("cvc").getValue().toString();


                txtpayID.setText(PayID);
                txtPatientName.setText(Patient_Name);
                txtPhoneNumber.setText(Phone_Number);
                txtNIC.setText(NIC);
                txtAge.setText(Age);
            }


            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        bttnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbref=FirebaseDatabase.getInstance().getReference().child("Payment");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild(Pay_ID)) {
                            try {
                                getpayment.setPayID(Integer.parseInt(txtpayID.getText().toString().trim()));
                                getpayment.setPatientName(txtPatientName.getText().toString().trim());
                                getpayment.setPhoneNumber(txtPhoneNumber.getText().toString().trim());
                                getpayment.setNIC(txtNIC.getText().toString().trim());
                                getpayment.setAge(txtAge.getText().toString().trim());

                                getpayment.setCardType(Card_type);
                                getpayment.setCardNumber(Card_number);
                                getpayment.setNameOnCard(Card_Name);
                                getpayment.setExpDate(Exp_date);
                                getpayment.setCVC(CVC);

                                dbref.child(Pay_ID).setValue(getpayment);

                                Toast.makeText(getBaseContext(), "Your Appointment has been Updated", Toast.LENGTH_LONG).show();



                            } catch (NumberFormatException e) {
                                Toast.makeText(getBaseContext(), "Enter Valid Format", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getBaseContext(), "No Source to update", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        bttnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditPayment_Info.this, ViewAppointment.class);
                startActivity(intent);
            }
        });
    }
}