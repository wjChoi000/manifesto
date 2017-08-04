package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CitizenHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help);

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



        ListView listview ;
        ListViewAdapter_help adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_help() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_help);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash),
                "쓰레기통 설치를 어떻게 해야 효율적일까요?","123");

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_help item = (ListViewItem_help) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;

                Toast.makeText(CitizenHelpActivity.this, titleStr, Toast.LENGTH_SHORT).show();
                Intent intentRow = new Intent(CitizenHelpActivity.this, CitizenHelpContentActivity.class);
                startActivity(intentRow);

            }
        }) ;



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_citizen_listenhelp,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
