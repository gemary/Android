package com.example.finalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
    EditText edt_number,edt_id,edt_price,edt_des,edt_name,edt_maker;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        init();
        addevent();
    }

    private void addevent() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.modelList.clear();
                long price = Long.parseLong(edt_price.getText().toString());
                String des = edt_des.getText().toString();
                String name = edt_name.getText().toString();
                String maker = edt_maker.getText().toString();
                long Id = Long.parseLong(edt_id.getText().toString());
                Model model = new Model(price,des,Id,maker,name);
                MainActivity.myRef.child(edt_number.getText().toString()).setValue(model);
                MainActivity.modelList.clear();
                MainActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void init() {
        edt_number = (EditText) findViewById(R.id.edt_item_number);
        edt_id = (EditText) findViewById(R.id.edt_item_id);
        edt_name = (EditText) findViewById(R.id.edt_item_name);
        edt_price =(EditText) findViewById(R.id.edt_item_price);
        edt_des = (EditText) findViewById(R.id.edt_item_des);
        edt_maker = (EditText) findViewById(R.id.edt_item_maker);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }
}
