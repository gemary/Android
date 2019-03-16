package com.example.dangtrungduc_1706020011;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Activity extends AppCompatActivity {
    EditText edt_HoVaTen,edt_MSSV,edt_TinChi,edt_nganh,edt_TenMonHoc,edt_MMH,edt_GiangVien;
    Button btn_Update;
    int position = 0;
    Mh_model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);
        init();
        OngetIntent();
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetDAta();
                Custom_listview_Activity.modelList.set(position,model);
                Custom_listview_Activity.adapter.notifyDataSetChanged();
                Toast.makeText(Edit_Activity.this, "Update Success", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void OngetIntent() {
        Intent intent  = getIntent();
        model = (Mh_model) intent.getSerializableExtra("model");
        position = intent.getIntExtra("position",0);
        edt_TenMonHoc.setText(model.getNameMh());
        edt_TinChi.setText(model.getSoChi());
        edt_nganh.setText(model.getNganh());
        edt_MSSV. setText(model.getMSSV());
        edt_MMH.setText(model.getIdMh());
        edt_MMH. setText(model.getMSSV());
        edt_GiangVien.setText(model.getGv());
        edt_HoVaTen.setText(model.getFullname());

    }
    private void clear(){
        edt_TenMonHoc.setText("");
        edt_TinChi.setText("");
        edt_nganh.setText("");
        edt_MSSV.setText("");
        edt_MMH.setText("");
        edt_MMH.setText("");
        edt_GiangVien.setText("");
        edt_HoVaTen.setText("");
    }

    private void onGetDAta() {
        model.setNameMh(edt_TenMonHoc.getText().toString());
        model.setSoChi(edt_TinChi.getText().toString());
        model.setNganh(edt_nganh.getText().toString());
        model.setMSSV(edt_MSSV.getText().toString());
        model.setIdMh(edt_MMH.getText().toString());
        model.setGv(edt_GiangVien.getText().toString());
        model.setFullname(edt_HoVaTen.getText().toString());

    }

    private void init() {
        edt_GiangVien =(EditText) findViewById(R.id.edt_GIANGVIEN);
        edt_HoVaTen = (EditText) findViewById(R.id.edt_TENHOCSINH);
        edt_MMH =(EditText) findViewById(R.id.edt_MAMONHOC);
        edt_MSSV =(EditText) findViewById(R.id.edt_MSSV);
        edt_nganh =(EditText) findViewById(R.id.edt_NGANH);
        edt_TinChi = (EditText) findViewById(R.id.edt_TINCHI);
        edt_TenMonHoc =(EditText) findViewById(R.id.edt_TENMONHOC);
        btn_Update = (Button) findViewById(R.id.btn_Update);
    }
}
