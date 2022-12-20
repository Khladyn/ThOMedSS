package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomedss.data.ComplaintModel;
import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.UserModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class ChiefComplaintActivity extends AppCompatActivity {

    Button saveButton, cancelButton;

    RadioGroup caseType, location;

    RadioButton caseTypeSelected, locationSelected;

    TextView patientName, dateCreated, timeQueued, attendingDoctor, caseDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chief_complaint);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        caseType = findViewById(R.id.radioGroupCaseType);
        location = findViewById(R.id.radioGroupLocation);

        patientName = findViewById(R.id.patientName);
        dateCreated = findViewById(R.id.dateCreated);
        timeQueued = findViewById(R.id.timeQueue);
        attendingDoctor = findViewById(R.id.attendingDoctor);
        caseDetails = findViewById(R.id.caseDetails);

        UserModel currentUser = new UserModel();

        patientName.setText(currentUser.getName());

        String currentDate = new SimpleDateFormat("MM/dd/yyyy",
                Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm",
                Locale.getDefault()).format(new Date());
        String currentHour = new SimpleDateFormat("HH",
                Locale.getDefault()).format(new Date());

        dateCreated.setText(currentDate);
        timeQueued.setText(currentTime);

        if(Integer.parseInt(currentHour) > 7 && Integer.parseInt(currentHour) < 11) {
            attendingDoctor.setText("Emily Perez");
        } else if (Integer.parseInt(currentHour) > 11 && Integer.parseInt(currentHour) < 15) {
            attendingDoctor.setText("Danilo Lee");
        } else if (Integer.parseInt(currentHour) > 15 && Integer.parseInt(currentHour) < 19) {
            attendingDoctor.setText("Samantha Hidalgo");
        } else {
            attendingDoctor.setText("Outside work hours. Doctor will be manually assigned.");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                caseTypeSelected = findViewById(caseType.getCheckedRadioButtonId());
                locationSelected = findViewById(location.getCheckedRadioButtonId());

                ComplaintModel complaint = new ComplaintModel(
                        currentUser.getId(),
                        caseTypeSelected.getText().toString(),
                        locationSelected.getText().toString(),
                        patientName.getText().toString(),
                        dateCreated.getText().toString(),
                        timeQueued.getText().toString(),
                        attendingDoctor.getText().toString(),
                        caseDetails.getText().toString()
                );

                DatabaseHelper db = new DatabaseHelper(ChiefComplaintActivity.this);
                boolean createSuccess = db.createAppointment(complaint);

                if ( createSuccess == true ) {
                    Toast.makeText(ChiefComplaintActivity.this, "Appointment created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiefComplaintActivity.this, "Cannot create Appointment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}