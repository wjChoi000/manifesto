package com.seoulapp.manifesto;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ManifestoRateActivity extends AppCompatActivity {

    private TextView oneT;
    private TextView oneS;
    private TextView twoT;
    private TextView twoS;
    private TextView threeT;
    private TextView threeS;
    private LinearLayout oneF;
    private LinearLayout twoF;
    private LinearLayout threeF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifesto_rate);

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //small titile
        oneT = (TextView)findViewById(R.id.m_map_small_t_one);
        oneS = (TextView)findViewById(R.id.m_map_small_s_one);
        twoT = (TextView)findViewById(R.id.m_map_small_t_two);
        twoS = (TextView)findViewById(R.id.m_map_small_s_two);
        threeT = (TextView)findViewById(R.id.m_map_small_t_three);
        threeS = (TextView)findViewById(R.id.m_map_small_s_three);

        RelativeLayout smallOne = (RelativeLayout)findViewById(R.id.m_map_small_one);
        RelativeLayout smallTwo = (RelativeLayout)findViewById(R.id.m_map_small_two);
        RelativeLayout smallThree = (RelativeLayout)findViewById(R.id.m_map_small_three);

        oneF = (LinearLayout) findViewById(R.id.m_map_small_frame_one);
        twoF = (LinearLayout) findViewById(R.id.m_map_small_frame_two);
        threeF = (LinearLayout) findViewById(R.id.m_map_small_frame_three);

        smallOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneT.setTextColor(getResources().getColor(R.color.colorMain));
                twoT.setTextColor(getResources().getColor(R.color.colorBlack));
                threeT.setTextColor(getResources().getColor(R.color.colorBlack));

                oneS.setVisibility(View.VISIBLE);
                twoS.setVisibility(View.INVISIBLE);
                threeS.setVisibility(View.INVISIBLE);

                oneF.setVisibility(View.VISIBLE);
                twoF.setVisibility(View.GONE);
                threeF.setVisibility(View.GONE);
            }
        });

        smallTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                oneT.setTextColor(getResources().getColor(R.color.colorBlack));
                twoT.setTextColor(getResources().getColor(R.color.colorMain));
                threeT.setTextColor(getResources().getColor(R.color.colorBlack));

                oneS.setVisibility(View.INVISIBLE);
                twoS.setVisibility(View.VISIBLE);
                threeS.setVisibility(View.INVISIBLE);

                oneF.setVisibility(View.INVISIBLE);
                twoF.setVisibility(View.VISIBLE);
                threeF.setVisibility(View.INVISIBLE);

            }
        });

        smallThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                oneT.setTextColor(getResources().getColor(R.color.colorBlack));
                twoT.setTextColor(getResources().getColor(R.color.colorBlack));
                threeT.setTextColor(getResources().getColor(R.color.colorMain));

                oneS.setVisibility(View.INVISIBLE);
                twoS.setVisibility(View.INVISIBLE);
                threeS.setVisibility(View.VISIBLE);

                oneF.setVisibility(View.GONE);
                twoF.setVisibility(View.GONE);
                threeF.setVisibility(View.VISIBLE);

            }
        });

        //text
        TextView textName = (TextView)  findViewById(R.id.m_name);
        TextView textDay = (TextView) findViewById(R.id.m_day);
        TextView textBorn = (TextView) findViewById(R.id.m_born);
        TextView textAge = (TextView) findViewById(R.id.m_age);
        TextView textEdu = (TextView) findViewById(R.id.m_education);

        textName.setText("박원순");
        textDay.setText("1996.3.26");
        textBorn.setText("경상남도 창녕");
        textAge.setText("62세");
        textEdu.setText("단국대학교");

        rate();
        promise();
        profile();
    }


    private void rate(){
        TableLayout table = (TableLayout) findViewById(R.id.rate_table);

        int[] a = {10,20,30,40,50};
        addNewTableRow(table,"전체",a);
        addNewTableRow(table,"문화",a);
        addNewTableRow(table,"정치",a);
        addNewTableRow(table,"경제",a);
        addNewTableRow(table,"외교",a);
        addNewTableRow(table,"교육",a);
        Log.i("rate","table complete");
    }

    private void addNewTableRow(TableLayout parent, String s, int[] a){

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

        //1dp
        float d = this.getResources().getDisplayMetrics().density;
        int margin = (int)(d);
        //params
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        params.setMargins(margin,0,0,margin);

        //row
        TextView text = new TextView(this);
        text.setText(s);

        for(int i: a){
            text.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
            text.setLayoutParams(params);
            text.setBackgroundResource(R.color.colorWhite);
            text.setTextColor(getResources().getColor(R.color.colorBlack));

            row.addView(text);

            text = new TextView(this);
            text.setText(i+"");
        }

        params.setMargins(margin,0,margin,margin);
        text.setLayoutParams(params);
        text.setTextColor(getResources().getColor(R.color.colorBlack));
        text.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        text.setBackgroundResource(R.color.colorWhite);
        row.addView(text);

        parent.addView(row, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
;
    }

    private void promise(){
        LinearLayout promiseList = (LinearLayout) findViewById(R.id.m_promise_list);
        addPromise(promiseList,"안전 특별시 서울",1);
        addPromise(promiseList,"어린이 안전도시 서울",2);
        addPromise(promiseList,"집 걱정 없는 서울",3);
        addPromise(promiseList,"힘내세요 베이비부머!",4);
        addPromise(promiseList,"여성들의 내일",5);
    }
    private void addPromise(LinearLayout parent,String s1, int i){
        LinearLayout linear = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);
        params.setMargins(0,0,0,2);
        linear.setGravity(Gravity.CENTER_VERTICAL);

        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setLayoutParams(params);
        linear.setBackgroundResource(R.color.colorWhite);

        TextView promiseBtn = new TextView(this);
        LinearLayout.LayoutParams paramsBtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsBtn.setMargins(10,10,50,10);
        promiseBtn.setLayoutParams(paramsBtn);

        switch (i){
            //care
            case 1:
                promiseBtn.setBackgroundResource(R.color.promise_care);
                promiseBtn.setText(R.string.promise_care);
                break;
            //culture
            case 2:
                promiseBtn.setBackgroundResource(R.color.promise_culture);
                promiseBtn.setText(R.string.promise_culture);
                break;

            //education
            case 3:
                promiseBtn.setBackgroundResource(R.color.promise_edu);
                promiseBtn.setText(R.string.promise_edu);
                break;

            //economy
            case 4:
                promiseBtn.setBackgroundResource(R.color.promise_eco);
                promiseBtn.setText(R.string.promise_eco);
                break;

            //city
            case 5:
                promiseBtn.setBackgroundResource(R.color.promise_city);
                promiseBtn.setText(R.string.promise_city);
                break;
            default:
        }
        promiseBtn.setTextColor(getResources().getColor(R.color.colorWhite));
        promiseBtn.setPadding(3,3,3,3);
        linear.addView(promiseBtn);

        TextView promiseText = new TextView(this);
        promiseText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        promiseText.setText(s1);
        promiseText.setTextColor(getResources().getColor(R.color.colorBlack));
        linear.addView(promiseText);

        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());

        ImageView promiseImg = new ImageView(this);
        promiseImg.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        promiseImg.setImageResource(R.drawable.move_black);
        linear.addView(promiseImg);

        //onclick
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go intent

            }
        });

        parent.addView(linear);
    }

    private void profile(){
        LinearLayout aware = (LinearLayout) findViewById(R.id.m_profile_aware);
        LinearLayout career = (LinearLayout) findViewById(R.id.m_profile_career);
        LinearLayout crime = (LinearLayout) findViewById(R.id.m_profile_crime);

        addText(aware,"2014년 매니패스토");
        addText(aware,"2016 예테보리 지속 가능 방성상");
        addText(aware,"2015 세계도시 전자정부 평가 공로상");
        addText(aware,"2009 제 15회 불교 인권상");
        addText(aware,"2016 예테보리 지속 가능 방성상");
        addText(aware,"2015 세계도시 전자정부 평가 공로상");

        addText(career,"2011~2015 한국 상수도 협회장");
        addText(career,"2006~2011 희망 제작소 상임이사");
        addText(career,"2005 미국 스탠퍼드 대학교 방분교수");
        addText(career,"2003 사법개혁 위원회 위원");
        addText(career,"2011~2015 한국 상수도 협회장");
        addText(career,"2005 미국 스탠퍼드 대학교 방분교수");

        addText(crime,"없음");
    }

    private void addText(LinearLayout parent,String s){
        TextView text = new TextView(this);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setText(s);
        text.setTextColor(getResources().getColor(R.color.colorBlack));
        text.setPadding(80,0,0,0);
        parent.addView(text);
    }

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

}
