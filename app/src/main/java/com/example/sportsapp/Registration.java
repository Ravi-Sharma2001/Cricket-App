package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;
import java.util.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText pass,cpass,name,dob,phone,email;
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
        phone=findViewById(R.id.phone);
        String ph=phone.getText().toString();
        email=findViewById(R.id.email);
        String mail=email.getText().toString();
        pass=findViewById(R.id.password);
        String p=pass.getText().toString();
        cpass=findViewById(R.id.confirmpassword);
        String cp=cpass.getText().toString();
        name=findViewById(R.id.name);
        String nm=name.getText().toString();
        dob=findViewById(R.id.DOB);
        String date=dob.getText().toString();

        String regex_mail = "^(.+)@(.+)$";
        Pattern pattern_mail = Pattern.compile(regex_mail);
        Matcher matcher_mail = pattern_mail.matcher(mail);

        String regex_date = "^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$";
        Pattern pattern_date = Pattern.compile(regex_date);
        Matcher matcher_date = pattern_date.matcher(date);

        String regex_phone = "^[0-9]{10}$";
        Pattern pattern_phone = Pattern.compile(regex_phone);
        Matcher matcher_phone = pattern_phone.matcher(ph);

        if(p.equals(cp) && matcher_mail.matches() && matcher_phone.matches() && matcher_date.matches() && !nm.equals(""))
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
            phone.setText("");
            email.setText("");

        }

    }
}