package com.sliit.medhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedBack extends AppCompatActivity {

    FeedBack_model feedBack_model;
    // Title bar
    TextView title;
    //feedback
    EditText feedback;
    //Button
    TextView save;
    // navigation button
    ImageView home, profile, appointment, my_book, logout;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("FeedBack");

        feedback = findViewById(R.id.enter_feedback);
        save = findViewById(R.id.save_feedback);

        feedBack_model = new FeedBack_model();

        final SessionManagement sessionManagement=new SessionManagement(FeedBack.this);
        String un=sessionManagement.getSession();

        dbref = FirebaseDatabase.getInstance().getReference().child("Patient").child(un);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(feedback.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"FeedBack Cannot Empty", Toast.LENGTH_SHORT).show();
                }else{
                    dbref = FirebaseDatabase.getInstance().getReference().child("FeedBack_model");

                    feedBack_model.setNic(un);
                    feedBack_model.setFeedbk(feedback.getText().toString().trim());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dbref.child(feedBack_model.getNic()).setValue(feedBack_model);
                            Toast.makeText(getApplicationContext(),"Successfully Send FeedBack",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        // navigation implement
        drawerLayout = findViewById(R.id.drawer_fb);
        //navigationView = findViewById(R.id.Navigation_view_pep);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        // navigation button
        home = findViewById(R.id.feedback_to_home);
        profile = findViewById(R.id.feedback_to_pat_pro);
        appointment = findViewById(R.id.feedback_to_appointment);
        my_book = findViewById(R.id.feedback_to_my_book);
        logout = findViewById(R.id.feedback_to_logout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this, SearchAppointment.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this, PatientProfile.class);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this, FindDoctor.class);
                startActivity(intent);
            }
        });

        my_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this, ViewAppointment.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this, WelcomePage.class);
                startActivity(intent);
            }
        });

    }
}