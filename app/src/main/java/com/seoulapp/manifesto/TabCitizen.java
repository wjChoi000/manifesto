package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        //들려줘요 첫번째 레이아웃
        ViewGroup Listenlayout1 = (ViewGroup) rootView.findViewById(R.id.layout_listen1);
        Listenlayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickListenlayout1(view);
            }
        });

        //도와줘요 첫번째 레이아웃
        ViewGroup Helplayout1 = (ViewGroup) rootView.findViewById(R.id.layout_help1);
        Helplayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickHelplayout1(view);
            }
        });

        //필요해요 첫번째 레이아웃
        ViewGroup Needlayout1 = (ViewGroup) rootView.findViewById(R.id.layout_need1);
        Needlayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickNeedlayout1(view);
            }
        });

        //더보기 들려줘요 버튼
        TextView listenAddBtn = (TextView)rootView.findViewById(R.id.listen_add);
        listenAddBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickListenAdd(view);
            }
        });
        //더보기 도와줘요 버튼
        TextView helpAddBtn = (TextView)rootView.findViewById(R.id.help_add);
        helpAddBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickHelpAdd(view);
            }
        });

        TextView needAddBtn = (TextView)rootView.findViewById(R.id.need_add);
        needAddBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickNeedAdd(view);
            }
        });
        return rootView;

    }

    public void onClickListenlayout1(View view){
        Intent intent = new Intent(getActivity(),CitizenListenContentActivity.class);
        startActivity(intent);
    }


    public void onClickHelplayout1(View view){
        Intent intent = new Intent(getActivity(),CitizenHelpContentActivity.class);
        startActivity(intent);
    }


    public void onClickNeedlayout1(View view){
        Intent intent = new Intent(getActivity(),CitizenNeedContentActivity.class);
        startActivity(intent);
    }


    public void onClickListenAdd(View view){
        Intent intent = new Intent(getActivity(),CitizenListenActivity.class);
        startActivity(intent);
    }

    public void onClickHelpAdd(View view){
        Intent intent = new Intent(getActivity(),CitizenHelpActivity.class);
        startActivity(intent);
    }

    public void onClickNeedAdd(View view){
        Intent intent = new Intent(getActivity(),CitizenNeedActivity.class);
        startActivity(intent);
    }


}