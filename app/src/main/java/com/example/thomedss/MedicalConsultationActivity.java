package com.example.thomedss;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
        showAppointments();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_medical_consultation);

        addActionButtons();
        showAppointments();
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
            if (cm.getLocation().equals("Online"))
            {
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

//            TranslateAnimation scale = new TranslateAnimation(0, 0,20, 0);
//            scale.setDuration(500);
//            textContainer.startAnimation(scale);

            View divider = new View(MedicalConsultationActivity.this);
            divider.setBackgroundColor(Color.BLACK);

            textContainer.addView(divider, dividerLayout);
            cardContainer.addView(textContainer, cardLayout);

        }
    }
}