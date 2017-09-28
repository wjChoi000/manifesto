package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoulapp.manifesto.model.KnowContent;
import com.seoulapp.manifesto.restful.RestAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabKnowledge extends Fragment {

    ArrayList<KnowContent> contestsList = new ArrayList<KnowContent>();
    private View rootView;
    private Context context;
    private Intent intent;
    private int first;
    private KnowContent content;
    private RestAPI restAPI;
    private int offset = 0;
    private int limit = 10;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_knowledge, container, false);
        context = rootView.getContext();
        //click listener
        LinearLayout L1 = (LinearLayout) rootView.findViewById(R.id.k_main);
        first=0;
        offset = 0;
        restAPI = new RestAPI();
        String url = "http://manifesto2017-env.fxmd3pye65.ap-northeast-2.elasticbeanstalk.com/KnowledgeServlet?offset="+offset+"&limit="+limit;
        JSONObject result = null;
        JSONArray jsonArray=null;
        try {
            result = restAPI.execute(url).get();
            jsonArray = result.getJSONArray("list");
            offset += limit;
        }catch (Exception e){
            Log.i("rest","rest api error",e);
        }

        int len =  jsonArray.length();
        try {
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                KnowContent t1 = new KnowContent(jsonObject.getString("title"), jsonObject.getString("contents"), jsonObject.getInt("good"),jsonObject.getInt("comment_num"));
                addNewLayout(L1,t1);
            }
        }catch (Exception e){
            Log.i("list","list json error",e);
        }

        return rootView;
    }

    private void addNewLayout(LinearLayout parent,KnowContent t1){
        if(first != 0) {
            int fiveDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            LinearLayout fiveGray = new LinearLayout(context);
            fiveGray.setBackgroundResource(R.color.colorBackgroundGray);
            fiveGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, fiveDp));
            parent.addView(fiveGray);

        }else {
            first = 1;
        }
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);

        TextView titleTV = new TextView(new ContextThemeWrapper(context,R.style.knowledgeTitle));
        titleTV.setText(t1.getTitle());
        titleTV.setLayoutParams(params);
        row.addView(titleTV);

        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(params);
        //수정
        img.setImageResource(R.drawable.knowledge_manifesto);
        row.addView(img);

        TextView commentTV = new TextView(new ContextThemeWrapper(context,R.style.knowledgeContents));
        commentTV.setText(t1.getContents());
        row.addView(commentTV);

        int oneDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        LinearLayout emptyGray = new LinearLayout(context);
        emptyGray.setBackgroundResource(R.color.colorBackgroundGray);
        emptyGray.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, oneDp));
        row.addView(emptyGray);

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setLayoutParams(params);
        int tenDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        buttonLayout.setPadding(tenDp,tenDp,tenDp,tenDp);

        int imageDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(imageDp,
                imageDp,0.0F);

        LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);


        ImageView goodI = new ImageView(context);
        goodI.setLayoutParams(paramsImage);
        goodI.setImageResource(R.drawable.agreement_black);
        goodI.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodI);

        TextView goodTV = new TextView(new ContextThemeWrapper(context,R.style.bottomButton));
        goodTV.setText(t1.getGoodSum()+"");
        goodTV.setLayoutParams(paramsWrap);
        goodTV.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodTV);

        ImageView commentI = new ImageView(context);
        commentI.setLayoutParams(paramsImage);
        commentI.setImageResource(R.drawable.comment_black);
        commentI.setPadding(tenDp,0,0,0);
        buttonLayout.addView(commentI);

        TextView commentTV2 = new TextView(new ContextThemeWrapper(context,R.style.bottomButton));
        commentTV2.setText(t1.getCommentSum()+"");
        commentTV2.setLayoutParams(paramsWrap);
        commentTV2.setPadding(tenDp,0,0,0);
        buttonLayout.addView(commentTV2);


        row.addView(buttonLayout);
        intent = new Intent(getActivity(),KnowledgeContentActivity.class);
        content = t1;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
        parent.addView(row);
    }
}
