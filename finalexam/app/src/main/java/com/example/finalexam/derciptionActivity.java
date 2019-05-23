package com.example.finalexam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class derciptionActivity extends AppCompatActivity {
    private EditText edt_credits,edt_description,edt_subject_code,edt_subject_name;
    private ImageView btn_edit;
    private String local;
    private Map<String, Object> credits = new HashMap<String, Object>();
    private Map<String, Object> description = new HashMap<String, Object>();
    private Map<String, Object> subject_code = new HashMap<String, Object>();
    private Map<String, Object> subject_name = new HashMap<String, Object>();
    String price,descriptions,producer,product_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derciption);
        init();
        setvalue();
        clickEevent();
         price = edt_credits.getText().toString();
         descriptions = edt_description.getText().toString();
         producer =edt_subject_code.getText().toString();
         product_name = edt_subject_name.getText().toString();
    }

    private void clickEevent() {
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price.equals(edt_credits.getText().toString()) && descriptions.equals(edt_description.getText().toString()) && producer.equals(edt_subject_code.getText().toString())&& product_name.equals(edt_subject_name.getText().toString()))
                {

                }
                else {
                    credits.put("price", Long.parseLong(edt_credits.getText().toString()));
                    description.put("description", edt_description.getText().toString());
                    subject_code.put("producer", edt_subject_code.getText().toString());
                    subject_name.put("product_name", edt_subject_name.getText().toString());
                    MainActivity.myRef.child(local).updateChildren(credits);
                    MainActivity.myRef.child(local).updateChildren(description);
                    MainActivity.myRef.child(local).updateChildren(subject_code);
                    MainActivity.myRef.child(local).updateChildren(subject_name);
                    MainActivity.modelList.clear();
                    MainActivity.adapter.notifyDataSetChanged();
                    finish();
                    Toast.makeText(derciptionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setvalue() {
        Intent intent =getIntent();
        local = intent.getStringExtra("local");
        edt_credits.setText(intent.getStringExtra("price"));
        edt_description.setText(intent.getStringExtra("description"));
        edt_subject_code.setText(intent.getStringExtra("producer"));
        edt_subject_name.setText(intent.getStringExtra("product_name"));

    }

    private void init() {
        btn_edit = findViewById(R.id.btn_edit);
        edt_credits = findViewById(R.id.edt_credits);
        edt_description = findViewById(R.id.edt_description);
        edt_subject_code = findViewById(R.id.edt_subject_code);
        edt_subject_name = findViewById(R.id.edt_subject_name);
    }
}
