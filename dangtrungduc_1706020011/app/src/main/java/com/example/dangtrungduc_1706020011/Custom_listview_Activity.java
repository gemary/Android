package com.example.dangtrungduc_1706020011;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Custom_listview_Activity extends AppCompatActivity {
    ImageView btn_back;
    Button btn_edit;
    ListView lv_show;
   static adapter_Mh adapter;
   static ArrayList<Mh_model> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_listview_);
        init();
        setdata();

        adapter = new adapter_Mh(Custom_listview_Activity.this,R.layout.custom,modelList);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Mh_model model = (Mh_model) lv_show.getItemAtPosition(position);

                Intent intent = new Intent(Custom_listview_Activity.this,Edit_Activity.class);
                intent.putExtra("position",position);
                intent.putExtra("model",model);
                startActivity(intent);

            }
        });
        lv_show.setAdapter(adapter);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void setdata() {
        modelList = new ArrayList<>();
        modelList.add(new Mh_model("Android","2TH129","3.0","Dang Trung Duc","CNTT","Cao Thanh Phu","1706020011"));
        modelList.add(new Mh_model("Anh van 2","2DC008","3.0","Dang Trung Duc","CNTT","khong biet","1706020011"));
        modelList.add(new Mh_model("Lap trinh PHP","2TH129-7","3.0","Dang Trung Duc","CNTT","khong biet","1706020011"));
        modelList.add(new Mh_model("Android Nang Cao","2TH129","3.0","Dang Trung Duc","CNTT","khong biet","1706020011"));
    }

    private void init() {
        btn_back = (ImageView) findViewById(R.id.btnBack);
        lv_show = (ListView) findViewById(R.id.lv_show);
        btn_edit = (Button) findViewById(R.id.btn_Edit);

    }
}
