package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.seoulapp.manifesto.model.KnowContent;

public class KnowledgeContentActivity extends AppCompatActivity {

    private KnowContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_content);

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

}
