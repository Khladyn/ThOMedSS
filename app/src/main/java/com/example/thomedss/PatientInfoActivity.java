package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class PatientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        DrawerLayout sidebar = findViewById(R.id.sidebar);
        findViewById(R.id.icon_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.openDrawer(GravityCompat.START);
            }
        });

    }
}