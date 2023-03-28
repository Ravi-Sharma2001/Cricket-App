package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity {
    ArrayList<Matches> match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        RecyclerView rvMatches = (RecyclerView) findViewById(R.id.rvMatches);

        // Initialize contacts
        match = Matches.createContactsList();
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(match);
        // Attach the adapter to the recyclerview to populate items
        rvMatches.setAdapter(adapter);
        // Set layout manager to position the items
        rvMatches.setLayoutManager(new LinearLayoutManager(this));
    }
}