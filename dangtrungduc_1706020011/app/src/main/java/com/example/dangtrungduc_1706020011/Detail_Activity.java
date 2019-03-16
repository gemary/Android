package com.example.dangtrungduc_1706020011;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detail_Activity extends AppCompatActivity {
    TextView tv_Tsv,tv_msv,tv_nganh,tv_sochi,tv_tmh,tv_mmh,tv_tgv;
    Button btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        init();
        OngetIntent();


    }



    private void OngetIntent() {
        Intent intent  = getIntent();
        Mh_model model = (Mh_model) intent.getSerializableExtra("model");
        tv_tmh.setText(model.getNameMh());
        tv_Tsv.setText(model.getFullname());
        tv_tgv.setText(model.getGv());
        tv_sochi.setText(model.getSoChi());
        tv_nganh.setText(model.getNganh());
        tv_msv.setText(model.getMSSV());
        tv_mmh.setText(model.getIdMh());


    }

    private void init() {
        tv_mmh = (TextView) findViewById(R.id.tvMAMONHOC);
        tv_msv =(TextView) findViewById(R.id.tvMSSV);
        tv_nganh = (TextView) findViewById(R.id.tvNGANH);
        tv_sochi = (TextView) findViewById(R.id.tv_TINCHI);
        tv_tgv = (TextView) findViewById(R.id.tvGIANGVIEN);
        tv_Tsv = (TextView) findViewById(R.id.tvTENHOCSINH);
        tv_tmh = (TextView) findViewById(R.id.tvTENMONHOC);
        btn_edit = (Button) findViewById(R.id.btn_Edit);
    }
}
