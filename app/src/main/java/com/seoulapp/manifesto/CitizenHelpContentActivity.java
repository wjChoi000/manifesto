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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CitizenHelpContentActivity extends AppCompatActivity {
    int goodCount;
    int checknum = 0;
    ImageView imgGood;
    TextView tvGood, tvGoodCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help_content);

        //리스트뷰
        ListView listview ;
        ListViewAdapter_comment adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_help_content_comment);
        listview.setAdapter(adapter);

        //
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
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


        //get intent and write text
        Intent intent = getIntent();
        Citizen content = (Citizen) intent.getSerializableExtra("help");
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

        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");
        adapter.addItem("Wonsoonpark","","", "2017-08-04","저도 똑같은 경험을 하였습니다. 해도 너무하네요");

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
}

