package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.jvm.Throws;

public class NewsFeed extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener  {
    BottomNavigationView bottomNavigationView, bottomNav2;
    ArrayList<newsModel> allNewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.news);
        bottomNavigationView.setOnItemSelectedListener(this);
        allNewsList = new ArrayList<>();
        getData();
    }
    private void getData() {
        //API CALL
        String api = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=255a8126b3f4429a9a98c2464efe2d7b";
//            String api = "https://jsonplaceholder.typicode.com/photos";
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
//                            Log.i("api","onSuccess:"+obj);
                            JSONArray array = new JSONArray();
                            array = obj.getJSONArray("articles");
//                            JSONArray array = new JSONArray();
//                            Log.i("api","onSuccess:"+obj2);
                            for(int i = 0; i< array.length(); i++){
                                JSONObject singleNews = array.getJSONObject(i);
//                                Log.i("api","onSuccess:"+singleNews);
                                newsModel singleModel = new newsModel(
                                        singleNews.getString("title"),
                                        singleNews.getString("author")
                                        );
                                allNewsList.add(singleModel);
//                                Log.e("api","onSuccess:"+singleNews);
                            }
                            Log.e("api","onSuccess:"+allNewsList.size());
//                            Log.e("api","onSuccess:"+allNewsList.get(0).getAuthor());

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Log.e("api","onErrorResponse:"+e.getMessage());
                        }

//                        Log.e("api","onSuccessResponse"+response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api","onErrorResponse:"+error.getLocalizedMessage());
            }
        }){
//            @Throws(AuthFailureError.class)
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "Mozilla/5.0");
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
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
            case R.id.profile: {
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("buttonStatus", item.getItemId());
                startActivity(intent);
                break;
            }
//            case R.id.news:{
//                Intent intent = new Intent(this, NewsFeed.class);
////                intent.putExtra("buttonStatus", item.getItemId());
//                startActivity(intent);
//            }
        }
        return true;
    }
}