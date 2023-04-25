package com.example.sportsapp;

import java.util.ArrayList;
import java.util.List;

public class Recent {
    private String hometeam;
    private String awayteam;
    private String date;
    private String result;
    public Recent(String home, String away,String mdate,String mresult) {
        hometeam=home;
        awayteam=away;
        date=mdate;
        result=mresult;
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
    static List<List<String>> match_detail=new ArrayList<List<String>>();
    static String[][] matcharr ={{"MI","CSK","03/05/2005","MI Won by 100 runs"},
            {"RCB","CSK","03/05/2005","CSK Won by 7 wickets"},
            {"KKR","CSK","03/05/2005","KKR Won by 100 runs"},
            {"DC","MI","03/05/2005","MI Won by 120 runs"},
            {"MI","CSK","03/05/2005","MI Won by 100 runs"},
            {"SRH","MI","03/05/2005","MI Won by 100 runs"},
            {"RR","RCB","03/05/2005","RCB Won by 10 wickets"}

    };


    public static ArrayList<Recent> createContactsList() {

        for(int i=0;i<matcharr.length;i++) {
            List<String> match1 = new ArrayList<String>();
            match1.add(matcharr[i][0]);
            match1.add(matcharr[i][1]);
            match1.add(matcharr[i][2]);
            match1.add(matcharr[i][3]);
            match_detail.add(match1);
        }
        ArrayList<Recent> match_final = new ArrayList<Recent>();
        for (int i = 0; i <match_detail.size(); i++) {
            List<String> list = match_detail.get(i);
            match_final.add(new Recent(list.get(0),list.get(1),list.get(2),list.get(3)));
        }

        return match_final;
    }
}
