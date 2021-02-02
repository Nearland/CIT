package com.example.cit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Practicee.db"; // НАЗВАНИЕ БАЗЫ ДАННЫХ
    private static final int DATABASE_VERSION = 69; // ВЕРСИЯ БАЗЫ ДАННЫХ

    private static final String TABLE_NAME = "Teachers";// НАЗВАНИЕ ТАБЛИЦЫ
    private static final String COLUMN_ID = "_id"; // КОЛОНКА ID
    private static final String COLUMN_NAME_TEACHER = "name_teacher"; // поля
    private static final String COLUMN_SURNAME_TEACHER = "surname_teacher";
    private static final String COLUMN_PATRONYMIC_TEACHER = "patronymic_teacher";

    private static final String TABLE_NAME2 = "Students";// НАЗВАНИЕ ТАБЛИЦЫ
    private static final String COLUMN_ID2 = "_id"; // КОЛОНКА ID
    private static final String COLUMN_NAME_STUDENT = "name_student"; // поля
    private static final String COLUMN_SURNAME_STUDENT = "surname_student";
    private static final String COLUMN_PATRONYMIC_STUDENT = "patronymic_student";

    private static final String TABLE_NAME3 = "Practice";// НАЗВАНИЕ ТАБЛИЦЫ
    private static final String COLUMN_ID3 = "_id"; // КОЛОНКА ID
    private static final String COLUMN_NAME_PRACTICE = "name_practice"; // поля
    private static final String COLUMN_STATUS = "column_status";
    private static final String COLUMN_MARK = "column_mark";
    private static final String COLUMN_SURNAME_TEACHER_OF_PRACTICE = "surname_teacher_of_practice";
    private static final String COLUMN_SURNAME_STUDENT_OF_PRACTICE = "surname_student_of_practice";
   // private static final String COLUMN_ID_auth = "auth_id";
    private static final String COLUMN_PRACTICE_PERIOD = "column_practice_period";
    private static final String COLUMN_PRACTICE_PERIOD2 = "column_practice_period2";

    private static final String TABLE_NAME4 = "PracticeItem";// НАЗВАНИЕ ТАБЛИЦЫ
    private static final String COLUMN_ID4 = "_id"; // КОЛОНКА ID
    private static final String COLUMN_ID_NAME_PRACTICE = "_idd"; // КОЛОНКА ID
    private static final String COLUMN_PRACTICE_PERIOD_ITEM = "column_practice_period";
    private static final String COLUMN_PRACTICE_PERIOD_ITEM2 = "column_practice_period2";
    private static final String COLUMN_SURNAME_TEACHER_OF_PRACTICE_ITEM = "surname_teacher_of_practice_item";
    private static final String COLUMN_SURNAME_STUDENT_OF_PRACTICE_ITEM = "surname_student_of_practice_item";
    private static final String COLUMN_MARK_ITEM = "mark";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // SQL СОЗДАНИЕ ТАБЛИЦЫ
                COLUMN_NAME_TEACHER + " TEXT, " +
                COLUMN_SURNAME_TEACHER + " TEXT, " +
                COLUMN_PATRONYMIC_TEACHER + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // SQL СОЗДАНИЕ ТАБЛИЦЫ
                COLUMN_NAME_STUDENT + " TEXT, " +
                COLUMN_SURNAME_STUDENT + " TEXT, " +
                COLUMN_PATRONYMIC_STUDENT + " TEXT);";

        String query3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // SQL СОЗДАНИЕ ТАБЛИЦЫ
                COLUMN_NAME_PRACTICE + " TEXT, " +
                COLUMN_SURNAME_TEACHER_OF_PRACTICE + " TEXT, " +
                COLUMN_MARK + " INTEGER, " +
                COLUMN_PRACTICE_PERIOD + " INTEGER, " +
                COLUMN_PRACTICE_PERIOD2 + " INTEGER, " +
          //      COLUMN_ID_auth + " INTEGER, " +
                COLUMN_STATUS + " INTEGER DEFAULT 0, " +
                COLUMN_SURNAME_STUDENT_OF_PRACTICE + " TEXT);";

