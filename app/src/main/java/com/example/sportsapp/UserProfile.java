package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class UserProfile extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView, bottomNav2;
    ArrayList<MatchHistory> Matches;
    FirebaseAuth auth;
    FirebaseUser user;
    JSONObject userDetails;
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
            try {
                callAPI(user.getEmail());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
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
    public void changeDetails() throws JSONException {
        TextView name=findViewById(R.id.textView7);
        TextView coins=findViewById(R.id.textView8);
        name.setText(userDetails.getString("name")+"\n"+userDetails.getString("email"));
        coins.setText(userDetails.getString("coins"));
    }

public void callAPI(final String email) throws JSONException {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/getUser"; // <----enter your post url here
        JSONObject object = new JSONObject();
        object.put("email",email);
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                 (response -> {
                     try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response;
                        JSONArray array = new JSONArray();
                        array = jsonObject.getJSONArray("val");
                        userDetails  = new JSONObject();
                        userDetails = array.getJSONObject(0);
                         changeDetails();
//                        Log.d("api",String.valueOf(response));
                         Log.d("api","final obj:"+userDetails);
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