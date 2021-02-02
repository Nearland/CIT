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

public class Practice extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button_practice;

    MyDatabaseHelper myDBH;
    ArrayList<String> practice_id, name_practice;
    CustomAdapterForPractice customAdapterForPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        recyclerView = findViewById(R.id.recyclerView_practice);
        add_button_practice = findViewById(R.id.add_button_practice);
        add_button_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Practice.this, Add_practice.class);
                startActivity(intent);
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Practice.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        recreate();
                    }
                });
            }
        }, 2000);

        myDBH = new MyDatabaseHelper(Practice.this);
        practice_id = new ArrayList<>();
        name_practice = new ArrayList<>();

        StoreDataPractice();

        customAdapterForPractice = new CustomAdapterForPractice(Practice.this, this, practice_id, name_practice);
        recyclerView.setAdapter(customAdapterForPractice);
        recyclerView.setLayoutManager(new LinearLayoutManager(Practice.this));
    }

    void StoreDataPractice(){
        Cursor cursor = myDBH.readAllDataPractice();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                practice_id.add(cursor.getString(0));
                name_practice.add(cursor.getString(1));

            }
        }
    }
}