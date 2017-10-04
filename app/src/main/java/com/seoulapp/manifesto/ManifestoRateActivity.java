package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.seoulapp.manifesto.restful.RestAPI;
import com.seoulapp.manifesto.restful.RestAPIImage;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;


public class ManifestoRateActivity extends AppCompatActivity {
    private JSONObject person;
    private JSONArray profile;
    private JSONObject promise_num;
    private float persent=0f;
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
    private Intent thisintent;
    private String cityString;
    private int id=0;
    private TextView Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifesto_rate);
        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        Title = (TextView) view.findViewById(R.id.actionbar_title);

        //intent
        thisintent = getIntent();
        id = thisintent.getIntExtra("id",0);
        //rest api
        RateRestAPI rateRestAPI = new RateRestAPI();
        String url1 = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/ElectedPersonInfoServlet?ep_id="+id;
        rateRestAPI.execute(url1);


        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //small titile
        oneT = (TextView)findViewById(R.id.m_map_small_t_one);
        oneS = (TextView)findViewById(R.id.m_map_small_s_one);
        twoT = (TextView)findViewById(R.id.m_map_small_t_two);
        twoS = (TextView)findViewById(R.id.m_map_small_s_two);
//        threeT = (TextView)findViewById(R.id.m_map_small_t_three);
//        threeS = (TextView)findViewById(R.id.m_map_small_s_three);
        fourT =  (TextView) findViewById(R.id.m_map_small_t_four);
        fourS = (TextView) findViewById(R.id.m_map_small_s_four);

        frameOne = (LinearLayout)findViewById(R.id.rate_frame_one);
        frameTwo = (LinearLayout)findViewById(R.id.rate_frame_two);
//        frameThree = (LinearLayout)findViewById(R.id.rate_frame_three);
        frameFour = (LinearLayout) findViewById(R.id.rate_frame_four);

        btnOne =(LinearLayout) findViewById(R.id.rate_btn_one);
        btnTwo =(LinearLayout) findViewById(R.id.rate_btn_two);
