package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.seoulapp.manifesto.model.Citizen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CitizenHelpContentActivity extends AppCompatActivity {
    private int goodCount;
    private int checknum = 0;
    private ImageView imgGood;
    private TextView tvGood, tvGoodCount;
    private ListViewAdapter_comment adapter;
    private ListView listview ;
    private ViewGroup header;
    private  Citizen content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help_content);

        //get intent and write text
        Intent intent = getIntent();
        Citizen content = (Citizen) intent.getSerializableExtra("help");

        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_help_content_comment);
        listview.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);


        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        listen.setVisibility(View.GONE);
        need.setVisibility(View.GONE);
        help.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);

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



        HelpRestAPIImage helpRestAPIImage = new HelpRestAPIImage();
        helpRestAPIImage.execute(content.getPriture());

        TextView tvTitle = (TextView)findViewById(R.id.help_content_title);
        TextView tvContext = (TextView)findViewById(R.id.help_context);
        TextView tvGoodNum = (TextView)findViewById(R.id.help_good);
        TextView tvHitNum = (TextView)findViewById(R.id.help_hit);
        TextView tvCDate = (TextView)findViewById(R.id.help_date);
        TextView tvComNum = (TextView)findViewById(R.id.help_commentNum);


        tvTitle.setText(content.getTitle());
        tvContext.setText(content.getComment());
        tvGoodNum.setText(content.getGood()+"");
        tvHitNum.setText(content.getHit()+"");
        tvCDate.setText(content.getCreate_date());
        tvComNum.setText(content.getCount()+"");

        tvGood = (TextView)findViewById(R.id.goodTvBtn);
        tvGoodCount = (TextView)findViewById(R.id.help_good);

        goodCount = content.getGood();
        findViewById(R.id.goodTvBtn).setOnClickListener(clickListener);
        imgGood = (ImageView)findViewById(R.id.imgGood);

        //add comment
        CommentRestAPI commentRestAPI = new CommentRestAPI();
        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetCommentListServlet?offset=0&category=help&id="+content.getId();
        commentRestAPI.execute(url);
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

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (checknum){
                case 0:
                    checknum++;
                    goodCount++;
                    imgGood.setImageResource(R.drawable.ag_pressed);
                    tvGood.setTextColor(getResources().getColor(R.color.agreement));
                    tvGoodCount.setTextColor(getResources().getColor(R.color.agreement));
                    tvGoodCount.setText(""+goodCount);
                    break;
                case 1:
                    checknum--;
                    goodCount--;
                    imgGood.setImageResource(R.drawable.agreement_normal);
                    tvGoodCount.setText(""+goodCount);
                    tvGood.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                    tvGoodCount.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                default:
                    break;
            }
        }
    };


    private class HelpRestAPIImage  extends AsyncTask<String, Void, Bitmap> {
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
            ImageView iv = (ImageView)findViewById(R.id.help_content_num1);
            iv.setImageBitmap(bitmap);
        }
    }


    private class CommentRestAPI extends AsyncTask<String, Void, JSONObject> {
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
            try {
                JSONArray jsonArray = result.getJSONArray("list");
                int len = jsonArray.length();

                for(int i =0; i<len; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    adapter.addItem(jsonObject.getInt("u_id")+"","","", jsonObject.getString("create_date"),jsonObject.getString("comments"));
                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Log.i("result","fail rest",e);
            }
        }
    }
}

