package com.example.general;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    String country[]={"India","Indonesia"};
    AutoCompleteTextView at;
    RadioGroup rg;
    RadioButton rb;
    TextView t1,t2,t3;
    CheckBox ch;
    ToggleButton tb;
    Button button;
    ProgressBar pb;
    ListView lt;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=findViewById(R.id.radioGroup);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        button=findViewById(R.id.button);
        ch=findViewById(R.id.checkBox);
        tb=findViewById(R.id.toggleButton);
        at=findViewById(R.id.autoCompleteTextView);
        pb=findViewById(R.id.progressBar);
        lt=findViewById(R.id.List);

        //code for list view
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,country);
        lt.setAdapter(adapter2);

        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, country[i], Toast.LENGTH_SHORT).show();
            }
        });

        //code for autocomplete text view
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, country);
        at.setAdapter(adapter);

        //code for toggle button
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tb.isChecked())
                    t3.setText("Activated");
                else
                    t3.setText("Deactivated");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for radio button
                int radioId=rg.getCheckedRadioButtonId();
                rb=findViewById(radioId);
                t2.setText(rb.getText().toString());

                //code for checkbox
                if(ch.isChecked())
                {
                    t1.setText("CheckBox Checked");
                }
                else
                {
                    t1.setText("Checkbox not Checked");
                }

                //code for progress bar
                counter=50;
                pb.setProgress(counter);



            }
        });




    }
}