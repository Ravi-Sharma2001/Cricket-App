package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import java.io.*;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;



public class UserProfile extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView, bottomNav2;
    ArrayList<MatchHistory> Matches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(this);

        RecyclerView matchHistory = (RecyclerView) findViewById(R.id.matchHistory);

        // Initialize contacts
        Matches = MatchHistory.createContactsList();
        // Create adapter passing in the sample user data
        HistoryAdapter adapter = new HistoryAdapter(Matches);
        // Attach the adapter to the recyclerview to populate items
        matchHistory.setAdapter(adapter);
        // Set layout manager to position the items
        matchHistory.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recent: {
                Intent intent = new Intent(this, FirstPage.class);
                startActivity(intent);
                break;
            }
            case R.id.upcoming: {
                Intent intent = new Intent(this, FirstPage.class);
                startActivity(intent);
                break;
            }
            case R.id.news:{
                Intent intent = new Intent(this, NewsFeed.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                break;
            }
        }
        return true;
    }
}