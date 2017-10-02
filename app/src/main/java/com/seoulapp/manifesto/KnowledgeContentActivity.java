package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.seoulapp.manifesto.model.KnowContent;
import com.tsengvn.typekit.TypekitContextWrapper;

public class KnowledgeContentActivity extends AppCompatActivity {

    private KnowContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_content);

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

        //add text
        Intent intent = getIntent();
        content = (KnowContent)intent.getSerializableExtra("content");

        TextView title = (TextView) findViewById(R.id.k_content_title);
        title.setText(content.getTitle());

        TextView contents = (TextView) findViewById(R.id.k_content_contents);
        contents.setText(content.getContents());

        TextView goodNum = (TextView) findViewById(R.id.k_content_goodNum);
        goodNum.setText(content.getGoodSum()+"");

        TextView commentNum = (TextView) findViewById(R.id.k_content_commentNum);
        commentNum.setText(content.getCommentSum()+"");


    }

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

}
