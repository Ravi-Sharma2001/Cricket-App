package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CompletedMatchStats extends AppCompatActivity {

    TextView t1,t2,s1,s2,res,det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_match_stats);
        Intent i=getIntent();
        t1=findViewById(R.id.CompletedTeam1);
        t2=findViewById(R.id.CompletedTeam2);
        s1=findViewById(R.id.Score1);
        s2=findViewById(R.id.Score2);
        res=findViewById(R.id.Result);
        det=findViewById(R.id.Details);
        t1.setText(i.getStringExtra("Team1"));
        t2.setText(i.getStringExtra("Team2"));
        s1.setText(i.getStringExtra("Score1"));
        s2.setText(i.getStringExtra("Score2"));
        res.setText(i.getStringExtra("Result"));
        String detail="\nDate: "+i.getStringExtra("Date")+"\n\nLocation: "+i.getStringExtra("TossWinner")+"\n\nToss Winner: "+i.getStringExtra("Location");
        Log.d("detail", "onCreate: "+detail);
        det.setText(detail);
    }
}