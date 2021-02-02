package com.example.cit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_practice extends AppCompatActivity {
    EditText name_practice;
    Button bn_add_practice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_practice);

        name_practice = findViewById(R.id.name_practice);

        bn_add_practice = findViewById(R.id.bn_add_practice);
        bn_add_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_practice.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заполните все поля", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    MyDatabaseHelper myDBH = new MyDatabaseHelper(Add_practice.this);
                    myDBH.addPractice(name_practice.getText().toString().trim());

                    name_practice.setText("");
                }

            }
        });

        ActionBar ab = getSupportActionBar(); // кнопка назад на toolbar
        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){ // закрывает активити
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}