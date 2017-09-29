package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CitizenListenActivity extends ActionBarActivity implements AbsListView.OnScrollListener{
    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;
    private int offset = 0;
    private int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_listen);

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

        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_listen);
        //rest
        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=say&offset="+offset;
        ListenRestAPI listenRestAPI = new ListenRestAPI();
        listenRestAPI.execute(url);


        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header, listview, false);
        listview.addHeaderView(header, null, false);



        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage);
        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage_help);
        del.setVisibility(View.GONE);
        listview.setOnScrollListener(this);
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
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        Rect rect = new Rect();

        backgroundImage.getLocalVisibleRect(rect);

        if (lastTopValue != rect.top) {

            lastTopValue = rect.top;

            backgroundImage.setY((float) (rect.top / 2.0));

        }

    }
    static JSONObject jsonObject=null;
    private class ListenRestAPI extends AsyncTask<String, Void, JSONObject> {

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
        protected void onPostExecute(JSONObject result){
            jsonObject = result;
            ListViewAdapter adapter = new ListViewAdapter() ;
            try{
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                int len = jsonArray.length();

                for(int i=0; i<len; i++){
                    JSONObject jres = jsonArray.getJSONObject(i);
                    Citizen item = Citizen.convertJsonToListen(jres);
                    adapter.addItem(R.drawable.listen_thaad, item.getTitle(), item.getComment(),
                            item.getAgree(), item.getOpposite(), item.getCreate_date(),
                            item.getGood() + "", item.getBad() + "", item.getCount() + "") ;
                }
                listview.setAdapter(adapter);

                offset +=limit;
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        // get item
                        Intent intent = new Intent(CitizenListenActivity.this, CitizenListenContentActivity.class);
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            JSONObject jres = jsonArray.getJSONObject(position - offset+limit-1);
                            Citizen citizen = Citizen.convertJsonToListen(jres);
                            intent.putExtra("say",citizen);
                            startActivity(intent);
                        }catch(Exception e){
                            Log.i("listen","click listen error",e);
                        }
                    }
                }) ;

            }catch (Exception e){
                Log.i("listen","error",e);
            }

        }
    }
}
