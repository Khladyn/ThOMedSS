package com.example.thomedss;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomedss.data.ConsultationModel;
import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CaseTrackerActivity extends AppCompatActivity {

    Button declarationButton;
    EditText datePicker;
    String checklistDate;
    int cDate, cMonth, cYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_tracker);

        addActionButtons();
        showDeclaration();

        showNavBar(CaseTrackerActivity.this);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_case_tracker);

        addActionButtons();
        showDeclaration();

        showNavBar(CaseTrackerActivity.this);

    }

    private void addActionButtons() {

        declarationButton = findViewById(R.id.declarationButton);
        datePicker = findViewById(R.id.datePicker);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

                final Calendar cal = Calendar.getInstance();
                cDate = cal.get(Calendar.DATE);
                cMonth = cal.get(Calendar.MONTH);
                cYear = cal.get(Calendar.YEAR);

                DatePickerDialog dpDialog = new DatePickerDialog(
                        CaseTrackerActivity.this, R.style.my_dialog_theme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dpd, int year, int month, int date) {
                                datePicker.setText((month+1) + "/" + date + "/"+year);
                                checklistDate = datePicker.getText().toString();
                                declarationButton.setEnabled(true);
                            }
                        }, cYear, cMonth, cDate);

                dpDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpDialog.show();
            }
        });

            declarationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(CaseTrackerActivity.this, HealthDeclarationActivity.class);
                    intent.putExtra("checklistDate", checklistDate);
                    startActivity(intent);
                }
            });
    }

    private void showDeclaration() {

        String currentDate = new SimpleDateFormat("MM/dd/yyyy",
                Locale.getDefault()).format(new Date());

        DatabaseHelper db = new DatabaseHelper(CaseTrackerActivity.this);
        Boolean declarationExists = db.verifyDeclaration(new UserModel().getId(), currentDate); //TO CHANGE

        Typeface bold = ResourcesCompat.getFont(CaseTrackerActivity.this, R.font.poppins_bold);
        Typeface semibold = ResourcesCompat.getFont(CaseTrackerActivity.this, R.font.poppins_semibold);
        Typeface light = ResourcesCompat.getFont(CaseTrackerActivity.this, R.font.poppins_light);

        LinearLayout caseContainer = findViewById(R.id.caseContainer);

        LinearLayout.LayoutParams cardLayout;
        cardLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardLayout.setMargins(45, 45, 45, 0);

        LinearLayout declarationCard = new LinearLayout(CaseTrackerActivity.this);
        declarationCard.setOrientation(LinearLayout.VERTICAL);
        declarationCard.setBackgroundColor(Color.parseColor("#28a745"));
        declarationCard.setPadding(40, 40, 40, 40);

        if ( declarationExists == false )
        {
            TextView noData = new TextView(CaseTrackerActivity.this);
            noData.setText("No data found");
            noData.setPadding(8, 8, 0, 0);
            noData.setTextSize(12);
            noData.setTextColor(Color.parseColor("#2D2926"));
            noData.setTypeface(light);
            noData.setGravity(Gravity.CENTER);

            caseContainer.addView(noData, cardLayout);

        } else {
            UserModel declaration = db.getDeclaration(new UserModel().getId());

            TextView result = new TextView(CaseTrackerActivity.this);
            result.setPadding(0, 0, 0, 20);
            result.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_thumb_up_24, 0, 0, 0);
            result.setCompoundDrawablePadding(20);
            result.setTypeface(bold);
            result.setTextColor(Color.WHITE);
            result.setText("You are good to go!");
            result.setTextSize(20);

            TextView dateToday = new TextView(CaseTrackerActivity.this);
            dateToday.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_calendar_today_24, 0, 0, 0);
            dateToday.setCompoundDrawablePadding(20);
            dateToday.setTypeface(semibold);
            dateToday.setTextColor(Color.WHITE);
            dateToday.setText(currentDate);

            TextView studentID = new TextView(CaseTrackerActivity.this);
            studentID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_assignment_ind_24, 0, 0, 0);
            studentID.setCompoundDrawablePadding(20);
            studentID.setTypeface(semibold);
            studentID.setTextColor(Color.WHITE);
            studentID.setText(declaration.getId());

            TextView studentName = new TextView(CaseTrackerActivity.this);
            studentName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_person_24, 0, 0, 0);
            studentName.setCompoundDrawablePadding(20);
            studentName.setTypeface(semibold);
            studentName.setTextColor(Color.WHITE);
            studentName.setText(declaration.getName());

            TextView personType = new TextView(CaseTrackerActivity.this);
            personType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_business_center_24, 0, 0, 0);
            personType.setCompoundDrawablePadding(20);
            personType.setTypeface(semibold);
            personType.setTextColor(Color.WHITE);
            personType.setText(declaration.getType());

            TextView college = new TextView(CaseTrackerActivity.this);
            college.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_location_city_24, 0, 0, 0);
            college.setCompoundDrawablePadding(20);
            college.setTypeface(semibold);
            college.setTextColor(Color.WHITE);
            college.setText(declaration.getCollege());

            TextView instruction = new TextView(CaseTrackerActivity.this);
            instruction.setPadding(0, 40, 0, 0);
            instruction.setTypeface(light);
            instruction.setTextColor(Color.WHITE);
            instruction.setText("Please show this section to the authorized personnel when entering the University premises.");

            declarationCard.addView(result);
            declarationCard.addView(dateToday);
            declarationCard.addView(studentID);
            declarationCard.addView(studentName);
            declarationCard.addView(personType);
            declarationCard.addView(college);
            declarationCard.addView(instruction);

            caseContainer.addView(declarationCard, cardLayout);

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
//        childLayout3.setMargins(800, 0, 0, 0);

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