//        btnThree =(LinearLayout) findViewById(R.id.rate_btn_three);
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
        btnFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                replaceButton(4);

            }
        });
        btnOne.performClick();

    }

    private void replaceButton(int fragmentId){
        oneT.setTextColor(getResources().getColor(R.color.colorBlack));
        twoT.setTextColor(getResources().getColor(R.color.colorBlack));
//        threeT.setTextColor(getResources().getColor(R.color.colorBlack));
        fourT.setTextColor(getResources().getColor(R.color.colorBlack));

        oneS.setVisibility(View.INVISIBLE);
        twoS.setVisibility(View.INVISIBLE);
//        threeS.setVisibility(View.INVISIBLE);
        fourS.setVisibility(View.INVISIBLE);

        frameOne.setVisibility(View.GONE);
        frameTwo.setVisibility(View.GONE);
//        frameThree.setVisibility(View.GONE);
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
//                threeT.setTextColor(getResources().getColor(R.color.colorMain));
//                threeS.setVisibility(View.VISIBLE);
//                frameThree.setVisibility(View.VISIBLE);
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
    private String[] rateList2 = {"계속추진","일부추진","정상추진","사업완료","검토중"};
    private float[] rateRatio2 ={2,10,50,37,1};
    private PieChart mChart;
    private String[] rateList = {"검토중","일부추진","계속추진","정상추진","사업완료"};
    private float[] rateRatio =null;
    protected HorizontalBarChart hChart;

    private void rate(){
        try {
            int review = person.getInt("review");
            int part = person.getInt("part");
            int complete = person.getInt("complete");
            int normal = person.getInt("normal");
            int continues = person.getInt("continues");
            // {"검토중","일부추진","정상추진","계속추진","사업완료"};
            rateRatio = new float[]{(float)review,(float)part,(float)continues,(float)normal,(float)complete};
            int sum = review+part+complete+normal+continues;
            //{"일부추진","정상추진","계속추진","사업완료","검토중"};
            rateRatio2 = new float[]{(float)continues/sum,(float)part/sum,(float)normal/sum,(float)complete/sum,(float)review/sum};
            persent = (float)((sum-review-part)*100)/sum;
            Log.i("rate", rateRatio[0]+" "+rateRatio[4]);
        }catch (Exception e){
            Log.i("rate","rate json error",e);
        }
        hChart = (HorizontalBarChart) findViewById(R.id.rate_Barchart);
        mChart = (PieChart) findViewById(R.id.chart1);
        makeChart();
        makeHChart();
        RadioButton segment1 = (RadioButton) findViewById(R.id.rate_gr_1);
        RadioButton segment2 = (RadioButton) findViewById(R.id.rate_gr_2);
        segment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChart.setVisibility(View.VISIBLE);
                hChart.setVisibility(View.GONE);
            }
        });
        segment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChart.setVisibility(View.GONE);
                hChart.setVisibility(View.VISIBLE);
            }
        });
        segment1.performClick();
    }
    //make barchrt : rate_Barchart


    private void makeHChart(){
        hChart.setDrawBarShadow(false);
        hChart.setDrawValueAboveBar(true);
        hChart.getDescription().setEnabled(false);

        hChart.setMaxVisibleValueCount(100);
        hChart.setPinchZoom(false);
        hChart.setDrawGridBackground(false);
        XAxis xl = hChart.getXAxis();
        xl.setDrawGridLines(false);
        hChart.getAxisLeft().setEnabled(false);
        hChart.getXAxis().setEnabled(false);

        xl.setGranularity(1f);
        xl.setCenterAxisLabels(true);
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });

        setDataH(hChart,5,rateList,rateRatio);
        hChart.setFitBars(true);
        hChart.animateY(2500);
        hChart.getLegend().setEnabled(false);
          }
    private void setDataH(HorizontalBarChart hchart, int count,String[] s, float[] f) {

        float barWidth = 9f;
        float spaceForBar = 15f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            yVals1.add(new BarEntry(i * spaceForBar, f[i],s[i]));
        }

        BarDataSet set1;

        if (hchart.getData() != null &&
                hchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) hchart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            hchart.getData().notifyDataChanged();
            hchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");

            ArrayList<Integer> colors = new ArrayList<Integer>();
            colors.add(getResources().getColor(R.color.rate_title_five));
            colors.add(getResources().getColor(R.color.rate_title_four));
            colors.add(getResources().getColor(R.color.rate_title_three));
            colors.add(getResources().getColor(R.color.rate_title_two));
            colors.add(getResources().getColor(R.color.rate_title_one));
            set1.setColors(colors);

            set1.setDrawIcons(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);

            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            hchart.setData(data);
        }
    }

    //make chart

    private void makeChart(){
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(getResources().getColor(R.color.colorWhite));
        mChart.setCenterText("공약이행률\n"+String.format("%.1f",persent)+"%");
        mChart.setCenterTextSize(20f);

        mChart.setTransparentCircleColor(getResources().getColor(R.color.colorWhite));
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        int count = 0;
        for(int i =0; i< rateRatio2.length;i++){
            if(rateRatio2[i] !=0){
                count += 1;
            }
        }
        float[] rateRatioTemp = new float[count];
        String[] rateListTemp = new String[count];
        int j =0;
        for(int i =0; i< rateRatio2.length;i++) {
            if (rateRatio2[i] != 0) {
                rateRatioTemp[j] = rateRatio2[i];
                rateListTemp[j] = rateList2[i];
                j += 1;
            }
        }
        setData(mChart,count,rateListTemp,rateRatioTemp,1,false);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        mChart.getLegend().setEnabled(false);
        mChart.setEntryLabelTextSize(15f);
        mChart.setEntryLabelColor(getResources().getColor(R.color.colorWhite));

    }
    private void setData(PieChart chart,int count,String[] s, float[] f, int mode,boolean outLable) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry(f[i], s[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, " ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        if(mode ==1) {

            for(String item: s){
                if(item.compareTo("사업완료")==0){
                    colors.add(getResources().getColor(R.color.rate_title_one));
                }
                else if(item.compareTo("정상추진")==0){
                    colors.add(getResources().getColor(R.color.rate_title_two));
                }
                else if(item.compareTo("일부추진")==0){
                    colors.add(getResources().getColor(R.color.rate_title_three));
                }
                else if(item.compareTo("계속추진")==0){
                    colors.add(getResources().getColor(R.color.rate_title_four));
                }
                else if(item.compareTo("검토중")==0){
                    colors.add(getResources().getColor(R.color.rate_title_five));
                }
            }
        }else{
            for(String item: s){
                if(item.compareTo("복지")==0)
                    colors.add(getResources().getColor(R.color.welfare));
                else if(item.compareTo("문화")==0)
                    colors.add(getResources().getColor(R.color.culture));
                else if(item.compareTo("경제")==0)
                    colors.add(getResources().getColor(R.color.economy));
                else if(item.compareTo("환경")==0)
                    colors.add(getResources().getColor(R.color.environment));
                else if(item.compareTo("행정")==0)
                    colors.add(getResources().getColor(R.color.administation));
                else if(item.compareTo("도시안전")==0)
                    colors.add(getResources().getColor(R.color.cityAndSafty));
            }
        }
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);

        //data.setValueTypeface(mTfLight);
        if(outLable) {
            data.setValueTextColor(getResources().getColor(R.color.colorBlack));
            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        }else{
            data.setValueTextColor(getResources().getColor(R.color.colorWhite));
        }
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    /*
    make promise
     */
    private PieChart listChart;
    private TextView tvX, tvY;

    private void promise(){
        makePromiseChart();
        LinearLayout promiseList = (LinearLayout) findViewById(R.id.m_promise_list);
        Button welfare = (Button) findViewById(R.id.rate_list_welfare);
        Button culture = (Button) findViewById(R.id.rate_list_culture);
        Button economy = (Button) findViewById(R.id.rate_list_economy);
        Button envirpment = (Button) findViewById(R.id.rate_list_environment);
        Button administrain = (Button)findViewById(R.id.rate_list_administation);
        Button citybtn = (Button)findViewById(R.id.rate_list_city);

        welfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","복지");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","wel");
                startActivity(intent);
            }
        });
        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","문화");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","cul");
                startActivity(intent);
            }
        });
        economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","경제");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","eco");
                startActivity(intent);
            }
        });
        envirpment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","환경");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","envi");
                startActivity(intent);
            }
        });
        administrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","행정");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","adm");
                startActivity(intent);
            }
        });
        citybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManifestoRateActivity.this, Rate_detail_Activity.class);
                intent.putExtra("name","도시·안전");
                intent.putExtra("city", cityString);
                intent.putExtra("ep_id",id);
                intent.putExtra("category","city");
                startActivity(intent);
            }
        });
    }
    private void makePromiseChart(){
        listChart= (PieChart) findViewById(R.id.rate_list_chart);
        listChart.setUsePercentValues(true);
        listChart.getDescription().setEnabled(false);
        listChart.setExtraOffsets(5, 10, 5, 5);

        listChart.setDragDecelerationFrictionCoef(0.95f);

        listChart.setDrawHoleEnabled(true);
        listChart.setHoleColor(getResources().getColor(R.color.colorWhite));
        listChart.setCenterText("공약 비율");
        listChart.setCenterTextSize(20f);

        listChart.setTransparentCircleColor(getResources().getColor(R.color.colorWhite));
        listChart.setTransparentCircleAlpha(110);

        listChart.setHoleRadius(58f);
        listChart.setTransparentCircleRadius(61f);

        // enable rotation of the chart by touch
        listChart.setRotationEnabled(false);
        listChart.setHighlightPerTapEnabled(true);
        try{
            int wel = promise_num.getInt("welfare");
            int cul =  promise_num.getInt("culture");
            int eco =  promise_num.getInt("economy");
            int envi =  promise_num.getInt("envi");
            int city = promise_num.getInt("city");
            int admi =  promise_num.getInt("admi");
            int sum= wel+cul+ eco+envi+city+admi;
            float[] rate = {(float)wel/sum, (float)cul/sum, (float)eco/sum, (float)envi/sum, (float)admi/sum, (float)city/sum};
            String[] list = {"복지","문화","경제","환경","행정","도시안전"};

            int count = 0;
            for(int i =0; i< rate.length;i++){
                if(rate[i] !=0){
                    count += 1;
                }
            }
            float[] rateRatioTemp = new float[count];
            String[] rateListTemp = new String[count];
            int j =0;
            for(int i =0; i< rate.length;i++) {
                if (rate[i] != 0) {
                    rateRatioTemp[j] = rate[i];
                    rateListTemp[j] = list[i];
                    j += 1;
                }
            }
            setData(listChart,count,rateListTemp,rateRatioTemp,2,false);
        }catch (Exception e){
            Log.i("promise","json promise error",e);
        }
        listChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        listChart.getLegend().setEnabled(false);
        listChart.setEntryLabelTextSize(0f);

        listChart.setEntryLabelColor(getResources().getColor(R.color.colorWhite));
        listChart.setEntryLabelTextSize(15f);
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
//    private void news(){
//        LinearLayout newsLayout = (LinearLayout) findViewById(R.id.rate_news);
//
//        String title="서울시 중국등 동북아 도시와 미세먼지 대응 나선다.";
//
//        newList = new ArrayList<>();
//
//        for(int i =0 ; i<10; i++){
//            newList.add(new News("아시아 투데이",title,"2017-05-26"));
//        }
//        addNewsList(newsLayout,newList);
//    }
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
        int tenDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        params.setMargins(tenDp,tenDp,tenDp,tenDp);
        //linear.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout textLayout = new LinearLayout(this);

        LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);

        //linear.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.VERTICAL);
        row.setLayoutParams(params);

        TextView textView1 = new TextView(new ContextThemeWrapper(this,R.style.rate_news_cor));
        textView1.setText(cop);
        textView1.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView textView2 = new TextView(new ContextThemeWrapper(this, R.style.rate_news_nontitle));
        textView2.setText(date);
        textView2.setTextColor(getResources().getColor(R.color.colorBlack));

        LinearLayout.LayoutParams paramsT1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);

        textView1.setLayoutParams(paramsT1);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textLayout.addView(textView1);
        textLayout.addView(textView2);
        row.addView(textLayout);

