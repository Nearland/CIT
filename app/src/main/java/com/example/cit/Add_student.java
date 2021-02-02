package com.example.cit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_student extends AppCompatActivity {

    EditText name_student, surname_student, patronymic_student;
    Button bn_add2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name_student = findViewById(R.id.name_student);
        surname_student = findViewById(R.id.surname_student);
        patronymic_student = findViewById(R.id.patronymic_student);

        bn_add2 = findViewById(R.id.bn_add2);
        bn_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_student.getText().toString().isEmpty() || surname_student.getText().toString().isEmpty()
                        || patronymic_student.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заполните все поля", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    MyDatabaseHelper myDBH = new MyDatabaseHelper(Add_student.this);
                    myDBH.addStudent(name_student.getText().toString().trim(),
                            surname_student.getText().toString().trim(),
                            patronymic_student.getText().toString().trim());

                    name_student.setText("");
                    surname_student.setText(""); // после нажатия кнопки все стиралось
                    patronymic_student.setText("");
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