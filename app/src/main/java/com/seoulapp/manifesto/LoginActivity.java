package com.seoulapp.manifesto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etId;
    private EditText etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etId = (EditText) findViewById(R.id.input_ID);
        etPw = (EditText) findViewById(R.id.input_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override   //&& etPw.getText().toString().equals(strpw)
            public void onClick(View v) {
                if (etId.getText().toString().equals("admin")&&etPw.getText().toString().equals("123")) {
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentLogin);
                } else {
                    Toast.makeText(LoginActivity.this, "ID 또는 Password 가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onClick(View target){
        int id=target.getId();

        switch (id){
            case R.id.link_lookaround:
                Intent intentLookAround = new Intent(this,MainActivity.class);
                startActivity(intentLookAround);
                break;
            case R.id.btn_signup:
                Intent intentSignup = new Intent(this,SignupActivity.class);
                startActivity(intentSignup);
                break;
        }

    }
}

