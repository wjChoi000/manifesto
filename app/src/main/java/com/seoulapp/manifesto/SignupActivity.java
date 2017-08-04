package com.seoulapp.manifesto;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.editTextBackground;
import static android.R.attr.value;

public class SignupActivity extends AppCompatActivity {
    EditText et_id, et_pw, et_pw_chk, et_name, et_gu;
    String sId, sPw, sPw_chk, sName, sGu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_id= (EditText)findViewById(R.id.C_input_ID);
        et_pw= (EditText)findViewById(R.id.C_input_password);
        et_pw_chk= (EditText)findViewById(R.id.C_input_ConfirmPassword);
        et_name= (EditText)findViewById(R.id.C_input_name);
        et_gu= (EditText)findViewById(R.id.C_input_gu);

    }
    public void onClick(View v){
        sId = et_id.getText().toString();
        sPw = et_pw.getText().toString();
        sPw_chk = et_pw_chk.getText().toString();
        sName = et_name.getText().toString();
        sGu = et_gu.getText().toString();


        //이름 입력 확인
        if(sName.length()==0){
            Toast.makeText(this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
            et_name.requestFocus();
            return;
        }

        //아이디 입력 확인
        if(sId.length()<3){
            Toast.makeText(this, "ID를 최소 3글자 이상 입력하세요", Toast.LENGTH_SHORT).show();
            et_id.requestFocus();
            return;
        }

        //비밀번호 입력 확인
        if(sPw.length()==0){
            Toast.makeText(this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
            et_pw.requestFocus();
            return;
        }

        //비밀번호 확인 입력 확인
        if(sPw_chk.length()==0){
            Toast.makeText(this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
            et_pw_chk.requestFocus();
            return;
        }

        //사는 구 입력 확인
        if(sGu.length()==0){
            Toast.makeText(this, "사는 구를 입력하세요!", Toast.LENGTH_SHORT).show();
            et_gu.requestFocus();
            return;
        }

        //비밀번호 일치 확인
        if(!sPw.equals(sPw_chk)){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            et_pw.setText("");
            et_pw_chk.setText("");
            et_pw.requestFocus();
            return;
        }



    }

}
