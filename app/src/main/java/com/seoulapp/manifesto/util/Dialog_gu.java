package com.seoulapp.manifesto.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.seoulapp.manifesto.LoginsActivity;
import com.seoulapp.manifesto.R;

import info.hoang8f.widget.FButton;

/**
 * Created by swelo on 2017-10-01.
 */

public class Dialog_gu extends Dialog{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_gu);

    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public Dialog_gu(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        ;
    }
}
