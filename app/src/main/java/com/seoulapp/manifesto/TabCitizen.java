package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        TextView listenAddBtn = (TextView)rootView.findViewById(R.id.listen_add);
        listenAddBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickListenAdd(view);
            }
        });

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