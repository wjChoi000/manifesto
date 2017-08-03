package com.seoulapp.manifesto;

import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;
    EditText idInput,pwInput;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //SharedPreferences 객체 생성
        final SharedPreferences pref = getSharedPreferences("info",MODE_APPEND);

        //SharedPreferences에 값을 넣기 위해 Editor 생성
        SharedPreferences.Editor editor = pref.edit();

        idInput=(EditText)findViewById(R.id.C_input_ID);
        String idText = idInput.getText().toString(); //사용자가 입력한 값

        pwInput=(EditText)findViewById(R.id.C_input_password);
        String pwText = idInput.getText().toString(); //사용자가 입력한 값

        //SharedPreferences에 값 넣기
        editor.putString("ID",idText);
        editor.putString("PW",pwText);
        editor.commit();

        final Button button = (Button)findViewById(R.id.btn_create_account);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                save();
                Toast.makeText(SignupActivity.this, "저장됨", Toast.LENGTH_SHORT).show();

//                //값 가져오기 Key
//                String valueid = pref.getString("ID","");
//                String valuepw = pref.getString("PW","");
            }
        });
    }

    public void save(){
        SharedPreferences pref = getSharedPreferences("info",MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID",idInput.getText().toString());
        editor.putString("PW",pwInput.getText().toString());
        editor.commit();
    }


}
