package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CitizenNeedContentActivity extends AppCompatActivity {
    int need_goodCount;
    int need_checknum = 0;
    ImageView need_imgGood;
    TextView need_tvGood, need_tvGoodCount;
    private  ListViewAdapter_comment adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need_content);

        //리스트뷰
        ListView listview ;


        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_need_content_comment);
        listview.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        listen.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        need.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);


        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("글 상세");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title



        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

/////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        Citizen content = (Citizen) intent.getSerializableExtra("need");

        TextView tvTitle = (TextView)findViewById(R.id.need_title_context);
        TextView tvGu = (TextView)findViewById(R.id.gu);
        TextView tvU_id = (TextView)findViewById(R.id.need_context_u_id);
        TextView tvC_date = (TextView)findViewById(R.id.need_C_date);
        TextView tvContext = (TextView)findViewById(R.id.need_context);
        TextView tvGoodNum = (TextView)findViewById(R.id.need_goodNum);
        TextView tvHitNum = (TextView)findViewById(R.id.need_hitNum);
        TextView tvComNum = (TextView)findViewById(R.id.need_comNum);
        tvTitle.setText(content.getTitle());
        tvU_id.setText(content.getU_id()+"");
        tvGu.setText(content.getGu());
        tvC_date.setText(content.getCreate_date());
        tvContext.setText(content.getComment());
        tvGoodNum.setText(content.getGood()+"");
        tvHitNum.setText(content.getHit()+"");
        tvComNum.setText(content.getCount()+"");

        need_tvGood = (TextView)findViewById(R.id.need_goodTvBtn);
        need_tvGoodCount = (TextView)findViewById(R.id.need_goodNum);

        need_goodCount = content.getGood();

        findViewById(R.id.need_goodTvBtn).setOnClickListener(need_clickListener);

        need_imgGood = (ImageView)findViewById(R.id.need_imgGood);

        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetCommentListServlet?offset=0&category=post&id="+content.getId();
        CommentRestAPI commentRestAPI = new CommentRestAPI();
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


    private View.OnClickListener need_clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (need_checknum){
                case 0:
                    need_checknum++;
                    need_goodCount++;
                    need_imgGood.setImageResource(R.drawable.ag_pressed);
                    need_tvGood.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setText(""+need_goodCount);
                    break;
                case 1:
                    need_checknum--;
                    need_goodCount--;
                    need_imgGood.setImageResource(R.drawable.agreement_normal);
                    need_tvGoodCount.setText(""+need_goodCount);
                    need_tvGood.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                default:
                    break;
            }
        }
    };

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

