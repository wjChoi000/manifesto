package com.seoulapp.manifesto.restful;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.seoulapp.manifesto.util.ResizeBitmap;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by swelo on 2017-09-28.
 */

public class RestAPIImage  extends AsyncTask<String, Void, Bitmap> {
    private ResizeBitmap resizeBitmap = new ResizeBitmap();

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

        } catch (Exception e) {
            Log.i("result", urls[0], e);
        }
        return bitmap;
    }
}
