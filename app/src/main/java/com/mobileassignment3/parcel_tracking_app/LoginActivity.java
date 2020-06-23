package com.mobileassignment3.parcel_tracking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView usernameEditText;
    TextView passwordEditText;

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

       usernameEditText = findViewById(R.id.etLoginUsername);


        passwordEditText = findViewById(R.id.etLoginPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                loginUser(username,password);

            }
        });


    }

    private void loginUser(String username, String password) {
            if (username.equals("admin") && password.equals("admin")){

                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
    //             Intent myIntent = new Intent(LoginActivity.this, DriverMainActivity.class);
    //                Intent myIntent = new Intent(LoginActivity.this, ReceiverMainActivity.class);
                startActivity(myIntent);
            }else{
                Toast.makeText(this, "COULD NOT LOGIN got this :un: "+username+"pwd: "+ password, Toast.LENGTH_SHORT).show();
            }
        }




}
