package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.rootSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
        signUp = findViewById(R.id.logSignUp);
        signIn = findViewById(R.id.logSignIn);
        userName = findViewById(R.id.loguserName);
        password = findViewById(R.id.logPassword);
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(signIn);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
//            Intent i = new Intent(getApplicationContext(), SocialMediaActivity.class);
//            startActivity(i);
        }

        signIn.setOnClickListener(LogIn.this);
        signUp.setOnClickListener(LogIn.this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            Intent i = new Intent(getApplicationContext(), SignUp.class);
            startActivity(i);
        } else if (v == signIn) {
            if (userName.getText().toString().equals("")
                    || password.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Fill All", Toast.LENGTH_LONG).show();
            } else {
                signIn.setText("Please Wait");
                signIn.setEnabled(false);
                signIn.setAlpha(0.5f);
                ParseUser.logInInBackground(userName.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            //Toast.makeText(getApplicationContext(), "LoggedIn", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), SocialMediaActivity.class);
                            startActivity(i);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    signIn.setText("SIGN IN");
                                    signIn.setAlpha(1f);
                                    signIn.setEnabled(true);
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }
}
