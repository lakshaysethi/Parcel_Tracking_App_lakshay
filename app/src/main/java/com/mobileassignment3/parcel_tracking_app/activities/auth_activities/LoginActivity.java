package com.mobileassignment3.parcel_tracking_app.activities.auth_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileassignment3.parcel_tracking_app.FirebaseController;
import com.mobileassignment3.parcel_tracking_app.SignupActivity;
import com.mobileassignment3.parcel_tracking_app.activities.main_activities.AdminMainActivity;
import com.mobileassignment3.parcel_tracking_app.R;

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

    private void loginUser(String email, String password) {

       new FirebaseController().loginUser(this,email,password);

    }




}
