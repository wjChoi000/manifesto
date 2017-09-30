package com.seoulapp.manifesto.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.seoulapp.manifesto.LoginsActivity;
import com.seoulapp.manifesto.R;

import info.hoang8f.widget.FButton;

/**
 * Created by swelo on 2017-09-30.
 */

public class LoginCheckDialog extends Dialog{

    private FButton mLeftButton;
    private FButton mRightButton;
    private Boolean flag;
    private TextView textView;
    private LoginCheck loginCheck;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.login_check_dialog);

        mLeftButton = (FButton) findViewById(R.id.login_check_yes);
        mRightButton = (FButton) findViewById(R.id.login_check_no);
        textView = (TextView)findViewById(R.id.login_check_text);
        if(flag) {
            textView.setText("로그아웃 하시겠습니까?");
            mLeftButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    loginCheck.logout();
                    LoginCheckDialog.this.dismiss();

                    //go logout page
                }
            });
        }else{
            textView.setText("로그인 후 이용해 주세요.");
            mLeftButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    LoginCheckDialog.this.dismiss();
                    Intent intent = new Intent(context, LoginsActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        mRightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginCheckDialog.this.dismiss();
            }
        });
    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public LoginCheckDialog(Context context, boolean flag) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context= context;
        this.flag = flag;
        loginCheck = new LoginCheck(context);
    }
}
