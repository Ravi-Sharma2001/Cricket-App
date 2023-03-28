package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Login extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void userCheck(View view) throws InterruptedException {
        LottieAnimationView animation = findViewById(R.id.animationView);
        username=findViewById(R.id.email);
        String uname=username.getText().toString();
        password=findViewById(R.id.pass);
        String pass=password.getText().toString();

        if(uname.equals("Ravi") && pass.equals("ravi@1234"))
        {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            animation.setAnimation(R.raw.takeoff);
            animation.setScaleX(1f);
            animation.setScaleY(1f);
            animation.playAnimation();
            float x=animation.getY();
            animation.setY(x-75);
            animation.addAnimatorListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animator) {
                    Intent i=new Intent(getApplicationContext(), FirstPage.class);
                    startActivity(i);
                }


            });

        }
        else
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();

    }
    public void registrationPage(View view)
    {
        Intent i= new Intent(getApplicationContext(),Registration.class);
        startActivity(i);
    }
}