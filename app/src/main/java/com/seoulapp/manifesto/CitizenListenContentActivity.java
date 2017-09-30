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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CitizenListenContentActivity extends AppCompatActivity {
    int agcount;
    int opcount;
    TextView agtextview,optextview;
    CheckBox agCheck,opCheck;
    private ListViewAdapter_comment adapter;
    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    TextView dateNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_listen_content);

        //리스트뷰
        ListView listview ;


        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_listen_content_comment);
        listview.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        help.setVisibility(View.GONE);
        need.setVisibility(View.GONE);
        listen.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);


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

        /////////////////////////////////////////////////////////////////////////
        agtextview = (TextView)findViewById(R.id.ag_count);
        findViewById(R.id.agreementBtn).setOnClickListener(clickListener);
        agCheck = (CheckBox)findViewById(R.id.agreementBtn);

        optextview = (TextView)findViewById(R.id.op_count);
        findViewById(R.id.oppositionBtn).setOnClickListener(clickListener);
        opCheck = (CheckBox)findViewById(R.id.oppositionBtn);


        //////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        Citizen content = (Citizen) intent.getSerializableExtra("say");
        ListenRestAPIImage listenRestAPIImage = new  ListenRestAPIImage();
        listenRestAPIImage.execute(content.getPriture());

        TextView tvTitle = (TextView)findViewById(R.id.titleText);
        TextView tvSubTitle = (TextView)findViewById(R.id.Listen_subtitle);
        TextView tvAgContext = (TextView)findViewById(R.id.Ag_context);
        TextView tvOpContext = (TextView)findViewById(R.id.Op_context);
        TextView tvCDate = (TextView)findViewById(R.id.c_date);
        TextView tvComNum = (TextView)findViewById(R.id.listen_comNum);

        tvTitle.setText(content.getTitle());
        tvSubTitle.setText(content.getComment());
        tvAgContext.setText(content.getAgree()+"");
        tvOpContext.setText(content.getOpposite()+"");
        tvCDate.setText(content.getCreate_date());
        tvComNum.setText(content.getCount()+"");

        agtextview.setText("찬성 "+content.getGood());
        optextview.setText("반대 "+content.getBad());

        agcount = content.getGood();
        opcount = content.getBad();

        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetCommentListServlet?offset=0&category=say&id="+content.getId();
        CommentRestAPI commentRestAPI = new CommentRestAPI();
        commentRestAPI.execute(url);

    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (v.getId()){
                case R.id.agreementBtn:
                    if(opCheck.isChecked() ==true){
                        opcount--;
                        optextview.setText("반대 "+opcount);
                        optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        opCheck.setChecked(false);
                        agcount++;
                        agtextview.setText("찬성 "+agcount);
                        agtextview.setTextColor(getResources().getColor(R.color.agreement));
                    }else {
                        if(agCheck.isChecked() ==true){
                            agcount++;
                            agtextview.setText("찬성 "+agcount);
                            agtextview.setTextColor(getResources().getColor(R.color.agreement));
                        }else{
                            agcount--;
                            agtextview.setText("찬성 "+agcount);
                            agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        }
                    }
                    break;
                case R.id.oppositionBtn:
                    if(agCheck.isChecked() ==true){
                        agcount--;
                        agtextview.setText("찬성 "+agcount);
                        agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        agCheck.setChecked(false);
                        opcount++;
                        optextview.setText("반대 "+opcount);
                        optextview.setTextColor(getResources().getColor(R.color.opposition));
                    }else{
                        if(opCheck.isChecked() ==true){
                            opcount++;
                            optextview.setText("반대 "+opcount);
                            optextview.setTextColor(getResources().getColor(R.color.opposition));
                        }else{
                            opcount--;
                            optextview.setText("반대 "+opcount);
                            optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onClick(View target){
        int id=target.getId();


        switch (id){
            case R.id.agreementBtn:
                Toast.makeText(this, "찬성", Toast.LENGTH_SHORT).show();
                break;
            case R.id.oppositionBtn:
                Toast.makeText(this, "반대", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private class ListenRestAPIImage  extends AsyncTask<String, Void, Bitmap> {
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
            ImageView iv = (ImageView)findViewById(R.id.Listen_content_num1);
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

                    if(jsonObject.getString("opinion").compareTo("1")==0)
                        adapter.addItem(jsonObject.getInt("u_id")+"","찬성","", jsonObject.getString("create_date"),jsonObject.getString("comments"));
                    else
                        adapter.addItem(jsonObject.getInt("u_id")+"","","반대", jsonObject.getString("create_date"),jsonObject.getString("comments"));

                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Log.i("result","fail rest",e);
            }
        }
    }
    
}

