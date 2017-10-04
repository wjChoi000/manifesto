package com.seoulapp.manifesto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seoulapp.manifesto.util.InterestingAreaDialog;
import com.seoulapp.manifesto.util.LoginCheck;

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

public class TabManifesto extends Fragment {

    private int interestingArea= 1;
    private LoginCheck loginCheck;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_manifesto, container, false);

        loginCheck = new LoginCheck(rootView.getContext());


        button();

        ((ImageView) rootView.findViewById(R.id.manifestoBtn1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(1);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn2)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(2);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn3)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(3);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn4)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(4);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn5)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(5);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn6)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(6);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn7)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(7);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn8)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(8);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn9)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(9);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn10)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(10);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn11)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(11);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn12)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(12);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn13)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(13);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn14)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(14);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn15)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(15);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn16)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(16);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn17)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(17);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn18)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(18);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn19)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(19);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn20)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(20);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn21)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(21);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn22)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(22);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn23)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(23);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn24)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(24);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn25)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(25);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn26)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(26);
            }
        });
        ((ImageView) rootView.findViewById(R.id.manifestoBtn27)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(27);
            }
        });
        ((CardView) rootView.findViewById(R.id.manifesto_change)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InterestingAreaDialog dialog  = new InterestingAreaDialog(rootView.getContext());
                dialog.show();
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        button();
                    }
                });
            }
        });
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        //rest api

    }
    public void button(){
        interestingArea = loginCheck.getInterestingArea();
        RateRestAPI rateRestAPI = new RateRestAPI();
        String url1 = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/ElectedPersonInfoServlet?ep_id="+interestingArea;
        rateRestAPI.execute(url1);
    }
    private void startActivityRate(int i){
        Intent intent = new Intent(getActivity(), ManifestoRateActivity.class);
        intent.putExtra("id",i);
        startActivity(intent);
    }
    JSONObject person;
    JSONArray profile;
    JSONObject promise_num;
    private class RateRestAPI extends AsyncTask<String, Void, JSONObject> {
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

            try{
                person = result.getJSONObject("person");
                if(interestingArea ==1) {
                    ((TextView) rootView.findViewById(R.id.manifesto_governer_title)).setText("시장");
                }else if(interestingArea ==27){
                    ((TextView) rootView.findViewById(R.id.manifesto_governer_title)).setText("구청장");
                }else{

                    ((TextView) rootView.findViewById(R.id.manifesto_governer_title)).setText("교육감");
                }
                ((TextView) rootView.findViewById(R.id.manifesto_governer)).setText(person.getString("person_name"));
                ((TextView) rootView.findViewById(R.id.manifesto_term)).setText(person.getString("term"));
                ((TextView) rootView.findViewById(R.id.manifesto_party)).setText(person.getString("party"));
                ((TextView) rootView.findViewById(R.id.manifesto_interesting)).setText(person.getString("interesting"));
                ((TextView) rootView.findViewById(R.id.manifesto_region)).setText(person.getString("local"));

                int review = person.getInt("review");
                int part = person.getInt("part");
                int complete = person.getInt("complete");
                int normal = person.getInt("normal");
                int continues = person.getInt("continues");
                int sum = review+part+complete+normal+continues;
                float persent = (float)((sum-review-part)*100)/sum;

                ((TextView) rootView.findViewById(R.id.manifesto_rate)).setText(String.format("%.1f",persent)+"%");


//                String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/gorvenor/"+person.getString("priture");
//                Log.i("restful", url);
//                RestAPIImage restAPIImage = new RestAPIImage();
//                restAPIImage.execute(url);

                profile = result.getJSONArray("profile");
                promise_num = result.getJSONObject("promise_num");
                Log.i("restful","Success");
            }catch (Exception e){
                Log.i("result","fail rest",e);
            }
        }
    }

}
