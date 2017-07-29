package com.seoulapp.manifesto;

import android.content.Intent;
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

public class Rate_detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_detail_);

        //cardviewFunc();

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
        int threedp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        promiseBtn.setPadding(threedp,threedp,threedp,threedp);
        CardView.LayoutParams paramsBtn = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        //paramsBtn.setMargins(10, 10, 50, 10);
        promiseBtn.setLayoutParams(paramsBtn);
        promiseBtn.setRadius(threedp);
        promiseBtn.setCardElevation(fiveDp);
        TextView txt = new TextView(this);
        txt.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT));
        switch (i) {
            //사업완료
            case 1:
                promiseBtn.setBackgroundResource(R.color.rate_title_one);
                txt.setText(R.string.rate_table_title_one);
                break;
            //정상추진
            case 2:
                promiseBtn.setBackgroundResource(R.color.rate_title_two);
                txt.setText(R.string.rate_table_title_two);
                break;

            //일부추진
            case 3:
                promiseBtn.setBackgroundResource(R.color.rate_title_three);
                txt.setText(R.string.rate_table_title_three);
                break;
            //계속추진
            case 4:
                promiseBtn.setBackgroundResource(R.color.rate_title_four);
                txt.setText(R.string.rate_table_title_four);
                break;
            //검토중
            case 5:
                promiseBtn.setBackgroundResource(R.color.rate_title_five);
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
}

