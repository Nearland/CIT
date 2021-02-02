package com.example.cit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id_teach, name_teach, surname_teach, patronymic_teach;

    CustomAdapter(Activity activity, Context context, ArrayList id_teach,
                  ArrayList name_teach,
                  ArrayList surname_teach,
                  ArrayList patronymic_teach){

        this.activity = activity;
        this.context = context;
        this.id_teach = id_teach;
        this.name_teach = name_teach;
        this.surname_teach = surname_teach;
        this.patronymic_teach = patronymic_teach;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_adapter, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_teach_txt.setText(String.valueOf(id_teach.get(position)));
        holder.name_teach_txt.setText(String.valueOf(name_teach.get(position)));
        holder.surname_teach_txt.setText(String.valueOf(surname_teach.get(position)));
        holder.patronymic_teach_txt.setText(String.valueOf(patronymic_teach.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTeacher.class);
                intent.putExtra("id", String.valueOf(id_teach.get(position)));
                intent.putExtra("name", String.valueOf(name_teach.get(position)));
                intent.putExtra("surname", String.valueOf(surname_teach.get(position)));
                intent.putExtra("patronymic", String.valueOf(patronymic_teach.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id_teach.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_teach_txt, name_teach_txt, surname_teach_txt, patronymic_teach_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_teach_txt = itemView.findViewById(R.id.id_teach);
            name_teach_txt = itemView.findViewById(R.id.name_teach);
            surname_teach_txt = itemView.findViewById(R.id.surname_teach);
            patronymic_teach_txt = itemView.findViewById(R.id.patronymic_teach);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

