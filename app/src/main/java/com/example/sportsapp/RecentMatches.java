package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class RecentMatches extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    ArrayList<Recent> match;
    BottomNavigationView bottomNavigationView;
    RecyclerView rvMatches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_matches);
        rvMatches=(RecyclerView) findViewById(R.id.recentMatches);

        // Initialize contacts
        match = Recent.createContactsList();
        // Create adapter passing in the sample user data
        RecentAdapter adapter = new RecentAdapter(match);
        // Attach the adapter to the recyclerview to populate items
        rvMatches.setAdapter(adapter);
        // Set layout manager to position the items
        rvMatches.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.recent1);
        bottomNavigationView.setOnItemSelectedListener(this);
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile: {
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                finish();
                break;
            }
            case R.id.upcoming: {
                Intent intent1 = new Intent(this, FirstPage.class);
                startActivity(intent1);
                finish();
                break;
            }
            case R.id.news:{
                Intent intent2 = new Intent(this, NewsFeed.class);
                intent2.putExtra("buttonStatus", item.getItemId());
                startActivity(intent2);
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