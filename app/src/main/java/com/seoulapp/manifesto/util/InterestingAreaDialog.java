package com.seoulapp.manifesto.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.seoulapp.manifesto.LoginsActivity;
import com.seoulapp.manifesto.MainActivity;
import com.seoulapp.manifesto.R;

import info.hoang8f.widget.FButton;

/**
 * Created by swelo on 2017-10-04.
 */

public class InterestingAreaDialog extends Dialog{

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

        setContentView(R.layout.dialog_select_interesting);
        setCanceledOnTouchOutside(true);


        ((ImageView) findViewById(R.id.manifestoBtnDialog1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                
                loginCheck.setInterestingAres(1);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog2)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(2);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog3)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(3);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog4)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(4);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog5)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(5);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog6)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(6);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog7)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(7);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog8)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(8);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog9)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(9);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog10)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(10);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog11)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(11);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog12)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(12);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog13)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(13);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog14)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(14);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog15)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(15);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog16)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(16);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog17)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(17);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog18)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(18);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog19)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(19);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog20)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(20);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog21)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(21);
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog22)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(22);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog23)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(23);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog24)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(24);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog25)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(25);
                startActivity();
                cancel();
            }
        });
        ((TextView) findViewById(R.id.manifestoBtnDialog26)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(26);
                startActivity();
                cancel();
            }
        });
        ((ImageView) findViewById(R.id.manifestoBtnDialog27)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginCheck.setInterestingAres(27);
                startActivity();
                cancel();
            }
        });

        ((FButton) findViewById(R.id.manifestoBtnDialog_cencel)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public InterestingAreaDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context= context;
        loginCheck = new LoginCheck(context);
    }
    public void startActivity(){

    }
}