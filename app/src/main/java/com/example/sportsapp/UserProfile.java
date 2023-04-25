package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserProfile extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView, bottomNav2;
    ArrayList<MatchHistory> Matches;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView t=findViewById(R.id.textView7);
            t.setText(user.getEmail());
        }
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

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.news:{
                Intent intent = new Intent(this, NewsFeed.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                break;
            }
            case R.id.upcoming:
                Intent intent = new Intent(this, FirstPage.class);
                startActivity(intent);
                finish();
                break;
            case R.id.recent1:
                Intent intent1 = new Intent(this, RecentMatches.class);
                startActivity(intent1);
                finish();
                break;
        }
        return true;
    }
}