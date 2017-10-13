package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {

    private ViewPager TmViewPager = null;
    private RollingAdapter TmAdapter = null;
    private IndicatorView TmIndicatorView = null;
    private AutoRollingManager TmAutoRollingManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity);

        TmIndicatorView = (IndicatorView)findViewById(R.id.tutorial_indicator_view);
        TmViewPager = (ViewPager)findViewById(R.id.tutorial_view_pager);

        TmAdapter = new RollingAdapter(TutorialActivity.this, getData(), new RollingAdapter.OnAdapterItemClickListener() {
            @Override
            public void onItemClick(RollingModel object, int position) {
                if(position==3){
                    Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                //Toast.makeText(TutorialActivity.this, position + " items click!", Toast.LENGTH_SHORT).show();
            }
        });
        TmViewPager.setAdapter(TmAdapter);
        TmIndicatorView.setViewPager(TmViewPager);
        TmAutoRollingManager = new AutoRollingManager(TmViewPager, TmAdapter, TmIndicatorView);
        TmAutoRollingManager.onRollingStop();


        Button button = (Button) findViewById(R.id.tutorial_close);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TutorialActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<RollingModel> getData(){
        List<RollingModel> list = new ArrayList<>();

        list.add(new RollingModel("1", R.drawable.listen_book));
        list.add(new RollingModel("2", R.drawable.listen_moon100));
        list.add(new RollingModel("3", R.drawable.listen_out));
        list.add(new RollingModel("4", R.drawable.listen_power));       //포지션 3

        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        TmAutoRollingManager.onRollingStop();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        TmAutoRollingManager.onRollingStart();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TmAutoRollingManager.onRollingDestroy();
    }
}


