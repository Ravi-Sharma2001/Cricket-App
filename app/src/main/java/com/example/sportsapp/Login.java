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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText username,password;
    FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent i=new Intent(getApplicationContext(), FirstPage.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
    }
    public void userCheck(View view) throws InterruptedException {
        LottieAnimationView animation = findViewById(R.id.animationView);
        username=findViewById(R.id.Userid);
        String uname=username.getText().toString();
        password=findViewById(R.id.pass);
        String pass=password.getText().toString();

        if(!uname.equals("") && !pass.equals(""))
        {

            auth.signInWithEmailAndPassword(uname, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
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
                                        finish();
                                    }


                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




        }
        else {
            Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
        }

    }
    public void registrationPage(View view)
    {
        Intent i= new Intent(getApplicationContext(),Registration.class);
        startActivity(i);
        finish();
    }
}