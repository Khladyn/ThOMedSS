package com.example.thomedss;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thomedss.data.DatabaseHelper;
import com.example.thomedss.data.UserModel;

public class LoginActivity extends AppCompatActivity {

    EditText userID, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userID = findViewById(R.id.userID);
        password = findViewById(R.id.userPass);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel currentUser = new UserModel();

                DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                boolean loginSuccess = db.verifyUser(userID.getText().toString(), password.getText().toString());

                if ( loginSuccess == true ) {

                    currentUser.setId(userID.getText().toString());
                    currentUser.setPassword(password.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, CaseTrackerActivity.class);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "ID or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}