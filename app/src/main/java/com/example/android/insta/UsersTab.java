package com.example.android.insta;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;

    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;

    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_users_tab, container, false);
        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setOnItemClickListener(this);
        //Importing Users
        final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                TextView loadingUser = view.findViewById(R.id.loadingUser);
                if (e == null) {
                    if (users.size() > 0) {
                        for (ParseUser user : users) {
                            arrayList.add(user.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
                        loadingUser.setVisibility(view.GONE);
                        listView.setVisibility(view.VISIBLE);
                    }
                }
            }
        });

        listView.setOnItemLongClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getContext(), UsersPost.class);
        i.putExtra("username", arrayList.get(position));
        startActivity(i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereEqualTo("username", arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (object != null && e == null) {
                    //Toast.makeText(getContext(),"Info",Toast.LENGTH_LONG).show();
                    final PrettyDialog prettyDialog = new PrettyDialog(getContext());
                    prettyDialog.setTitle(object.getUsername() + "'s Info")
                            .setMessage(object.get("Bio") + "\n"
                                    + object.get("Profession") + "\n"
                                    + object.get("Hobbie") + "\n"
                                    + object.get("Sport"))
                            .setIcon(R.drawable.ic_person_black_24dp)
                            .addButton("OK",
                                    R.color.pdlg_color_white,
                                    R.color.pdlg_color_green,
                                    new PrettyDialogCallback() {
                                        @Override
                                        public void onClick() {
                                            prettyDialog.dismiss();
                                        }
                                    }).show();
                }
            }
        });
        return true;
    }
}
