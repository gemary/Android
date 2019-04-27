package com.example.thigiuaky;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcv_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        new myAsyntask(new Iview() {
            @Override
            public void getdatasuccess(JSONArray jsonArray) {
                for (int i = 0 ; i < jsonArray.length();i++) {
                    try {
                        JSONObject jobject = jsonArray.getJSONObject(i);
                        modelList = new ArrayList<>();
                        model models = new model();
                        models.setId(Integer.parseInt(jobject.getString("id")));
                        models.setMssv(jobject.getString("student_code"));
                        models.setHoten(jobject.getString("name"));
                        models.setLop(jobject.getString("class"));
                        models.setTuoi(Integer.parseInt(jobject.getString("age")));
                        models.setEmail(jobject.getString("email"));
                        modelList.add(models);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                    MyAdapter adapter = new MyAdapter(MainActivity.this,modelList,R.layout.item_layout);
                    recyclerView.setAdapter(adapter);

            }
        }).execute("http://www.vidophp.tk/api/student/getall");

    }
}
