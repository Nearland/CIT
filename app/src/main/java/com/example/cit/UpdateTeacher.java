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

public class UpdateTeacher extends AppCompatActivity {

    EditText nameT, surnameT, patronymicT;
    Button update_button, delete_button;

    String id, namee, surname, patronymic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        nameT = findViewById(R.id.name_update);
        surnameT = findViewById(R.id.surname_update);
        patronymicT = findViewById(R.id.patronymic_update);
        update_button = findViewById(R.id.bn_update);
        delete_button = findViewById(R.id.bn_delete);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(namee);
            ab.setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateTeacher.this);
                namee = nameT.getText().toString().trim();
                surname = surnameT.getText().toString().trim();
                patronymic = patronymicT.getText().toString().trim();
                myDB.updateData(id, namee, surname, patronymic);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
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

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("surname") && getIntent().hasExtra("patronymic")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            namee = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            patronymic = getIntent().getStringExtra("patronymic");

            //Setting Intent Data
            nameT.setText(namee);
            surnameT.setText(surname);
            patronymicT.setText(patronymic);
            Log.d("stev", namee+" "+surname+" "+patronymic);
        }else{
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }
    //всплывающее окно с диалогом удаления
    void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + namee + "?");
        builder.setMessage("Вы действительно хотите удалить " + namee + "?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateTeacher.this);
                myDB.deleteData(id);
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
