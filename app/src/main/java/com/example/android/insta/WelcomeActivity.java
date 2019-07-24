package com.example.android.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    TextView welcome;
    Button logOut;
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcome = findViewById(R.id.welcome);
        logOut = findViewById(R.id.logOut);
        welcome.setText("Welcome " + ParseUser.getCurrentUser().getUsername());
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getApplicationContext(), SignUpLoginActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "LoggedOut", Toast.LENGTH_LONG).show();
            }
        });
    }
}
