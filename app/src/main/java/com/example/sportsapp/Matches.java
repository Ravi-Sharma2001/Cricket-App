package com.example.sportsapp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Matches {
        private String hometeam;
        String MatchID;
    private String awayteam;
    private String date;
    private String T1_Players;
    private String T2_Players;
    private String stadium;
        public Matches(String home, String away,String mdate,String mstadium,String id,String P1,String P2) {
            hometeam=home;
            awayteam=away;
            date=mdate;
            stadium=mstadium;
            MatchID=id;
            T1_Players=P1;
            T2_Players=P2;
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
    public String getStadium() {
        return stadium;
    }
    public String getMatchID() {
        return MatchID;
    }
    public String Team1_Players() {
        return T1_Players;
    }
    public String Team2_Players() {
        return T2_Players;
    }

    }

