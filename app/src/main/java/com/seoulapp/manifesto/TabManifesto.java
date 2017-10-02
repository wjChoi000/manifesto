package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabManifesto extends Fragment {


    static int i = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_manifesto, container, false);

        ((ImageView) rootView.findViewById(R.id.manifestoBtn1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(1);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn2)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(2);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn3)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(3);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn4)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(4);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn5)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(5);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn6)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(6);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn7)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(7);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn8)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(8);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn9)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(9);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn10)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(10);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn11)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(11);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn12)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(12);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn13)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(13);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn14)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(14);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn15)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(15);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn16)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(16);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn17)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(17);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn18)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(18);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn19)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(19);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn20)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(20);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn21)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(21);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn22)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(22);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn23)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(23);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn24)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(24);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn25)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(25);
            }
        });
        ((TextView) rootView.findViewById(R.id.manifestoBtn26)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(26);
            }
        });
        ((ImageView) rootView.findViewById(R.id.manifestoBtn27)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(27);
            }
        });

        return rootView;
    }

    private void startActivityRate(int i){
        Intent intent = new Intent(getActivity(), ManifestoRateActivity.class);
        intent.putExtra("id",i);
        startActivity(intent);
    }



}
