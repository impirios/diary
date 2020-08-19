package com.example.diary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "TaskManager";
    public static final int VERSION = 1;
    private  Context context;
    String UT_NAME = "USERS";
    String NM_COLUMN = "USER_NAME";
    String DOJ = "DateUserCreated";
    String TB_NAME = "TASKS";
    String ID_COLUMN = "TASK_ID";
    String NAME_COLUMN = "TASK_NAME";
    String STATUS_COLUMN = "TASK_STATUS";
    String YEAR_COLUMN = "YEAR";
    String DATE_COLUMN = "DATE";
    String MONTH_COLUMN = "MONTH";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTableQuery = "create table "+TB_NAME+"("+ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+NAME_COLUMN +" TEXT,"+STATUS_COLUMN+" INTEGER,"+YEAR_COLUMN+" INTEGER,"+MONTH_COLUMN +" INTEGER,"+DATE_COLUMN+" INTEGER);";
        sqLiteDatabase.execSQL(CreateTableQuery);
    }

    void AddTask(Task t)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN,t.getTaskName());
        cv.put(STATUS_COLUMN,0);
        cv.put(YEAR_COLUMN,t.getDate().getYear());
        cv.put(MONTH_COLUMN,t.getDate().getMonth());
        cv.put(DATE_COLUMN,t.getDate().getDay());
        db.insert(TB_NAME,null,cv);
    }

    ArrayList<Task> getTasks(date today)
    {
        ArrayList<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TB_NAME+" WHERE("+YEAR_COLUMN+"="+today.getYear()+" AND "+MONTH_COLUMN+"="+today.getMonth()+" AND "+DATE_COLUMN+"="+today.getDay()+");";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do {
                Task t = new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),new date(Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5))));
                tasks.add(t);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    ArrayList<Task> getAllTasks()
    {
        ArrayList<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TB_NAME+" ORDER BY "+ID_COLUMN+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do {
                Task t = new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),new date(Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5))));
                tasks.add(t);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
    }
}
