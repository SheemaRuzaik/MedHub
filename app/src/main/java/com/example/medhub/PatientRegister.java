package com.example.medhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class PatientRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Title bar
    TextView title;
    // Patient class object
    Patient patient;
    // Form details
    EditText first_name, last_name, nic, pwd, cpwd, email, contact;
    private DatePickerDialog datePickerDialog;
    private Button datebtn;
    Spinner spinner;
    // login link
    TextView sign_in;
    // button
    TextView back, sign_up;
    // navigation details
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        // Tool bar
        title = findViewById(R.id.head);
        title.setText("Patient Register");

        // Form details
        first_name = findViewById(R.id.First_name);
        last_name = findViewById(R.id.Last_name);
        nic = findViewById(R.id.Nic);
        pwd = findViewById(R.id.Patient_Pwd);
        cpwd = findViewById(R.id.Patient_Cpwd);
        datebtn = findViewById(R.id.Date);

        //Intent intent = getIntent();
        email = findViewById(R.id.Patient_Email);
        contact = findViewById(R.id.Patient_Contact);

        datebtn.setText(getTodaysDate());

        spinner = findViewById(R.id.Gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // login link
        sign_in = findViewById(R.id.Patient_register_to_sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Button
        back = findViewById(R.id.profile_home);
        sign_up = findViewById(R.id.Register_to_profile);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PatientRegister.this, PatientRegisterContact.class);
                //startActivity(intent);
            }
        });

        // navigation implement
        drawerLayout = findViewById(R.id.drawer_pr);
        navigationView = findViewById(R.id.Navigation_view_pr);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        month = month + 1;
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        return makeDateString(dayOfMonth, month, year);
    }

    private void datepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                datebtn.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year){
        return  day  + "-" + getMonth(month) + "-" + year;
    }

    private String getMonth(int month) {
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month == 12)
            return "Dec";
        return "Jan";
    }

    public void datepicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}