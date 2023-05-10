package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UpcomingMatchStats extends AppCompatActivity {

    TextView t1,t2,p1,p2,det;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_match_stats);
        Intent i=getIntent();
        t1=findViewById(R.id.UmcomingTeam1);
        t2=findViewById(R.id.UpcomingTeam2);
        p1=findViewById(R.id.Team1Player);
        p2=findViewById(R.id.Team2Player);
        det=findViewById(R.id.Timing);
        t1.setText(i.getStringExtra("Team1"));
        t2.setText(i.getStringExtra("Team2"));
        String Team1_players=i.getStringExtra("Team1_players");
        String Team2_players=i.getStringExtra("Team2_players");
        String[] Team1Array = Team1_players.split(",");
        String[] Team2Array = Team2_players.split(",");
        Log.d("teamlist", "onCreate() returned: " +Team1_players+Team2_players );
        String T1="",T2="";
        for (int j = 0; j < Team1Array.length; j++){
            T1+=Team1Array[j]+"\n";
        }
        for (int k=0; k < Team2Array.length; k++){
            T2+=Team2Array[k]+"\n";
        }
        p1.setText("\n"+T1);
        p2.setText("\n"+T2);
        String detail="On "+i.getStringExtra("Date")+" at "+i.getStringExtra("Location");
        det.setText(detail);

    }
}