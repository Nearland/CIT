package com.example.cit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Students extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    CustomAdapterForStudents customAdapterForStudents;

    MyDatabaseHelper myDBH;
    ArrayList<String> student_id, name_student, surname_student, patronymic_student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView = findViewById(R.id.recyclerView2);
        add_button = findViewById(R.id.add_button2);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Students.this, Add_student.class);
                startActivity(intent);
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Students.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        recreate();
                    }
                });
            }
        }, 2000);

        myDBH = new MyDatabaseHelper(Students.this);
        student_id = new ArrayList<>();
        name_student = new ArrayList<>();
        surname_student = new ArrayList<>();
        patronymic_student = new ArrayList<>();

        StoreDataStudents();

        customAdapterForStudents = new CustomAdapterForStudents(Students.this, this, student_id, name_student, surname_student, patronymic_student);
        recyclerView.setAdapter(customAdapterForStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(Students.this));
    }

    void StoreDataStudents(){
        Cursor cursor = myDBH.readAllDataStudent();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                student_id.add(cursor.getString(0));
                name_student.add(cursor.getString(1));
                surname_student.add(cursor.getString(2));
                patronymic_student.add(cursor.getString(3));
            }
        }
    }

}