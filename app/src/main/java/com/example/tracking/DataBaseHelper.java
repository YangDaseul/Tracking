package com.example.tracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Tracking.db";
    public static final String TABLE_NAME = "test_info";

    public static final String COL_1 = "START_TIME";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "MEMO";
    public static final String COL_4 = "AGE";
    public static final String COL_5 = "GENDER";
    public static final String COL_6 = "HEIGHT";
    public static final String COL_7 = "WEIGHT";
    public static final String COL_8 = "NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(START_TIME TEXT PRIMARY KEY, TITLE TEXT, MEMO TEXT, AGE TEXT, GENDER TEXT, HEIGHT TEXT, WEIGHT TEXT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String startTime, String title, String memo, String age, String gender, String height, String weight, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, startTime);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, memo);
        contentValues.put(COL_4, age);
        contentValues.put(COL_5, gender);
        contentValues.put(COL_6, height);
        contentValues.put(COL_7, weight);
        contentValues.put(COL_8, name);

        long result = db.insertOrThrow(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Cursor readData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }


}
