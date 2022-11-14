package com.example.crud;


import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class MyArticles extends AppCompatActivity  implements View.OnClickListener {
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArrayList<ArticleEntity> articleEntityArrayList;
    ArticleAdapter2 adapter;

    Button buttonAdd;
    Button  buttonGo;
    Button buttonComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myarticle_items);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articleEntityArrayList = new ArrayList<>();

        //button for the redirect to my articles
        buttonGo = (Button) findViewById(R.id.buttonGo);
        //button for the redirect to create new articles
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonGo.setOnClickListener(this);



      /*  buttonGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyArticles.this, MainActivityArticle.class);
                startActivity(intent);
            }
        });*/



        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyArticles.ViewDialogAdd viewDialogAdd = new MyArticles.ViewDialogAdd();
                viewDialogAdd.showDialog(MyArticles.this);
            }
        });





       /*buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivityArticle.class);
                startActivity(intent);

            }
        });*/
        readData();
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MyArticles.this, MainActivityArticle.class);
        startActivity(intent);
    }





    private void readData() {

        databaseReference.child("ARTICLES").orderByChild("Category").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                articleEntityArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ArticleEntity articles = dataSnapshot.getValue(ArticleEntity.class);
                    articleEntityArrayList.add(articles);
                }
                adapter = new ArticleAdapter2(MyArticles.this, articleEntityArrayList);
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
            dialog.setContentView(R.layout.alert_dialog_add_new_article);

            EditText textCategory = dialog.findViewById(R.id.textCategory);
            EditText textTitle = dialog.findViewById(R.id.textTitle);
            EditText textDescription = dialog.findViewById(R.id.textDescription);


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
                    String id = "article" + new Date().getTime();
                    String category = textCategory.getText().toString();
                    String title = textTitle.getText().toString();
                    String description = textDescription.getText().toString();

                    if (category.isEmpty() || title.isEmpty() || description.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("ARTICLES").child(id).setValue(new ArticleEntity(id, category, title, description));
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

