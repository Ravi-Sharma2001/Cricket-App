package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpcomingMatchStats extends AppCompatActivity {

    TextView t1,t2,p1,p2,det,prob1,prob2;
    TextView invest;
    Button b;
    int investAmt;
    FirebaseAuth auth;
    Intent i;
    FirebaseUser user;
    ToggleButton tb;
    JSONObject MatchDetails;
    ProgressBar pb;
    @SuppressLint({"MissingInflatedId", "SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_match_stats);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        i = getIntent();
        try {
            checkInvested(user.getEmail(),i.getStringExtra("Match_ID"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        t1 = findViewById(R.id.UmcomingTeam1);
        t2 = findViewById(R.id.UpcomingTeam2);
        p1 = findViewById(R.id.Team1Player);
        b=findViewById(R.id.button4);
        p2 = findViewById(R.id.Team2Player);
        tb=findViewById(R.id.toggleButton2);
        det = findViewById(R.id.Timing);
        prob1 = findViewById(R.id.Team1_Winprob);
        invest=findViewById(R.id.Amount_Invest);
        pb = findViewById(R.id.WinProb);
        prob2 = findViewById(R.id.Team2_Winprob);
        tb.setTextOn(i.getStringExtra("Team1"));
        tb.setTextOff(i.getStringExtra("Team2"));
        t1.setText(i.getStringExtra("Team1"));
        t2.setText(i.getStringExtra("Team2"));
        prob1.setText(i.getStringExtra("Team1_prob") + "%");
        prob2.setText(i.getStringExtra("Team2_prob") + "%");
        String Team1_players = i.getStringExtra("Team1_players");
        String Team2_players = i.getStringExtra("Team2_players");
        String[] Team1Array = Team1_players.split(",");
        String[] Team2Array = Team2_players.split(",");
        Log.d("teamlist", "onCreate() returned: " + Team1_players + Team2_players);
        String T1 = "", T2 = "";
        for (int j = 0; j < Team1Array.length; j++) {
            T1 += Team1Array[j] + "\n";
        }
        for (int k = 0; k < Team2Array.length; k++) {
            T2 += Team2Array[k] + "\n";
        }
        p1.setText("\n" + T1);
        p2.setText("\n" + T2);
        String detail = "On " + i.getStringExtra("Date") + " at " + i.getStringExtra("Location");
        det.setText(detail);
        pb.setProgress(Integer.parseInt(i.getStringExtra("Team1_prob")));

        SeekBar seekBar = findViewById(R.id.InvestmentAmount);
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(
                    new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(
                                SeekBar seekBar, int progress,
                                boolean fromUser) {
                            // Write code to perform some action
                            // when progress is changed.
                        }

                        @Override
                        public void onStartTrackingTouch(
                                SeekBar seekBar) {
                            // Write code to perform some action
                            // when touch is started.
                        }

                        @Override
                        public void onStopTrackingTouch(
                                SeekBar seekBar) {
                                investAmt=seekBar.getProgress();
                                invest.setText(String.valueOf(investAmt));

                        }
                    });

        }
    }
    public void DoInvestment(View view) throws JSONException {
        Log.d("Team", "Selected: "+tb.getText());
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String winID="";
        if(tb.getText().equals(i.getStringExtra("Team1")))
        {
            winID=i.getStringExtra("Team1_ID");
        }
        else {
            winID=i.getStringExtra("Team2_ID");
        }
        String url = "https://cricketappserver.onrender.com/invest"; // <----enter your post url here
        JSONObject object = new JSONObject();
        object.put("email",user.getEmail());
        object.put("match_id",i.getStringExtra("Match_ID"));
        object.put("coins",String.valueOf(investAmt));
        object.put("winning_team",winID);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                (response -> {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response;

                        Log.d("api","final obj:"+jsonObject.getString("success"));
                        Log.d("api","final obj:"+jsonObject.getString("message"));
                        Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        try {
                            checkInvested(user.getEmail(),i.getStringExtra("Match_ID"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

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
    public void checkInvested(final String email,String ID) throws JSONException {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/checkEligible"; // <----enter your post url here
        JSONObject object = new JSONObject();
        object.put("email",email);
        object.put("match_id",ID);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                (response -> {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response;
                        if(jsonObject.getString("invested").equals("true"))
                        {
                            b.setEnabled(false);
                        }
//                        MatchDetails  = new JSONObject();
//                        MatchDetails = jsonObject.getJSONObject("invested");
//                        if(MatchDetails.getString())
//                        Log.d("api",String.valueOf(response));
                        Log.d("api","final obj:"+jsonObject);
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