//        LinearLayout empty = new LinearLayout(this);
//        empty.setOrientation(LinearLayout.HORIZONTAL);
//        //empty.setBackgroundResource(R.color.fbutton_color_green_sea);
//        empty.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tenDp));
//
//        LinearLayout empty1 = new LinearLayout(this);
//        empty1.setBackgroundResource(R.color.rate_new_1);
//        empty1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));
//
//        LinearLayout empty2 = new LinearLayout(this);
//        empty2.setBackgroundResource(R.color.rate_new_2);
//        empty2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));
//        empty.addView(empty1);
//        empty.addView(empty2);
//        row.addView(empty);

        TextView textViewTitle = new TextView(new ContextThemeWrapper(this,R.style.rate_news_title));
        textViewTitle.setText(title);
        textViewTitle.setLayoutParams(paramsT1);

        textViewTitle.setTextColor(getResources().getColor(R.color.colorBlack));

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
//        TextView textEdu = (TextView) findViewById(R.id.rate_profile_education);
        try{
            textName.setText(person.getString("person_name"));
            String born_date = person.getString("born_day");
            StringTokenizer tokens = new StringTokenizer(born_date);
            int born = Integer.parseInt(tokens.nextToken("-"));
            textDay.setText(born_date);
            textBorn.setText(person.getString("born_region"));

            java.util.Calendar cal = java.util.Calendar.getInstance();
            String ntime = new String();
            textAge.setText((cal.get(Calendar.YEAR)-born)+"세");
        }catch (Exception e){
            Log.i("profile","profile json error",e);
        }

        final LinearLayout aware = (LinearLayout) findViewById(R.id.m_profile_aware);
        final LinearLayout career = (LinearLayout) findViewById(R.id.m_profile_career);
        final LinearLayout edu = (LinearLayout) findViewById(R.id.m_profile_edu);

        final LinearLayout segment1 = (LinearLayout) findViewById(R.id.rate_profile_header1);
        final LinearLayout segment2 = (LinearLayout) findViewById(R.id.rate_profile_header2);
        final LinearLayout segment3 = (LinearLayout) findViewById(R.id.rate_profile_header3);
        final TextView segementt1=(TextView) findViewById(R.id.rate_profile_header_text1);
        final TextView segementt2=(TextView) findViewById(R.id.rate_profile_header_text2);
        final TextView segementt3=(TextView) findViewById(R.id.rate_profile_header_text3);
        segment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aware.setVisibility(View.VISIBLE);
                career.setVisibility(View.GONE);
                edu.setVisibility(View.GONE);
                segment1.setBackgroundResource(R.color.rate_profile_head_dark_gray);
                segment2.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segment3.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segementt1.setTextColor(getResources().getColor(R.color.colorWhite));
                segementt2.setTextColor(getResources().getColor(R.color.colorBlack));
                segementt3.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        segment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aware.setVisibility(View.GONE);
                career.setVisibility(View.VISIBLE);
                edu.setVisibility(View.GONE);
                segment1.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segment2.setBackgroundResource(R.color.rate_profile_head_dark_gray);
                segment3.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segementt1.setTextColor(getResources().getColor(R.color.colorBlack));
                segementt2.setTextColor(getResources().getColor(R.color.colorWhite));
                segementt3.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        segment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aware.setVisibility(View.GONE);
                career.setVisibility(View.GONE);
                edu.setVisibility(View.VISIBLE);
                segment1.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segment2.setBackgroundResource(R.color.rate_profile_head_white_gray);
                segment3.setBackgroundResource(R.color.rate_profile_head_dark_gray);
                segementt1.setTextColor(getResources().getColor(R.color.colorBlack));
                segementt2.setTextColor(getResources().getColor(R.color.colorBlack));
                segementt3.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });
        segment1.performClick();

        try{
            int len = profile.length();
            for(int i =0; i<len; i++){
                JSONObject jsonObject = profile.getJSONObject(i);
                String category = jsonObject.getString("category");
                if(category.compareTo("career") == 0){
                    addText(career,jsonObject.getString("date")+" "+jsonObject.get("contents"));
                }else if(category.compareTo("prize") == 0){
                    addText(aware,jsonObject.getString("date")+" "+jsonObject.get("contents"));
                }else if(category.compareTo("eduback") == 0){
                    addText(edu,jsonObject.getString("date")+" "+jsonObject.get("contents"));
                }
            }
        }catch (Exception e){

        }
