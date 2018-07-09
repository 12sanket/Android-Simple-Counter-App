package com.sanket.simplecounterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Saved_data.db";
    private static final String TABLE_NAME = "Data_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "COUNTS";
    private static final String COL_3 = "NAME";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COUNTS INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String counts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,counts);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }




    public Cursor getAllData(SQLiteDatabase db){

        Cursor cursor;
        String[] Projections = {DatabaseHelper.COL_1,DatabaseHelper.COL_2,DatabaseHelper.COL_3};

        cursor = db.query(DatabaseHelper.TABLE_NAME, Projections, null, null, null,
                null, DatabaseHelper.COL_1+" DESC");

        return cursor;

    }

}
