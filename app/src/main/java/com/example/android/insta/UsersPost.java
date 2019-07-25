package com.example.android.insta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UsersPost extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        linearLayout = findViewById(R.id.linearLayout);

        final Intent objectRecived = getIntent();
        final String recivedUsername = objectRecived.getStringExtra("username");

        setTitle(recivedUsername + "'s Posts");
        final ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("username", recivedUsername);
        parseQuery.orderByDescending("createdAt");

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null) {
                    for (final ParseObject post : objects) {
                        final TextView imgDesc = new TextView(UsersPost.this);
                        imgDesc.setText(post.get("image_des") + "");
                        ParseFile parseFile = (ParseFile) post.get("Picture");
                        parseFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (data != null && e == null) {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    ImageView postImageView = new ImageView(UsersPost.this);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(8, 8, 8, 8);
                                    postImageView.setLayoutParams(params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams params_post = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params_post.setMargins(8, 8, 8, 8);
                                    imgDesc.setLayoutParams(params_post);
                                    imgDesc.setTextSize(30);
                                    imgDesc.setGravity(Gravity.CENTER);
                                    imgDesc.setBackgroundColor(Color.BLUE);
                                    imgDesc.setTextColor(Color.WHITE);

                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(imgDesc);
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(UsersPost.this, "No post so far", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }


}
