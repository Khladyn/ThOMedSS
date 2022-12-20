package com.example.thomedss;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.UserModel;

public class PersonalActivity extends AppCompatActivity {

    Button update_btn;
    ImageView studentPhoto;
    TextView
            studentName, studentID,
            sex, age, personType, collegeOrDepartment, program, civilStatus, citizenship, religion;
    EditText
            primaryMobileNo, otherContactNo, email, residence, facebookUsername,
            contactPerson, relationToContactPerson, contactPersonPrimaryContactNo, contactPersonEmail, contactPersonAddress, contactPersonZipCode;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        update_btn = findViewById(R.id.updatePersonalAndContactInfo);

        //OVERVIEW
        studentPhoto = findViewById(R.id.studentPhoto);
        studentName = findViewById(R.id.studentName);
        studentID = findViewById(R.id.studentID);

        //PERSONAL INFO
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        personType = findViewById(R.id.personType);
        collegeOrDepartment = findViewById(R.id.collegeOrDepartment);
        program = findViewById(R.id.program);
        civilStatus = findViewById(R.id.civilStatus);
        citizenship = findViewById(R.id.citizenship);
        religion = findViewById(R.id.religion);

        //CONTACT INFO
        primaryMobileNo = findViewById(R.id.primaryMobileNo);
        otherContactNo = findViewById(R.id.otherContactNo);
        email = findViewById(R.id.email);
        residence = findViewById(R.id.residence);
        facebookUsername = findViewById(R.id.facebookUsername);

        //EMERGENCY INFO
        contactPerson = findViewById(R.id.contactPerson);
        relationToContactPerson = findViewById(R.id.relationToContactPerson);
        contactPersonPrimaryContactNo = findViewById(R.id.contactPersonPrimaryContactNo);
        contactPersonEmail = findViewById(R.id.contactPersonEmail);
        contactPersonAddress = findViewById(R.id.contactPersonAddress);
        contactPersonZipCode = findViewById(R.id.contactPersonZipCode);

        //USER OVERVIEW
        DatabaseHelper db = new DatabaseHelper(PersonalActivity.this);
        UserModel currentUser = db.getUser(new UserModel().getId());

        String fileSource = currentUser.getPhoto();
        String fileName = fileSource.substring(0, fileSource.length()-4);

        int photoID = getResources().getIdentifier(fileName, "drawable", getPackageName());
        Drawable userPhoto = getResources().getDrawable(photoID);

        studentID.setText(currentUser.getId());
        studentName.setText(currentUser.getName());
        studentPhoto.setImageDrawable(userPhoto);
        sex.setText(currentUser.getSex());
        age.setText(currentUser.getAge());
        personType.setText(currentUser.getType());
        collegeOrDepartment.setText(currentUser.getCollege());
        program.setText(currentUser.getProgram());
        civilStatus.setText(currentUser.getCivil());
        citizenship.setText(currentUser.getCitizenship());
        religion.setText(currentUser.getReligion());

        primaryMobileNo.setText(currentUser.getMobile());
        otherContactNo.setText(currentUser.getOther_mobile());
        email.setText(currentUser.getEmail());
        residence.setText(currentUser.getEmail());
        facebookUsername.setText(currentUser.getFacebook());

        contactPerson.setText(currentUser.getC_person());
        relationToContactPerson.setText(currentUser.getC_relationship());
        contactPersonPrimaryContactNo.setText(currentUser.getC_primary());
        contactPersonEmail.setText(currentUser.getC_email());
        contactPersonAddress.setText(currentUser.getC_address());
        contactPersonZipCode.setText(currentUser.getC_zipcode());

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel personalUpdate = null;
                
                try {
                    personalUpdate = new UserModel(
                            studentID.getText().toString(),
                            primaryMobileNo.getText().toString(),
                            otherContactNo.getText().toString(),
                            email.getText().toString(),
                            residence.getText().toString(),
                            facebookUsername.getText().toString(),
                            contactPerson.getText().toString(),
                            relationToContactPerson.getText().toString(),
                            contactPersonPrimaryContactNo.getText().toString(),
                            contactPersonEmail.getText().toString(),
                            contactPersonAddress.getText().toString(),
                            contactPersonZipCode.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(PersonalActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper db = new DatabaseHelper(PersonalActivity.this);
                boolean updateSuccess = db.updateUser(personalUpdate);

                if ( updateSuccess == true ) {
                    Toast.makeText(PersonalActivity.this, "Personal Information successfully updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PersonalActivity.this, "Cannot update Personal Information", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showNavBar(PersonalActivity.this);
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