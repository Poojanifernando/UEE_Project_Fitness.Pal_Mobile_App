package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonex;
    Button buttonpro;
    Button buttonc;
    Button buttonart;
    Button buttondiet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_app);

        buttonex = (Button) findViewById(R.id.buttonex);
        buttonpro = (Button) findViewById(R.id.buttonpro);
        buttonc = (Button) findViewById(R.id.buttonc);
        buttonart = (Button) findViewById(R.id.buttonart);
        buttondiet = (Button) findViewById(R.id.buttondiet);


        buttonex.setOnClickListener(this);
        buttonpro.setOnClickListener(this);
        buttonc.setOnClickListener(this);
        buttonart.setOnClickListener(this);
        buttondiet.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonex:
                Intent intent6 = new Intent(this, MainActivityExcersice.class);
                startActivity(intent6);
                break;
            case R.id.buttonpro:
                Intent intent7 = new Intent(this, MainActivityUserProfile.class);
                startActivity(intent7);
                break;
            case R.id.buttonc:
                Intent intentcal = new Intent(this, MainActivityHome.class);
                startActivity(intentcal);
                break;
            case R.id.buttonart:
                Intent intentart = new Intent(this, MyArticles.class);
                startActivity(intentart);
                break;
            case R.id.buttondiet:
                Intent intentd = new Intent(this, HomeR.class);
                startActivity(intentd);
                break;


        }
    }
}


