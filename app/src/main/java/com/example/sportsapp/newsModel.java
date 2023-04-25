package com.example.sportsapp;

import android.util.Log;

public class newsModel {
    String title;
    String author;

    public  newsModel(String title, String author){
        try {
                this.title = title;
        }catch (Error e){
            Log.e("class",e.getMessage());
        }
        try {
            if(author.equals("null"))
                this.author="Anonymous";
            else
                this.author = author;
        }catch(Error e){
            Log.e("class", e.getMessage());
        }
    }

    public String getTitle() {
            return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
