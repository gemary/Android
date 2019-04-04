package com.example.managespending;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    TextView total_Cost,tv_cur;
    ImageButton btn_edit_cost;
    List<Model_if> modelIfList;
    ImageButton btn_Add;
    Mysqlite database ;
    Adapter_Info adapter_info;
    SharedPreferences sharedPreferences;
    public  static final String MYRE = "MyPres";
    public  static final String TOTAL_COST = "total";
    public  static  long TOTAL =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        onsetEnvet();
        loadData();
        adapter_info = new Adapter_Info(this,R.layout.custom_list,modelIfList);
        listView.setAdapter(adapter_info);
        getDATA();
    }

    private void getDATA() {
        database = new Mysqlite(this,"SPEND.splite",null,1);

        database.queryData("CREATE TABLE IF NOT EXISTS Spend (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,Product VARCHAR,Price VARCHAR,Location VARCHAR,Note VARCHAR , Reason VARCHAR,Currency VARCHAR,Time VARCHAR)");



        Cursor cursor = database.getData("SELECT * FROM Spend");
        //adapter_info.clear();
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
    }

    private void init() {
        tv_cur =findViewById(R.id.tv_cur);
        listView = findViewById(R.id.List_View);
        modelIfList = new ArrayList<>();
        btn_Add =findViewById(R.id.btn_Add);
        btn_edit_cost = findViewById(R.id.btn_Edit_cost);
        total_Cost = findViewById(R.id.Total_cost);
    }

    private void onsetEnvet() {
        btn_edit_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog();

            }
        });
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
            modelIfList.add(new Model_if(Id, product, price, location, Note, Reason, currency, time));
        }


        adapter_info.notifyDataSetChanged();

    }


    public  void dialogUpdate(final long id,String local,String product,String price,String note,String reason,String time,String currency)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.show();
        final EditText edt_date,edt_cost,edt_Reason,edt_note,edt_product,edt_currency,edt_location;
        ImageButton btn_time_dialog,btn_submit,btn_unsubmit;



        edt_location = dialog.findViewById(R.id.edt_local);
        edt_cost = dialog.findViewById(R.id.edt_cost);
        edt_currency = dialog.findViewById(R.id.edt_currency);
        edt_note = dialog.findViewById(R.id.edt_note);
        edt_product = dialog.findViewById(R.id.edt_product);
        edt_Reason = dialog.findViewById(R.id.edt_reason);
        edt_date = dialog.findViewById(R.id.edt_date);
        btn_submit = dialog.findViewById(R.id.btn_submit);
        btn_time_dialog= dialog.findViewById(R.id.btn_time_dialog);
        btn_unsubmit = dialog.findViewById(R.id.btn_Un_submit);

        edt_product.setText(product);
        edt_cost.setText(price);
        edt_currency.setText(currency);
        edt_date.setText(time);
        edt_location.setText(local);
        edt_Reason.setText(reason);
        edt_note.setText(note);



        btn_unsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product,price,location,note,reason,currency,time;

                product = edt_product.getText().toString();
                price = edt_cost.getText().toString();
                location = edt_location.getText().toString();
                note = edt_note.getText().toString();
                reason = edt_Reason.getText().toString();
                currency = edt_currency.getText().toString();
                time = edt_date.getText().toString();

                database.queryData("UPDATE Spend SET Product = '"+product+"', Price = '"+price+"',Location = '"+location+"', Note='"+note+"',Reason = '"+reason+"',Currency = '"+currency+"', Time ='"+time+"' WHERE Id ='"+id+"' ");
                dialog.dismiss();
                changeData();

            }
        });

        btn_time_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final java.util.Calendar calendar = java.util.Calendar.getInstance();
                int Year  = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edt_date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },Year,Month,Day);
                datePickerDialog.show();


            }
        });

    }

    public  void dialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add_bill_);
        dialog.show();
        ImageButton btn_time_dialog,btn_submit;
        final   EditText edt_date,edt_cost,edt_Reason,edt_note,edt_product,edt_currency,edt_location;



        edt_location =dialog.findViewById(R.id.edt_local);
        edt_cost = dialog.findViewById(R.id.edt_cost);
        edt_currency = dialog.findViewById(R.id.edt_currency);
        edt_note = dialog.findViewById(R.id.edt_note);
        edt_product = dialog.findViewById(R.id.edt_product);
        edt_Reason = dialog.findViewById(R.id.edt_reason);
        edt_date = dialog.findViewById(R.id.edt_date);
        btn_submit = dialog.findViewById(R.id.btn_submit);
        btn_time_dialog= dialog.findViewById(R.id.btn_time_dialog);

        btn_time_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final java.util.Calendar calendar = java.util.Calendar.getInstance();
                int Year  = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edt_date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },Year,Month,Day);
                datePickerDialog.show();


            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product,price,location,note,reason,currency,time;
                product = edt_product.getText().toString();
                price = edt_cost.getText().toString();
                location = edt_location.getText().toString();
                note = edt_note.getText().toString();
                reason = edt_Reason.getText().toString();
                currency = edt_currency.getText().toString();
                time = edt_date.getText().toString();
                sharedPreferences =  getSharedPreferences(MYRE, Context.MODE_PRIVATE);
                int a = Integer.parseInt(price);
                int b  = sharedPreferences.getInt(TOTAL_COST,0);
                int result = b - a;
                saveData(result);
                loadData();


                if(product.isEmpty() || price.isEmpty() || location.isEmpty() || note.isEmpty()|| time.isEmpty() || currency.isEmpty() )
                {
                    Toast.makeText(Main2Activity.this, "Fill in Field", Toast.LENGTH_SHORT).show();
                }
                else {

                    database.queryData("INSERT INTO Spend VALUES(null,'"+product+"','"+price+"','"+location+"','"+reason+"','"+currency+"','"+note+"','"+time+"')");
                    dialog.dismiss();
                }


                changeData();

            }
        });
    }

    public  void dialogdelete(final int id)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to Delete?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.queryData(" DELETE FROM Spend WHERE Id = '"+ id+"'");
                changeData();

            }
        });


        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ex,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu)
        {
            Intent intent = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public  int plus(int a , int b)
    {
        return  a+ b;
    }

    private  void ShowDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_cost_dialog);

            final EditText edt_currency = dialog.findViewById(R.id.edt_edit_curency);
        final EditText editText = dialog.findViewById(R.id.edt_edit_cost);
        ImageButton btn_submit_cost = dialog.findViewById(R.id.btn_submit_cost);

       // editText.setText(total_Cost.getText());

        btn_submit_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_currencys = edt_currency.getText().toString();
                String NewCost = editText.getText().toString();
                String total = total_Cost.getText().toString();
                if(editText.getText().toString().isEmpty() || edt_currencys.isEmpty())
                {

                }
                else
                {

                    int NCost = Integer.parseInt(NewCost);
                    int Total = Integer.parseInt(total);
                    int result = plus(NCost,Total);
                    saveData(result);
                    total_Cost.setText(result+"");
                    tv_cur.setText(edt_currencys);
                    editText.setText("");

                    dialog.dismiss();
                }


            }
        });
        dialog.show();
    }
    private void saveData(int cost)
    {
        sharedPreferences =  getSharedPreferences(MYRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TOTAL_COST,cost);
        editor.apply();
    }
    private void loadData()
    {
        sharedPreferences =  getSharedPreferences(MYRE, Context.MODE_PRIVATE);
        total_Cost.setText( sharedPreferences.getInt(TOTAL_COST,0)+"");
        TOTAL =sharedPreferences.getInt(TOTAL_COST,0);
    }
}
