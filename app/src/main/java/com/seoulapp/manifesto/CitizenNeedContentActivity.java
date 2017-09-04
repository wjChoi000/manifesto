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
    int need_goodCount;
    int need_checknum = 0;
    ImageView need_imgGood;
    TextView need_tvGood, need_tvGoodCount;


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

/////////////////////////////////////////////////////////////////////////////////////////////////////
        TextView tvTitle = (TextView)findViewById(R.id.need_title_context);
        TextView tvGu = (TextView)findViewById(R.id.gu);
        TextView tvU_id = (TextView)findViewById(R.id.need_context_u_id);
        TextView tvC_date = (TextView)findViewById(R.id.need_C_date);
        TextView tvContext = (TextView)findViewById(R.id.need_context);
        TextView tvGoodNum = (TextView)findViewById(R.id.need_goodNum);
        TextView tvHitNum = (TextView)findViewById(R.id.need_hitNum);
        TextView tvComNum = (TextView)findViewById(R.id.need_comNum);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다

        tvTitle.setText(intent.getStringExtra("title"));
        tvU_id.setText(intent.getStringExtra("u_id"));
        tvGu.setText(intent.getStringExtra("gu"));
        tvC_date.setText(intent.getStringExtra("C_date"));
        tvContext.setText(intent.getStringExtra("Need_context"));
        tvGoodNum.setText(intent.getStringExtra("GoodNum"));
        tvHitNum.setText(intent.getStringExtra("HitNum"));
        tvComNum.setText(intent.getStringExtra("ComNum"));

        need_tvGood = (TextView)findViewById(R.id.need_goodTvBtn);
        need_tvGoodCount = (TextView)findViewById(R.id.need_goodNum);

        String countTest = intent.getStringExtra("GoodNum");
        need_goodCount = Integer.parseInt(countTest);

        findViewById(R.id.need_goodTvBtn).setOnClickListener(need_clickListener);

        need_imgGood = (ImageView)findViewById(R.id.need_imgGood);



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


    private View.OnClickListener need_clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (need_checknum){
                case 0:
                    need_checknum++;
                    need_goodCount++;
                    need_imgGood.setImageResource(R.drawable.ag_pressed);
                    need_tvGood.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setText(""+need_goodCount);
                    break;
                case 1:
                    need_checknum--;
                    need_goodCount--;
                    need_imgGood.setImageResource(R.drawable.agreement_normal);
                    need_tvGoodCount.setText(""+need_goodCount);
                    need_tvGood.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                default:
                    break;
            }
        }
    };



}