//"FOREIGN KEY ("+ COLUMN_ID_auth +") REFERENCES "+ TABLE_NAME4 +"(_id))";
        String query4 = "CREATE TABLE " + TABLE_NAME4 + " (" +
                COLUMN_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // SQL СОЗДАНИЕ ТАБЛИЦЫ
                COLUMN_ID_NAME_PRACTICE + " INTEGER, " +
                COLUMN_PRACTICE_PERIOD_ITEM + " INTEGER, " +
                COLUMN_PRACTICE_PERIOD_ITEM2 + " INTEGER, " +
                COLUMN_SURNAME_STUDENT_OF_PRACTICE_ITEM + " TEXT, " +
                COLUMN_MARK_ITEM + " INTEGER, " +
                COLUMN_SURNAME_TEACHER_OF_PRACTICE_ITEM + " TEXT);";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    void addTeacher(String name, String surname, String patronymic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_TEACHER, name);
        cv.put(COLUMN_SURNAME_TEACHER, surname);
        cv.put(COLUMN_PATRONYMIC_TEACHER, patronymic);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Преподаватель добавлен", Toast.LENGTH_SHORT).show();
        }

    }

    void addStudent(String name, String surname, String patronymic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_STUDENT, name);
        cv.put(COLUMN_SURNAME_STUDENT, surname);
        cv.put(COLUMN_PATRONYMIC_STUDENT, patronymic);

        long result = db.insert(TABLE_NAME2, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Студент добавлен", Toast.LENGTH_SHORT).show();
        }

    }

    void addPractice(String name_practice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_PRACTICE, name_practice);

        long result = db.insert(TABLE_NAME3, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Практика добавленна", Toast.LENGTH_SHORT).show();
        }
    }

    void addPracticeItem(String surnameTeacher) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SURNAME_TEACHER_OF_PRACTICE_ITEM, surnameTeacher);

        long result = db.insert(TABLE_NAME4, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Данные добавленны", Toast.LENGTH_SHORT).show();
        }
    }

    void addPracticeItemDate( String DateStart, String DateFinish) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_PRACTICE_PERIOD_ITEM, DateStart);
        cv.put(COLUMN_PRACTICE_PERIOD_ITEM2, DateFinish);

        long result = db.insert(TABLE_NAME4, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Данные добавленны", Toast.LENGTH_SHORT).show();
        }
    }

    void addPracticeItem2( String surnameStudent, String mark) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SURNAME_STUDENT_OF_PRACTICE_ITEM, surnameStudent);
        cv.put(COLUMN_MARK_ITEM, mark);

        long result = db.insert(TABLE_NAME4, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Студент добавлен", Toast.LENGTH_SHORT).show();
        }
    }

    void addPracticeItem3( String surnameStudent, String mark) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SURNAME_STUDENT_OF_PRACTICE_ITEM, surnameStudent);
        cv.put(COLUMN_MARK_ITEM, mark);

        long result = db.insert(TABLE_NAME4, null, cv);

        if(result == -1){
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Студент добавлен", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    Cursor readAllDataStudent(){
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    Cursor readAllDataPractice(){
        String query = "SELECT _id, name_practice FROM " + TABLE_NAME3;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    Cursor readAllDataPracticeItem(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT surname_teacher_of_practice_item, column_practice_period, " +
                "column_practice_period2, surname_student_of_practice_item, mark FROM " + TABLE_NAME4;
//column_practice_period,column_practice_period2,surname_teacher_of_practice_item,surname_student_of_practice_item
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id,String name, String surname, String patronymic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_NAME_TEACHER, name);
        cv.put(COLUMN_SURNAME_TEACHER, surname);
        cv.put(COLUMN_PATRONYMIC_TEACHER, patronymic);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Обновлено", Toast.LENGTH_SHORT).show();
        }
    }

    void updateDataStudents(String row_id2,String name2, String surname2, String patronymic2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_NAME_STUDENT, name2);
        cv.put(COLUMN_SURNAME_STUDENT, surname2);
        cv.put(COLUMN_PATRONYMIC_STUDENT, patronymic2);

        long result = db.update(TABLE_NAME2, cv, "_id=?", new String[]{row_id2});

        if(result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Обновлено", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Ошибка удаления", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteDataStudents(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Ошибка удаления", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show();
        }
    }

   /* void insertSpinnerTeacher(String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues value;
        try {
            value = new ContentValues();
            value.put(COLUMN_SURNAME_TEACHER, surname);
            db.insert(TABLE_NAME3, null, value);
            db.setTransactionSuccessful();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }

    void insertSpinnerStudents(String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues value;
        try {
            value = new ContentValues();
            value.put(COLUMN_SURNAME_STUDENT, surname);
            db.insert(TABLE_NAME3, null, value);
            db.setTransactionSuccessful();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }*/

    public ArrayList<String> getAllSpinnerTeacher(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String select = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(select, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String surname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME_TEACHER));
                    list.add(surname);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public ArrayList<String> getAllSpinnerStudent(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String select = "SELECT " + COLUMN_SURNAME_STUDENT + " FROM " + TABLE_NAME2;
            Cursor cursor = db.rawQuery(select, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String surname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME_STUDENT));
                    list.add(surname);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public ArrayList<String> getAllMarkStudent(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String select = "SELECT " + COLUMN_MARK_ITEM + " FROM " + TABLE_NAME4;
            Cursor cursor = db.rawQuery(select, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String mark = cursor.getString(cursor.getColumnIndex(COLUMN_MARK_ITEM));
                    list.add(mark);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

}
