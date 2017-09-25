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

        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn1));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn2));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn3));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn4));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn5));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn6));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn7));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn8));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn9));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn10));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn11));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn12));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn13));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn14));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn15));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn16));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn17));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn18));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn19));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn20));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn21));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn22));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn23));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn24));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn25));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn26));
        FbuttonList.add((FButton) rootView.findViewById(R.id.manifestoBtn27));

        for(FButton temp:FbuttonList){
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityRate(i);
                    i +=1;
                }
            });
        }
        return rootView;
    }

    private void startActivityRate(int i){
        Intent intent = new Intent(getActivity(), ManifestoRateActivity.class);
        intent.putExtra("id",i);
        startActivity(intent);
    }
}
