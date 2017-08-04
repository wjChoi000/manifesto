package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoulapp.manifesto.model.KnowContent;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_knowledge, container, false);
        context = rootView.getContext();
        //temt
        KnowContent t1 = new KnowContent("매니패스토",getString(R.string.k_temp),150,300);

        //click listener
        LinearLayout L1 = (LinearLayout) rootView.findViewById(R.id.k_main);

        first=0;
        String temp = "매니페스토(Manifesto)는 개인이나 단체가 대중에 대하여 확고한 정치적 의도와 견해를 밝히는 것으로 연설이나 문서의 형태이다. 종종 비정치적인 분야에서도 자신의 주장과 견해를 분명히 밝히는 때에도 사용된다. 한국에서는 예산확보, 구체적 실행계획 등이 있어 이행이 가능한 선거 공약의 의미로 주로 쓰인다.";
        addNewLayout(L1, t1);
//        addNewLayout(L1, t1);
//        addNewLayout(L1, t1);
//        addNewLayout(L1, t1);

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

        LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,0.0F);


        ImageView goodI = new ImageView(context);
        goodI.setLayoutParams(paramsWrap);
        goodI.setImageResource(R.drawable.agreement_black);
        goodI.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodI);

        TextView goodTV = new TextView(new ContextThemeWrapper(context,R.style.bottomButton));
        goodTV.setText(t1.getGoodSum()+"");
        goodTV.setLayoutParams(paramsWrap);
        goodTV.setPadding(tenDp,0,0,0);
        buttonLayout.addView(goodTV);

        ImageView commentI = new ImageView(context);
        commentI.setLayoutParams(paramsWrap);
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
