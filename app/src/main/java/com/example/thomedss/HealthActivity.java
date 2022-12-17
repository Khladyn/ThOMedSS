package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactoryKt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.HealthModel;
import com.example.thomedss.data.UserModel;

public class HealthActivity extends AppCompatActivity {

    Button medical_btn, social_btn, obstetric_btn;

    CheckBox[] illnesses = new CheckBox[33];

    CheckBox smoker, drinker, regular, contraceptives, pregnant, miscarriage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        medical_btn = findViewById(R.id.saveButton);
        social_btn = findViewById(R.id.buttonUpdateSHR);
        obstetric_btn = findViewById(R.id.buttonUpdateOHR);

        //MEDICAL VARIABLES
        illnesses[0] = findViewById(R.id.checkBoxArthritis);
        illnesses[1] = findViewById(R.id.checkBoxAsthma);
        illnesses[2] = findViewById(R.id.checkBoxBleedingDisorder);
        illnesses[3] = findViewById(R.id.checkBoxBoneMarrowTransplant);
        illnesses[4] = findViewById(R.id.checkBoxCOVID);
        illnesses[5] = findViewById(R.id.checkBoxCancer);
        illnesses[6] = findViewById(R.id.checkBoxChickenpox);
        illnesses[7] = findViewById(R.id.checkBoxCOPD);
        illnesses[8] = findViewById(R.id.checkBoxDengue);
        illnesses[9] = findViewById(R.id.checkBoxDiabetes);
        illnesses[10] = findViewById(R.id.checkBoxHeartDisease);
        illnesses[11] = findViewById(R.id.checkBoxHematologicDisease);
        illnesses[12] = findViewById(R.id.checkBoxHepatitisA);
        illnesses[13] = findViewById(R.id.checkBoxHepatitisB);
        illnesses[14] = findViewById(R.id.checkBoxHBP);
        illnesses[15] = findViewById(R.id.checkBoxHighCholesterol);
        illnesses[16] = findViewById(R.id.checkBoxHighRiskPregnancy);
        illnesses[17] = findViewById(R.id.checkBoxOrganTransplant);
        illnesses[18] = findViewById(R.id.checkBoxAllergicReaction);
        illnesses[19] = findViewById(R.id.checkBoxImmunoDisease);
        illnesses[20] = findViewById(R.id.checkBoxKidneyDisease);
        illnesses[21] = findViewById(R.id.checkBoxLiverDisease);
        illnesses[22] = findViewById(R.id.checkBoxLungDisease);
        illnesses[23] = findViewById(R.id.checkBoxMeasles);
        illnesses[24] = findViewById(R.id.checkBoxMumps);
        illnesses[25] = findViewById(R.id.checkBoxNeurologicalDisease);
        illnesses[26] = findViewById(R.id.checkBoxObesity);
        illnesses[27] = findViewById(R.id.checkBoxDialysis);
        illnesses[28] = findViewById(R.id.checkBoxHIV);
        illnesses[29] = findViewById(R.id.checkBoxSteroids);
        illnesses[30] = findViewById(R.id.checkBoxTuberculosis);
        illnesses[31] = findViewById(R.id.checkBoxTyphoid);
        illnesses[32] = findViewById(R.id.checkBoxImmunoDrugs);

        //SOCIAL VARIABLES
        smoker = findViewById(R.id.checkBoxSmoker);
        drinker = findViewById(R.id.checkBoxAlcohol);

        //OBSTETRIC VARIABLES
        regular = findViewById(R.id.checkBoxRegularMens);
        contraceptives = findViewById(R.id.checkBoxOCP);
        pregnant = findViewById(R.id.checkBoxPregnant);
        miscarriage = findViewById(R.id.checkBoxMiscarriage);

        //HEALTH HISTORY
        DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
        HealthModel currentHealth = db.getHealth("2021176201");

        String[] getIllnesses = currentHealth.getIllnesses().split("\\s*,\\s*");

        for (int cb = 0; cb < illnesses.length; cb++ )
        {
            for (int g = 0; g < getIllnesses.length; g++)
            {
                if (getIllnesses[g].equals(illnesses[cb].getText().toString()))
                {
                    illnesses[cb].setChecked(true);
                }
            }
        }

        smoker.setChecked(currentHealth.isSmoker());
        drinker.setChecked(currentHealth.isDrinker());
        regular.setChecked(currentHealth.isRegular_cycle());
        contraceptives.setChecked(currentHealth.isContraceptives());
        pregnant.setChecked(currentHealth.isPregnant());
        miscarriage.setChecked(currentHealth.isMiscarriage());

        medical_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HealthModel healthHistory = null;

                StringBuilder sb = new StringBuilder();

                for (int cb = 0; cb < illnesses.length; cb++ )
                {
                    if (illnesses[cb].isChecked())
                    {
                        sb.append(illnesses[cb].getText().toString() + ", ");
                    }
                }

                try {
                    healthHistory = new HealthModel(
                            "2021176201",
                            sb.toString());

                } catch (Exception e) {
                    Toast.makeText(HealthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
                boolean updateSuccess = db.updateMedical(healthHistory);

                if ( updateSuccess == true ) {
                    Toast.makeText(HealthActivity.this, "Medical History Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthActivity.this, "Cannot update Medical History", Toast.LENGTH_SHORT).show();
                }

            }
        });

        social_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HealthModel healthHistory = null;

                try {
                    healthHistory = new HealthModel(
                            "2021176201",
                            smoker.isChecked(),
                            drinker.isChecked());

                } catch (Exception e) {
                    Toast.makeText(HealthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
                boolean updateSuccess = db.updateSocial(healthHistory);

                if ( updateSuccess == true ) {
                    Toast.makeText(HealthActivity.this, "Social History Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthActivity.this, "Cannot update Social History", Toast.LENGTH_SHORT).show();
                }

            }
        });

        obstetric_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HealthModel healthHistory = null;

                try {
                    healthHistory = new HealthModel(
                            "2021176201",
                            regular.isChecked(),
                            contraceptives.isChecked(),
                            pregnant.isChecked(),
                            miscarriage.isChecked());

                } catch (Exception e) {
                    Toast.makeText(HealthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
                boolean updateSuccess = db.updateObstetric(healthHistory);

                if ( updateSuccess == true ) {
                    Toast.makeText(HealthActivity.this, "Obstetric History Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthActivity.this, "Cannot update Obstetric History", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}