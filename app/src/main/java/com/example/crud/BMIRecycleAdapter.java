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


public class BMIRecycleAdapter extends RecyclerView.Adapter<BMIRecycleAdapter.ViewHolder> {

    Context context;
    ArrayList<BMIEntity> BMIEntityArrayList;
    DatabaseReference databaseReference;


    public BMIRecycleAdapter(Context context, ArrayList<BMIEntity> BMIEntityArrayList) {
        this.context = context;
        this.BMIEntityArrayList = BMIEntityArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.bmi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        BMIEntity bmi = BMIEntityArrayList.get(position);


        holder.edAge.setText("Age : " + bmi.getAge());
        holder.edKg.setText("Weight : " + bmi.getWeight());
        holder.edFeet.setText("Feet : " + bmi.getFeet());
        holder.edIns.setText("Inch : " + bmi.getInch());
        holder.edIndex.setText("Index : " + bmi.getIndex());


        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, bmi.getId(),bmi.getWeight(), bmi.getAge(), bmi.getFeet(), bmi.getInch(), bmi.getIndex());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, bmi.getId());
            }
        });

    }



    @Override
    public int getItemCount() {

        return BMIEntityArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView edKg;
        TextView edAge;
        TextView edFeet;
        TextView edIns;
        TextView edIndex;


        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            edKg = itemView.findViewById(R.id.edKg);
            edAge = itemView.findViewById(R.id.edAge);
            edFeet = itemView.findViewById(R.id.edFeet);
            edIns = itemView.findViewById(R.id.edIns);
            edIndex = itemView.findViewById(R.id.edIndex);


            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String weight, String age, String feet, String inch, String index) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_bmi);

            EditText edKg = dialog.findViewById(R.id.edKg);
            EditText edAge = dialog.findViewById(R.id.edAge);
            EditText edFeet = dialog.findViewById(R.id.edFeet);
            EditText edIns = dialog.findViewById(R.id.edIns);
            EditText edIndex = dialog.findViewById(R.id.edIndex);

            edAge.setText(age);
            edKg.setText(weight);
            edFeet.setText(feet);
            edIns.setText(inch);
            edIndex.setText(index);


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

                    String newWeight = edKg.getText().toString();
                    String newAge = edAge.getText().toString();
                    String newFeet = edFeet.getText().toString();
                    String newInch = edIns.getText().toString();
                    String newIndex = edIndex.getText().toString();


                    if (weight.isEmpty() || age.isEmpty() || feet.isEmpty() || inch.isEmpty() || index.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newWeight.equals(weight) && newAge.equals(age) && newFeet.equals(feet)&& newInch.equals(inch)&& newIndex.equals(index)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("BMI").child(id).setValue(new BMIEntity(id, newWeight, newAge, newFeet,newInch,newIndex));
                            Toast.makeText(context, "bmi Updated successfully!", Toast.LENGTH_SHORT).show();
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

                    databaseReference.child("BMI").child(id).removeValue();
                    Toast.makeText(context, "record Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }



}
