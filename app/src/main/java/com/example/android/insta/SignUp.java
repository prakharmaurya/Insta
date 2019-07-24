package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void tapped(View view) {
        Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
        startActivity(intent);
    }
}
