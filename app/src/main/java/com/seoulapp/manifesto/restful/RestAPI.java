package com.seoulapp.manifesto.restful;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by swelo on 2017-08-15.
 */

public class RestAPI extends AsyncTask<String, Void, JSONObject>{

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
            //connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            data = new JSONObject(json.toString());
            //image
            if(urls[1] !=null){
                url = new URL(urls[1]);
                connection =(HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream is  = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }

        } catch (Exception e) {
            Log.i("result", urls[0], e);
        }
        return data;
    }
}
