package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    public class HomeR extends AppCompatActivity implements View.OnClickListener {

        Button button, button2, button3;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.diet_home);

            button = (Button) findViewById(R.id.button);
            button2 = (Button) findViewById(R.id.button2);



            button.setOnClickListener(this);
            button2.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button:
                    Intent intent6 = new Intent(this, Recommended.class);
                    startActivity(intent6);
                    break;
                case R.id.button2:
                    Intent intent7 = new Intent(this, CreatePlan.class);
                    startActivity(intent7);
                    break;


            }
        }
    }

