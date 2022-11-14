package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Recommended extends AppCompatActivity implements View.OnClickListener {

    Button button4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experts);

        button4 = (Button) findViewById(R.id.button4);



        button4.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

                Intent intent6 = new Intent(this, MainActivity.class);
                startActivity(intent6);


        }
    }


