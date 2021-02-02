package com.example.cit;

import androidx.annotation.Nullable;
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

public class Teacher extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDBH;
    ArrayList<String> teacher_id, name_teacher, surname_teacher, patronymic_teacher;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Teacher.this, Add_teacher.class);
                startActivity(intent);
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Teacher.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        recreate();
                    }
                });
            }
        }, 2000);

        myDBH = new MyDatabaseHelper(Teacher.this);
        teacher_id = new ArrayList<>();
        name_teacher = new ArrayList<>();
        surname_teacher = new ArrayList<>();
        patronymic_teacher = new ArrayList<>();

        StoreDataTeacher();

        customAdapter = new CustomAdapter(Teacher.this, this, teacher_id, name_teacher, surname_teacher, patronymic_teacher);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Teacher.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void StoreDataTeacher(){
        Cursor cursor = myDBH.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                teacher_id.add(cursor.getString(0));
                name_teacher.add(cursor.getString(1));
                surname_teacher.add(cursor.getString(2));
                patronymic_teacher.add(cursor.getString(3));
            }
        }
    }

}