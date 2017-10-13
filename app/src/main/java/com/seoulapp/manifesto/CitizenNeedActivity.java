/**
 * Copyright 2014-2015 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 Copyright {2014} {Le Van Hoang}

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/**
 Copyright {2016} {Philipp Jahoda}

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/**
 * Copyright (c) 2015 Hien Ngo

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.*/

/**
 * The MIT License (MIT)

 Copyright (c) 2014 Le Van Hoang

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.*/
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
import java.net.URLEncoder;

public class CitizenNeedActivity extends AppCompatActivity {
    private int offset = 0;
    private int limit = 0;
    private ListView listview ;

    private String category = "분류";
    private String gu = "구선택";

    private boolean flag1 = false;
    private boolean flag2 = false;
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
                category = parent.getItemAtPosition(pos).toString();
                if (flag1 ) {
                    NeedRestAPI helpRestAPI = new NeedRestAPI();
                    String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=post&offset="+offset+"&category2="+category+"&gu="+gu;
                    helpRestAPI.execute(url);
                }
                flag1 =true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //spinner_sort
        Spinner spinner_sort = (Spinner)findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter_sort = ArrayAdapter.createFromResource(
                this,R.array.writing_Gu_array, android.R.layout.simple_spinner_item);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter_sort);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                gu = parent.getItemAtPosition(pos).toString();
                if(gu.compareTo("구 선택")==0)
                    gu="구선택";
                if (flag2) {
                    NeedRestAPI helpRestAPI = new NeedRestAPI();
                    String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=post&offset="+offset+"&category2="+category+"&gu="+gu;
                    helpRestAPI.execute(url);
                }
                flag2 = true;
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

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            JSONObject data = null;
            Bitmap bitmap = null;
            try {
                Log.d("Need",urls[0]);
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
        NeedRestAPI helpRestAPI = new NeedRestAPI();
        String url = null;
        try {
            url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=post&offset=" + offset + "&category2=" + URLEncoder.encode(category, "UTF-8") + "&gu=" + URLEncoder.encode(gu, "UTF-8");
        }catch (Exception e){
            Log.d("test","url",e);
        }
            helpRestAPI.execute(url);
    }

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}


