package com.example.android.insta;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    EditText name, bio, profession, hobbies, sport;
    Button updateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        name = view.findViewById(R.id.name);
        bio = view.findViewById(R.id.bio);
        profession = view.findViewById(R.id.profession);
        hobbies = view.findViewById(R.id.hobbie);
        sport = view.findViewById(R.id.sport);
        updateInfo = view.findViewById(R.id.updateProfile);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        try {
            //get data
            name.setText(parseUser.get("ProfileName").toString());
            bio.setText(parseUser.get("Bio").toString());
            profession.setText(parseUser.get("Profession").toString());
            hobbies.setText(parseUser.get("Hobbie").toString());
            sport.setText(parseUser.get("Sport").toString());
        } catch (NullPointerException e) {
            Log.i("profileTab", e + "");
        }
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("ProfileName", name.getText().toString());
                parseUser.put("Bio", bio.getText().toString());
                parseUser.put("Profession", profession.getText().toString());
                parseUser.put("Sport", sport.getText().toString());
                parseUser.put("Hobbie", hobbies.getText().toString());
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "Info Updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Failed " + e, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        return view;
    }

}
