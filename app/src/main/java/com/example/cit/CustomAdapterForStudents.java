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

public class CustomAdapterForStudents extends RecyclerView.Adapter<CustomAdapterForStudents.MyViewHolderStudents> {

    private Context context2;
    Activity activity2;
    private ArrayList id_stud, name_stud, surname_stud, patronymic_stud;

    CustomAdapterForStudents(Activity activity2, Context context2, ArrayList id_stud,
                  ArrayList name_stud,
                  ArrayList surname_stud,
                  ArrayList patronymic_stud){

        this.activity2 = activity2;
        this.context2 = context2;
        this.id_stud = id_stud;
        this.name_stud = name_stud;
        this.surname_stud = surname_stud;
        this.patronymic_stud = patronymic_stud;

    }

    @NonNull
    @Override
    public MyViewHolderStudents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context2);
        View view = layoutInflater.inflate(R.layout.custom_adapter_for_students, parent, false);
        return new CustomAdapterForStudents.MyViewHolderStudents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderStudents holder, int position) {
        holder.id_stud_txt.setText(String.valueOf(id_stud.get(position)));
        holder.name_stud_txt.setText(String.valueOf(name_stud.get(position)));
        holder.surname_stud_txt.setText(String.valueOf(surname_stud.get(position)));
        holder.patronymic_stud_txt.setText(String.valueOf(patronymic_stud.get(position)));
        holder.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context2, UpdateStudent.class);
                intent.putExtra("id", String.valueOf(id_stud.get(position)));
                intent.putExtra("name", String.valueOf(name_stud.get(position)));
                intent.putExtra("surname", String.valueOf(surname_stud.get(position)));
                intent.putExtra("patronymic", String.valueOf(patronymic_stud.get(position)));
                activity2.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_stud.size();
    }

    public class MyViewHolderStudents extends RecyclerView.ViewHolder {

        TextView id_stud_txt, name_stud_txt, surname_stud_txt, patronymic_stud_txt;
        LinearLayout mainLayout2;

        public MyViewHolderStudents(@NonNull View itemView) {
            super(itemView);
            id_stud_txt = itemView.findViewById(R.id.id_stud);
            name_stud_txt = itemView.findViewById(R.id.name_stud);
            surname_stud_txt = itemView.findViewById(R.id.surname_stud);
            patronymic_stud_txt = itemView.findViewById(R.id.patronymic_stud);
            mainLayout2 = itemView.findViewById(R.id.mainLayout2);
        }
    }
}
