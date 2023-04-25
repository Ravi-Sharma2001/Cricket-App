package com.example.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {
    EditText pass,cpass,name,dob,user;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        auth=FirebaseAuth.getInstance();
    }
    public void loginPage(View view)
    {
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
        finish();
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
            auth.createUserWithEmailAndPassword(userID, p)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>(){
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                        try {
                                            callAPI(userID,nm,date);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                        Intent i=new Intent(getApplicationContext(),Login.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Registration failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
        });

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
    public void callAPI(final String email, final String name, String dob) throws JSONException {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://cricketappserver.onrender.com/register"; // <----enter your post url here
        JSONObject object = new JSONObject();
        object.put("email",email);
        object.put("name",name);
        object.put("dob",dob);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                (response -> {
                    try{
                        Log.d("api",String.valueOf(response));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                })
                ,(error -> {
            Log.e("Error","Error"+error.getMessage());
        }));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}