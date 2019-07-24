package com.example.android.insta;
import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("X91zvQWMMwTHMenX7E9gP6eV5EKxd2qykd3ydU2F")
                // if defined
                .clientKey("OjGcwttVHN5yZUKAmfobxMT2mWcNFY8Taw14wY0B")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}