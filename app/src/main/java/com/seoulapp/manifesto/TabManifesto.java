package com.seoulapp.manifesto;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabManifesto extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_manifesto, container, false);

        Button major = (Button) rootView.findViewById(R.id.manifestoBtnOfficial);
        major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOfficial(view);
            }
        });

        return rootView;
    }

    public void onClickOfficial(View view){
        Intent intent = new Intent(getActivity(), ManifestoRateActivity.class);
        intent.putExtra("city","서울특별시");
        startActivity(intent);
    }

}
