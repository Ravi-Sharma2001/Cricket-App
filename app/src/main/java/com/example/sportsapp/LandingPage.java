package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingPage extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        if(user != null){
            Toast.makeText(this, "You are Already Logged in.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);
            finish();
        }
    }
    public void login_page(View view)
    {
        Intent i=new Intent(this,Login.class);
        startActivity(i);
        finish();
    }
    public void register_page(View view)
    {
        Intent i=new Intent(this,Registration.class);
        startActivity(i);
        finish();
    }
}