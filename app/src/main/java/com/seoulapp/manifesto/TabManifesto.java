package com.seoulapp.manifesto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import info.hoang8f.widget.FButton;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabManifesto extends Fragment {

    private ArrayList<FButton> FbuttonList= new ArrayList();
    static int i = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_manifesto, container, false);

        ((FButton) rootView.findViewById(R.id.manifestoBtn1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(1);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn2)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(2);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn3)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(3);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn4)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(4);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn5)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(5);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn6)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(6);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn7)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(7);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn8)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(8);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn9)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(9);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn10)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(10);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn11)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(11);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn12)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(12);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn13)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(13);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn14)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(14);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn15)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(15);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn16)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(16);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn17)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(17);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn18)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(18);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn19)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(19);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn20)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(20);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn21)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(21);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn22)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(22);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn23)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(23);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn24)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(24);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn25)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(25);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn26)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(26);
            }
        });
        ((FButton) rootView.findViewById(R.id.manifestoBtn27)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityRate(27);
            }
        });
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn1));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn2));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn3));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn4));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn5));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn6));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn7));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn8));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn9));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn10));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn11));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn12));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn13));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn14));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn15));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn16));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn17));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn18));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn19));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn20));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn21));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn22));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn23));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn24));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn25));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn26));
//        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn27));


//        for(FButton temp:FbuttonList){
//            temp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivityRate(i);
//                    i +=1;
//                }
//            });
//        }
        return rootView;
    }

    private void startActivityRate(int i){
        Intent intent = new Intent(getActivity(), ManifestoRateActivity.class);
        intent.putExtra("id",i);
        startActivity(intent);
    }

}
