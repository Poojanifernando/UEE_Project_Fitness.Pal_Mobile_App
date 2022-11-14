package com.example.crud;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainActivityUserProfile extends AppCompatActivity {

    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArrayList<UserProfileEntity> userProfileEntityArrayList;
    UserProfileRecycleAdapter adapter;

    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_userprofile);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userProfileEntityArrayList = new ArrayList<>();

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(MainActivityUserProfile.this);
            }
        });

        readData();
    }




    private void readData() {

        databaseReference.child("USERSPROFILE").orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfileEntityArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserProfileEntity userspro = dataSnapshot.getValue(UserProfileEntity.class);
                    userProfileEntityArrayList.add(userspro);
                }
                adapter = new UserProfileRecycleAdapter(MainActivityUserProfile.this, userProfileEntityArrayList);
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
            dialog.setContentView(R.layout.alert_dialog_add_new_usersprofile);

            EditText textName = dialog.findViewById(R.id.textName);
            EditText textAge = dialog.findViewById(R.id.textAge);
            EditText textGender = dialog.findViewById(R.id.textGender);
            EditText textOccupation = dialog.findViewById(R.id.textOccupation);
            EditText textEmail = dialog.findViewById(R.id.textEmail);


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
                    String id = "userspro" + new Date().getTime();
                    String nameuser = textName.getText().toString();
                    String age = textAge.getText().toString();
                    String gender = textGender.getText().toString();
                    String occupation = textOccupation.getText().toString();
                    String email = textEmail.getText().toString();

                    if (nameuser.isEmpty() || age.isEmpty() || gender.isEmpty() || occupation.isEmpty() || email.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("USERSPROFILE").child(id).setValue(new UserProfileEntity(id, nameuser, age, gender,occupation,email));
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
