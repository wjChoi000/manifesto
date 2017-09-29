package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rate_detail_Activity extends AppCompatActivity {

    private JSONArray promise =null;
    private JSONObject count = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_detail_);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String city = intent.getStringExtra("city");
        int id = intent.getIntExtra("ep_id",0);
        String category = intent.getStringExtra("category");

        //rest api
        DetailRestAPI restAPI = new DetailRestAPI();
        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/ElectedPersonPromiseServlet?ep_id="+id+"&category="+category;
        restAPI.execute(url);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText(city);

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        TextView guTxt = (TextView)findViewById(R.id.rate_detail_gu);
        guTxt.setText(city);
        TextView categoryTxt = (TextView) findViewById(R.id.detail_category);
        categoryTxt.setText(name);
        CardView categoryCard = (CardView) findViewById(R.id.detail_category_card);
        if(name.compareTo("복지")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.welfare));
        else if(name.compareTo("문화")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.culture));
        else if(name.compareTo("경제")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.economy));
        else if(name.compareTo("환경")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.environment));
        else if(name.compareTo("행정")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.administation));
        else if(name.compareTo("도시·안전")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.cityAndSafty));
    }

    private void list() {
        LinearLayout list_view = (LinearLayout) findViewById(R.id.detail_list);
        int len = promise.length();
        try {
            for(int i = 0; i<len; i++){
                    JSONObject jsonObject = promise.getJSONObject(i);
                    addPromise(list_view,jsonObject.getString("contents"),jsonObject.getString("state"));
            }
        }
        catch (Exception e){
            Log.i("list","list json error",e);
        }

    }
    private void addPromise(LinearLayout parent, String s1, String i) {
        LinearLayout linear = new LinearLayout(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.0F);
        int fiveDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        params.setMargins(fiveDp,fiveDp*2,fiveDp,fiveDp*2);
        linear.setGravity(Gravity.CENTER_VERTICAL);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setLayoutParams(params);

        TextView promiseText = new TextView(this);
        promiseText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        promiseText.setText(s1);
        promiseText.setTextColor(getResources().getColor(R.color.colorBlack));
        linear.addView(promiseText);

        CardView promiseBtn = new CardView(this);
        promiseBtn.setRadius(5.0f);

        int threedp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        CardView.LayoutParams paramsBtn = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        paramsBtn.setMargins(fiveDp,0,0,0);
        promiseBtn.setLayoutParams(paramsBtn);

        TextView txt = new TextView(this);
        CardView.LayoutParams txtParam = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        txtParam.setMargins(fiveDp*2,fiveDp,fiveDp*2,fiveDp);
        txt.setLayoutParams(txtParam);
        txt.setTypeface(null, Typeface.BOLD);
        if( i.compareTo("complete")==0){
            promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_one));
            txt.setText(R.string.rate_detail_table_title_one);
        }else if(i.compareTo("normal")==0){
            promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_two));
            txt.setText(R.string.rate_detail_table_title_two);
        }else if(i.compareTo("part")==0) {
            promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_three));
            txt.setText(R.string.rate_detail_table_title_three);
        }else if(i.compareTo("continues")==0) {
            promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_four));
            txt.setText(R.string.rate_detail_table_title_four);
        }else if(i.compareTo("review")==0) {
            promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_five));
            txt.setText(R.string.rate_detail_table_title_five);
        }
        txt.setTextColor(getResources().getColor(R.color.colorWhite));
        promiseBtn.addView(txt);
        linear.addView(promiseBtn);

        int oneDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        LinearLayout emptyGray = new LinearLayout(this);
        emptyGray.setBackgroundResource(R.color.colorBackgroundGray);
        emptyGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, oneDp));

        parent.addView(linear);
        parent.addView(emptyGray);

    }

    private void count(){
        TextView detail_one = (TextView) findViewById(R.id.detail_one);
        TextView detail_two = (TextView) findViewById(R.id.detail_two);
        TextView detail_three = (TextView) findViewById(R.id.detail_three);
        TextView detail_four = (TextView) findViewById(R.id.detail_four);
        TextView detail_five = (TextView) findViewById(R.id.detail_five);
        TextView detail_six = (TextView) findViewById(R.id.detail_six);
        try{
            detail_one.setText((count.getInt("complete")+count.getInt("normal")+count.getInt("part")+count.getInt("continues")+count.getInt("review"))+"");
            detail_two.setText(count.get("complete")+"");
            detail_three.setText(count.getInt("normal")+"");
            detail_four.setText(count.getInt("part")+"");
            detail_five.setText(count.getInt("continues")+"");
            detail_six.setText(count.getInt("review")+"");
        }catch (Exception e){
            Log.i("count","count json error",e);
        }
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

    private class DetailRestAPI extends AsyncTask<String, Void, JSONObject> {

        final static String openJSONObjectURL = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/";

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
        @Override
        protected void  onPostExecute(JSONObject result){
            try {
                promise = result.getJSONArray("promise");
                count = result.getJSONObject("count");
                list();
                count();
            }catch (Exception e){
                Log.i("rest","rest api err",e);
            }
        }
    }

}

