package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CitizenNeedContentActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_citizen_need_content);

        //리스트뷰
        ListView listview ;
        ListViewAdapter_comment adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_need_content_comment);
        listview.setAdapter(adapter);


        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        listen.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        need.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);


        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("글 상세");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title



        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        TextView tvTitle = (TextView)findViewById(R.id.need_title_context);
        TextView tvGu = (TextView)findViewById(R.id.gu);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        tvTitle.setText(intent.getStringExtra("title"));
        tvGu.setText(intent.getStringExtra("gu"));




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

