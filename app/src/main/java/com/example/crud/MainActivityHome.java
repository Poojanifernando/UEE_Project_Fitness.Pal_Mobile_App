package com.example.crud;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityHome extends AppCompatActivity implements View.OnClickListener {


    Button buttoncal;
    Button buttonview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_home_activity_main);


        buttoncal = (Button) findViewById(R.id.buttoncal);

        buttonview = (Button) findViewById(R.id.buttonview);




        buttoncal.setOnClickListener(this);
        buttonview.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttoncal:
                Intent intentbtn = new Intent(this, BMIMainActivity.class);
                startActivity(intentbtn);
                break;
            case R.id.buttonview:
                Intent intentbtnview = new Intent(this, MainActivityBmi.class);
                startActivity(intentbtnview);
                break;


        }
    }
}
