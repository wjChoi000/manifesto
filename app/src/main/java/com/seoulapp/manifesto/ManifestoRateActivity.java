package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Date;


public class ManifestoRateActivity extends AppCompatActivity {

    private TextView oneT;
    private TextView oneS;
    private TextView twoT;
    private TextView twoS;
    private TextView threeT;
    private TextView threeS;
    private TextView fourT;
    private TextView fourS;

    private LinearLayout frameOne;
    private LinearLayout frameTwo;
    private LinearLayout frameThree;
    private LinearLayout frameFour;

    private LinearLayout btnOne;
    private LinearLayout btnTwo;
    private LinearLayout btnThree;
    private LinearLayout btnFour;

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
        fourT =  (TextView) findViewById(R.id.m_map_small_t_four);
        fourS = (TextView) findViewById(R.id.m_map_small_s_four);

        frameOne = (LinearLayout)findViewById(R.id.rate_frame_one);
        frameTwo = (LinearLayout)findViewById(R.id.rate_frame_two);
        frameThree = (LinearLayout)findViewById(R.id.rate_frame_three);
        frameFour = (LinearLayout) findViewById(R.id.rate_frame_four);

        btnOne =(LinearLayout) findViewById(R.id.rate_btn_one);
        btnTwo =(LinearLayout) findViewById(R.id.rate_btn_two);
        btnThree =(LinearLayout) findViewById(R.id.rate_btn_three);
        btnFour =(LinearLayout) findViewById(R.id.rate_btn_four);
        //fragmen

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceButton(1);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                replaceButton(2);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                replaceButton(3);
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                replaceButton(4);

            }
        });

        rate();
        promise();
        news();
        profile();

    }
        /*
        //text

        */


    private void replaceButton(int fragmentId){
        oneT.setTextColor(getResources().getColor(R.color.colorBlack));
        twoT.setTextColor(getResources().getColor(R.color.colorBlack));
        threeT.setTextColor(getResources().getColor(R.color.colorBlack));
        fourT.setTextColor(getResources().getColor(R.color.colorBlack));

        oneS.setVisibility(View.INVISIBLE);
        twoS.setVisibility(View.INVISIBLE);
        threeS.setVisibility(View.INVISIBLE);
        fourS.setVisibility(View.INVISIBLE);

        frameOne.setVisibility(View.GONE);
        frameTwo.setVisibility(View.GONE);
        frameThree.setVisibility(View.GONE);
        frameFour.setVisibility(View.GONE);

        switch (fragmentId){
            case 1:
                oneT.setTextColor(getResources().getColor(R.color.colorMain));
                oneS.setVisibility(View.VISIBLE);
                frameOne.setVisibility(View.VISIBLE);

//                replaceFragment(1);
                break;
            case 2:
                twoT.setTextColor(getResources().getColor(R.color.colorMain));
                twoS.setVisibility(View.VISIBLE);
                frameTwo.setVisibility(View.VISIBLE);
//                replaceFragment(2);
                break;
            case 3:
                threeT.setTextColor(getResources().getColor(R.color.colorMain));
                threeS.setVisibility(View.VISIBLE);
                frameThree.setVisibility(View.VISIBLE);
//                replaceFragment(3);
                break;
            case 4:
                fourT.setTextColor(getResources().getColor(R.color.colorMain));
                fourS.setVisibility(View.VISIBLE);
                frameFour.setVisibility(View.VISIBLE);
//                replaceFragment(4);
                break;
        }
    }

    /*
    make rate
     */
    private LinearLayout promiseOne;
    private TableLayout promiseTwo;
    private void rate(){

        RadioButton segment1 = (RadioButton) findViewById(R.id.rate_gr_1);
        RadioButton segment2 = (RadioButton) findViewById(R.id.rate_gr_2);

        promiseOne = (LinearLayout) findViewById(R.id.rate_graph_layout);
        promiseTwo = (TableLayout) findViewById(R.id.rate_table_layout);

        segment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promiseOne.setVisibility(View.VISIBLE);
                promiseTwo.setVisibility(View.GONE);
            }
        });

        segment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promiseOne.setVisibility(View.GONE);
                promiseTwo.setVisibility(View.VISIBLE);
            }
        });

        segment1.performClick();
        makeTable();
        makeChart();
    }
    //make chart
    private PieChart mChart;
    private TextView tvX, tvY;
    private void makeChart(){

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(getResources().getColor(R.color.colorWhite));
        mChart.setCenterText("공약이행률\n88%");
        mChart.setCenterTextSize(20f);

        mChart.setTransparentCircleColor(getResources().getColor(R.color.colorWhite));
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        setData(5);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        mChart.getLegend().setEnabled(false);
        mChart.setEntryLabelTextSize(0f);

    }
    private String[] rateList = {"사업완료","계속추진","정상추진","일부추진","검토중"};
    private float[] rateRatio ={37,50,10,2,1};
    private void setData(int count) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry(rateRatio[i],
                    rateList[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, " ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(getResources().getColor(R.color.rate_title_one));
        colors.add(getResources().getColor(R.color.rate_title_two));
        colors.add(getResources().getColor(R.color.rate_title_three));
        colors.add(getResources().getColor(R.color.rate_title_four));
        colors.add(getResources().getColor(R.color.rate_title_five));

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(20f);
        data.setValueTextColor(getResources().getColor(R.color.colorWhite));
        //data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
    //make table
    private void makeTable(){
        TableLayout table = (TableLayout) findViewById(R.id.rate_table_layout);

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

    /*
    make promise
     */
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
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());

        ImageView promiseImg = new ImageView(this);
        promiseImg.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        promiseImg.setImageResource(R.drawable.move_black);
        linear.addView(promiseImg);

        //onclick
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                startActivity(intent);

            }
        });

        parent.addView(linear);
    }

    /*
    Three
     */
    private void news(){
        LinearLayout newsLayout = (LinearLayout) findViewById(R.id.rate_news);

        String title="서울시 중국등 동북아 도시와 미세먼지 대응 나선다.";

        newList = new ArrayList<>();

        for(int i =0 ; i<10; i++){
            newList.add(new News("아시아 투데이",title,"2017-05-26"));
        }
        addNewsList(newsLayout,newList);
    }
    ArrayList<News> newList;
    class News{
        News(String cop, String title, String date){
            this.title = title;
            this.cop = cop;
            this.date = date;
        }
        String title;
        String cop;
        String date;
    }
    private void addNewsList(LinearLayout parent,ArrayList<News> arrayList){
        for (News a: arrayList){
            addNews(parent,a.cop, a.date, a.title," ");
        }
    }
    private void addNews(LinearLayout parent, String cop, String date,String title, String url){
        LinearLayout row = new LinearLayout(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);
        int tenDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

        params.setMargins(tenDp,tenDp/2,tenDp,tenDp/2);
        //linear.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout textLayout = new LinearLayout(this);

        LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);

        //linear.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.VERTICAL);
        row.setLayoutParams(params);

        TextView textView1 = new TextView(this);
        textView1.setText(cop);
        textView1.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView textView2 = new TextView(this);
        textView2.setText(date);
        textView2.setTextColor(getResources().getColor(R.color.colorBlack));

        LinearLayout.LayoutParams paramsT1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);

        textView1.setLayoutParams(paramsT1);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textLayout.addView(textView1);
        textLayout.addView(textView2);

        LinearLayout empty = new LinearLayout(this);
        empty.setBackgroundResource(R.color.fbutton_color_green_sea);
        empty.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tenDp/2));

        TextView textViewTitle = new TextView(this);
        textViewTitle.setText(title);
        textViewTitle.setLayoutParams(paramsT1);
        textViewTitle.setTextColor(getResources().getColor(R.color.colorBlack));

        row.addView(textLayout);
        row.addView(empty);
        row.addView(textViewTitle);

        int oneDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());

        LinearLayout emptyGray = new LinearLayout(this);
        emptyGray.setBackgroundResource(R.color.colorBackgroundGray);
        emptyGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, oneDp));

        parent.addView(row);
        parent.addView(emptyGray);

    }
    /*
    four view
     */
    private void profile(){

        TextView textName = (TextView)  findViewById(R.id.rate_profile_name);
        TextView textDay = (TextView) findViewById(R.id.rate_profile_day);
        TextView textBorn = (TextView) findViewById(R.id.rate_profile_born);
        TextView textAge = (TextView) findViewById(R.id.rate_profile_age);
        TextView textEdu = (TextView) findViewById(R.id.rate_profile_education);

        textName.setText("박원순");
        textDay.setText("1996.3.26");
        textBorn.setText("경상남도 창녕");
        textAge.setText("62세");
        textEdu.setText("단국대학교");

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
    }

}
