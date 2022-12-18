package com.example.thomedss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MedicalConsultationActivity extends AppCompatActivity {

    String medicalProcedure = "medical_procedure.pdf";
    String doctorOnline = "doctors_online.pdf";
    String doctorOnsite = "doctors_onsite.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_consultation);

        Button button1 = findViewById(R.id.buttonLinkForMedicalConsultation);
        Button button2 = findViewById(R.id.buttonLinkForDoctorsScheduleOnline);
        Button button3 = findViewById(R.id.buttonLinkForDoctorsScheduleOnsite);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalConsultationActivity.this, PDFViewer.class);
                intent.putExtra("fileName", medicalProcedure);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalConsultationActivity.this, PDFViewer.class);
                intent.putExtra("fileName", doctorOnline);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalConsultationActivity.this, PDFViewer.class);
                intent.putExtra("fileName", doctorOnsite);
                startActivity(intent);
            }
        });
    }
}