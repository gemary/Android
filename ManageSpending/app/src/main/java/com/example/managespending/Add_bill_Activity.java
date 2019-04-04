package com.example.managespending;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;

public class Add_bill_Activity extends AppCompatActivity {
    ImageButton btn_time_dialog,btn_submit;
    EditText edt_date,edt_cost,edt_Reason,edt_note,edt_product,edt_currency,edt_location;
    String product,price,location,note,reason,currency,time;
    Model_if model_if;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_);
        init();
        ongetData();


        btn_time_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model_if = new Model_if();
                Intent intent= new Intent(Add_bill_Activity.this,Main_Frament.class);
                intent.putExtra("Model",model_if);
            }
        });
    }

    private void ongetData() {

        product = edt_product.getText().toString();
        price = edt_cost.getText().toString();
        location = edt_location.getText().toString();
        note = edt_note.getText().toString();
        reason = edt_Reason.getText().toString();
        currency = edt_currency.getText().toString();
        time = edt_date.getText().toString();
    }

    private void init() {
        edt_location =findViewById(R.id.edt_local);
        edt_cost = findViewById(R.id.edt_cost);
        edt_currency = findViewById(R.id.edt_currency);
        edt_note = findViewById(R.id.edt_note);
        edt_product = findViewById(R.id.edt_product);
        edt_Reason = findViewById(R.id.edt_reason);
        edt_date = findViewById(R.id.edt_date);
        btn_submit = findViewById(R.id.btn_submit);
        btn_time_dialog = findViewById(R.id.btn_time_dialog);
    }

    private  void showDialog()
    {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int Year  = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },Year,Month,Day);
        datePickerDialog.show();
    }
}
