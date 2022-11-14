package com.example.crud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DietsRecyclerAdapter extends RecyclerView.Adapter<DietsRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<DietsItem> dietsItemArrayList;
    DatabaseReference databaseReference;

    public DietsRecyclerAdapter(Context context, ArrayList<DietsItem> dietsItemArrayList) {
        this.context = context;
        this.dietsItemArrayList = dietsItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.diet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DietsItem diets = dietsItemArrayList.get(position);

        holder.textName.setText("Plan Name : " + diets.getDietName());
        holder.textBreakfast.setText("Breakfast : " + diets.getBreakfast());
        holder.textLunch.setText("Lunch : " + diets.getLunch());
        holder.textDinner.setText("Dinner : " + diets.getDinner());
        holder.textWater.setText("Water Consumption : " + diets.getWater());
        holder.textGoals.setText("My Goals : " + diets.getGoals());


        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, diets.getUserID(), diets.getDietName(), diets.getBreakfast(), diets.getLunch(), diets.getDinner(), diets.getWater(), diets.getGoals());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, diets.getUserID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dietsItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textBreakfast;
        TextView textLunch;
        TextView textDinner;
        TextView textWater;
        TextView textGoals;

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textBreakfast = itemView.findViewById(R.id.textBreakfast);
            textLunch = itemView.findViewById(R.id.textLunch);
            textDinner = itemView.findViewById(R.id.textDinner);
            textWater = itemView.findViewById(R.id.textWater);
            textGoals = itemView.findViewById(R.id.textGoals);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String name, String breakfast, String lunch, String dinner, String water, String goals) {
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

            textName.setText(name);
            textBreakfast.setText(breakfast);
            textLunch.setText(lunch);
            textDinner.setText(dinner);
            textWater.setText(water);
            textGoals.setText(goals);


            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newName = textName.getText().toString();
                    String newBreakfast = textBreakfast.getText().toString();
                    String newLunch = textLunch.getText().toString();
                    String newDinner = textDinner.getText().toString();
                    String newWater = textWater.getText().toString();
                    String newGoals = textGoals.getText().toString();

                    if (name.isEmpty() || breakfast.isEmpty() || lunch.isEmpty()|| dinner.isEmpty() || water.isEmpty() || goals.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newName.equals(name) && newBreakfast.equals(breakfast) && newLunch.equals(lunch) && newDinner.equals(dinner) && newWater.equals(water) && newGoals.equals(goals)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("DIETS").child(id).setValue(new DietsItem(id, newName, newBreakfast, newLunch, newDinner, newWater, newGoals));
                            Toast.makeText(context, "Details Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReference.child("DIETS").child(id).removeValue();
                    Toast.makeText(context, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}
