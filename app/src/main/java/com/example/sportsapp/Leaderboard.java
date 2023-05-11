package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Leaderboard extends AppCompatActivity {


    int items=0;


    TableLayout leaderboard;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        leaderboard=findViewById(R.id.leaderboardTableLayout);

        try {
            callAPI();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }






    }
    public void callAPI() throws JSONException {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/leaderboard"; // <----enter your post url here
        JSONObject object = new JSONObject();

        @SuppressLint("UseCompatLoadingForDrawables") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object,
                (response -> {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response;
                        JSONArray array = new JSONArray();
                        array = jsonObject.getJSONArray("val");
                        items=array.length();
                        JSONObject MatchList = new JSONObject();
                        for(int i=0;i<array.length();i++) {
                            MatchList = array.getJSONObject(i);

                            TableRow row = new TableRow(this);

                            TextView leadname = new TextView(this);
                            leadname.setText(MatchList.getString("name"));
                            leadname.setTextSize(20);
                            leadname.setPadding(15,5,5,5);
                            leadname.setTextColor(Color.parseColor("white"));
                            leadname.setBackground(getResources().getDrawable(R.drawable.cell_border));
                            row.addView(leadname);

                            TextView leadcoin = new TextView(this);
                            leadcoin.setText(MatchList.getString("coins"));
                            leadcoin.setTextSize(20);
                            leadcoin.setGravity(Gravity.CENTER);
                            leadcoin.setPadding(5,5,5,5);
                            leadcoin.setTextColor(Color.parseColor("white"));
                            leadcoin.setBackground(getResources().getDrawable(R.drawable.cell_border));
                            row.addView(leadcoin);




                            leaderboard.addView(row);
                            Log.w("Data", "callAPI: "+MatchList );


                        }

                        Log.d("List", "callAPI: ");


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
}