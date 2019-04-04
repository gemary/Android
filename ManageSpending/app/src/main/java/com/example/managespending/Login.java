package com.example.managespending;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edt_user,edt_pass;
    Button btn_sign_in;
    TextView btn_sign_up;
    CheckBox cb_remember;
    SharedPreferences sharedPreferences;
    public  static  final String MyPREFERENCES ="MyPres";
    public  static  final String    USER = "UserKey";
    public  static  final String    PASS = "PassKey";
    public  static  final String   REMEMBER ="Remember";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        init();
        LoadDate();
        OnsetEvent();
    }

    private void LoadDate() {
        if(sharedPreferences.getBoolean(REMEMBER,false))
        {
            Intent intent = new Intent(Login.this,Main2Activity.class);
            startActivity(intent);
            edt_user.setText(sharedPreferences.getString(USER,""));
            edt_pass.setText(sharedPreferences.getString(PASS,""));
            cb_remember.setChecked(true);
        }
        else
        {
            cb_remember.setChecked(false);
        }
    }

    private void OnsetEvent() {

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( cb_remember.isChecked())
                {
                    saveData(edt_user.getText().toString(),edt_pass.getText().toString());
                }
                else
                {
                    clearData();
                }
                if (edt_user.getText().toString().isEmpty() && edt_pass.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Fill in text field", Toast.LENGTH_SHORT).show();
                }
                else if (edt_user.getText().toString().equals("admin") && edt_pass.getText().toString().equals("admin"))
                {
                    Intent intent = new Intent(Login.this,Main2Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Login.this, "User Name or PassWord is not correct", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        cb_remember = (CheckBox) findViewById(R.id.cb_Remember);
        edt_pass =(EditText) findViewById(R.id.edt_Password);
        edt_user = (EditText) findViewById(R.id.edt_user);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_up =(TextView) findViewById(R.id.btn_sign_up);
    }
    private  void saveData(String user,String Password)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER,user);
        editor.putString(PASS,Password);
        editor.putBoolean(REMEMBER,cb_remember.isChecked());
        editor.commit();
    }
    private void clearData()
    {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
