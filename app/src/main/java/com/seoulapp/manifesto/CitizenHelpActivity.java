package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class CitizenHelpActivity extends ActionBarActivity implements AbsListView.OnScrollListener{

    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;

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


        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_help);


        ListViewAdapter_help adapter;
        adapter = new ListViewAdapter_help() ;

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.citizen_help_trash), "쓰레기통 설치를 어떻게 해야 효율적일까?","47","123");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_pay), "최저임금의 적정 수준은 얼마일까?","179","27");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_edu), "‘공교육 경쟁력’을 위해 외고·자사고 폐지가 최선일까?","37","95");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_date_abuse), "‘데이트 폭력’ 어떻게 해결해야 할까?","100","271");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_baby), "저출산·고령화사회 대책은 무엇일까?","279","53");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_ai), "인간과 인공지능은 공존할 수 있을까?","108","21");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_guui), "제 2의 구의역 스크린도어 사고가 발생하지 않으려면?","127","141");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_noise), "층간소음, 해결할 방법은 없는가?","187","121");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_gab), "‘갑질 문화’ 어떻게 해결할까?","88","791");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.help_elec), "바람직한 전기요금 누진세는 무엇일까?","270","123");


        listview.setAdapter(adapter);

        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header, listview, false);
        listview.addHeaderView(header, null, false);


        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage_help);
        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage);
        del.setVisibility(View.GONE);
        listview.setOnScrollListener(this);


//        ListView listview ;
//        ListViewAdapter_help adapter;
//
//        // Adapter 생성
//        adapter = new ListViewAdapter_help() ;
//
//        // 리스트뷰 참조 및 Adapter달기
//        listview = (ListView) findViewById(R.id.listview_help);
//        listview.setAdapter(adapter);






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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }



    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Rect rect = new Rect();
        backgroundImage.getLocalVisibleRect(rect);
        if (lastTopValue != rect.top) {
            lastTopValue = rect.top;
            backgroundImage.setY((float) (rect.top / 2.0));
        }

    }

}
