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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;
import com.seoulapp.manifesto.restful.RestAPI;
import com.seoulapp.manifesto.restful.RestAPIImage;
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

import info.hoang8f.widget.FButton;


public class CitizenNeedContentActivity extends AppCompatActivity {
    int need_goodCount;
    int need_checknum = 0;
    ImageView need_imgGood;
    TextView need_tvGood, need_tvGoodCount;
    private  ListViewAdapter_comment adapter;
    private LoginCheck loginCheck;
    private EditText editText;
    private int id;
    private Citizen content;
    private TextView listen_fake;

    //1 no_change , -1 change
    private int checknum_flag =1;
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
        LinearLayout know = (LinearLayout)header.findViewById(R.id.listheader_know_content);
        know.setVisibility(View.GONE);
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
        content = (Citizen) intent.getSerializableExtra("need");
        id = content.getId();
        String picture = content.getPriture();
        if(picture != null){
            if(picture.length() >0){
                Log.d("need","getImage : "+picture);
                NeedImageRestAPI needImageRestAPI = new NeedImageRestAPI();
                String url2 = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/need/"+picture;
                needImageRestAPI.execute(url2);
            }
        }

        TextView tvTitle = (TextView)findViewById(R.id.need_title_context);
        TextView tvGu = (TextView)findViewById(R.id.gu);
        TextView tvU_id = (TextView)findViewById(R.id.need_context_u_id);
        TextView tvC_date = (TextView)findViewById(R.id.need_C_date);
        final TextView tvContext = (TextView)findViewById(R.id.need_context);
        TextView tvGoodNum = (TextView)findViewById(R.id.need_goodNum);
        TextView tvHitNum = (TextView)findViewById(R.id.need_hitNum);
        TextView tvComNum = (TextView)findViewById(R.id.need_comNum);
        tvTitle.setText(content.getTitle());
        tvU_id.setText(content.getUser_name());
        tvGu.setText(content.getGu());
        tvC_date.setText(content.getCreate_date());
        tvContext.setText(content.getComment());
        tvGoodNum.setText(content.getGood()+"");
        tvHitNum.setText(content.getHit()+"");
        tvComNum.setText(content.getCount()+"");

        need_tvGood = (TextView)findViewById(R.id.need_goodTvBtn);
        need_tvGoodCount = (TextView)findViewById(R.id.need_goodNum);
        need_goodCount = content.getGood();

        loginCheck = new LoginCheck(this);


        need_imgGood = (ImageView)findViewById(R.id.need_imgGood);


        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenGetCommentListServlet?offset=0&category=post&id="+content.getId()+"&u_id="+loginCheck.getID();
        CommentRestAPI commentRestAPI = new CommentRestAPI();
        commentRestAPI.execute(url);

        //comment
        //edit text
        editText = (EditText) findViewById(R.id.need_editText);
        listen_fake= (TextView) findViewById(R.id.need_fake);


    }
    @Override
    public void onResume(){
        super.onResume();
        if(loginCheck.isItLogin()){
            editText.setVisibility(View.VISIBLE);
            listen_fake.setVisibility(View.GONE);
            FButton listen_transmit = (FButton) findViewById(R.id.need_transmit);
            listen_transmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    String comment =editText.getText().toString();
                    if ( comment.length() > 0 ) {
                        String comment2= null;
                        try {
                            comment2 = URLEncoder.encode(editText.getText().toString(), "UTF-8");
                        }catch (Exception e){
                            Log.d("test","comment",e);
                        }
                        RestAPI restAPI = new RestAPI();
                        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenCommentInsertServlet?category=post&u_id="+loginCheck.getID()+"&h_id="+id+"&comment="+comment2;
                        editText.setText("");
                        restAPI.execute(url);

                        adapter.addItem(loginCheck.getNickname()+"","","", "방금",comment);
                        content.setCount(content.getCount()+1);
                        ((TextView)findViewById(R.id.need_comNum)).setText(content.getCount()+"");
                        adapter.notifyDataSetChanged();
                        Toast.makeText(CitizenNeedContentActivity.this,"댓글 작성 완료",Toast.LENGTH_SHORT).show();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(CitizenNeedContentActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            });
            findViewById(R.id.need_good_btn).setOnClickListener(need_clickListener);
        }else{
            editText.setVisibility(View.GONE);
            listen_fake.setVisibility(View.VISIBLE);

            LinearLayout listen_comment = (LinearLayout) findViewById(R.id.need_comment);
            listen_comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(CitizenNeedContentActivity.this,false);
                    loginCheckDialog.show();
                }
            });
            findViewById(R.id.need_good_btn).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(CitizenNeedContentActivity.this,false);
                    loginCheckDialog.show();
                }
            });
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
    }


    private View.OnClickListener need_clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String url=null;
            RestAPI restAPI = new RestAPI();
            switch (need_checknum){
                case 0:
                    need_checknum++;
                    need_goodCount++;
                    need_imgGood.setImageResource(R.drawable.ag_pressed);
                    need_tvGood.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setText(""+need_goodCount);

                    url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/GoodOrBadUpdateServlet?category=post&c_id="+id+"&u_id="+loginCheck.getID()+"&option=update";
                    restAPI.execute(url);

                    break;
                case 1:
                    need_checknum--;
                    need_goodCount--;
                    need_imgGood.setImageResource(R.drawable.agreement_normal);
                    need_tvGoodCount.setText(""+need_goodCount);
                    need_tvGood.setTextColor(getResources().getColor(R.color.colorDefault));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.colorDefault));

                    url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/GoodOrBadUpdateServlet?category=post&c_id="+id+"&u_id="+loginCheck.getID()+"&option=delete";
                    restAPI.execute(url);
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
                JSONObject jres = result.getJSONObject("opinion");
                String code = jres.getString("code");
                if(code.compareToIgnoreCase("success")==0){
                    need_checknum=1;
                    need_imgGood.setImageResource(R.drawable.ag_pressed);
                    need_tvGood.setTextColor(getResources().getColor(R.color.agreement));
                    need_tvGoodCount.setTextColor(getResources().getColor(R.color.agreement));
                }
                JSONArray jsonArray = result.getJSONArray("list");
                int len = jsonArray.length();

                for(int i =0; i<len; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    adapter.addItem(jsonObject.getString("u_id"),"","", jsonObject.getString("create_date"),jsonObject.getString("comments"));
                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Log.i("result","fail rest",e);
            }
        }
    }

    private class NeedImageRestAPI extends RestAPIImage{
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = (ImageView) findViewById(R.id.need_context_img);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(result);
        }
    }
    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}

