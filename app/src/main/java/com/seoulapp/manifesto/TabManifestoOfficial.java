package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seoulapp.manifesto.model.KnowContent;

import java.util.ArrayList;

/**
 * Created by wjcho on 2017-07-06.
 */

public class TabManifestoOfficial extends Fragment{





    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("official","create");
        rootView = inflater.inflate(R.layout.tab_manifesto_official, container, false);



        return rootView;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("official","destroy");
    }
}