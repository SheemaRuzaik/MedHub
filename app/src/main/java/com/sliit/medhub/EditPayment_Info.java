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

    // navigation button
    ImageView home, doctor, profile, appointment, extra, logout;

    // navigation details
    private ConstraintLayout constraintLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    // Button
    Button bttnSave, bttnDelete, bttnCancel;

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

        //Button
        bttnSave = findViewById(R.id.Save);

        final SessionManagement sessionManagement = new SessionManagement(HospitalEditProfile.this);
        String un = sessionManagement.getHospitalSession();

        dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child(un);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                txtPatientName.setText(snapshot.child("name").getValue().toString());
                txtPhoneNumber.setText(snapshot.child("email").getValue().toString());
                txtNIC.setText(snapshot.child("register_no").getValue().toString());
                txtAge.setText(snapshot.child("phone").getValue().toString());
            }


            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        bttnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upref= FirebaseDatabase.getInstance().getReference().child("Payment").child(register.getText().toString());
                upref.child("name").setValue(txtPatientName.getText().toString().trim());
                upref.child("email").setValue(txtPhoneNumber.getText().toString().trim());
                upref.child("register_no").setValue(txtNIC.getText().toString().trim());
                upref.child("address").setValue(txtAge.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HospitalEditProfile.this, HospitalProfile.class);
                startActivity(intent);
            }
        });
    }
}