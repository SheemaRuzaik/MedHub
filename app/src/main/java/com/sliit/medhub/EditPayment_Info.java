package com.sliit.medhub;

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

        txtPatientName = findViewById(R.id.editPatientName);
        txtPhoneNumber = findViewById(R.id.editPhoneNumber);
        txtNIC = findViewById(R.id.editNIC);
        txtAge = findViewById(R.id.editAge);
        txtpayID=findViewById(R.id.viewpayID);

        //Button
        bttnSave = findViewById(R.id.Save);

        final SessionManagement sessionManagement = new SessionManagement(EditPayment_Info.this);
        Integer un = sessionManagement.getSession();

        dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child(un.toString());

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                txtPatientName.setText(snapshot.child("PatientName").getValue().toString());
                txtPhoneNumber.setText(snapshot.child("phoneNumber").getValue().toString());
                txtNIC.setText(snapshot.child("NIC").getValue().toString());
                txtAge.setText(snapshot.child("Age").getValue().toString());
            }


            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        bttnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upref= FirebaseDatabase.getInstance().getReference().child("Payment").child(txtpayID.getText().toString());
                upref.child("PatientName").setValue(txtPatientName.getText().toString().trim());
                upref.child("phoneNumber").setValue(txtPhoneNumber.getText().toString().trim());
                upref.child("NIC").setValue(txtNIC.getText().toString().trim());
                upref.child("Age").setValue(txtAge.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditPayment_Info.this, ViewAppointment.class);
                startActivity(intent);
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