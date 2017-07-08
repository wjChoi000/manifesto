package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.seoulapp.manifesto.model.KnowContent;

import java.util.ArrayList;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabKnowledge extends Fragment {

    ArrayList<KnowContent> contestsList = new ArrayList<KnowContent>();
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_knowledge, container, false);

        //temt
        KnowContent t1 = new KnowContent("매니패스토",getString(R.string.k_temp),150,300);
        contestsList.add(t1);

        //click listener
        LinearLayout L1 = (LinearLayout) rootView.findViewById(R.id.k_layout_1);
        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLayout(contestsList.get(0));
            }
        });

        return rootView;
    }


    void onClickLayout(KnowContent content){
        Intent intent = new Intent(getActivity() ,KnowledgeContentActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);

    }
}
