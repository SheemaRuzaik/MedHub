package com.sliit.medhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAppointment extends AppCompatActivity {


    // Title bar
    TextView title;

    Spinner spinnerconsultant,spinnerhospital,spinnerspecilization;

    Button bttnsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);

        spinnerconsultant=findViewById(R.id.consultantspinner);
        spinnerhospital=findViewById(R.id.hospitalspinner);
        spinnerspecilization=findViewById(R.id.specialspinner);


        //Tool bar
        title = findViewById(R.id.head);
        title.setText("Find Your Doctor");


        List<String> consultant = new ArrayList();
        consultant.add(0,"CHOOSE CONSULTANT");
        consultant.add("DR.(MRS.) THALAKOLA");
        consultant.add("DR.(MRS.) CHALUKYA GUNASEKARA");
        consultant.add("DR.(MR.) RAEESUL ISLAM");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, consultant);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerconsultant.setAdapter(adapter);
        spinnerconsultant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("CHOOSE Consultant")){
                    // nothing
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> hospital = new ArrayList();
        hospital.add(0,"CHOOSE HOSPITAL");
        hospital.add("ASIRI SURGICALS (PVT) LTD.");
        hospital.add("DELMON LANKA SURGICALS LTD");


        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hospital);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhospital.setAdapter(adapter1);
        spinnerhospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("CHOOSE HOSPITAL")){
                    // nothing
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        List<String> special = new ArrayList();
        special.add(0,"CHOOSE SPECIALIZATION");
        special.add("CONSULTANT CARDIOLOGIST");
        special.add("CONSULTANT PHYSICIAN");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, special);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerspecilization.setAdapter(adapter2);
        spinnerspecilization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("CHOOSE SPECIALIZATION")){
                    // nothing
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //button
        bttnsearch=findViewById(R.id.search_for_doc);


        bttnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchAppointment.this, FindDoctor.class);
                startActivity(intent);
            }
        });





    }
}