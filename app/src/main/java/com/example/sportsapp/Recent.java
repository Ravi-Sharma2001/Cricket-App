package com.example.sportsapp;

import java.util.ArrayList;
import java.util.List;

public class Recent {
    private String hometeam;
    private String awayteam;
    private String date;
    private String Score1;
    private String Score2;
    private String Location;
    private String TossWinner;

    String MatchID;
    private String result;
    public Recent(String home, String away,String mdate,String mresult,String id,String S1,String S2,String loc,String Tosswin) {
        hometeam=home;
        awayteam=away;
        date=mdate;
        result=mresult;
        MatchID=id;
        Score1=S1;
        Score2=S2;
        Location=loc;
        TossWinner=Tosswin;
    }

    public String getHome() {
        return hometeam;
    }
    public String getAway() {
        return awayteam;
    }
    public String getDate() {
        return date;
    }
    public String getResult() {
        return result;
    }
    public String getMatchID() {
        return MatchID;
    }

    public String getScore1() {
        return Score1;
    }
    public String getScore2() {
        return Score2;
    }
    public String getLocation() {
        return Location;
    }
    public String getTossWinner() {
        return TossWinner;
    }

}
