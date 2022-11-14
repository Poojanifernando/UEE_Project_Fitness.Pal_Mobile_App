package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        buttonWelcome = (Button) findViewById(R.id.buttonWelcome);

        buttonWelcome.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonWelcome:
                Intent intent6 = new Intent(this, MainLoginActivity.class);
                startActivity(intent6);
                break;


        }
    }
}


