package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin;

    Button buttonSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login_activity);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);




        buttonLogin.setOnClickListener(this);

        buttonSignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonLogin:
                Intent intent6 = new Intent(this, HomeActivity.class);
                startActivity(intent6);
                break;
            case R.id.buttonSignup:
                Intent intent7 = new Intent(this, MainActivityUserProfile.class);
                startActivity(intent7);
                break;


        }
    }
}

