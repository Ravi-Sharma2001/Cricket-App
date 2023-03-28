package com.example.sportsapp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Matches {
        private String hometeam;
    private String awayteam;
    private String date;
    private String stadium;
        public Matches(String home, String away,String mdate,String mstadium) {
            hometeam=home;
            awayteam=away;
            date=mdate;
            stadium=mstadium;
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
    static List<List<String>> match_detail=new ArrayList<List<String>>();
    static String[][] matcharr ={{"MI","CSK","03/05/2005","Chinnaswami Stadium"},
            {"RCB","CSK","03/05/2005","Chinnaswami Stadium"},
            {"KKR","CSK","03/05/2005","Dharamshala Stadium"},
            {"DC","MI","03/05/2005","Eden Gardens Stadium"},
            {"MI","CSK","03/05/2005","Vankhade Stadium"},
            {"SRH","MI","03/05/2005","Dharamshala Stadium"},
            {"RR","RCB","03/05/2005","Chinnaswami Stadium"},
            {"KXP","CSK","03/05/2005","Eden Gardens Stadium"},
            {"KKR","CSK","03/05/2005","Vankhade Stadium"},
            {"CSK","KKR","03/05/2005","Chinnaswami Stadium"},
            {"SRH","RCB","03/05/2005","Eden Gardens Stadium"},
            {"RCB","KXP","03/05/2005","Vankhade Stadium"},

    };


        public static ArrayList<Matches> createContactsList() {
            for(int i=0;i<matcharr.length;i++) {
                List<String> match1 = new ArrayList<String>();
                match1.add(matcharr[i][0]);
                match1.add(matcharr[i][1]);
                match1.add(matcharr[i][2]);
                match1.add(matcharr[i][3]);
                match_detail.add(match1);
            }
            String hteam,ateam,matchdate,matchstadium;
            ArrayList<Matches> match_final = new ArrayList<Matches>();
            for (int i = 0; i <match_detail.size(); i++) {
                List<String> list = match_detail.get(i);
                match_final.add(new Matches(list.get(0),list.get(1),list.get(2),list.get(3)));
            }

            return match_final;
        }
    }

