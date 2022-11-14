package com.example.crud;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ArticleAdapter2 extends RecyclerView.Adapter<ArticleAdapter2.ViewHolder>{


    Context context;
    ArrayList<ArticleEntity> articleEntityArrayList;
    DatabaseReference databaseReference;


    public ArticleAdapter2(Context context, ArrayList<ArticleEntity> articleEntityArrayList) {
        this.context = context;
        this.articleEntityArrayList = articleEntityArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }


    @NonNull
    @Override
    public ArticleAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.article_item2, parent, false);
        return new ArticleAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter2.ViewHolder holder, int position) {


        ArticleEntity article = articleEntityArrayList.get(position);

        holder.textCategory.setText("Category : " + article.getCategory());
        holder.textTitle.setText("Title : " + article.getTitle());
        holder.textDescription.setText("Description : " + article.getDescription());


    }

    @Override
    public int getItemCount() {

        return articleEntityArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textCategory;
        TextView textTitle;
        TextView textDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textCategory = itemView.findViewById(R.id.textCategory);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);


        }
    }
}
