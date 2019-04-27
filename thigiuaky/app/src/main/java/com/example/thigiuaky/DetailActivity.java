package com.example.thigiuaky;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    EditText edt_product_name,edt_price,edt_dres,edt_producer;
    Button btn_update,btn_insert,btn_delete;
    List<dienthoaiModel> dienthoaiModelList= new ArrayList<>();
    String userid= loginAsyntask.result_id + "";
    String Id;
    Map<String, String> map_delete = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        Intent intent = getIntent();
        Id = intent.getStringExtra("Id");
        edt_product_name.setText(intent.getStringExtra("productname"));
        edt_price.setText(intent.getStringExtra("price"));
        edt_dres.setText(intent.getStringExtra("Des"));
        edt_producer.setText(intent.getStringExtra("producer"));

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_delete.put("id",String.valueOf(Id));
                map_delete.put("user_id",userid);
                map_delete.put("name",edt_product_name.getText().toString());
                map_delete.put("number",edt_price.getText().toString());
                map_delete.put("description",edt_dres.getText().toString());
                map_delete.put("code",edt_producer.getText().toString());
                new dienthoaiAsyntask(map_delete).execute("http://www.vidophp.tk/api/account/dataaction?context=update");
                finish();
                update();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map_delete.put("id",String.valueOf(Id));
                map_delete.put("user_id",String.valueOf(userid));
                new dienthoaiAsyntask(map_delete).execute("http://www.vidophp.tk/api/account/dataaction?context=delete");
                Toast.makeText(DetailActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                finish();
                update();

            }
        });


    }

    void showDialog()
    {
        Dialog dialog = new Dialog(DetailActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btn_insert =dialog.findViewById(R.id.btn_Inserts);
        final EditText edt_product_names,edt_prices,edt_dess,edt_producers,ids;
        edt_product_names = dialog.findViewById(R.id.edt_produc_names);
        edt_prices = dialog.findViewById(R.id.edt_prices);
        edt_dess = dialog.findViewById(R.id.edt_dcriptions);
        edt_producers = dialog.findViewById(R.id.edt_producers);
        ids = dialog.findViewById(R.id.edt_id);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map_delete.put("id",ids.getText().toString());
                map_delete.put("user_id",userid);
                map_delete.put("name",edt_product_names.getText().toString());
                map_delete.put("number",edt_prices.getText().toString());
                map_delete.put("description",edt_dess.getText().toString());
                map_delete.put("code",edt_producers.getText().toString());
                new dienthoaiAsyntask(map_delete).execute("http://www.vidophp.tk/api/account/dataaction?context=insert");
                finish();
                update();
            }
        });
        dialog.show();
    }

    private void update() {

        map_delete.put("id", String.valueOf(userid) );
        Main2Activity.dienthoaiModelList.clear();
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
                        Main2Activity.dienthoaiModelList.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
              Main2Activity.adapter.notifyDataSetChanged();

            }
        },map_delete).execute("http://www.vidophp.tk/api/account/getdata");
    }

    private void init() {
        edt_product_name = findViewById(R.id.edt_produc_name);
        edt_price= findViewById(R.id.edt_pricr);
        edt_dres = findViewById(R.id.edt_dcription);
        edt_producer = findViewById(R.id.edt_producer);
        btn_update = findViewById(R.id.btn_update);
        btn_insert = findViewById(R.id.btn_Insert);
        btn_delete = findViewById(R.id.btn_delete);
    }

}
