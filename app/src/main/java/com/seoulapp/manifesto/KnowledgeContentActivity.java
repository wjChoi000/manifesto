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
import android.media.Image;
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

import com.seoulapp.manifesto.model.KnowContent;
import com.seoulapp.manifesto.restful.RestAPI;
import com.seoulapp.manifesto.util.LoginCheck;
import com.seoulapp.manifesto.util.LoginCheckDialog;
import com.seoulapp.manifesto.util.ResizeBitmap;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import info.hoang8f.widget.FButton;

public class KnowledgeContentActivity extends AppCompatActivity {

    private KnowContent content;
    private TextView goodText;
    private TextView goodTitleText;
    private TextView hitText;
    private ImageView goodImage;
    private TextView commentNumText;
    private TextView createText;
    private int checknum = 0;
    private int goodCount;
    private int id;
    private LoginCheck loginCheck;
    private LinearLayout k_goobtn;

    private EditText know_editText;
    private TextView know_fake;
    private ListViewAdapter_comment adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_content);
        //add text
        Intent intent = getIntent();
        content = (KnowContent)intent.getSerializableExtra("content");
        id =content.getId();
        loginCheck = new LoginCheck(this);

        if(content.getPriture().length()>0){
            KnowRestAPIImage helpRestAPIImage = new KnowRestAPIImage();
            helpRestAPIImage.execute("http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/knowledge/"+content.getPriture());
        }
        //리스트뷰
        ListView listview ;
        // Adapter 생성
        adapter = new ListViewAdapter_comment() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_know_content_comment);
        listview.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header_content, listview, false);
        LinearLayout listen = (LinearLayout)header.findViewById(R.id.listheader_listen_content);
        LinearLayout help = (LinearLayout)header.findViewById(R.id.listheader_help_content);
        LinearLayout need = (LinearLayout)header.findViewById(R.id.listheader_need_content);
        LinearLayout know = (LinearLayout)header.findViewById(R.id.listheader_know_content);
        listen.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        need.setVisibility(View.GONE);
        know.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        listview.addHeaderView(header, null, false);


        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("지식플러스");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        k_goobtn = (LinearLayout) findViewById(R.id.k_goobtn);
        know_editText = (EditText) findViewById(R.id.know_editText);
        know_fake = (TextView) findViewById(R.id.know_fake);

        TextView title = (TextView) findViewById(R.id.k_content_title);
        TextView contents = (TextView) findViewById(R.id.k_content_contents);
        goodImage = (ImageView) findViewById(R.id.k_imgGood);
        goodText = (TextView) findViewById(R.id.know_good);
        hitText = (TextView) findViewById(R.id.know_hit);
        commentNumText = (TextView) findViewById(R.id.know_commentNum);
        createText = (TextView) findViewById(R.id.k_content_date);
        goodTitleText = (TextView) findViewById(R.id.k_goodTvBtn);


        CommentRestAPI commentRestAPI = new CommentRestAPI();
        String url="http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/KnowledgeCommentServlet?k_id="+content.getId()+"&u_id="+loginCheck.getID();
        commentRestAPI.execute(url);
        title.setText(content.getTitle());
        contents.setText(content.getContents());
        goodText.setText(content.getGoodSum()+"");
        commentNumText.setText(content.getCommentSum()+"");
        hitText.setText(content.getHits()+"");
        createText.setText(content.getCreate_date());
        goodCount = content.getGoodSum();

    }

    @Override
    public void onResume(){
        super.onResume();
        if(loginCheck.isItLogin()){
            know_editText.setVisibility(View.VISIBLE);
            know_fake.setVisibility(View.GONE);

            FButton listen_transmit = (FButton) findViewById(R.id.know_transmit);
            listen_transmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    String comment =know_editText.getText().toString();
                    if ( comment.length() > 0 ) {
                        String comment2= null;
                        try {
                            comment2 = URLEncoder.encode(comment, "UTF-8");
                        }catch (Exception e){
                            Log.d("test","comment",e);
                        }
                        RestAPI restAPI = new RestAPI();
                        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenCommentInsertServlet?category=know&u_id="+loginCheck.getID()+"&h_id="+id+"&comment="+comment2;
                        know_editText.setText("");
                        restAPI.execute(url);

                        adapter.addItem(loginCheck.getNickname()+"","","", "방금",comment);
                        content.setCommentSum(content.getCommentSum()+1);
                        ((TextView)findViewById(R.id.help_commentNum)).setText(content.getCommentSum()+"");
                        adapter.notifyDataSetChanged();
                        Toast.makeText(KnowledgeContentActivity.this,"댓글 작성 완료",Toast.LENGTH_SHORT).show();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(KnowledgeContentActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(know_editText.getWindowToken(), 0);


                }
            });
           k_goobtn.setOnClickListener(clickListener);

        }
        else{
            know_editText.setVisibility(View.GONE);
            know_fake.setVisibility(View.VISIBLE);

            LinearLayout listen_comment = (LinearLayout) findViewById(R.id.know_comment);
            listen_comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(KnowledgeContentActivity.this,false);
                    loginCheckDialog.show();
                }
            });
            findViewById(R.id.help_good_btn).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(KnowledgeContentActivity.this,false);
                    loginCheckDialog.show();
                }
            });
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String url = null;
            RestAPI restAPI = new RestAPI();
            switch (checknum){
                case 0:
                    checknum++;
                    goodCount++;
                    goodImage.setImageResource(R.drawable.ag_pressed);
                    goodTitleText.setTextColor(getResources().getColor(R.color.agreement));
                    goodText.setTextColor(getResources().getColor(R.color.agreement));
                    goodText.setText(""+goodCount);

                    url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/GoodOrBadUpdateServlet?category=know&c_id="+id+"&u_id="+loginCheck.getID()+"&option=update";
                    restAPI.execute(url);
                    break;
                case 1:
                    checknum--;
                    goodCount--;
                    goodImage.setImageResource(R.drawable.agreement_normal);

                    goodText.setText(""+goodCount);
                    goodTitleText.setTextColor(getResources().getColor(R.color.colorDefault));
                    goodText.setTextColor(getResources().getColor(R.color.colorDefault));
                    url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/GoodOrBadUpdateServlet?category=know&c_id="+id+"&u_id="+loginCheck.getID()+"&option=delete";
                    restAPI.execute(url);
                default:
                    break;
            }
        }
    };



    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    private class CommentRestAPI extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            JSONObject data = null;
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
                    checknum=1;
                    goodImage.setImageResource(R.drawable.ag_pressed);
                    goodText.setTextColor(getResources().getColor(R.color.agreement));
                    goodTitleText.setTextColor(getResources().getColor(R.color.agreement));
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

    private ResizeBitmap resizeBitmap = new ResizeBitmap();
    private class KnowRestAPIImage  extends AsyncTask<String, Void, Bitmap> {
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
                bitmap = resizeBitmap.resizeBitmapImage(BitmapFactory.decodeStream(is),300);
//                bitmap =BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.i("result", urls[0], e);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            ImageView iv = (ImageView)findViewById(R.id.k_content_image);
            iv.setImageBitmap(bitmap);
        }
    }
}
