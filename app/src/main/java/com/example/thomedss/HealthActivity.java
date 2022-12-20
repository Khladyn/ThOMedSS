package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.SavedStateViewModelFactoryKt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        Boolean healthExists = db.verifyHealth(new UserModel().getId());

        if (healthExists == true) {

        HealthModel currentHealth = db.getHealth(new UserModel().getId());

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

        }

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
                            new UserModel().getId(),
                            sb.toString());

                } catch (Exception e) {
                    Toast.makeText(HealthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
                boolean updateSuccess = db.updateMedical(healthHistory);

                if ( updateSuccess == true ) {
                    Toast.makeText(HealthActivity.this, "Medical History successfully updated!", Toast.LENGTH_SHORT).show();
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
                            new UserModel().getId(),
                            smoker.isChecked(),
                            drinker.isChecked());

                } catch (Exception e) {
                    Toast.makeText(HealthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(HealthActivity.this);
                boolean updateSuccess = db.updateSocial(healthHistory);

                if ( updateSuccess == true ) {
                    Toast.makeText(HealthActivity.this, "Social History successfully updated!", Toast.LENGTH_SHORT).show();
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
                            new UserModel().getId(),
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
                    Toast.makeText(HealthActivity.this, "Obstetric History successfully updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthActivity.this, "Cannot update Obstetric History", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showNavBar(HealthActivity.this);
    }


    public void showNavBar(Context context)
    {
        DatabaseHelper db = new DatabaseHelper(context);
        UserModel userProfile = db.getUser(new UserModel().getId());

        Typeface bold = ResourcesCompat.getFont(context, R.font.poppins_bold);
        Typeface light = ResourcesCompat.getFont(context, R.font.poppins_light);

        FrameLayout navContainer = findViewById(R.id.rootLayout);

        LinearLayout.LayoutParams mainLayout;
        mainLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams childLayout;
        childLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams childLayout2;
        childLayout2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayout2.setMargins(0,25,0,25);

//        LinearLayout.LayoutParams childLayout3;
//        childLayout3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        childLayout3.setMargins(700, 100, 0, 0);

        LinearLayout.LayoutParams childLayout4;
        childLayout4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams imageLayout;
        imageLayout = new LinearLayout.LayoutParams(150, 150);
        imageLayout.setMargins(0, 0, 0, 25);

        LinearLayout navBar = new LinearLayout(context);
        navBar.setOrientation(LinearLayout.VERTICAL);
        navBar.setBackgroundColor(Color.parseColor("#2D2926"));

        LinearLayout profileArea = new LinearLayout(context);
        profileArea.setOrientation(LinearLayout.VERTICAL);
        profileArea.setPadding(60, 70, 60, 60);
        profileArea.setBackgroundColor(Color.parseColor("#FDBF15"));

        LinearLayout navArea = new LinearLayout(context);
        navArea.setOrientation(LinearLayout.VERTICAL);
        navArea.setPadding(60, 70, 60, 60);

        String fileSource = userProfile.getPhoto();
        String fileName = fileSource.substring(0, fileSource.length()-4);

        int photoID = getResources().getIdentifier(fileName, "drawable", getPackageName());
        Drawable userPhoto = getResources().getDrawable(photoID);

        ImageView profilePhoto = new ImageView(context);
        profilePhoto.setImageDrawable(userPhoto);

        TextView userName = new TextView(context);
        userName.setText(userProfile.getName().toUpperCase());
        userName.setTypeface(bold);
        userName.setTextColor(Color.BLACK);

        TextView userNumber = new TextView(context);
        String withStudentType = new String(userProfile.getId() + " • " + userProfile.getType());
        userNumber.setText(withStudentType);
        userNumber.setTypeface(light);
        userNumber.setTextColor(Color.BLACK);

        TextView userCollege = new TextView(context);
        userCollege.setText(userProfile.getCollege());
        userCollege.setTypeface(light);
        userCollege.setTextColor(Color.BLACK);

        TextView header1 = new TextView(context);
        header1.setText("PATIENT INFORMATION");
        header1.setTypeface(bold);
        header1.setTextColor(Color.parseColor("#FDBF15"));

        TextView subheader1 = new TextView(context);
        subheader1.setText("Personal and Contact Information");
        subheader1.setTypeface(light);
        subheader1.setTextColor(Color.WHITE);
        subheader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PersonalActivity.class);
                startActivity(intent);
            }
        });

        TextView subheader2 = new TextView(context);
        subheader2.setText("Health History");
        subheader2.setTypeface(light);
        subheader2.setTextColor(Color.WHITE);
        subheader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HealthActivity.class);
                startActivity(intent);
            }
        });


        TextView header2 = new TextView(context);
        header2.setText("COVID-19 RESPONSE");
        header2.setTypeface(bold);
        header2.setTextColor(Color.parseColor("#FDBF15"));

        TextView subheader3 = new TextView(context);
        subheader3.setText("Case Tracker");
        subheader3.setTypeface(light);
        subheader3.setTextColor(Color.WHITE);
        subheader3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CaseTrackerActivity.class);
                startActivity(intent);
            }
        });

        TextView header3 = new TextView(context);
        header3.setText("ONLINE CONSULTATION");
        header3.setTypeface(bold);
        header3.setTextColor(Color.parseColor("#FDBF15"));

        TextView subheader4 = new TextView(context);
        subheader4.setText("Medical Consultation");
        subheader4.setTypeface(light);
        subheader4.setTextColor(Color.WHITE);
        subheader4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MedicalConsultationActivity.class);
                startActivity(intent);
            }
        });

        TextView subheader5 = new TextView(context);
        subheader5.setText("Dental Consultation");
        subheader5.setTypeface(light);
        subheader5.setTextColor(Color.WHITE);
        subheader5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DentalConsultationActivity.class);
                startActivity(intent);
            }
        });

        TextView header4 = new TextView(context);
        header4.setText("THE DEVELOPERS");
        header4.setTypeface(bold);
        header4.setTextColor(Color.parseColor("#FDBF15"));

        TextView subheader6 = new TextView(context);
        subheader6.setText("About Us");
        subheader6.setTypeface(light);
        subheader6.setTextColor(Color.WHITE);
        subheader6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DevelopersActivity.class);
                startActivity(intent);
            }
        });

        TextView footer = new TextView(context);
        footer.setText("Logout");
        footer.setTypeface(bold);
        footer.setTextColor(Color.parseColor("#FDBF15"));
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        ImageView hideNav = new ImageView(context);
        hideNav.setImageResource(R.drawable.ic_baseline_keyboard_arrow_left_24);

        profileArea.addView(profilePhoto, imageLayout);
        profileArea.addView(userName, childLayout4);
        profileArea.addView(userNumber, childLayout4);
        profileArea.addView(userCollege, childLayout4);

        navArea.addView(header1, childLayout2);
        navArea.addView(subheader1, childLayout2);
        navArea.addView(subheader2, childLayout2);
        navArea.addView(header2, childLayout2);
        navArea.addView(subheader3, childLayout2);
        navArea.addView(header3, childLayout2);
        navArea.addView(subheader4, childLayout2);
        navArea.addView(subheader5, childLayout2);
        navArea.addView(header4, childLayout2);
        navArea.addView(subheader6, childLayout2);
        navArea.addView(footer, childLayout2);

        navBar.addView(profileArea, childLayout);
        navBar.addView(navArea, childLayout);

//        navBar.addView(hideNav, childLayout3);

        navContainer.addView(navBar, mainLayout);

        ImageView menuButton;
        menuButton = findViewById(R.id.menuButton);

        navBar.setVisibility(View.GONE);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAnimation scale = new TranslateAnimation(-1000, 0,0, 0);
                scale.setDuration(500);
                navBar.startAnimation(scale);
                navBar.setVisibility(View.VISIBLE);
            }
        });

        hideNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAnimation scale = new TranslateAnimation(0, -1000,0, 0);
                scale.setDuration(500);
                scale.setFillEnabled(true);
                scale.setFillAfter(true);
                navBar.startAnimation(scale);
            }
        });
    }
}