package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        Button welfare = (Button) rootView.findViewById(R.id.citizen_welfare);
        welfare.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                onClickCategory(view);
            }
        });
        return rootView;

    }
    public void onClickCategory(View view){
        Intent intent = new Intent(getActivity(),CategorylistActivity.class);
        startActivity(intent);
    }
}