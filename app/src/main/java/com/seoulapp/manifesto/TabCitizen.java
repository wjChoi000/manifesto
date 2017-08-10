package com.seoulapp.manifesto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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
    ViewPager vp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        vp = (ViewPager)rootView.findViewById(R.id.vp);
        vp.setAdapter(new pagerAdapter(getFragmentManager()));
        vp.setCurrentItem(0);


        //들려줘요 첫번째 레이아웃
        final ViewGroup Listenlayout1 = (ViewGroup) rootView.findViewById(R.id.layout_listen1);
        Listenlayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickListenlayout1(view);
            }
        });

        //들려줘요 더보기 레이아웃
        ViewGroup ListenAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_listenAdd);
        ListenAddlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickListenAddlayout(view);
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

        //도와줘요 더보기 레이아웃
        ViewGroup HelpAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_helpAdd);
        HelpAddlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickHelpAddlayout(view);
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

        //필요해요 더보기 레이아웃
        ViewGroup NeedAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_needAdd);
        NeedAddlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickNeedAddlayout(view);
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


    public void onClickListenAddlayout(View view){
        Intent intent = new Intent(getActivity(),CitizenListenActivity.class);
        startActivity(intent);
    }

    public void onClickHelpAddlayout(View view){
        Intent intent = new Intent(getActivity(),CitizenHelpActivity.class);
        startActivity(intent);
    }

    public void onClickNeedAddlayout(View view){
        Intent intent = new Intent(getActivity(),CitizenNeedActivity.class);
        startActivity(intent);
    }

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }


}