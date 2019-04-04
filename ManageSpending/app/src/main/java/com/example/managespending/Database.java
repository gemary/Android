package com.example.managespending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database  extends SQLiteOpenHelper {
    // TEN DATABASE
    public  static  final String DataBName = "Spend";
//
//    // FIELD OF TABLE
//    public  static  final String TABLENAME ="Spending";
//    public  static  final String ID ="Id";
//    public  static  final String PRODUCT ="Product";
//    public  static  final String PRICE ="Price";
//    public  static  final String LOCALTION = "Location";
//    public  static  final String CURRENCY ="Currency";
//    public  static  final String NOTE ="Note";
//    public  static  final String REASON ="Reason";
//    public  static  final String TIME ="Time";

    public SQLiteDatabase database;

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


//

    public Database(Context context) {
        super(context, DataBName, null, 1);
    }

    public  void queryData (String query)
    {
        SQLiteDatabase database =  getWritableDatabase();
        database.execSQL(query);
    }

    public  Cursor getDATA(String query)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String ctbill = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s TEXT,%s INTEGER ,%s TEXT,%s TEXT, %s TEXT,%s TEXT,%s TEXT)",
//                TABLENAME,ID,PRODUCT,PRICE,LOCALTION,CURRENCY,NOTE,REASON,TIME);
//
//        db.execSQL(ctbill);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
//    public void OpenDB()
//    {
//        database = getReadableDatabase();
//    }
//
//    public void CloseDB()
//    {
//        if (database != null)
//        {
//            database.close();
//        }
//    }
//
//    public List<Model_if> GetData()
//    {
//        List<Model_if> modelIfs = new ArrayList<>();
//        String query = String.format("SELECT * FROM  %s",TABLENAME);
//
//        Cursor cursor = database.rawQuery(query,null);
//
//        if (cursor.moveToFirst())
//        {
//            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
//            {
//                Model_if item = new Model_if();
//                item.setProduct(cursor.getString(cursor.getColumnIndex(PRODUCT)));
//                item.setCost(cursor.getString(cursor.getColumnIndex(PRICE)));
//                item.setLocal(cursor.getString(cursor.getColumnIndex(LOCALTION)));
//                item.setCurrency(cursor.getString(cursor.getColumnIndex(CURRENCY)));
//                item.setNote(cursor.getString(cursor.getColumnIndex(NOTE)));
//                item.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
//                item.setReason(cursor.getString(cursor.getColumnIndex(REASON)));
//            }
//        }
//        return modelIfs;
//    }
//
//
//
//
//
//    public void  addItems (Model_if item)
//    {
//        ContentValues Values = new ContentValues();
//        Values.put(PRODUCT,item.getProduct());
//        Values.put(PRICE,item.getCost());
//        Values.put(LOCALTION,item.getLocal());
//        Values.put(REASON,item.getReason());
//        Values.put(NOTE,item.getNote());
//        Values.put(TIME,item.getTime());
//
//
//        database.insert(TABLENAME,null,Values);
//
//    }

}
