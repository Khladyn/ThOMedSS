package com.example.thomedss;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private Timer timer;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        ActionBar actionBar= getSupportActionBar();
////        actionBar.hide();


        progressBar = findViewById(R.id.progressBar);
        progressBar.setScaleY(7f);
        progressBar.getProgressDrawable().setColorFilter(
                Color.rgb(0,206,209), android.graphics.PorterDuff.Mode.SRC_IN
        );

        timer=new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {

                               if(i<100){
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {

                                       }
                                   });
                                   progressBar.setProgress(i);
                                   i++;
                               }else
                               {
                                   timer.cancel();
                                   Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                   startActivity(intent);
                                   finish();
                               }

                           }
                       }, 0, 40
        );

    }
}
