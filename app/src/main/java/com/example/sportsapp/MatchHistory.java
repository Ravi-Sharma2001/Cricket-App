package com.example.sportsapp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MatchHistory {
    private String hometeam;
    private String awayteam;
    private String date;
    private String investment;
    private String income;
    public MatchHistory(String home, String away,String mdate,String minvestment, String mincome) {
        hometeam=home;
        awayteam=away;
        date=mdate;
        investment=minvestment;
        income=mincome;
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
    public String getInvestment() {
        return investment;
    }
    public String getIncome() {
        return income;
    }
    static List<List<String>> match_detail=new ArrayList<List<String>>();
    static String[][] matcharr ={{"MI","CSK","03/05/2005","100","+150"},
            {"RCB","CSK","03/05/2005","200","+300"},
            {"KKR","CSK","03/05/2005","200","+300"},
            {"DC","MI","03/05/2005","200","+300"},
            {"MI","CSK","03/05/2005","200","+300"},

    };


    public static ArrayList<MatchHistory> createContactsList() {

        for(int i=0;i<matcharr.length;i++) {
            List<String> match1 = new ArrayList<String>();
            match1.add(matcharr[i][0]);
            match1.add(matcharr[i][1]);
            match1.add(matcharr[i][2]);
            match1.add(matcharr[i][3]);
            match1.add(matcharr[i][4]);
            match_detail.add(match1);
        }
        String hteam,ateam,matchdate,matchstadium;
        ArrayList<MatchHistory> match_final = new ArrayList<MatchHistory>();
        for (int i = 0; i <match_detail.size(); i++) {
            List<String> list = match_detail.get(i);
            match_final.add(new MatchHistory(list.get(0),list.get(1),list.get(2),list.get(3), list.get(4)));
        }

        return match_final;
    }
}

