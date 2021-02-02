package com.example.cit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Add_practice_item extends AppCompatActivity {

    Button bn_add_practice_item, bn_add_students;
    EditText NumberStudents, DateStart, DateFinish; // обявление editText



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_practice_item);

        //LinearLayout linearLayout = findViewById(R.id.scrollliner);
        DateFinish = findViewById(R.id.DateFinish); // инициализация editText
        DateStart = findViewById(R.id.DateStart);
        bn_add_students = findViewById(R.id.button_Add_students);

        bn_add_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Add_practice_item.this, Add_students_Item.class);
                startActivity(intent);
            }
        });



       /* add_stud = findViewById(R.id.add_stud);
        add_stud.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(NumberStudents.getText().toString());
                MyDatabaseHelper dbb = new MyDatabaseHelper(Add_practice_item.this);

                 for (int i = 1; i <= a; i++) {

                     ArrayList<String> listSurSS = dbb.getAllSpinnerStudent();
                   //  Spinner spinnerSS = new Spinner(Add_practice_item.this);
                  //   ArrayAdapter<String> adapterSS = new ArrayAdapter<String>(Add_practice_item.this, R.layout.custom_adapter_item2, R.id.student_item, listSurSS);
                   //  spinnerSS.setAutofillHints(String.valueOf(i));
                  //   spinnerSS.setAdapter(adapterSS);
                  //   linearLayout.addView(spinnerSS);

                     View markView = getLayoutInflater().inflate(R.layout.add_students_item_colvo, null, false);

                     EditText editText = markView.findViewById(R.id.edit_mark);


                     Spinner spinnerS = markView.findViewById(R.id.spinner_for_student_col);
                     //Spinner spinnerS = new Spinner(Add_practice_item.this);
                     ArrayAdapter<String> adapterS = new ArrayAdapter<String>(Add_practice_item.this, R.layout.custom_adapter_item2, R.id.student_item, listSurSS);
                     spinnerS.setAutofillHints(String.valueOf(i));
                     spinnerS.setAdapter(adapterS);
                     linearLayout.addView(markView);
                     // ArrayList<String> listMark = dbb.getAllMarkStudent();
                     //EditText marks = new EditText(Add_practice_item.this);
                    // ArrayAdapter<String> adapterMark = new ArrayAdapter<String>(Add_practice_item.this, R.layout.custom_adapter_item2, R.id.mark_item, listMark);
                   //  marks.setText(String.valueOf(i));
                    // marks.setText(marks);
                    // linearLayout.addView(marks);
                 }
            }
        });*/

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        ArrayList<String> listSurT = db.getAllSpinnerTeacher();
        Spinner spinnerT = findViewById(R.id.surname_teacher_item);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this, R.layout.custom_adapter_item, R.id.teacher_item, listSurT);
        spinnerT.setAdapter(adapterT);

        /*ArrayList<String> listSurS = db.getAllSpinnerStudent();
        Spinner spinnerS = findViewById(R.id.surname_student_item);
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this, R.layout.custom_adapter_item2, R.id.student_item, listSurS);
        spinnerS.setAdapter(adapterS);*/


        bn_add_practice_item = findViewById(R.id.bn_add_practice_item);
        bn_add_practice_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDBH = new MyDatabaseHelper(Add_practice_item.this);

                myDBH.addPracticeItem(spinnerT.getSelectedItem().toString());
                myDBH.addPracticeItemDate(DateFinish.getText().toString().trim(), DateStart.getText().toString().trim());
               // myDBH.addPracticeItem2(spinnerS.getSelectedItem().toString(),mark.getText().toString());
                //spinnerT.setAdapter(adapterT);


               /* if (NumberStudents.getText().toString().isEmpty()) {

                }
                else{
                    System.out.println(NumberStudents);
                    int a = Integer.parseInt(NumberStudents.getText().toString());
                    for (int i = 0; i <= a; i++) {
                        myDBH.addPracticeItem2(spinnerS.getSelectedItem().toString(),mark.getText().toString());
                        //spinnerS.setAdapter(adapterS);
                    }
                }*/

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