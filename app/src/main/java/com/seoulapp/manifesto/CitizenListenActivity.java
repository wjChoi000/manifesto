package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CitizenListenActivity extends ActionBarActivity implements AbsListView.OnScrollListener{
    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_listen);

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

        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_listen);


        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        adapter.addItem(R.drawable.listen_thaad, "대한민국의 사드 배치는 누구를 위해서인가?", "63","49","32") ;
        adapter.addItem(R.drawable.listen_tax, "‘대기업+고소득자 증세‘ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_skktlg, "‘단통법 폐지’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_trump, "트럼프정권 ‘대북 선제타격론’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_power, "‘탈 원전’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_book, "‘역사교과서 국정화’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_privilege, "‘국회의원 특권 내려놓기’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_religion, "‘종교인 과세 2년 유예’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_out, "‘국회의원 국민소환제’ 무엇이 옳은 걸까?", "423","299","362") ;
        adapter.addItem(R.drawable.listen_moon100, "文 대통령 ‘100일 기자회견’ 무엇이 옳은 걸까?", "423","299","362") ;

        listview.setAdapter(adapter);

        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header, listview, false);
        listview.addHeaderView(header, null, false);



        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage);
        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage_help);
        del.setVisibility(View.GONE);
        listview.setOnScrollListener(this);


         //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();
                Intent intentRow = new Intent(CitizenListenActivity.this, CitizenListenContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);
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
