package com.example.thomedss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class PatientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        final DrawerLayout drawerLayout = findViewById(R.id.layout_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView sidebar = findViewById(R.id.sidebar);



        //TEST
        Button testbtn = findViewById(R.id.testbtn);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientInfoActivity.this, PersonalActivity.class));
                Toast.makeText(PatientInfoActivity.this, "Personal", Toast.LENGTH_LONG).show();
            }
        });

        setSupportActionBar(toolbar);

        findViewById(R.id.icon_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        sidebar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getTitle().toString())
                {
                    case "Personal and Contact Information":
                    {
                        startActivity(new Intent(PatientInfoActivity.this, PersonalActivity.class));
                        Toast.makeText(PatientInfoActivity.this, "Personal", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case "Health History":
                    {
                        startActivity(new Intent(PatientInfoActivity.this, HealthActivity.class));
                        break;
                    }

                }

                return true;
            }
        });

    }
}