package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Rate_detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_detail_);
    //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String city = intent.getStringExtra("city");

        TextView guTxt = (TextView)findViewById(R.id.rate_detail_gu);
        guTxt.setText(city);
        TextView categoryTxt = (TextView) findViewById(R.id.detail_category);
        categoryTxt.setText(name);
        CardView categoryCard = (CardView) findViewById(R.id.detail_category_card);
        if(name.compareTo("복지")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.welfare));
        else if(name.compareTo("문화")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.culture));
        else if(name.compareTo("경제")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.economy));
        else if(name.compareTo("환경")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.environment));
        else if(name.compareTo("행정")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.administation));
        else if(name.compareTo("도시·안전")==0)
            categoryCard.setCardBackgroundColor(getResources().getColor(R.color.cityAndSafty));
        list();
    }

    private void cardviewFunc() {

    }

    private void list() {
        LinearLayout list_view = (LinearLayout) findViewById(R.id.detail_list);
        addPromise(list_view,"도시 안전 예산 확대 및 통합",1);
        addPromise(list_view,"시작 직속 재난 컨트롤 타워",2);
        addPromise(list_view,"도시 안전 예산 확대 및 통합",3);
        addPromise(list_view,"시작 직속 재난 컨트롤 타워",4);
        addPromise(list_view,"도시 안전 예산 확대 및 통합",5);
        addPromise(list_view,"시작 직속 재난 컨트롤 타워",1);

    }

    private void addPromise(LinearLayout parent, String s1, int i) {
        LinearLayout linear = new LinearLayout(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.0F);
        int fiveDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        params.setMargins(fiveDp,fiveDp*2,fiveDp,fiveDp*2);
        linear.setGravity(Gravity.CENTER_VERTICAL);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setLayoutParams(params);

        TextView promiseText = new TextView(this);
        promiseText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        promiseText.setText(s1);
        promiseText.setTextColor(getResources().getColor(R.color.colorBlack));
        linear.addView(promiseText);


        CardView promiseBtn = new CardView(this);
        promiseBtn.setRadius(5.0f);
        //promiseBtn.setCardElevation(10.0f);


        int threedp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        CardView.LayoutParams paramsBtn = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        promiseBtn.setLayoutParams(paramsBtn);

        TextView txt = new TextView(this);
        CardView.LayoutParams txtParam = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        txtParam.setMargins(fiveDp*2,fiveDp,fiveDp*2,fiveDp);
        txt.setLayoutParams(txtParam);
        txt.setTypeface(null, Typeface.BOLD);
        switch (i) {
            //사업완료
            case 1:
                promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_one));
                txt.setText(R.string.rate_table_title_one);
                break;
            //정상추진
            case 2:
                promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_two));
                txt.setText(R.string.rate_table_title_two);
                break;

            //일부추진
            case 3:
                promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_three));
                txt.setText(R.string.rate_table_title_three);
                break;
            //계속추진
            case 4:
                promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_four));
                txt.setText(R.string.rate_table_title_four);
                break;
            //검토중
            case 5:
                promiseBtn.setCardBackgroundColor(getResources().getColor(R.color.rate_title_five));
                txt.setText(R.string.rate_table_title_five);
                break;
            default:
        }
        txt.setTextColor(getResources().getColor(R.color.colorWhite));
        //promiseBtn.setPadding(3, 3, 3, 3);
        promiseBtn.addView(txt);
        linear.addView(promiseBtn);

        int oneDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        LinearLayout emptyGray = new LinearLayout(this);
        emptyGray.setBackgroundResource(R.color.colorBackgroundGray);
        emptyGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, oneDp));

        parent.addView(linear);
        parent.addView(emptyGray);

    }

    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}

