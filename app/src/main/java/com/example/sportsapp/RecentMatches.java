package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecentMatches extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    ArrayList<Recent> Rmatch;
    BottomNavigationView bottomNavigationView;
    RecyclerView rvMatches;
    SearchView sv;
    JSONObject MatchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_matches);

        sv=findViewById(R.id.searchView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.recent1);
        bottomNavigationView.setOnItemSelectedListener(this);
        Rmatch=new ArrayList<>();
        try {
            callAPI();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals(""))
                {
                    Log.d("SearchBar", "Empty ");
                    try {
                        callAPI();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    newText=newText.toUpperCase();
                    try {
                        callAPI2(newText);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }

            public void callSearch(String query) {
                //Do searching
            }

        });
    }

    public void callAPI() throws JSONException {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/getCompleted"; // <----enter your post url here
        JSONObject object = new JSONObject();
        Rmatch.clear();
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
                            Recent match2=new Recent(
                            MatchList.getString("t1_name"),
                            MatchList.getString("t2_name"),
                            MatchList.getString("date"),
                            MatchList.getString("description"),
                            MatchList.getString("match_id"),
                            MatchList.getString("score_1"),
                            MatchList.getString("score_2"),
                            MatchList.getString("toss_winner"),
                            MatchList.getString("location")
                            );
                            Log.w("Data", "callAPI: "+MatchList );
                            Rmatch.add(match2);
                        }
                        rvMatches=(RecyclerView) findViewById(R.id.recentMatches);

                        // Initialize contacts

                        // Create adapter passing in the sample user data
                        RecentAdapter adapter = new RecentAdapter(Rmatch);
                        // Attach the adapter to the recyclerview to populate items
                        rvMatches.setAdapter(adapter);
                        // Set layout manager to position the items
                        rvMatches.setLayoutManager(new LinearLayoutManager(this));
                        rvMatches.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, rvMatches ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Intent i=new Intent(RecentMatches.this, CompletedMatchStats.class);

                                        i.putExtra("Team1",Rmatch.get(position).getHome());
                                        i.putExtra("Team2",Rmatch.get(position).getAway());
                                        i.putExtra("Date",Rmatch.get(position).getDate());
                                        i.putExtra("Location",Rmatch.get(position).getLocation());
                                        i.putExtra("Result",Rmatch.get(position).getResult());
                                        i.putExtra("Score1",Rmatch.get(position).getScore1());
                                        i.putExtra("Score2",Rmatch.get(position).getScore2());
                                        i.putExtra("TossWinner",Rmatch.get(position).getTossWinner());
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


    public void callAPI2(String search) throws JSONException {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/getCompleted"; // <----enter your post url here
        JSONObject object = new JSONObject();
        Rmatch.clear();
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
                            if(!(MatchList.getString("t1_name").contains(search)) && !(MatchList.getString("t2_name").contains(search)) )
                            {
                                continue;
                            }
                            Recent match2=new Recent(
                                    MatchList.getString("t1_name"),
                                    MatchList.getString("t2_name"),
                                    MatchList.getString("date"),
                                    MatchList.getString("description"),
                                    MatchList.getString("match_id"),
                                    MatchList.getString("score_1"),
                                    MatchList.getString("score_2"),
                                    MatchList.getString("toss_winner"),
                                    MatchList.getString("location")
                            );
                            Log.w("Data", "callAPI: "+MatchList );
                            Rmatch.add(match2);
                        }
                        rvMatches=(RecyclerView) findViewById(R.id.recentMatches);

                        // Initialize contacts

                        // Create adapter passing in the sample user data
                        RecentAdapter adapter = new RecentAdapter(Rmatch);
                        // Attach the adapter to the recyclerview to populate items
                        rvMatches.setAdapter(adapter);
                        // Set layout manager to position the items
                        rvMatches.setLayoutManager(new LinearLayoutManager(this));
                        rvMatches.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, rvMatches ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Intent i=new Intent(RecentMatches.this, CompletedMatchStats.class);

                                        i.putExtra("Team1",Rmatch.get(position).getHome());
                                        i.putExtra("Team2",Rmatch.get(position).getAway());
                                        i.putExtra("Date",Rmatch.get(position).getDate());
                                        i.putExtra("Location",Rmatch.get(position).getLocation());
                                        i.putExtra("Result",Rmatch.get(position).getResult());
                                        i.putExtra("Score1",Rmatch.get(position).getScore1());
                                        i.putExtra("Score2",Rmatch.get(position).getScore2());
                                        i.putExtra("TossWinner",Rmatch.get(position).getTossWinner());
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


}