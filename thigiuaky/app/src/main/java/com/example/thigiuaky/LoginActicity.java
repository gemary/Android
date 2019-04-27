package com.example.thigiuaky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActicity extends AppCompatActivity {
    EditText user,Pass;
    Button btn_login;
    String users = "";
    String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acticity);
        onit();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onValidateForm()) {

                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", users);
                    map.put("password", password);
                    new loginAsyntask(map, new LoginView() {
                        @Override
                        public void Loginsuccess(String mString) {

                            Toast.makeText(LoginActicity.this, mString, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActicity.this, Main2Activity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void LoginFail(String mString) {
                            Toast.makeText(LoginActicity.this, mString, Toast.LENGTH_SHORT).show();

                        }
                    }, LoginActicity.this).execute("http://www.vidophp.tk/api/account/signin");
                }
            }
        });
    }
    private void onit() {
        user = findViewById(R.id.edt_user);
        Pass = findViewById(R.id.edt_pass);
        btn_login = findViewById(R.id.btn_Login);
    }
    private boolean onValidateForm(){
        users = user.getText().toString();
        if (user.length() < 1){
            user.setError("Username field cannot be blank");
            return false;
        }

        password = Pass.getText().toString();
        if (password.length() < 1){
            Pass.setError("Password field cannot be blank");
            return  false;
        }
        return true;
    }

}
