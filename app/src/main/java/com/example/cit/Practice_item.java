package com.example.cit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Practice_item extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2;
    FloatingActionButton add_button_practice_item;

    MyDatabaseHelper myDBH;
    ArrayList<String> surname_teacher, surname_student, date_finish, date_start, mark;
    CustomAdapterItem customAdapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_item);

        recyclerView = findViewById(R.id.recyclerView_practice_item);
        add_button_practice_item = findViewById(R.id.add_button_practice_item);
        add_button_practice_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Practice_item.this, Add_practice_item.class);
                startActivity(intent);
            }
        });


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Practice_item.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        recreate();
                    }
                });
            }
        }, 2000);

        myDBH = new MyDatabaseHelper(Practice_item.this);
        //practice_id = new ArrayList<>();
        date_start = new ArrayList<>();
        date_finish = new ArrayList<>();
        surname_teacher = new ArrayList<>();
        surname_student = new ArrayList<>();
        mark = new ArrayList<>();

        StoreDataPracticeItem();

        customAdapterItem = new CustomAdapterItem(Practice_item.this, this, surname_teacher, date_start,date_finish, surname_student, mark);
        recyclerView.setAdapter(customAdapterItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(Practice_item.this));

    }

    void StoreDataPracticeItem(){

        Cursor cursor = myDBH.readAllDataPracticeItem();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                //practice_id.add(cursor.getString(0));

                surname_teacher.add(cursor.getString(0));
                date_start.add(cursor.getString(3));
                date_finish.add(cursor.getString(1));
                surname_student.add(cursor.getString(2));
                mark.add(cursor.getString(4));



            }
        }
    }

}