package com.mobileassignment3.parcel_tracking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //will hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //hide the title bar
        getSupportActionBar().hide();

        //show the activity in full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //change activity background color
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        // add a timer that will trigger next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000); //means 3 seconds
    }
}
