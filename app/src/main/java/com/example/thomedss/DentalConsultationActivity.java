package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DentalConsultationActivity extends AppCompatActivity {

    String dentalProcedure = "dental_procedure.pdf";
    String dentistSchedule = "dentist_schedule.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_consultation);

        Button button1 = findViewById(R.id.buttonLinkForDentalConsultation);
        Button button2 = findViewById(R.id.buttonLinkForDentistsSchedule);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentalConsultationActivity.this, PDFViewer.class);
                intent.putExtra("fileName", dentalProcedure);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentalConsultationActivity.this, PDFViewer.class);
                intent.putExtra("fileName", dentistSchedule);
                startActivity(intent);
            }
        });
    }
}