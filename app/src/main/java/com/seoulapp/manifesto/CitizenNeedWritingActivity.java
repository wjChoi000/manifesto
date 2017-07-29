package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CitizenNeedWritingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need_writing);
        
        
        //Writing_spinner_category
        Spinner WcateSpinner = (Spinner)findViewById(R.id.writing_category_spinner);
        ArrayAdapter<CharSequence> WcateAdapter = ArrayAdapter.createFromResource(
                this,R.array.writing_category_array, android.R.layout.simple_spinner_item);
        WcateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WcateSpinner.setAdapter(WcateAdapter);
        WcateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //Writing_spinner_Gu
        Spinner WGuSpinner = (Spinner)findViewById(R.id.writing_Gu_spinner);
        ArrayAdapter<CharSequence> WGuAdapter = ArrayAdapter.createFromResource(
                this,R.array.writing_Gu_array, android.R.layout.simple_spinner_item);
        WGuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WGuSpinner.setAdapter(WGuAdapter);
        WGuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

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
    }
    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_complete:
                Toast.makeText(this, "새 글 등록 완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,CitizenNeedActivity.class);
                startActivity(intent);
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

}
