package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.seoulapp.manifesto.model.Citizen;
import com.seoulapp.manifesto.util.LoginCheck;
import com.seoulapp.manifesto.util.LoginCheckDialog;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CitizenNeedActivity extends AppCompatActivity {
    private int offset = 0;
    private int limit = 0;
    private ListView listview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need);


        //spinner_category
        Spinner cateSpinner = (Spinner)findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> cateAdapter = ArrayAdapter.createFromResource(
                this,R.array.category_array, android.R.layout.simple_spinner_item);
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //spinner_sort
        Spinner spinner_sort = (Spinner)findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter_sort = ArrayAdapter.createFromResource(
                this,R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter_sort);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("필요해요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //리스트
        listview = (ListView) findViewById(R.id.listview_need);

        //rest
//        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=post&offset="+offset;
//        NeedRestAPI helpRestAPI = new NeedRestAPI();
//        helpRestAPI.execute(url);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.need_btnFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                LoginCheck loginCheck = new LoginCheck(CitizenNeedActivity.this);
                if(loginCheck.isItLogin()) {
                    Intent intent = new Intent(CitizenNeedActivity.this, CitizenNeedWritingActivity.class);
                    startActivity(intent);
                }else{
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(CitizenNeedActivity.this, false);
                    loginCheckDialog.show();
                }
            }
        });
    }
    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_writing:
                Intent intent = new Intent(this,CitizenNeedWritingActivity.class);
                startActivity(intent);
                return true;
//            case R.id.action_search:
        }
        return super.onOptionsItemSelected(item);
    }
    //메뉴 리소스를 팽창한다. 액션바에 항목들을 추가한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_citizen_need,menu);
        return super.onCreateOptionsMenu(menu);
    }
    static JSONObject jsonObject=null;
    private class NeedRestAPI extends AsyncTask<String, Void, JSONObject> {

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
            ListViewAdapter_need adapter = new ListViewAdapter_need() ;
            try{
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                int len = jsonArray.length();

                for(int i=0; i<len; i++){
                    JSONObject jres = jsonArray.getJSONObject(i);
                    Citizen item = Citizen.convertJsonToNeed(jres);
                    adapter.addItem(item.getCategory(), item.getTitle(), item.getGu(), item.getU_id() + "",
                            item.getComment(), item.getCreate_date(), item.getGood() + "", "0", item.getCount() + "");
                }
                listview.setAdapter(adapter);

                offset +=limit;
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        // get item
                        Intent intent = new Intent(CitizenNeedActivity.this, CitizenNeedContentActivity.class);
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            JSONObject jres = jsonArray.getJSONObject(position - offset+limit);
                            Citizen citizen = Citizen.convertJsonToNeed(jres);
                            intent.putExtra("need",citizen);
                            startActivity(intent);
                        }catch(Exception e){
                            Log.i("need","click listen error",e);
                        }
                    }
                }) ;

            }catch (Exception e){
                Log.i("need","error",e);
            }

        }
    }
    @Override
    protected void onResume(){
        super.onResume();

        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=post&offset="+offset;
        NeedRestAPI helpRestAPI = new NeedRestAPI();
        helpRestAPI.execute(url);
    }

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}


