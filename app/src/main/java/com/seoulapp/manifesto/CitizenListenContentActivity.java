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
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;
import com.seoulapp.manifesto.restful.RestAPI;
import com.seoulapp.manifesto.util.LoginCheck;
import com.seoulapp.manifesto.util.LoginCheckDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

import info.hoang8f.widget.FButton;

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

    private EditText editText;
    private int id;
    private LoginCheck loginCheck;
    private boolean opinion= true;
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
        agCheck = (CheckBox)findViewById(R.id.agreementBtn);
        optextview = (TextView)findViewById(R.id.op_count);
        opCheck = (CheckBox)findViewById(R.id.oppositionBtn);
//
//        findViewById(R.id.agreementBtn).setOnClickListener(clickListener);
//        findViewById(R.id.oppositionBtn).setOnClickListener(clickListener);
        //////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        Citizen content = (Citizen) intent.getSerializableExtra("say");
        id = content.getId();
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

        //edit text
        loginCheck = new LoginCheck(this);
        editText = (EditText) findViewById(R.id.listen_editText);
        TextView listen_fake= (TextView) findViewById(R.id.listen_fake);

        if(loginCheck.isItLogin()){
            editText.setVisibility(View.VISIBLE);
            listen_fake.setVisibility(View.GONE);
            FButton listen_transmit = (FButton) findViewById(R.id.listen_transmit);
            listen_transmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    String comment =editText.getText().toString();
                    if ( comment.length() > 0 ) {
                        RestAPI restAPI = new RestAPI();
                        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenCommentInsertServlet?category=say&u_id="+loginCheck.getID()+"&h_id="+id+"&comment="+comment+"&opinion="+opinion;
                        editText.setText("");
                        restAPI.execute(url);
                        if(opinion){
                            adapter.addFirstItem(loginCheck.getNickname()+"","찬성","", "방금",comment);
                        }else{
                            adapter.addFirstItem(loginCheck.getNickname()+"","","반대", "방금",comment);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(CitizenListenContentActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            });
        }else{
            editText.setVisibility(View.GONE);
            listen_fake.setVisibility(View.VISIBLE);

            LinearLayout listen_comment = (LinearLayout) findViewById(R.id.listen_comment);
            listen_comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(CitizenListenContentActivity.this,false);
                    loginCheckDialog.show();
                }
            });
        }

        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetCommentListServlet?offset=0&category=say&id="+content.getId();
        CommentRestAPI commentRestAPI = new CommentRestAPI();
        commentRestAPI.execute(url);


    }

//    private View.OnClickListener clickListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View v){
//
//            switch (v.getId()){
//                case R.id.agreementBtn:
//                    if(opCheck.isChecked() ==true){
//                        opcount--;
//                        optextview.setText("반대 "+opcount);
//                        optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
//                        opCheck.setChecked(false);
//                        agcount++;
//                        agtextview.setText("찬성 "+agcount);
//                        agtextview.setTextColor(getResources().getColor(R.color.agreement));
//                    }else {
//                        if(agCheck.isChecked() ==true){
//                            agcount++;
//                            agtextview.setText("찬성 "+agcount);
//                            agtextview.setTextColor(getResources().getColor(R.color.agreement));
//                        }else{
//                            agcount--;
//                            agtextview.setText("찬성 "+agcount);
//                            agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
//                        }
//                    }
//                    break;
//                case R.id.oppositionBtn:
//                    if(agCheck.isChecked() ==true){
//                        agcount--;
//                        agtextview.setText("찬성 "+agcount);
//                        agtextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
//                        agCheck.setChecked(false);
//                        opcount++;
//                        optextview.setText("반대 "+opcount);
//                        optextview.setTextColor(getResources().getColor(R.color.opposition));
//                    }else{
//                        if(opCheck.isChecked() ==true){
//                            opcount++;
//                            optextview.setText("반대 "+opcount);
//                            optextview.setTextColor(getResources().getColor(R.color.opposition));
//                        }else{
//                            opcount--;
//                            optextview.setText("반대 "+opcount);
//                            optextview.setTextColor(getResources().getColor(R.color.colorBackgroundGray));
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    public static void scrollToView(View view,final ScrollView scrollView){
        scrollToView(view,scrollView,0);
    }
    public static void scrollToView(View view, final ScrollView scrollView,int count){
        if(view !=null && view != scrollView){
            count += view.getTop();
            scrollToView((View) view.getParent(),scrollView,count);
        }
        else if(scrollView!= null){
            final int finalCount =count;
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.smoothScrollTo(0,finalCount);
                }
            });
//            new Handler().postDelayed(new Runnable(){
//                @Override
//                public void run(){
//                    scrollView.smoothScrollTo(0,finalCount);
//                }
//            },200);
        }
    }
}

