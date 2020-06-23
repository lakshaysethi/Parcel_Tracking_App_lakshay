package com.mobileassignment3.parcel_tracking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //will hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hide the title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        TextView tvLoginSignup = findViewById(R.id.tvLoginSignup);
        Button btnLogin = findViewById(R.id.btnLogin);

        tvLoginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(myIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
             Intent myIntent = new Intent(LoginActivity.this, DriverMainActivity.class);
//                Intent myIntent = new Intent(LoginActivity.this, ReceiverMainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
