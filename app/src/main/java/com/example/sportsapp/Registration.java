package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText pass,cpass,name,dob,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
    }
    public void loginPage(View view)
    {
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    public void checkRegistration(View view)
    {

        user=findViewById(R.id.Userid);
        String userID=user.getText().toString();
        pass=findViewById(R.id.password);
        String p=pass.getText().toString();
        cpass=findViewById(R.id.confirmpassword);
        String cp=cpass.getText().toString();
        name=findViewById(R.id.name);
        String nm=name.getText().toString();
        dob=findViewById(R.id.DOB);
        String date=dob.getText().toString();


        String regex_date = "^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$";
        Pattern pattern_date = Pattern.compile(regex_date);
        Matcher matcher_date = pattern_date.matcher(date);


        if(p.equals(cp) &&  matcher_date.matches() && !nm.equals("") && !userID.equals(""))
        {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),LandingPage.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Incorrect Entry", Toast.LENGTH_SHORT).show();
            pass.setText("");
            cpass.setText("");
            name.setText("");
            dob.setText("");
            user.setText("");

        }

    }
}