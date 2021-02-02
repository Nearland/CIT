package com.example.cit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudent extends AppCompatActivity {

    EditText nameS, surnameS, patronymicS;
    Button update_button2, delete_button2;

    String id2, namee2, surname2, patronymic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        nameS = findViewById(R.id.name_update_student);
        surnameS = findViewById(R.id.surname_update_student);
        patronymicS = findViewById(R.id.patronymic_update_student);
        update_button2 = findViewById(R.id.bn_update2);
        delete_button2 = findViewById(R.id.bn_delete2);

        getAndSetIntentDataStudents();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(namee2);
            ab.setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        update_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateStudent.this);
                namee2 = nameS.getText().toString().trim();
                surname2 = surnameS.getText().toString().trim();
                patronymic2 = patronymicS.getText().toString().trim();
                myDB.updateDataStudents(id2, namee2, surname2, patronymic2);
            }
        });

        delete_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog2();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void getAndSetIntentDataStudents(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("surname") && getIntent().hasExtra("patronymic")){
            //Getting Data from Intent
            id2 = getIntent().getStringExtra("id");
            namee2 = getIntent().getStringExtra("name");
            surname2 = getIntent().getStringExtra("surname");
            patronymic2 = getIntent().getStringExtra("patronymic");

            //Setting Intent Data
            nameS.setText(namee2);
            surnameS.setText(surname2);
            patronymicS.setText(patronymic2);
            Log.d("Log", namee2+" "+surname2+" "+patronymic2);
        }else{
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }

    //всплывающее окно с диалогом удаления
    void Dialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + namee2 + "?");
        builder.setMessage("Вы действительно хотите удалить " + namee2 + "?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateStudent.this);
                myDB.deleteDataStudents(id2);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}