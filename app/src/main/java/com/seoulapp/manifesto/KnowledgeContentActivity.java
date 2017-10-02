package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.seoulapp.manifesto.model.KnowContent;
import com.tsengvn.typekit.TypekitContextWrapper;

public class KnowledgeContentActivity extends AppCompatActivity {

    private KnowContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_content);

        //리스트뷰
        ListView listview ;
        ListViewAdapter_comment adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_know_content_comment);
        listview.setAdapter(adapter);

        adapter.addItem("보규","","", "2017-10-03","개천절 데쓰");
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
        LinearLayout know = (LinearLayout)header.findViewById(R.id.listheader_know_content);
        listen.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        need.setVisibility(View.GONE);
        know.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);






        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("지식플러스");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //add text
        Intent intent = getIntent();
        content = (KnowContent)intent.getSerializableExtra("content");

        TextView title = (TextView) findViewById(R.id.k_content_title);
        title.setText(content.getTitle());

        TextView contents = (TextView) findViewById(R.id.k_content_contents);
        contents.setText(content.getContents());

//        TextView goodNum = (TextView) findViewById(R.id.k_content_goodNum);
//        goodNum.setText(content.getGoodSum()+"");
//
//        TextView commentNum = (TextView) findViewById(R.id.k_content_commentNum);
//        commentNum.setText(content.getCommentSum()+"");


    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
