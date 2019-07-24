package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {
    TextView loginUsr, loginPass, signUpUsr, signUpPass;
    Button loginBtn, signUpBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        loginBtn = findViewById(R.id.loginButton);
        loginPass = findViewById(R.id.loginPass);
        loginUsr = findViewById(R.id.loginUsr);

        signUpBtn = findViewById(R.id.signUpBtn);
        signUpPass = findViewById(R.id.signUpPass);
        signUpUsr = findViewById(R.id.signUpUserName);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();
                user.setUsername(signUpUsr.getText().toString());
                user.setPassword(signUpPass.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "SignedUp", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(loginUsr.getText().toString(), loginPass.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getApplicationContext(), "LoggedIn", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
