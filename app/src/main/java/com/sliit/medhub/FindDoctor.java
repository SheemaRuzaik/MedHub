package com.sliit.medhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FindDoctor extends AppCompatActivity {


    // Title bar
    TextView title;

    //button
    Button book1,book2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        //Tool bar
        title = findViewById(R.id.head);
        title.setText("Book Now");


        book1=findViewById(R.id.bookdoc1);
        book2=findViewById(R.id.bookdoc2);

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, AddInfo_Payment.class);
                startActivity(intent);
            }


        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, AddInfo_Payment.class);
                startActivity(intent);
            }

        });


    }
}