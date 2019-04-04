package com.example.managespending;

import android.content.Context;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class History_fragment extends Fragment {
    ListView listView;
    List<Model_if> modelIfList;
    Mysqlite database ;
    Adapter_history adapter_info;
    String Today;
    Date today;
    Date date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history_fragment, container, false);
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int Year  = calendar.get(Calendar.YEAR );
        int Month = calendar.get(Calendar.MONTH) ;
        int Day = calendar.getMaximum(Calendar.DATE);

        calendar.set(Year,Month -1,Day);



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Today = simpleDateFormat.format(calendar.getTime());

        try {
            today =  simpleDateFormat.parse(Today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        database = new Mysqlite(getContext(),"SPEND.splite",null,1);
        listView = view.findViewById(R.id.List_view_History);
        modelIfList = new ArrayList<>();
        Cursor cursor = database.getData("SELECT * FROM Spend  ");
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
            try {
                 date = simpleDateFormat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date.compareTo(today) < 0)
            {
                time = simpleDateFormat.format(date)+"";
                modelIfList.add(new Model_if(Id,product,price,location,Note,Reason,currency,time));
            }


        }
//        adapter_info.notifyDataSetChanged();

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter_info = new Adapter_history(getContext(),R.layout.report_history_layout,modelIfList);
        listView.setAdapter(adapter_info);


    }
}
