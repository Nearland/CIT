package com.example.cit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_students_Item extends AppCompatActivity {

    LinearLayout layoutList;
    Button plus_student, add_student;
    Spinner students;
    EditText mark, numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students__item);

        students = findViewById(R.id.spinner_stud);
        //students = findViewById(R.id.spinner_student);
        mark = findViewById(R.id.editText_mark);
        //mark = findViewById(R.id.edit_mark);
        plus_student = findViewById(R.id.button_one_student);
        add_student = findViewById(R.id.button_stud);
        layoutList = findViewById(R.id.Layout_stud);
        numb = findViewById(R.id.Number_students);

        MyDatabaseHelper db = new MyDatabaseHelper(Add_students_Item.this);
        //ArrayList<String> listSurS = db.getAllSpinnerStudent();
       // Spinner spinnerS = findViewById(R.id.spinner_stud);
       // ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this, R.layout.custom_adapter_item2, R.id.student_item, listSurS);
       // spinnerS.setAdapter(adapterS);

       // View cricketerView = getLayoutInflater().inflate(R.layout.add_stud, null, false);
       // EditText editText = (EditText) cricketerView.findViewById(R.id.edit_mark);
       // Spinner spinnerSS = cricketerView.findViewById(R.id.spinner_student);
        //ArrayList<String> listSurSr = db.getAllSpinnerStudent();
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Add_students_Item.this, R.layout.custom_adapter_item2, R.id.student_item, listSurSr);
        //spinnerSS.setAdapter(arrayAdapter);

        plus_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   int a = Integer.parseInt(numb.getText().toString());
              //  for (int i = 1; i <= a; i++) {
                ArrayList<String> listSurS = db.getAllSpinnerStudent();

                View cricketerView = getLayoutInflater().inflate(R.layout.add_stud, null, false);
                EditText editText = (EditText) cricketerView.findViewById(R.id.edit_mark);
                Spinner spinnerSS = cricketerView.findViewById(R.id.spinner_student);

                ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Add_students_Item.this, R.layout.custom_adapter_item2, R.id.student_item, listSurS);
                spinnerSS.setAdapter(arrayAdapter);

                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            removeView(cricketerView);
                        }
                });
                layoutList.addView(cricketerView);
             //   }
            }
        });

        ArrayList<String> listSurSs = db.getAllSpinnerStudent();
        final View cricketerView = getLayoutInflater().inflate(R.layout.add_stud, null, false);
        EditText editText = (EditText) cricketerView.findViewById(R.id.edit_mark);
        Spinner spinnerSS = cricketerView.findViewById(R.id.spinner_student);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Add_students_Item.this, R.layout.custom_adapter_item2, R.id.student_item, listSurSs);
        spinnerSS.setAdapter(arrayAdapter);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // int a = Integer.parseInt(numb.getText().toString());
               // for (int i = 1; i <= a; i++) {
                db.addPracticeItem2(spinnerSS.getSelectedItem().toString(), editText.getText().toString());
               // }


               // MyDatabaseHelper myDBH = new MyDatabaseHelper(Add_students_Item.this);

             //   myDBH.addPracticeItem2(spinnerS.getSelectedItem().toString(), mark.getText().toString());

             //   myDBH.addPracticeItem2(spinnerSS.getSelectedItem().toString(), editText.getText().toString());


            }
        });
    }
    private void removeView(View view){

        layoutList.removeView(view);
    }

}