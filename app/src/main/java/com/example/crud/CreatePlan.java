package com.example.crud;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class CreatePlan extends AppCompatActivity {
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArrayList<DietsItem> dietsItemArrayList;
    DietsRecyclerAdapter adapter;

    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dietsItemArrayList = new ArrayList<>();

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(CreatePlan.this);
            }
        });

        readData();
    }

    private void readData() {

        databaseReference.child("DIETS").orderByChild("DietName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dietsItemArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DietsItem diets = dataSnapshot.getValue(DietsItem.class);
                    dietsItemArrayList.add(diets);
                }
                adapter = new DietsRecyclerAdapter(CreatePlan.this, dietsItemArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public class ViewDialogAdd {
        public void showDialog(Context context) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_diet);

            EditText textName = dialog.findViewById(R.id.textName);
            EditText textBreakfast = dialog.findViewById(R.id.textBreakfast);
            EditText textLunch = dialog.findViewById(R.id.textLunch);
            EditText textDinner = dialog.findViewById(R.id.textDinner);
            EditText textWater = dialog.findViewById(R.id.textWater);
            EditText textGoals = dialog.findViewById(R.id.textGoals);


            Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonAdd.setText("ADD");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = "diet" + new Date().getTime();
                    String name = textName.getText().toString();
                    String breakfast = textBreakfast.getText().toString();
                    String lunch = textLunch.getText().toString();
                    String dinner = textDinner.getText().toString();
                    String water = textWater.getText().toString();
                    String goals = textGoals.getText().toString();

                    if (name.isEmpty() || breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty() || water.isEmpty() || goals.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("DIETS").child(id).setValue(new DietsItem(id, name, breakfast, lunch, dinner, water, goals));
                        Toast.makeText(context, "DONE!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}
