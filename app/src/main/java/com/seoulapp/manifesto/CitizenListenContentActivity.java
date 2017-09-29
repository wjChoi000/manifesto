package com.seoulapp.manifesto;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;

import org.w3c.dom.Text;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CitizenListenContentActivity extends AppCompatActivity {
    int agcount;
    int opcount;
    TextView agtextview,optextview;
    CheckBox agCheck,opCheck;

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
        setContentView(R.layout.activity_citizen_listen_content);

        //리스트뷰
        ListView listview ;
        ListViewAdapter_comment adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_listen_content_comment);
        listview.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        help.setVisibility(View.GONE);
        need.setVisibility(View.GONE);
        listen.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);


//        //현재시간 출력(1)
//        dateNow = (TextView) findViewById(R.id.dateNow1);
//        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당
//
//        //현재시간 출력(2)
//        dateNow = (TextView) findViewById(R.id.dateNow2);
//        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당
//
//        //현재시간 출력(3)
//        dateNow = (TextView) findViewById(R.id.dateNow3);
//        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당


        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("들려줘요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        /////////////////////////////////////////////////////////////////////////
        agtextview = (TextView)findViewById(R.id.ag_count);
        findViewById(R.id.agreementBtn).setOnClickListener(clickListener);
        agCheck = (CheckBox)findViewById(R.id.agreementBtn);

        optextview = (TextView)findViewById(R.id.op_count);
        findViewById(R.id.oppositionBtn).setOnClickListener(clickListener);
        opCheck = (CheckBox)findViewById(R.id.oppositionBtn);


        //////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        Citizen content = (Citizen) intent.getSerializableExtra("say");

        TextView tvTitle = (TextView)findViewById(R.id.titleText);
        ImageView iv = (ImageView)findViewById(R.id.Listen_content_num1);
        TextView tvSubTitle = (TextView)findViewById(R.id.Listen_subtitle);
        TextView tvAgContext = (TextView)findViewById(R.id.Ag_context);
        TextView tvOpContext = (TextView)findViewById(R.id.Op_context);
        TextView tvCDate = (TextView)findViewById(R.id.c_date);
        TextView tvComNum = (TextView)findViewById(R.id.listen_comNum);

        tvTitle.setText(content.getTitle());
        iv.setImageResource(R.drawable.listen_tax);
        tvSubTitle.setText(content.getComment());
        tvAgContext.setText(content.getAgree()+"");
        tvOpContext.setText(content.getOpposite()+"");
        tvCDate.setText(content.getCreate_date());
        tvComNum.setText(content.getCount()+"");

        agtextview.setText("찬성 "+content.getGood());
        optextview.setText("반대 "+content.getBad());

        agcount = content.getGood();
        opcount = content.getBad();

        //

        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","찬성","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","반대", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");

    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (v.getId()){
                case R.id.agreementBtn:
                    if(opCheck.isChecked() ==true){
                        opcount--;
                        optextview.setText("반대 "+opcount);
                        optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        opCheck.setChecked(false);
                        agcount++;
                        agtextview.setText("찬성 "+agcount);
                        agtextview.setTextColor(getResources().getColor(R.color.agreement));
                    }else {
                        if(agCheck.isChecked() ==true){
                            agcount++;
                            agtextview.setText("찬성 "+agcount);
                            agtextview.setTextColor(getResources().getColor(R.color.agreement));
                        }else{
                            agcount--;
                            agtextview.setText("찬성 "+agcount);
                            agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        }
                    }
                    break;
                case R.id.oppositionBtn:
                    if(agCheck.isChecked() ==true){
                        agcount--;
                        agtextview.setText("찬성 "+agcount);
                        agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        agCheck.setChecked(false);
                        opcount++;
                        optextview.setText("반대 "+opcount);
                        optextview.setTextColor(getResources().getColor(R.color.opposition));
                    }else{
                        if(opCheck.isChecked() ==true){
                            opcount++;
                            optextview.setText("반대 "+opcount);
                            optextview.setTextColor(getResources().getColor(R.color.opposition));
                        }else{
                            opcount--;
                            optextview.setText("반대 "+opcount);
                            optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onClick(View target){
        int id=target.getId();


        switch (id){
            case R.id.agreementBtn:
                Toast.makeText(this, "찬성", Toast.LENGTH_SHORT).show();
                break;
            case R.id.oppositionBtn:
                Toast.makeText(this, "반대", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    
}

