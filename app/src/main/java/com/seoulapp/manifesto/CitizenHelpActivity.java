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
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.seoulapp.manifesto.model.Citizen;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CitizenHelpActivity extends ActionBarActivity implements AbsListView.OnScrollListener{

    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;
    private int offset = 0;
    private int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help);

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


        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_help);

//        //rest
//        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=help&offset="+offset;
//        HelpRestAPI helpRestAPI = new HelpRestAPI();
//        helpRestAPI.execute(url);

        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_help, listview, false);
        listview.addHeaderView(header, null, false);


        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage_help);
//        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage);
//        del.setVisibility(View.GONE);
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
    private class HelpRestAPI extends AsyncTask<String, Void, JSONObject> {

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
            try{
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                CitizenRestAPIImage citizenRestAPIImage = new CitizenRestAPIImage();
                citizenRestAPIImage.execute(jsonArray);

            }catch (Exception e){
                Log.i("help","error",e);
            }

        }
    }


    private class CitizenRestAPIImage  extends AsyncTask<JSONArray, Void, Bitmap[]> {
        JSONArray jsonArray;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Bitmap[] doInBackground(JSONArray... urls) {
            jsonArray = urls[0];
            int len = jsonArray.length();
            Bitmap[] bitmap = new Bitmap[len];
            try {
                for(int i =0 ; i<len;i++) {
                    URL url = new URL("http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/help/"+jsonArray.getJSONObject(i).getString("priture"));
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    bitmap[i] = BitmapFactory.decodeStream(is);
                }
            } catch (Exception e) {
                Log.i("result", "error", e);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap[] bitmap){
            try {
                ListViewAdapter_help adapter = new ListViewAdapter_help();
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject jres = jsonArray.getJSONObject(i);
                    Citizen item = Citizen.convertJsonToHelp(jres);
                    adapter.addItem(bitmap[i], item.getTitle(), item.getComment(),
                            item.getCreate_date(), item.getGood() + "", "0", item.getCount() + "");
                }
                listview.setAdapter(adapter);

//                offset += limit;
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        // get item
                        Intent intent = new Intent(CitizenHelpActivity.this, CitizenHelpContentActivity.class);
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
//                            JSONObject jres = jsonArray.getJSONObject(position - offset + limit - 1);
                            JSONObject jres = jsonArray.getJSONObject(position - offset - 1);
                            Citizen citizen = Citizen.convertJsonToHelp(jres);
                            citizen.setPriture("http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/help/"+citizen.getPriture());
                            intent.putExtra("help", citizen);
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.i("help", "click listen error", e);
                        }
                    }
                });
            }catch (Exception e){
                Log.i("help","error",e);
            }
        }
    }
    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onResume(){
        super.onResume();
        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetListServlet?category=help&offset="+offset;
        HelpRestAPI helpRestAPI = new HelpRestAPI();
        helpRestAPI.execute(url);
    }
}
