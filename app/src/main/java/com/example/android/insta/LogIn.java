package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText userName, password;
    Button signIn, signUp;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUp = findViewById(R.id.logSignUp);
        signIn = findViewById(R.id.logSignIn);
        userName = findViewById(R.id.loguserName);
        password = findViewById(R.id.logPassword);

        signIn.setOnClickListener(LogIn.this);
        signUp.setOnClickListener(LogIn.this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            Intent i = new Intent(getApplicationContext(), SignUp.class);
            startActivity(i);
        } else if (v == signIn) {
            ParseUser.logInInBackground(userName.getText().toString(), password.getText().toString(), new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "LoggedIn", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
