package com.example.managespending;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mysqlite extends SQLiteOpenHelper {
    public Mysqlite( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void queryData(String query)
    {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL(query);
    }
    public Cursor getData(String query)
    {
        SQLiteDatabase sql = getReadableDatabase();

        return sql.rawQuery(query,null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
