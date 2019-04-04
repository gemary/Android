package com.example.managespending;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Main_Frament extends Fragment {
    ListView listView;
    List<Model_if> modelIfList;
    Mysqlite database ;
    Adapter_history adapter_info;
    TextView tv_count;
    int count =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main__frament, container, false);
        database = new Mysqlite(getContext(),"SPEND.splite",null,1);
//        tv_count = view.findViewById(R.id.tv_count);
        listView = view.findViewById(R.id.List_View);
        modelIfList = new ArrayList<>();


        Cursor cursor = database.getData("SELECT * FROM Spend");
        while (cursor.moveToNext())
        {
            String product = cursor.getString(cursor.getColumnIndex("Product"));
             int Id = cursor.getInt(cursor.getColumnIndex("Id"));
            String price = cursor.getString(cursor.getColumnIndex("Price"));
            String location = cursor.getString(cursor.getColumnIndex("Location"));
            String Note = cursor.getString(cursor.getColumnIndex("Note"));
            String Reason = cursor.getString(cursor.getColumnIndex("Reason"));
            String currency = cursor.getString(cursor.getColumnIndex("Currency"));
            String time = cursor.getString(cursor.getColumnIndex("Time"));
            modelIfList.add(new Model_if(Id,product,price,location,Note,Reason,currency,time));

        }
//        adapter_info.notifyDataSetChanged();


        return  view;
    }



    private  void changeData() {
        Cursor cursor = database.getData("SELECT * FROM Spend");
        adapter_info.clear();
            while (cursor.moveToNext()) {
                String product = cursor.getString(cursor.getColumnIndex("Product"));
                int Id = cursor.getInt(cursor.getColumnIndex("Id"));
                String price = cursor.getString(cursor.getColumnIndex("Price"));
                String location = cursor.getString(cursor.getColumnIndex("Location"));
                String Note = cursor.getString(cursor.getColumnIndex("Note"));
                String Reason = cursor.getString(cursor.getColumnIndex("Reason"));
                String currency = cursor.getString(cursor.getColumnIndex("Currency"));
                String time = cursor.getString(cursor.getColumnIndex("Time"));
                modelIfList.add(new Model_if(Id,product,price,location,Note,Reason,currency,time));
            }

        adapter_info.notifyDataSetChanged();

    }
    @Override
    public void onStart() {
        super.onStart();

        adapter_info = new Adapter_history(getContext(),R.layout.report_history_layout,modelIfList);
        listView.setAdapter(adapter_info);


    }

}
