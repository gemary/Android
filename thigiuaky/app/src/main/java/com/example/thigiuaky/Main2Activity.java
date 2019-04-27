package com.example.thigiuaky;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static List<dienthoaiModel> dienthoaiModelList= new ArrayList<>();
    String userid= loginAsyntask.result_id + "";
    public static  dienthoaiAdapter adapter;
    Map<String, String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.Recycle_view_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        map.put("id", String.valueOf(userid) );
        new dienthoaiAsyntask(new Iview() {
            @Override
            public void getdatasuccess(JSONArray jsonArray) {

                for (int i =0 ; i < jsonArray.length();i++)
                {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dienthoaiModel model = new dienthoaiModel();
                        model.setId(Integer.parseInt(jsonObject.getString("id")));
                        model.setProduct_name(jsonObject.getString("product_name"));
                        model.setPrice(Integer.parseInt(jsonObject.getString("price")));
                        model.setDescription(jsonObject.getString("description"));
                        model.setProducer(jsonObject.getString("producer"));
                        dienthoaiModelList.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                 adapter = new dienthoaiAdapter(Main2Activity.this,R.layout.dienthoai_item,dienthoaiModelList);
                recyclerView.setAdapter(adapter);

            }
        },map).execute("http://www.vidophp.tk/api/account/getdata");




    }

}
