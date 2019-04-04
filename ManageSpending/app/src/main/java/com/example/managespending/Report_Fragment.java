package com.example.managespending;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report_Fragment extends Fragment {
    ListView listView;
    TextView tv_total_spent,tv_total;
    List<Model_if> modelIfList;
    Mysqlite database ;
    Adapter_history adapter_info;
    String First,Last;
    Date fisrtDay,Lastdate;
    Date date;
    long toatl_spent=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_, container, false);

        tv_total_spent = view.findViewById(R.id.tv_spent);
        tv_total = view.findViewById(R.id.tv_total_cost);
        tv_total.setText(Main2Activity.TOTAL+"");

        //First
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int Year  = calendar.get(Calendar.YEAR );
        int Month = calendar.get(Calendar.MONTH) ;
        int Day = calendar.getMinimum(Calendar.DATE);

        calendar.set(Year,Month,Day);

        //Second
        final java.util.Calendar calendar1 = java.util.Calendar.getInstance();
        int Year1  = calendar1.get(Calendar.YEAR );
        int Month1 = calendar1.get(Calendar.MONTH) ;
        int Day1 = calendar1.getMaximum(Calendar.DATE);

        calendar1.set(Year1,Month1,Day1);

        //format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");

        First = simpleDateFormat.format(calendar.getTime());

        Last = simpleDateFormat.format(calendar1.getTime());


        // parse string to date
        try {
            fisrtDay = simpleDateFormat.parse(First);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Lastdate = simpleDateFormat.parse(Last);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        database = new Mysqlite(getContext(),"SPEND.splite",null,1);
        listView = view.findViewById(R.id.List_View_report);
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
            long pricr = Integer.parseInt(price);

            try {
                date = simpleDateFormat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date.compareTo(fisrtDay) >= 0 && date.compareTo(Lastdate) < 0)
            {
                toatl_spent+= pricr;
                tv_total_spent.setText(toatl_spent+"");
                modelIfList.add(new Model_if(Id, product, price, location, Note, Reason, currency, time));
            }


        }
        toatl_spent =0;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter_info = new Adapter_history(getContext(),R.layout.report_history_layout,modelIfList);
        listView.setAdapter(adapter_info);


    }
}
