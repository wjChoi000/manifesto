package com.seoulapp.manifesto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.manifesto.model.Citizen;
import com.seoulapp.manifesto.restful.RestAPI;
import com.seoulapp.manifesto.util.LoginCheck;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CitizenNeedWritingActivity extends AppCompatActivity {
    final int REQ_CODE_SELECT_IMAGE=100;
    private String gu="";
    private String category="";

    private String absolutePath =null;
    private String fileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need_writing);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("글 쓰기");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        Spinner cateSpinner = (Spinner)findViewById(R.id.writing_category);
        ArrayAdapter<CharSequence> cateAdapter = ArrayAdapter.createFromResource(
                this,R.array.category_array, android.R.layout.simple_spinner_item);
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(pos ==0){
                    category = "";
                }else{
                    category = parent.getItemAtPosition(pos).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //spinner_sort
        Spinner spinner_sort = (Spinner)findViewById(R.id.writing_gu);
        ArrayAdapter<CharSequence> adapter_sort = ArrayAdapter.createFromResource(
                this,R.array.writing_Gu_array, android.R.layout.simple_spinner_item);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter_sort);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(pos ==0){
                    gu = "";
                }else{
                    gu = parent.getItemAtPosition(pos).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.need_writing_btnFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                            Log.d("Test","마시멜로 이상");
                                                            int permissionResult = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                                                            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                                                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
                                                            }
                                                            /* READ_EXTERNAL_STORAGE의 권한이 있을 때 */
                                                            else {
                                                                Intent intent = new Intent(Intent.ACTION_PICK);
                                                                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                                                                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
                                                            }

                                                        }
                                                        /* 사용자의 OS 버전이 마시멜로우 이하일 떄 */
                                                        else {
                                                            Log.d("Test","마시멜로 이하");
                                                            Intent intent = new Intent(Intent.ACTION_PICK);
                                                            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                                                            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
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
            case R.id.action_complete:
                EditText writing_title = (EditText) findViewById(R.id.writing_title);
                String title= writing_title.getText().toString();

                EditText writing_comments = (EditText) findViewById(R.id.writing_comments);
                String comment= writing_comments.getText().toString();

                String error=null;

                if(title.length()==0 )
                    error ="제목을 입력해주세요.";
                else if(gu.length() ==0)
                    error = "구를 선택해주세요.";
                else if(category.length()==0)
                    error = "분류를 선택해주세요";
                else if(comment.length() ==0)
                    error = "내용을 입력해주세요";
                else {
                    category = "["+category+"]";
                    LoginCheck loginCheck = new LoginCheck(CitizenNeedWritingActivity.this);
                    if(absolutePath != null) {
                        WritingRestAPI writingRestAPI = new WritingRestAPI();
                        try {
                            JSONObject jres = (JSONObject) writingRestAPI.execute().get();
                            Log.d("Test",jres.toString());
                        }catch (Exception e){
                            Log.d("Test","writing rest api error");
                        }
                        Log.i("writing","update img");
                    }
                    String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenPosterInsertServlet?u_id=" + loginCheck.getID() + "&title=" + title + "&category=" + category + "&comments=" + comment + "&gu=" + gu+"&priture="+fileName;
                    RestAPI restAPI = new RestAPI();
                    Citizen citizen =null;
                    try {
                        JSONObject jres = restAPI.execute(url).get();

                        citizen = new Citizen(jres.getInt("id"),loginCheck.getID(),title,category,comment,0,0,"방금",0,fileName);
                    }catch (Exception e){

                    }
                    Intent intent = new Intent(this,CitizenNeedActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.putExtra("post",citizen);
                    startActivity(intent);

                    finish();
                    error = "새 글 등록 완료";
                }
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //메뉴 리소스를 팽창한다. 액션바에 항목들을 추가한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_complete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    fileName = getImageNameToUri(data.getData());

                    Cursor c = getContentResolver().query(Uri.parse(data.getData().toString()), null,null,null,null);
                    c.moveToNext();
                    absolutePath = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
                    Toast.makeText(getBaseContext(), "업로드 성공"+fileName, Toast.LENGTH_SHORT).show();
                } catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "업로드 실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
  }

    public String getImageNameToUri(Uri data){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }


    FileInputStream mFileInputStream;
    URL connectUrl;
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    private class WritingRestAPI extends AsyncTask<String, Object, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            try {
                File file = new File(absolutePath);
                mFileInputStream = new FileInputStream(file);
                connectUrl = new URL("http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/NeedImageUploadServlet");
                Log.d("Test", "mFileInputStream  is " + mFileInputStream);

                // open connection
                HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                // write data
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);

                int bytesAvailable = mFileInputStream.available();
                int maxBufferSize = 1024;
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);

                byte[] buffer = new byte[bufferSize];
                int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

                Log.d("Test", "image byte is " + bytesRead);

                // read image
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = mFileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // close streams
                Log.e("Test", "File is written");
                mFileInputStream.close();
                dos.flush();
                dos.close();
                //////////
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp = "";
                while ((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();

                return new JSONObject(json.toString());
            } catch (Exception e) {
                Log.d("Test", "exception " + e.getMessage(),e);
            }
            return null;
        }
    }




}
