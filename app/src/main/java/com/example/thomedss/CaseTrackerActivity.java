package com.example.thomedss;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_case_tracker);

        addActionButtons();
        showDeclaration();
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
        Boolean declarationExists = db.getDeclaration(new UserModel().getId(), currentDate); //TO CHANGE

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
            UserModel declaration = new UserModel();

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
            dateToday.setText("12/20/2022");

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
}