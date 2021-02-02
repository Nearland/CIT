package com.example.cit;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class CustomAdapterItem extends RecyclerView.Adapter<ViewHolder> {


    private Context context;
    Activity activity;
    private ArrayList  teacher, student, DateStart, DateFinish, mark;

    CustomAdapterItem(Activity activity, Context context,ArrayList teacher, ArrayList student, ArrayList DateStart, ArrayList DateFinish,  ArrayList mark){

        this.activity = activity;
        this.context = context;
       // this.id_practiceItem = id_practiceItem;
        this.teacher = teacher;
        this.student = student;
        this.DateStart = DateStart;
        this.DateFinish = DateFinish;
        this.mark = mark;
    }

    @Override
    public int getItemViewType(int position){

        /*if(position == 0){
            return 0;
        }
        return 1;*/

        switch (position) {
            case 0:
               return  0;
            case 1:
               return  1;
            default:
               return  2;
        }
       // return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view, view2, view1;


        switch (viewType) {
            case 0:
                System.out.println("Second");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter_item,parent,false);
                return new CustomAdapterItem.MyPracticeItemHolder(view);
            case 1:
                System.out.println("First");
                view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter_item_date,parent,false);
                return new MyPracticeItemHolder3(view2);
            case 2:
                System.out.println("Third");
                view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter_item2,parent,false);
                return new MyPracticeItemHolder2(view1);

         //   default: throw new IllegalArgumentException();
        }

        return null;

        /*View view;
        switch (viewType){

            case(Constant.TEACHER) :
                view = LayoutInflater.from(context).inflate(R.layout.custom_adapter_item,parent,false);
                return new MyPracticeItemHolder(view);
            case (Constant.DATE) :
                view = LayoutInflater.from(context).inflate(R.layout.custom_adapter_item_date,parent,false);
                return new MyPracticeItemHolder3(view);
            case (Constant.STUDENT) :
                view = LayoutInflater.from(context).inflate(R.layout.custom_adapter_item2,parent,false);
                return new MyPracticeItemHolder2(view);

            default: throw new IllegalArgumentException();
        }*/
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:
                System.out.println("2");
                MyPracticeItemHolder myPracticeItemHolder =(MyPracticeItemHolder) holder;
                myPracticeItemHolder.teacher_txt.setText(String.valueOf(teacher.get(position)));
                break;
            case 1:
                System.out.println("1");
                MyPracticeItemHolder3 myPracticeItemHolder3 = (MyPracticeItemHolder3) holder;
                myPracticeItemHolder3.DateStart_txt.setText(String.valueOf(DateStart.get(position)));
                myPracticeItemHolder3.DateFinish_txt.setText(String.valueOf(DateFinish.get(position)));
                break;
            case 2:
                System.out.println("3");
                MyPracticeItemHolder2 myPracticeItemHolder2 =(MyPracticeItemHolder2) holder;
                myPracticeItemHolder2.student_txt.setText(String.valueOf(student.get(position)));
                myPracticeItemHolder2.mark_txt.setText(String.valueOf(mark.get(position)));
                break;
        }

      //  holder.teacher_txt.setText(String.valueOf(teacher.get(position)));
    }



    @Override
    public int getItemCount() {

        return student.size();
    }


    public class MyPracticeItemHolder2 extends ViewHolder {
        TextView  student_txt, mark_txt;

        public MyPracticeItemHolder2(@NonNull View itemView) {
            super(itemView);
            student_txt = itemView.findViewById(R.id.student_item);
            mark_txt = itemView.findViewById(R.id.mark_item);
        }
    }

    public class MyPracticeItemHolder extends ViewHolder {
        TextView  teacher_txt;

        public MyPracticeItemHolder(@NonNull View itemView) {
            super(itemView);

            teacher_txt = itemView.findViewById(R.id.teacher_item);
            }
        }

    public class MyPracticeItemHolder3 extends ViewHolder {
        TextView DateStart_txt, DateFinish_txt;

        public MyPracticeItemHolder3(@NonNull View itemView) {
            super(itemView);

            DateFinish_txt = itemView.findViewById(R.id.date_start_item);
            DateStart_txt = itemView.findViewById(R.id.date_finish_item);

        }
    }
}
