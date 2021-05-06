package com.sliit.medhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;

public class AddInfo_Payment extends AppCompatActivity {

    // Title bar
    TextView title;

    EditText txtPatientName,txtPhoneNumber,txtNIC,txtAge,txtCardType,txtCardNumber,txtNameOnCard,txtExpDate,txtCVC;
    Button bttnPay;
    FirebaseAuthException firebaseAuthException;
    DatabaseReference dbref;
    Payment payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info__payment);

        txtPatientName=findViewById(R.id.patientFullName);
        txtPhoneNumber=findViewById(R.id.phoneNumber);
        txtNIC=findViewById(R.id.NIC);
        txtAge=findViewById(R.id.patientAge);
        txtCardType=findViewById(R.id.cardtype);
        txtCardNumber=findViewById(R.id.cardnumber);
        txtNameOnCard=findViewById(R.id.nameoncard);
        txtExpDate=findViewById(R.id.expdate);
        txtCVC=findViewById(R.id.CVC);

        bttnPay=findViewById(R.id.Pay);

        payment=new Payment();


        bttnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void clearControls(){
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