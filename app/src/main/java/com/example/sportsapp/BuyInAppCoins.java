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
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class BuyInAppCoins extends AppCompatActivity implements PaymentResultListener{

    ListView l;
    Button buy;
    String[] FinalBuy;
    TextView Final_Coins,Final_Rupees;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_in_app_coins);
        Checkout.preload(getApplicationContext());
        l=findViewById(R.id.CoinsList);
        Final_Coins=findViewById(R.id.FinalCoins);
        Final_Rupees=findViewById(R.id.FinalRupees);
        buy=findViewById(R.id.BuyCoins);
        String arr[]={"50 : 75","100 : 150","200 : 300","500 : 750","1000 : 1500",};
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this, R.layout.list_text,R.id.textItem,arr);
        l.setAdapter(ad);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                FinalBuy = selectedItem.split(" : ");
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



        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_pnOVOroqu0bVkB");
                /**
                 * Instantiate Checkout
                 */

                /**
                 * Set your logo here
                 */
                checkout.setImage(R.drawable.applogo);

                /**
                 * Reference to current activity
                 */
//                final Activity activity= this;

                /**
                 * Pass your payment options to the Razorpay Checkout as a JSONObject
                 */
                try {
                    JSONObject options = new JSONObject();

                    options.put("name", "Sports App");
                    options.put("description", "Reference No. #123456");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//                    options.put("order_id", "12");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    options.put("amount", String.valueOf(Integer.parseInt(FinalBuy[1])*100));//pass amount in currency subunits
//                    options.put("prefill.email", "kasrsanket@gmail.com");
//                    options.put("prefill.contact", "8591235483");
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);

                    checkout.open(BuyInAppCoins.this, options);

                } catch (Exception e) {
                    Log.e("Payment", "Error in starting Razorpay Checkout", e);
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG);
    }

}

