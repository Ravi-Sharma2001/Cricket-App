package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    ArrayList<Matches> match;
    BottomNavigationView bottomNavigationView;
    RecyclerView rvMatches;
    JSONObject MatchList;
    CardView c;


    @SuppressLint("NotConstructor")
    public FirstPage()
    {

    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.upcoming);
        bottomNavigationView.setOnItemSelectedListener(this);
        c=findViewById(R.id.UpcomingCard);
        match=new ArrayList<>();

        try {
            callAPI();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void callAPI() throws JSONException {

        ArrayList<List<String>> match_detail1=new ArrayList<List<String>>();
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/getUpcoming"; // <----enter your post url here
        JSONObject object = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object,
                (response -> {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response;
                        JSONArray array = new JSONArray();
                        array = jsonObject.getJSONArray("val");
                        MatchList  = new JSONObject();
                        for(int i=0;i<array.length();i++) {
                            MatchList = array.getJSONObject(i);
                            Matches match1=new Matches(
                            MatchList.getString("t1_name"),
                            MatchList.getString("t2_name"),
                            MatchList.getString("date"),
                            MatchList.getString("location"),
                            MatchList.getString("match_id"),
                            MatchList.getString("t1_players"),
                            MatchList.getString("t2_players"),
                            MatchList.getString("team1_prob"),
                            MatchList.getString("team2_prob"),
                                    MatchList.getString("team1_id"),
                                    MatchList.getString("team2_id")
                            );
                            match.add(match1);
                        }
                        rvMatches=(RecyclerView) findViewById(R.id.rvMatches);

                        // Initialize contacts

                        // Create adapter passing in the sample user data
                        ContactsAdapter adapter = new ContactsAdapter(match);

                        // Attach the adapter to the recyclerview to populate items
                        rvMatches.setAdapter(adapter);
                        // Set layout manager to position the items
                        rvMatches.setLayoutManager(new LinearLayoutManager(this));
                        rvMatches.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, rvMatches ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Intent i=new Intent(FirstPage.this, UpcomingMatchStats.class);
                                        Log.w("Intent List", "onItemClick: "+match.get(position).Team1_Players()+match.get(position).Team2_Players());
                                        i.putExtra("Team1",match.get(position).getHome());
                                        i.putExtra("Team2",match.get(position).getAway());
                                        i.putExtra("Date",match.get(position).getDate());
                                        i.putExtra("Location",match.get(position).getStadium());
                                        i.putExtra("Team1_players",match.get(position).Team1_Players());
                                        i.putExtra("Team2_players",match.get(position).Team2_Players());
                                        i.putExtra("Team1_prob",match.get(position).Team1_Prob());
                                        i.putExtra("Team2_prob",match.get(position).Team2_Prob());
                                        i.putExtra("Match_ID",match.get(position).getMatchID());
                                        i.putExtra("Team1_ID",match.get(position).Team1_ID());
                                        i.putExtra("Team2_ID",match.get(position).Team2_ID());
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onItemLongClick(View view, int position) {

                                    }
                                })
                        );

//                        Log.d("api",String.valueOf(response));
                        Log.d("api","final obj:"+MatchList);
//                         String error = response.getString("httpStatus");


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                })
                ,(error -> {
            Log.e("Error","Error"+error.getMessage());
        }));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
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

}