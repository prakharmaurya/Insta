package com.example.android.insta;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    TextView welcome;

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcome = findViewById(R.id.welcome);
        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "LoggedOut", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
