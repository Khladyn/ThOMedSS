package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.DeclarationModel;
import com.example.thomedss.data.UserModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class HealthDeclarationActivity extends AppCompatActivity {

    Button saveButton, cancelButton;

    RadioGroup[] radioGroups = new RadioGroup[16];
    RadioButton[] radioButtons = new RadioButton[16];
    EditText[] datePicker = new EditText[14];
    EditText
            placeOfWorkVisit,
            temperature;

    Spinner countryOfTravel;

    private int cDate, cMonth, cYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_declaration);

            saveButton = findViewById(R.id.saveButton);
            cancelButton = findViewById(R.id.cancelButton);

            placeOfWorkVisit = findViewById(R.id.placeOfWorkVisit);
            temperature = findViewById(R.id.temperature);
            countryOfTravel = findViewById(R.id.countryOfTravel);

            for ( int rg = 0; rg < radioGroups.length; rg++ )
            {
                    String fieldName = "radioGroup" + (rg+1);
                    int fieldNameID = getResources().getIdentifier(fieldName, "id", getPackageName());
                    radioGroups[rg] = findViewById(fieldNameID);
                    radioButtons[rg] = findViewById(radioGroups[rg].getCheckedRadioButtonId());

                    if ( rg > 11 && rg < 14 )
                    {
                        for ( int i = 0; i < radioGroups[rg].getChildCount(); i++ )
                        {
                            radioGroups[rg].getChildAt(i).setEnabled(false);
                        }
                    }
            }

            for ( int dp = 0; dp < datePicker.length; dp++ )
            {
                    int dp2 = dp;
                    String fieldName = "date" + (dp+1);
                    int fieldNameID = getResources().getIdentifier(fieldName, "id", getPackageName());
                    datePicker[dp] = findViewById(fieldNameID);
                    datePicker[dp].setEnabled(false);

                    datePicker[dp].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

                                    final Calendar cal = Calendar.getInstance();
                                    cDate = cal.get(Calendar.DATE);
                                    cMonth = cal.get(Calendar.MONTH);
                                    cYear = cal.get(Calendar.YEAR);
                                    int dp3 = dp2;

                                    DatePickerDialog dpDialog = new DatePickerDialog(
                                            HealthDeclarationActivity.this, R.style.my_dialog_theme,
                                            new DatePickerDialog.OnDateSetListener() {
                                                    @Override
                                                    public void onDateSet(DatePicker dpd, int year, int month, int date) {
                                                            datePicker[dp3].setText((month+1) + "/" + date + "/"+year);
                                                    }
                                            }, cYear, cMonth, cDate);

                                    dpDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                                    dpDialog.show();
                            }
                    });
            }

        for ( int rb = 0; rb < radioButtons.length; rb++ ) {
            int rb2 = rb;
            radioGroups[rb].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    radioButtons[rb2] = findViewById(radioGroup.getCheckedRadioButtonId());


                    if ( radioButtons[rb2].getText().toString().equals("Yes") ) {

                        Toast.makeText(HealthDeclarationActivity.this, "Please fill in the date field.", Toast.LENGTH_SHORT).show();

                        if ( rb2 > 0 && rb2 < 11 ) {
                            datePicker[rb2 - 1].setEnabled(true);
                            datePicker[rb2 - 1].requestFocus();
                        } else if ( rb2 == 11 ) {
                            datePicker[rb2 - 1].setEnabled(true);
                            radioGroups[rb2 + 1].getChildAt(0).setEnabled(true);
                            radioGroups[rb2 + 1].getChildAt(1).setEnabled(true);
                            radioGroups[rb2 + 2].getChildAt(0).setEnabled(true);
                            radioGroups[rb2 + 2].getChildAt(1).setEnabled(true);
                        } else if ( rb2 == 14 ) {
                            datePicker[rb2 - 3].setEnabled(true);
                        } else if ( rb2 == 15 ) {
                            datePicker[rb2 - 2].setEnabled(true);
                            datePicker[rb2 - 3].setEnabled(true);
                            countryOfTravel.setEnabled(true);
                        }

                    } else {

                        if ( rb2 > 0 && rb2 < 11 ) {
                            datePicker[rb2 - 1].setEnabled(false);
                            datePicker[rb2 - 1].setText(null);
                        } else if ( rb2 == 11 ) {
                            datePicker[rb2 - 1].setEnabled(false);
                            datePicker[rb2 - 1].setText(null);
                            radioButtons[12] = findViewById(R.id.radioNo13);
                            radioButtons[12].setChecked(true);
                            radioGroups[rb2 + 1].getChildAt(0).setEnabled(false);
                            radioGroups[rb2 + 1].getChildAt(1).setEnabled(false);
                            radioButtons[13] = findViewById(R.id.radioNo14);
                            radioButtons[13].setChecked(true);
                            radioGroups[rb2 + 2].getChildAt(0).setEnabled(false);
                            radioGroups[rb2 + 2].getChildAt(1).setEnabled(false);
                        } else if ( rb2 == 14 ) {
                            datePicker[rb2 - 3].setEnabled(false);
                            datePicker[rb2 - 3].setText(null);
                        } else if ( rb2 == 15 ) {
                            datePicker[rb2 - 2].setEnabled(false);
                            datePicker[rb2 - 3].setEnabled(false);
                            datePicker[rb2 - 2].setText(null);
                            datePicker[rb2 - 3].setText(null);
                            countryOfTravel.setEnabled(false);
                        }
                    }
                }
            });
        }

        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }

        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(HealthDeclarationActivity.this,
                android.R.layout.simple_spinner_item, countries);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryOfTravel.setAdapter(countryAdapter);
        countryOfTravel.setEnabled(false);

        saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                DeclarationModel declaration = null;
                                Intent intent = getIntent();

                                if ( placeOfWorkVisit.getText().toString().isEmpty() ) {
                                    Toast.makeText(HealthDeclarationActivity.this, "Place of Work or Visit is required", Toast.LENGTH_SHORT).show();
                                } else if ( temperature.getText().toString().isEmpty() ) {
                                    Toast.makeText(HealthDeclarationActivity.this, "Temperature is required", Toast.LENGTH_SHORT).show();
                                } else {

                                    try {
                                        declaration = new DeclarationModel(
                                                new UserModel().getId(),
                                                intent.getStringExtra("checklistDate"),
                                                radioButtons[0].getText().toString(),
                                                placeOfWorkVisit.getText().toString(),
                                                temperature.getText().toString(),
                                                radioButtons[1].getText().toString(),
                                                datePicker[0].getText().toString(),
                                                radioButtons[2].getText().toString(),
                                                datePicker[1].getText().toString(),
                                                radioButtons[3].getText().toString(),
                                                datePicker[2].getText().toString(),
                                                radioButtons[4].getText().toString(),
                                                datePicker[3].getText().toString(),
                                                radioButtons[5].getText().toString(),
                                                datePicker[4].getText().toString(),
                                                radioButtons[6].getText().toString(),
                                                datePicker[5].getText().toString(),
                                                radioButtons[7].getText().toString(),
                                                datePicker[6].getText().toString(),
                                                radioButtons[8].getText().toString(),
                                                datePicker[7].getText().toString(),
                                                radioButtons[9].getText().toString(),
                                                datePicker[8].getText().toString(),
                                                radioButtons[10].getText().toString(),
                                                datePicker[9].getText().toString(),
                                                radioButtons[11].getText().toString(),
                                                datePicker[10].getText().toString(),
                                                radioButtons[12].getText().toString(),
                                                radioButtons[13].getText().toString(),
                                                radioButtons[14].getText().toString(),
                                                datePicker[11].getText().toString(),
                                                radioButtons[15].getText().toString(),
                                                datePicker[12].getText().toString(),
                                                datePicker[13].getText().toString(),
                                                countryOfTravel.getSelectedItem().toString()
                                        );
                                    } catch (Exception e) {
                                        Toast.makeText(HealthDeclarationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    DatabaseHelper db = new DatabaseHelper(HealthDeclarationActivity.this);
                                    boolean updateSuccess = db.createDeclaration(declaration);

                                    if ( updateSuccess == true ) {
                                        Toast.makeText(HealthDeclarationActivity.this, "Health Declaration created!", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(HealthDeclarationActivity.this, CaseTrackerActivity.class);
                                        finish();

                                    } else {
                                        Toast.makeText(HealthDeclarationActivity.this, "Cannot create Health Declaration", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        }
                });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}