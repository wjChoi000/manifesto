package com.seoulapp.manifesto;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CitizenHelpContentActivity extends AppCompatActivity {

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);

    TextView dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help_content);

        //현재시간 출력(1)
        dateNow = (TextView) findViewById(R.id.dateNow1);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

        //현재시간 출력(2)
        dateNow = (TextView) findViewById(R.id.dateNow2);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

        //현재시간 출력(3)
        dateNow = (TextView) findViewById(R.id.dateNow3);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

        //현재시간 출력(4)
        dateNow = (TextView) findViewById(R.id.dateNow4);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("도와줘요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

