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

public class CustomAdapterForPractice extends RecyclerView.Adapter<CustomAdapterForPractice.MyPracticeHolder> {

    private Context context;
    Activity activity;
    private ArrayList id_practice,name_practice;

    CustomAdapterForPractice(Activity activity, Context context, ArrayList id_practice,
                  ArrayList name_practice){

        this.activity = activity;
        this.context = context;
        this.id_practice = id_practice;
        this.name_practice = name_practice;

    }

    @NonNull
    @Override
    public CustomAdapterForPractice.MyPracticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_adapter_for_practice, parent, false);
        return new CustomAdapterForPractice.MyPracticeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterForPractice.MyPracticeHolder holder, int position) {
        holder.id_practice_txt.setText(String.valueOf(id_practice.get(position)));
        holder.name_practice_txt.setText(String.valueOf(name_practice.get(position)));
        holder.mainLayoutPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Practice_item.class);
                intent.putExtra("id_practice", String.valueOf(id_practice.get(position)));
                intent.putExtra("name_practice", String.valueOf(name_practice.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return id_practice.size();
    }

    public class MyPracticeHolder extends RecyclerView.ViewHolder {

        TextView id_practice_txt, name_practice_txt;
        LinearLayout mainLayoutPractice;

        public MyPracticeHolder(@NonNull View itemView) {
            super(itemView);
            id_practice_txt = itemView.findViewById(R.id.id_practiceItem);
            name_practice_txt = itemView.findViewById(R.id.name_practice_adapter);
            mainLayoutPractice = itemView.findViewById(R.id.mainLayoutPractice);
        }
    }

}
