package com.example.cit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_teacher extends AppCompatActivity {

    EditText name_teacher, surname_teacher, patronymic_teacher;
    Button bn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        name_teacher = findViewById(R.id.name_teacher);
        surname_teacher = findViewById(R.id.surname_teacher);
        patronymic_teacher = findViewById(R.id.patronymic_teacher);

        bn_add = findViewById(R.id.bn_add);
        bn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_teacher.getText().toString().isEmpty() || surname_teacher.getText().toString().isEmpty()
                        || patronymic_teacher.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заполните все поля", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    MyDatabaseHelper myDBH = new MyDatabaseHelper(Add_teacher.this);
                    myDBH.addTeacher(name_teacher.getText().toString().trim(),
                            surname_teacher.getText().toString().trim(),
                            patronymic_teacher.getText().toString().trim());

                    name_teacher.setText("");
                    surname_teacher.setText(""); // после нажатия кнопки все стиралось
                    patronymic_teacher.setText("");
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
