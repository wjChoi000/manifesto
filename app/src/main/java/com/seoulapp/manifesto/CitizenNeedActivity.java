package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CitizenNeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need);


        //spinner_category
        Spinner cateSpinner = (Spinner)findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> cateAdapter = ArrayAdapter.createFromResource(
                this,R.array.category_array, android.R.layout.simple_spinner_item);
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //spinner_sort
        Spinner spinner_sort = (Spinner)findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter_sort = ArrayAdapter.createFromResource(
                this,R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter_sort);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("필요해요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //리스트
        ListView listview ;
        ListViewAdapter_need adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_need() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_need);
        listview.setAdapter(adapter);

        adapter.addItem("[복지]", "고속터미널 지하상가와 반포역에 노숙자 쉼터 개설", "서초구","154","41") ;
        adapter.addItem("[경제]", "청년 일자리 창출을 위한 청년벤처기업 조성", "종로구","23","18") ;
        adapter.addItem("[경제]", "상암 롯데몰 빠른 진행을 바랍니다.", "마포구","81","102") ;
        adapter.addItem("[도시]", "강서구 화곡본동 공원근처 가로등", "강서구","41","23") ;
        adapter.addItem("[경제]", "이화여대5길 상점 활성화를 위해서 꼭좀 부탁드립니다~!", "서대문구","73","32") ;
        adapter.addItem("[도시]", "마천3구역 비오면 하수구냄새", "송파구","11","24") ;
        adapter.addItem("[환경]", "무궁화 나무 자르지 않도록 지시", "중랑구","72","8") ;
        adapter.addItem("[문화]", "신촌물총축제 폐지해주세요.", "서대문구","26","85") ;
        adapter.addItem("[문화]", "휴일 도로 점거하는 마라톤", "광진구","90","12") ;
        adapter.addItem("[행정]", "120 다산콜센터 민원처리 문제", "강서구","52","38") ;



        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_need item = (ListViewItem_need) parent.getItemAtPosition(position) ;

                String titleStr = item.getNeed_title() ;
                String guStr = item.getGu();

                Intent intentRow = new Intent(CitizenNeedActivity.this, CitizenNeedContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("gu",guStr);
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
            case R.id.action_writing:
                Intent intent = new Intent(this,CitizenNeedWritingActivity.class);
                startActivity(intent);
                return true;
//            case R.id.action_search:
        }
        return super.onOptionsItemSelected(item);
    }
    //메뉴 리소스를 팽창한다. 액션바에 항목들을 추가한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_citizen_need,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
