package com.sliit.medhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindDoctor extends AppCompatActivity {

    //button
    Button book1,book2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);



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