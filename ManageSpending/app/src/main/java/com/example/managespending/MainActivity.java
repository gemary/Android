package com.example.managespending;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tb_lay;
    ViewPager viewPager;
    TextView total_Cost;
    ImageButton btn_edit_cost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        inSetEvent();
        show();


    }








    private void show() {

        tb_lay.setupWithViewPager(viewPager);
        Adapter_VIew Adapter_VIew = new Adapter_VIew(getSupportFragmentManager() );
        Adapter_VIew.addFragment(new Main_Frament(),"Main");
        Adapter_VIew.addFragment(new History_fragment(),"History");
        Adapter_VIew.addFragment(new Report_Fragment(),"Report");
        viewPager.setAdapter(Adapter_VIew);
    }

    private void inSetEvent() {

    }


    private  void ShowDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_cost_dialog);

        final EditText editText = dialog.findViewById(R.id.edt_edit_cost);
        ImageButton btn_submit_cost = dialog.findViewById(R.id.btn_submit_cost);

        editText.setText(total_Cost.getText());

        btn_submit_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewCost = editText.getText().toString();
                String total = total_Cost.getText().toString();
                if(editText.getText().toString().isEmpty())
                {

                }
                else
                {

                    int NCost = Integer.parseInt(NewCost);
                    int Total = Integer.parseInt(total);
                    int result = plus(NCost,Total);



                   total_Cost.setText(result+"");
                    editText.setText("");
                }


            }
        });
        dialog.show();
    }
    public  int plus(int a , int b)
    {
        return  a+ b;
    }

    private void init() {

        btn_edit_cost = findViewById(R.id.btn_Edit_cost);
        total_Cost = findViewById(R.id.Total_cost);
        tb_lay = findViewById(R.id.Tb_Layout);
        viewPager = findViewById(R.id.view_Pager);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_ex,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }



}
