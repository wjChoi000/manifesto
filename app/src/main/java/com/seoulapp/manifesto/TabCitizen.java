package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        //들려줘요 첫번째 이미지
        ImageButton ListenImageNum1 =(ImageButton) rootView.findViewById(R.id.Listen_list_image_num1);
        ListenImageNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickListenImageNum1(view);
            }
        });

        //들려줘요 첫번째 제목 버튼
        TextView ListenTitleNum1 = (TextView)rootView.findViewById(R.id.Listen_list_title_num1);
        ListenTitleNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickListenTitleNum1(view);
            }
        });


        //도와줘요 첫번째 이미지
        ImageButton HelpImageNum1 =(ImageButton) rootView.findViewById(R.id.Help_list_image_num1);
        HelpImageNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickHelpImageNum1(view);
            }
        });

        //도와줘요 첫번째 제목 버튼
        TextView HelpTitleNum1 = (TextView)rootView.findViewById(R.id.Help_list_title_num1);
        HelpTitleNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickHelpTitleNum1(view);
            }
        });


        //필요해요 첫번째 카테고리 버튼
        TextView NeedCateNum1 = (TextView)rootView.findViewById(R.id.Need_list_cate_num1);
        NeedCateNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickNeedCateNum1(view);
            }
        });

        //필요해요 첫번째 제목 버튼
        TextView NeedTitleNum1 = (TextView)rootView.findViewById(R.id.Need_list_title_num1);
        NeedTitleNum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickNeedTitleNum1(view);
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

    public void onClickListenImageNum1(View view){
        Intent intent = new Intent(getActivity(),CitizenListenContentActivity.class);
        startActivity(intent);
    }

    public void onClickListenTitleNum1(View view){
        Intent intent = new Intent(getActivity(),CitizenListenContentActivity.class);
        startActivity(intent);
    }

    public void onClickHelpImageNum1(View view){
        Intent intent = new Intent(getActivity(),CitizenHelpContentActivity.class);
        startActivity(intent);
    }

    public void onClickHelpTitleNum1(View view){
        Intent intent = new Intent(getActivity(),CitizenHelpContentActivity.class);
        startActivity(intent);
    }

    public void onClickNeedCateNum1(View view){
        Intent intent = new Intent(getActivity(),CitizenNeedContentActivity.class);
        startActivity(intent);
    }

    public void onClickNeedTitleNum1(View view){
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