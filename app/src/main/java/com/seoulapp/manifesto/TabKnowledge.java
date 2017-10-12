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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.KnowContent;
import com.seoulapp.manifesto.restful.RestAPIImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabKnowledge extends Fragment {

    ArrayList<KnowContent> contestsList = new ArrayList<KnowContent>();
    private View rootView;
    private Context context;
    private Intent intent;
    private int first;

    private KnowledgeRestAPI restAPI;

    private int offset = 0;
    private int limit = 10;
    private LinearLayout L1 = null;
    private KnowContent content;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_knowledge, container, false);
        context = rootView.getContext();
        //click listener
        L1 = (LinearLayout) rootView.findViewById(R.id.k_main);
        first=0;
        offset = 0;
//        offset += limit;
        return rootView;
    }

    private void addNewLayout(LinearLayout parent,final KnowContent t1){
        if(first != 0) {
            int fiveDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            LinearLayout fiveGray = new LinearLayout(context);
            fiveGray.setBackgroundResource(R.color.colorBackgroundGray);
            fiveGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, fiveDp));
            parent.addView(fiveGray);

        }else {
            first = 1;
        }
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);

        TextView titleTV = new TextView(new ContextThemeWrapper(context,R.style.knowledgeTitle));
        titleTV.setText(t1.getTitle());
        titleTV.setLayoutParams(params);
        row.addView(titleTV);

        String image_addr = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/knowledge/"+t1.getPriture();
        RestAPIImage KnowRestAPIImage = new RestAPIImage();
        Bitmap bitmap =null;
        try{
            bitmap =  (Bitmap) KnowRestAPIImage.execute(image_addr).get();
        }catch (Exception e){

        }
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        int twohandDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                twohandDp,0.0F);
        img.setLayoutParams(paramsImg);
        //수정
        img.setImageBitmap(bitmap);
        row.addView(img);

        TextView commentTV = new TextView(new ContextThemeWrapper(context,R.style.knowledgeContents));
        commentTV.setText(t1.getContents());
        row.addView(commentTV);

        int oneDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        LinearLayout emptyGray = new LinearLayout(context);
        emptyGray.setBackgroundResource(R.color.colorBackgroundGray);
        emptyGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, oneDp));
        row.addView(emptyGray);

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setLayoutParams(params);
        int tenDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        buttonLayout.setPadding(tenDp,tenDp,tenDp,tenDp);

        int imageDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(imageDp,
                imageDp,0.0F);

        LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);


        ImageView goodI = new ImageView(context);
        goodI.setLayoutParams(paramsImage);
        goodI.setImageResource(R.drawable.agreement_black);
        goodI.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodI);

        TextView goodTV = new TextView(new ContextThemeWrapper(context,R.style.bottomButton));
        goodTV.setText(t1.getGoodSum()+"");
        goodTV.setLayoutParams(paramsWrap);
        goodTV.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodTV);

        ImageView commentI = new ImageView(context);
        commentI.setLayoutParams(paramsImage);
        commentI.setImageResource(R.drawable.comment_black);
        commentI.setPadding(tenDp,0,0,0);
        buttonLayout.addView(commentI);

        TextView commentTV2 = new TextView(new ContextThemeWrapper(context,R.style.bottomButton));
        commentTV2.setText(t1.getCommentSum()+"");
        commentTV2.setLayoutParams(paramsWrap);
        commentTV2.setPadding(tenDp,0,0,0);
        buttonLayout.addView(commentTV2);


        row.addView(buttonLayout);
        intent = new Intent(getActivity(),KnowledgeContentActivity.class);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("content", t1);
                startActivity(intent);
            }
        });
        parent.addView(row);
    }
    @Override
    public void onResume(){
        super.onResume();
        L1.removeAllViews();
        restAPI = new KnowledgeRestAPI();
        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/KnowledgeServlet?offset="+offset+"&limit="+limit;
        restAPI.execute(url);
    }
    class KnowledgeRestAPI extends AsyncTask<String, Void, JSONObject> {
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
        @Override
        protected  void onPostExecute(JSONObject result){
            try {
                JSONArray jsonArray = result.getJSONArray("list");
                int len =  jsonArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    KnowContent t1 = KnowContent.convertJsonToKnowledge(jsonObject);
                    addNewLayout(L1,t1);
                }
            }catch (Exception e){
                Log.i("list","list json error",e);
            }
        }
    }


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
                bitmap = BitmapFactory.decodeStream(is);

            } catch (Exception e) {
                Log.i("result", urls[0], e);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){

        }
    }
}
