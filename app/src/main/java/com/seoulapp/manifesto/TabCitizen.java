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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;
import com.seoulapp.manifesto.util.ResizeBitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    private ViewPager mViewPager = null;
    private RollingAdapter mAdapter = null;
    private IndicatorView mIndicatorView = null;
    private AutoRollingManager mAutoRollingManager = null;
    private ListView L_listview1, L_listview2, L_listview3, H_listview1, H_listview2, H_listview3, N_listview1, N_listview2, N_listview3;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        //들려줘요 더보기 레이아웃
        ViewGroup ListenAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_listenAdd);
        ListenAddlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListenAddlayout(view);
            }
        });

        //도와줘요 더보기 레이아웃
        ViewGroup HelpAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_helpAdd);
        HelpAddlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelpAddlayout(view);
            }
        });

        //필요해요 더보기 레이아웃
        ViewGroup NeedAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_needAdd);
        NeedAddlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNeedAddlayout(view);
            }
        });



        mIndicatorView = (IndicatorView) rootView.findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) rootView.findViewById(R.id.view_pager);



        return rootView;
    }


    public void onClickListenAddlayout(View view) {
        Intent intent = new Intent(getActivity(), CitizenListenActivity.class);
        startActivity(intent);
    }

    public void onClickHelpAddlayout(View view) {
        Intent intent = new Intent(getActivity(), CitizenHelpActivity.class);
        startActivity(intent);
    }

    public void onClickNeedAddlayout(View view) {
        Intent intent = new Intent(getActivity(), CitizenNeedActivity.class);
        startActivity(intent);
    }

    private List<RollingModel> getData() {
        List<RollingModel> list = new ArrayList<>();

        list.add(new RollingModel("1", R.drawable.listen_smoking_edit3));
        list.add(new RollingModel("2", R.drawable.listen_youth_edit3));
        list.add(new RollingModel("3", R.drawable.help_children_edit3));
        list.add(new RollingModel("4", R.drawable.help_development_edit3));

        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mAutoRollingManager.onRollingStop();
    }

    @Override
    public void onResume() {
        super.onResume();


        String url1 = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenMainGetListServlet";
        CitizenRestAPI restAPI = new CitizenRestAPI();
        restAPI.execute(url1);

        mAdapter = new RollingAdapter(rootView.getContext(), getData(), new RollingAdapter.OnAdapterItemClickListener() {
            @Override
            public void onItemClick(RollingModel object, int position) {
                if(position ==0){
                    L_listview1.performItemClick(L_listview1,0,0);
                    Log.d("citizen","click1");
                }else if(position ==1){
                    L_listview2.performItemClick(L_listview2,0,0);
                    Log.d("citizen","click2");
                }else if(position ==2){
                    H_listview1.performItemClick(H_listview1,0,0);;
                    Log.d("citizen","click3");
                }else if(position ==3){
                    H_listview2.performItemClick(H_listview2,0,0);
                    Log.d("citizen","click4");
                }

            }
        });
        mViewPager.setAdapter(mAdapter);
        mIndicatorView.setViewPager(mViewPager);
        mAutoRollingManager = new AutoRollingManager(mViewPager, mAdapter, mIndicatorView);
        mAutoRollingManager.setRollingTime(5500);
        mAutoRollingManager.onRollingStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAutoRollingManager.onRollingDestroy();
    }

    static Citizen[] list_say = null;
    static Citizen[] list_help =null;

    private class CitizenRestAPI extends AsyncTask<String, Void, JSONObject> {

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

        protected void onPostExecute(JSONObject jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("say");
                int len = jsonArray.length();
                list_say = new Citizen[len];
                String[] urls = new String[6];
                for (int i = 0; i < len; i++) {
                    list_say[i] = Citizen.convertJsonToListen(jsonArray.getJSONObject(i));
                    urls[i] = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/listen/"+list_say[i].getPriture();
                    list_say[i].setPriture(urls[i]);
                }

                JSONArray jsonArray2 = jsonObject.getJSONArray("help");
                int len2 = jsonArray2.length();
                list_help = new Citizen[len2];
                for (int i = 0; i < len2; i++) {
                    list_help[i] = Citizen.convertJsonToHelp(jsonArray2.getJSONObject(i));
                    urls[i+3] = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/help/"+list_help[i].getPriture();
                    list_help[i].setPriture(urls[i+3]);
                }
                CitizenRestAPIImage citizenRestAPIImage = new CitizenRestAPIImage();
                citizenRestAPIImage.execute(urls);
            } catch (Exception e) {
                Log.i("Help", "help error", e);
            }
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("post");
                    int len = jsonArray.length();
                    final Citizen[] list_need = new Citizen[len];
                    for (int i = 0; i < len; i++) {
                        list_need[i] = Citizen.convertJsonToNeed(jsonArray.getJSONObject(i));
                    }

                    int j = 0;
                    //리스트뷰1
                    N_listview1 = (ListView) rootView.findViewById(R.id.listview_need_tab1);
                    ListViewAdapter_need N_adapter1;
                    N_adapter1 = new ListViewAdapter_need();
                    N_adapter1.addItem(list_need[j].getCategory(), list_need[j].getTitle(), list_need[j].getGu(), list_need[j].getU_id() + "",
                            list_need[j].getComment(), list_need[j].getCreate_date(), list_need[j].getGood() + "", "0", list_need[j].getCount() + "");
                    N_listview1.setAdapter(N_adapter1);
                    // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
                    N_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            Intent intent = new Intent(getActivity(), CitizenNeedContentActivity.class);
                            intent.putExtra("need", list_need[0]);
                            startActivity(intent);
                        }
                    });

                    //리스트뷰2
                    j=1;
                    N_listview2 = (ListView) rootView.findViewById(R.id.listview_need_tab2);
                    ListViewAdapter_need N_adapter2;
                    N_adapter2 = new ListViewAdapter_need();
                    N_adapter2.addItem(list_need[j].getCategory(), list_need[j].getTitle(), list_need[j].getGu(), list_need[j].getU_id() + "",
                            list_need[j].getComment(), list_need[j].getCreate_date(), list_need[j].getGood() + "", "0", list_need[j].getCount() + "");
                    N_listview2.setAdapter(N_adapter2);
                    // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
                    N_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            Intent intent = new Intent(getActivity(), CitizenNeedContentActivity.class);
                            intent.putExtra("need", list_need[1]);
                            startActivity(intent);
                        }
                    });

                    //리스트뷰3
                    j=2;
                    N_listview3 = (ListView) rootView.findViewById(R.id.listview_need_tab3);
                    ListViewAdapter_need N_adapter3;
                    N_adapter3 = new ListViewAdapter_need();
                    N_adapter3.addItem(list_need[j].getCategory(), list_need[j].getTitle(), list_need[j].getGu(), list_need[j].getU_id() + "",
                            list_need[j].getComment(), list_need[j].getCreate_date(), list_need[j].getGood() + "", "0", list_need[j].getCount() + "");
                    N_listview3.setAdapter(N_adapter3);
                    // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
                    N_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            Intent intent = new Intent(getActivity(), CitizenNeedContentActivity.class);
                            intent.putExtra("need", list_need[2]);
                            startActivity(intent);


                        }
                    });
                    N_adapter1.notifyDataSetChanged();
                    N_adapter2.notifyDataSetChanged();
                    N_adapter3.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.i("need", "need list error", e);
                }

        }
    }

    private ResizeBitmap resizeBitmap = new ResizeBitmap();

    private class CitizenRestAPIImage  extends AsyncTask<String, Void, Bitmap[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Bitmap[] doInBackground(String... urls) {
            int len = urls.length;
            Bitmap[] bitmap = new Bitmap[len];
            try {
                for(int i =0 ; i<len;i++) {
                    URL url = new URL(urls[i]);
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    bitmap[i] = resizeBitmap.resizeBitmapImage( BitmapFactory.decodeStream(is),100);
                }
            } catch (Exception e) {
                Log.i("result", urls[0], e);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap[] bitmap){

            int j = 0;
            L_listview1 = (ListView) rootView.findViewById(R.id.listview_listen_tab1);
            ListViewAdapter L_adapter1;
            L_adapter1 = new ListViewAdapter();
            L_adapter1.addItem(bitmap[0], list_say[j].getTitle(), list_say[j].getComment(),
                    list_say[j].getAgree(), list_say[j].getOpposite(), list_say[j].getCreate_date(),
                    list_say[j].getGood() + "", list_say[j].getBad() + "", list_say[j].getCount() + "");
            L_listview1.setAdapter(L_adapter1);

            //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
            L_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenListenContentActivity.class);
                    intent.putExtra("say", list_say[0]);
                    startActivity(intent);
                }
            });

            j = 1;
            L_listview2 = (ListView) rootView.findViewById(R.id.listview_listen_tab2);
            ListViewAdapter L_adapter2;
            L_adapter2 = new ListViewAdapter();
            L_adapter2.addItem(bitmap[1], list_say[j].getTitle(), list_say[j].getComment(),
                    list_say[j].getAgree(), list_say[j].getOpposite(), list_say[j].getCreate_date(),
                    list_say[j].getGood() + "", list_say[j].getBad() + "", list_say[j].getCount() + "");
            L_listview2.setAdapter(L_adapter2);
            //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
            L_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenListenContentActivity.class);
                    intent.putExtra("say", list_say[1]);
                    startActivity(intent);
                }
            });

            j = 2;
            L_listview3 = (ListView) rootView.findViewById(R.id.listview_listen_tab3);
            ListViewAdapter L_adapter3;
            L_adapter3 = new ListViewAdapter();
            L_adapter3.addItem(bitmap[2], list_say[j].getTitle(), list_say[j].getComment(),
                    list_say[j].getAgree(), list_say[j].getOpposite(), list_say[j].getCreate_date(),
                    list_say[j].getGood() + "", list_say[j].getBad() + "", list_say[j].getCount() + "");
            L_listview3.setAdapter(L_adapter3);
            L_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenListenContentActivity.class);
                    intent.putExtra("say", list_say[2]);
                    startActivity(intent);
                }
            });

            j = 0;
            //리스트뷰1
            H_listview1 = (ListView) rootView.findViewById(R.id.listview_help_tab1);
            ListViewAdapter_help H_adapter1;
            H_adapter1 = new ListViewAdapter_help();
            H_adapter1.addItem(bitmap[3], list_help[j].getTitle(), list_help[j].getComment(),
                    list_help[j].getCreate_date(), list_help[j].getGood() + "", "0", list_help[j].getCount() + "");

            H_listview1.setAdapter(H_adapter1);
            // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
            H_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenHelpContentActivity.class);
                    intent.putExtra("help", list_help[0]);
                    startActivity(intent);
                }
            });

            //리스트뷰2
            j = 1;
            H_listview2 = (ListView) rootView.findViewById(R.id.listview_help_tab2);
            ListViewAdapter_help H_adapter2;
            H_adapter2 = new ListViewAdapter_help();
            H_adapter2.addItem(bitmap[4], list_help[j].getTitle(), list_help[j].getComment(),
                    list_help[j].getCreate_date(), list_help[j].getGood() + "", "0", list_help[j].getCount() + "");
            H_listview2.setAdapter(H_adapter2);
            // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
            H_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenHelpContentActivity.class);
                    intent.putExtra("help", list_help[1]);
                    startActivity(intent);
                }
            });

            //리스트뷰3
            j = 2;
            H_listview3 = (ListView) rootView.findViewById(R.id.listview_help_tab3);
            ListViewAdapter_help H_adapter3;
            H_adapter3 = new ListViewAdapter_help();
            H_adapter3.addItem(bitmap[5], list_help[j].getTitle(), list_help[j].getComment(),
                    list_help[j].getCreate_date(), list_help[j].getGood() + "", "0", list_help[j].getCount() + "");
            H_listview3.setAdapter(H_adapter3);
            // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
            H_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), CitizenHelpContentActivity.class);
                    intent.putExtra("help", list_help[2]);
                    startActivity(intent);
                }
            });
        }
    }

}