package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class payment extends AppCompatActivity implements PaymentResultListener {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn = findViewById(R.id.button5);
        Checkout.preload(getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
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

                    options.put("name", "Sanket");
                    options.put("description", "Reference No. #123456");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//                    options.put("order_id", "12");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    options.put("amount", "1000");//pass amount in currency subunits
                    options.put("prefill.email", "kasrsanket@gmail.com");
                    options.put("prefill.contact", "8591235483");
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);

                    checkout.open(payment.this, options);

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