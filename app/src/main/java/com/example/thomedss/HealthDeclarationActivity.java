package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

        showNavBar(HealthDeclarationActivity.this);

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