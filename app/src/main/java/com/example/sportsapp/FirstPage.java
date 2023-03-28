package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    ArrayList<Matches> match;
    BottomNavigationView bottomNavigationView;
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

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("buttonStatus",item.getItemId());
                startActivity(intent);
        }
        return true;
    }
}