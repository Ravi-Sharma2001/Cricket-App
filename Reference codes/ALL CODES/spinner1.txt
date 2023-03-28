package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String food[]={"Food1   Rs10","Food2   Rs20","Food3   Rs30","Food4    rs40"};
    String Beverage[]={"Beverage1   RS10","Beverage2   Rs20","Beverage3   Rs30","Beverage4   Rs40"};

    int price[]={10,20,30,40};
    Spinner s1,s2;
    Button button;

    TextView t1,t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1=findViewById(R.id.spinner);
        s2=findViewById(R.id.spinner2);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,food);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Beverage);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s2.setAdapter(adapter2);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f1=s1.getSelectedItem().toString();
                String b1=s2.getSelectedItem().toString();
                int pos=s1.getSelectedItemPosition();
                int pos2=s2.getSelectedItemPosition();
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                Bundle bundle=new Bundle();
                bundle.putString("food",f1);
                bundle.putString("Bvg",b1);
                bundle.putInt("p1",price[pos]);
                bundle.putInt("p2",price[pos2]);
                intent.putExtras(bundle);
                /*intent.putExtra("Food",f1);
                intent.putExtra("Bvg",b1);*/
                startActivity(intent);



            }
        });

    }
}