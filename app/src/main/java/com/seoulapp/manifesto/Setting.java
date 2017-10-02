package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.seoulapp.manifesto.util.LoginCheck;
import com.seoulapp.manifesto.util.LoginCheckDialog;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.w3c.dom.Text;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("설정");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        final LoginCheck loginCheck = new LoginCheck(this);
        if(loginCheck.isItLogin()){
            LinearLayout setting_logout = (LinearLayout) findViewById(R.id.setting_logout);
            setting_logout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    LoginCheckDialog loginCheckDialog = new LoginCheckDialog(Setting.this,true);
                    loginCheckDialog.show();
                }
            });
            ((LinearLayout) findViewById(R.id.setting_login)).setVisibility(View.GONE);
        }else{
            LinearLayout setting_login = (LinearLayout) findViewById(R.id.setting_login);
            setting_login.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Setting.this, LoginsActivity.class);
                    startActivity(intent);
                }
            });
            ((LinearLayout) findViewById(R.id.setting_logout)).setVisibility(View.GONE);
        }
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
