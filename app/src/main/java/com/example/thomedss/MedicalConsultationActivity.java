package com.example.thomedss;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.FontFamily;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomedss.data.ConsultationModel;
import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.UserModel;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.time.format.TextStyle;
import java.util.ArrayList;

public class MedicalConsultationActivity extends AppCompatActivity {

    String medicalProcedure = "medical_procedure.pdf";
    String doctorOnline = "doctors_online.pdf";
    String doctorOnsite = "doctors_onsite.pdf";

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_consultation);

        addActionButtons();

        DatabaseHelper db = new DatabaseHelper(MedicalConsultationActivity.this);
        Boolean appointmentExists = db.verifyAppointment(new UserModel().getId());

        if(appointmentExists == true)
        {
            showAppointments();
        }
        showNavBar(MedicalConsultationActivity.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_medical_consultation);

        addActionButtons();

        DatabaseHelper db = new DatabaseHelper(MedicalConsultationActivity.this);
        Boolean appointmentExists = db.verifyAppointment(new UserModel().getId());

        if(appointmentExists == true)
        {
            showAppointments();
        }

        showNavBar(MedicalConsultationActivity.this);

    }

    private void addActionButtons() {

        Button button1 = findViewById(R.id.buttonLinkForMedicalConsultation);
        Button button2 = findViewById(R.id.buttonLinkForDoctorsScheduleOnline);
        Button button3 = findViewById(R.id.buttonLinkForDoctorsScheduleOnsite);
        Button newCase = findViewById(R.id.buttonforScheduling);

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

        newCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalConsultationActivity.this, ChiefComplaintActivity.class);
                intent.putExtra("caseType", "Medical");
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void showAppointments() {

        DatabaseHelper db = new DatabaseHelper(MedicalConsultationActivity.this);
        ArrayList<ConsultationModel> appointments = db.getAppointment(new UserModel().getId());

        Typeface semibold = ResourcesCompat.getFont(MedicalConsultationActivity.this, R.font.poppins_semibold);
        Typeface light = ResourcesCompat.getFont(MedicalConsultationActivity.this, R.font.poppins_light);

        LinearLayout cardContainer = findViewById(R.id.appointmentCard);

        LinearLayout.LayoutParams cardLayout;
        cardLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cardLayout.setMargins(0, 20, 0, 0);
        LinearLayout.LayoutParams dividerLayout;
        dividerLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        dividerLayout.setMargins(0, 30, 0, 10);

        LinearLayout.LayoutParams pillButtonLayout;
        pillButtonLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pillButtonLayout.setMargins(10, 10, 10, 10);

        if ( appointments.isEmpty() )
        {
            TextView noData = new TextView(MedicalConsultationActivity.this);
            noData.setText("No data found");
            noData.setPadding(8, 8, 0, 0);
            noData.setTextSize(12);
            noData.setTextColor(Color.parseColor("#2D2926"));
            noData.setTypeface(light);
            noData.setGravity(Gravity.CENTER);

            cardContainer.addView(noData, cardLayout);
        }

        for(ConsultationModel cm : appointments)
        {
            if(cm.getCaseType().equals("Medical")) {

                LinearLayout textContainer = new LinearLayout(MedicalConsultationActivity.this);
                textContainer.setOrientation(LinearLayout.VERTICAL);
                textContainer.setPadding(20, 0, 20, 0);

                LinearLayout pillButtonContainer = new LinearLayout(MedicalConsultationActivity.this);
                pillButtonContainer.setOrientation(LinearLayout.HORIZONTAL);

                TextView caseNumber = new TextView(MedicalConsultationActivity.this);
                caseNumber.setText("#" + cm.getCaseID());
                caseNumber.setPadding(15, 5, 15, 0);
                caseNumber.setTextSize(10);
                caseNumber.setBackgroundColor(Color.GRAY);
                caseNumber.setTextColor(Color.WHITE);
                caseNumber.setTypeface(semibold);

                TextView location = new TextView(MedicalConsultationActivity.this);
                location.setText(cm.getLocation());
                location.setPadding(15, 5, 15, 0);
                location.setTextSize(10);
                if (cm.getLocation().equals("Online")) {
                    location.setBackgroundColor(Color.parseColor("#17A2B8"));
                } else {
                    location.setBackgroundColor(Color.parseColor("#B53471"));
                }

                location.setTextColor(Color.WHITE);
                location.setTypeface(semibold);

                pillButtonContainer.addView(caseNumber, pillButtonLayout);
                pillButtonContainer.addView(location, pillButtonLayout);

                TextView patientName = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan1 = new SpannableString("Patient Name   ");
                textSpan1.setSpan(new TypefaceSpan(semibold), 0, textSpan1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                patientName.setText(textSpan1);
                patientName.append(cm.getName());
                patientName.setPadding(8, 8, 0, 0);
                patientName.setTextSize(12);
                patientName.setTextColor(Color.parseColor("#2D2926"));
                patientName.setTypeface(light);

                TextView caseDetails = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan2 = new SpannableString("Case Details   ");
                textSpan2.setSpan(new TypefaceSpan(semibold), 0, textSpan2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                caseDetails.setText(textSpan2);
                caseDetails.append(cm.getDescription());
                caseDetails.setPadding(8, 8, 0, 0);
                caseDetails.setTextSize(12);
                caseDetails.setTextColor(Color.parseColor("#2D2926"));
                caseDetails.setTypeface(light);

                TextView dateCreated = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan3 = new SpannableString("Date Created   ");
                textSpan3.setSpan(new TypefaceSpan(semibold), 0, textSpan3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                dateCreated.setText(textSpan3);
                dateCreated.append(cm.getDateCreated());
                dateCreated.setPadding(8, 8, 0, 0);
                dateCreated.setTextSize(12);
                dateCreated.setTextColor(Color.parseColor("#2D2926"));
                dateCreated.setTypeface(light);

                TextView timeQueued = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan4 = new SpannableString("Time Queued   ");
                textSpan4.setSpan(new TypefaceSpan(semibold), 0, textSpan4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                timeQueued.setText(textSpan4);
                timeQueued.append(cm.getTimeQueued());
                timeQueued.setPadding(8, 8, 0, 0);
                timeQueued.setTextSize(12);
                timeQueued.setTextColor(Color.parseColor("#2D2926"));
                timeQueued.setTypeface(light);

                TextView queueStatus = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan5 = new SpannableString("Queue Status   ");
                textSpan5.setSpan(new TypefaceSpan(semibold), 0, textSpan5.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Spannable textSpan5_1 = new SpannableString(cm.getQueueStatus());
                textSpan5_1.setSpan(new TypefaceSpan(semibold), 0, textSpan5_1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (cm.getQueueStatus().equals("Done")) {
                    textSpan5_1.setSpan(new ForegroundColorSpan(Color.parseColor("#28a745")), 0, textSpan5_1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    textSpan5_1.setSpan(new ForegroundColorSpan(Color.parseColor("#FDBF15")), 0, textSpan5_1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                queueStatus.setText(textSpan5);
                queueStatus.append(textSpan5_1);
                queueStatus.setPadding(8, 8, 0, 0);
                queueStatus.setTextSize(12);
                queueStatus.setTextColor(Color.parseColor("#2D2926"));
                queueStatus.setTypeface(light);

                TextView attendingDoctor = new TextView(MedicalConsultationActivity.this);
                Spannable textSpan6 = new SpannableString("Attending Doctor   ");
                textSpan6.setSpan(new TypefaceSpan(semibold), 0, textSpan6.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                attendingDoctor.setText(textSpan6);
                attendingDoctor.append(cm.getAttendingDoctor());
                attendingDoctor.setPadding(8, 8, 0, 0);
                attendingDoctor.setTextSize(12);
                attendingDoctor.setTextColor(Color.parseColor("#2D2926"));
                attendingDoctor.setTypeface(light);

                textContainer.addView(pillButtonContainer);
                textContainer.addView(patientName);
                textContainer.addView(caseDetails);
                textContainer.addView(dateCreated);
                textContainer.addView(timeQueued);
                textContainer.addView(queueStatus);
                textContainer.addView(attendingDoctor);

                View divider = new View(MedicalConsultationActivity.this);
                divider.setBackgroundColor(Color.BLACK);

                textContainer.addView(divider, dividerLayout);
                cardContainer.addView(textContainer, cardLayout);

            }
        }
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