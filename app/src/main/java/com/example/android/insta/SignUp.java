package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText userName, password, email;
    Button signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        androidx.appcompat.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Sign Up");

        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            ParseUser user = new ParseUser();
            user.setUsername(userName.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Loggedin", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (v == signIn) {
            Toast.makeText(getApplicationContext(), "signIn", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), LogIn.class);
            startActivity(i);
        }
    }
}
