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

        // 첫 번째 아이템 추가.
        adapter.addItem("[복지]", "이런 복지가 필요합니다", "중랑구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[도시·안전]", "도시와 안전을 위해서 대책이 필요하다", "중구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[경제]", "경제가 살기위해선 젊은이들이의 복지를 해줘야한다", "영등포구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[복지]", "이런 복지가 필요합니다", "중랑구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[도시·안전]", "도시와 안전을 위해서 대책이 필요하다", "중구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[경제]", "경제가 살기위해선 젊은이들이의 복지를 해줘야한다", "영등포구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[복지]", "이런 복지가 필요합니다", "중랑구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[도시·안전]", "도시와 안전을 위해서 대책이 필요하다", "중구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[경제]", "경제가 살기위해선 젊은이들이의 복지를 해줘야한다", "영등포구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[복지]", "이런 복지가 필요합니다", "중랑구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[도시·안전]", "도시와 안전을 위해서 대책이 필요하다", "중구") ;

        // 첫 번째 아이템 추가.
        adapter.addItem("[경제]", "경제가 살기위해선 젊은이들이의 복지를 해줘야한다", "영등포구") ;

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_need item = (ListViewItem_need) parent.getItemAtPosition(position) ;

                String titleStr = item.getNeed_title() ;

                Toast.makeText(CitizenNeedActivity.this, titleStr, Toast.LENGTH_SHORT).show();
                Intent intentRow = new Intent(CitizenNeedActivity.this, CitizenNeedContentActivity.class);
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
