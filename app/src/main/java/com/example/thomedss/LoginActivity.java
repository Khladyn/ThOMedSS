package com.example.thomedss;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//          pamparemove lang ng time and battery stuff sa taas uncomment nyo lang if g kayo
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();


        button= findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                openHomePage();
            }
        });

    }
    public void openHomePage(){
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
    }
}