//        addText(crime,"없음");
    }

    private void addText(LinearLayout parent,String s){
        TextView text = new TextView(this);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setText(s);
        text.setTextColor(getResources().getColor(R.color.colorBlack));
        text.setPadding(40,0,0,0);
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
    private class RateRestAPI extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            JSONObject data = null;
            Bitmap bitmap = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                //connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp = "";
                while ((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();

                data = new JSONObject(json.toString());
            } catch (Exception e) {
                Log.i("result", urls[0], e);
            }
            return data;
        }

        protected void onPostExecute(JSONObject result) {

            try{
                person = result.getJSONObject("person");
                String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/gorvenor/"+person.getString("priture");
                Log.i("restful", url);
                RateRestAPIImage restAPIImage = new RateRestAPIImage();
                restAPIImage.execute(url);

                profile = result.getJSONArray("profile");
                promise_num = result.getJSONObject("promise_num");
                cityString = person.getString("local");
                Log.i("restful","Success");
            }catch (Exception e){
                Log.i("result","fail rest",e);
            }


            Title.setText(cityString);
            rate();
            promise();
            profile();
        }
    }

    private class RateRestAPIImage  extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream is  = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);

            } catch (Exception e) {
                Log.i("result", urls[0], e);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            ImageView imageView = (ImageView) findViewById(R.id.rate_profile_governor);
            imageView.setImageBitmap(bitmap);
        }
    }
    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
