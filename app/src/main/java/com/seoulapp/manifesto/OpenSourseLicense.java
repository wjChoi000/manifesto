package com.seoulapp.manifesto;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class OpenSourseLicense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_sourse_license);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("오픈소스 라이센스");

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
        }
        return super.onOptionsItemSelected(item);
    }

}
