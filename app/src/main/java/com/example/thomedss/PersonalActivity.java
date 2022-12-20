package com.example.thomedss;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
//        UserModel thisUser = new UserModel();
        UserModel currentUser = db.getUser(new UserModel().getId());

        int photoID = getResources().getIdentifier(currentUser.getPhoto(), "drawable", getPackageName());
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
                    Toast.makeText(PersonalActivity.this, "Personal Information Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PersonalActivity.this, "Cannot update Personal Information", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}