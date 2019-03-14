package com.example.dangtrungduc_1706020011;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_userName,edt_Password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intit();
        onSetEvent();

    }

    private void onSetEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_userName.getText().toString();
                String pass = edt_Password.getText().toString();
                if(userName.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "UserName and Password is not Null", Toast.LENGTH_SHORT).show();
                }
                else if(userName.equals("1706020011") && pass.equals("123456"))
                {
                    Intent intent = new Intent(MainActivity.this,Custom_listview_Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User or Password is not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void intit() {
        edt_userName = (EditText) findViewById(R.id.edt_UserName);
        edt_Password = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}
