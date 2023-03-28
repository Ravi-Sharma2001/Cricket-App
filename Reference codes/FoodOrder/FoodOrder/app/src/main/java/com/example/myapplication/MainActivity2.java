package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView5);
        t4=findViewById(R.id.textView6);
        Intent intent=getIntent();
        String s1=intent.getStringExtra("food");
        String s2=intent.getStringExtra("Bvg");
        int val=intent.getIntExtra("p1",0);
        int val2=intent.getIntExtra("p2",0);
        int total=val+val2;
        String tot=Integer.toString(total);
        Date currentTime = Calendar.getInstance().getTime();
        t3.setText(currentTime.toString());
        t1.setText(s1);
        t2.setText(s2);
        t4.setText(tot);
    }
}