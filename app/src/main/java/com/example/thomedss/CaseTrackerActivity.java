package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CaseTrackerActivity extends AppCompatActivity {

    Button declarationButton;
    EditText datePicker;
    String checklistDate;
    int cDate, cMonth, cYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_tracker);

        declarationButton = findViewById(R.id.declarationButton);
        datePicker = findViewById(R.id.checklistDate);

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
                            }
                        }, cYear, cMonth, cDate);

                dpDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpDialog.show();
            }
        });

        declarationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checklistDate = datePicker.getText().toString();

                Intent intent = new Intent(CaseTrackerActivity.this, HealthDeclarationActivity.class);
                intent.putExtra("checklistDate", checklistDate);
                startActivity(intent);
            }
        });

    }
}