package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BuyInAppCoins extends AppCompatActivity {

    ListView l;
    Button buy;
    TextView Final_Coins,Final_Rupees;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_in_app_coins);
        l=findViewById(R.id.CoinsList);
        Final_Coins=findViewById(R.id.FinalCoins);
        Final_Rupees=findViewById(R.id.FinalRupees);
        buy=findViewById(R.id.BuyCoins);
        String arr[]={"50 : 75","100 : 150","200 : 300","500 : 750","1000 : 1,500",};
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this, R.layout.list_text,R.id.textItem,arr);
        l.setAdapter(ad);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] FinalBuy = selectedItem.split(" : ");
                buy.setEnabled(true);
//                if(FinalBuy[1].length()==4)
//                {
//                    FinalBuy[1]=FinalBuy[1].substring(0,1)+","+FinalBuy[1].substring(1);
//                }
                Final_Coins.setText(": "+FinalBuy[0]);
                Final_Rupees.setText(": "+FinalBuy[1]);
                Log.d("FinalCoins", "onItemClick: "+FinalBuy[0]+" "+FinalBuy[1]);
            }
        });

    }
}