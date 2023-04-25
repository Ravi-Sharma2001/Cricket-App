package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    ArrayList<Matches> match;
    BottomNavigationView bottomNavigationView;
    RecyclerView rvMatches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        rvMatches=(RecyclerView) findViewById(R.id.rvMatches);

        // Initialize contacts
        match = Matches.createContactsList();
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(match);
        // Attach the adapter to the recyclerview to populate items
        rvMatches.setAdapter(adapter);
        // Set layout manager to position the items
        rvMatches.setLayoutManager(new LinearLayoutManager(this));
        rvMatches.setOnClickListener(view -> {
        Intent i=new Intent(getApplicationContext(),UpcomingMatchStats.class);
        startActivity(i);
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.upcoming);
        bottomNavigationView.setOnItemSelectedListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile: {
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                break;
            }
            case R.id.news:{
                Intent intent = new Intent(this, NewsFeed.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                break;
            }
            case R.id.recent1:{
                Intent intent1 = new Intent(this, RecentMatches.class);
                startActivity(intent1);
                finish();
                break;
             }
        }
        return true;
    }
    public void Stats(View view)
    {
        Intent i=new Intent(getApplicationContext(),UpcomingMatchStats.class);
        startActivity(i);

    }

}