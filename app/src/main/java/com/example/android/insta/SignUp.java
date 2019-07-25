package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        setTitle("Sign Up");

        findViewById(R.id.root_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(signUp);
                }
                return false;
            }
        });
        email = findViewById(R.id.email);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            if (email.getText().toString().equals("") || userName.getText().toString().equals("")
                    || password.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Fill all info", Toast.LENGTH_LONG).show();
            } else {
                ParseUser user = new ParseUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                signUp.setText("Please Wait");
                signUp.setAlpha(0.5f);
                signUp.setEnabled(false);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            //Toast.makeText(getApplicationContext(), "Loggedin", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), SocialMediaActivity.class);
                            startActivity(i);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    signUp.setText("SIGN IN");
                                    signUp.setAlpha(1f);
                                    signUp.setEnabled(true);
                                    finish();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } else if (v == signIn) {
            Toast.makeText(getApplicationContext(), "signIn", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), LogIn.class);
            startActivity(i);
        }
    }
}