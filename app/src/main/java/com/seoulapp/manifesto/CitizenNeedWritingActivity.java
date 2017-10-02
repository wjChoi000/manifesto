package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class CitizenNeedWritingActivity extends AppCompatActivity {
    private String gu="";
    private String category="";
    private String priture="";
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
                    String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/CitizenPosterInsertServlet?u_id=" + loginCheck.getID() + "&title=" + title + "&category=" + category + "&comments=" + comment + "&gu=" + gu+"&priture="+priture;
                    RestAPI restAPI = new RestAPI();
                    Citizen citizen =null;
                    try {
                        JSONObject jres = restAPI.execute(url).get();

                        citizen = new Citizen(jres.getInt("id"),loginCheck.getID(),title,category,comment,0,0,"방금",0," ");
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

    public void onClick(View target){
        Toast.makeText(getApplicationContext(), "사진을 가져옵니다.", Toast.LENGTH_SHORT).show();
    }

    //fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
