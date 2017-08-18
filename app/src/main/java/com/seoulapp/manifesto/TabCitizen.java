package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjcho on 2017-07-04.
 */

public class TabCitizen extends Fragment {
    private ViewPager mViewPager = null;
    private RollingAdapter mAdapter = null;
    private IndicatorView mIndicatorView = null;
    private AutoRollingManager mAutoRollingManager = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_citizen, container, false);

        mIndicatorView = (IndicatorView)rootView.findViewById(R.id.indicator_view);
        mViewPager = (ViewPager)rootView.findViewById(R.id.view_pager);
        mAdapter = new RollingAdapter(rootView.getContext(), getData(), new RollingAdapter.OnAdapterItemClickListener() {
            @Override
            public void onItemClick(RollingModel object, int position) {
               // Toast.makeText(rootView.getContext(), position + " items click!", Toast.LENGTH_SHORT).show();
            }
        });
        mViewPager.setAdapter(mAdapter);
        mIndicatorView.setViewPager(mViewPager);
        mAutoRollingManager = new AutoRollingManager(mViewPager, mAdapter, mIndicatorView);
        mAutoRollingManager.setRollingTime(5500);


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

    private List<RollingModel> getData(){
        List<RollingModel> list = new ArrayList<>();

        list.add(new RollingModel("1", R.drawable.listen_trump));
        list.add(new RollingModel("2", R.drawable.listen_religion));
        list.add(new RollingModel("3", R.drawable.listen_tax));
        list.add(new RollingModel("4", R.drawable.listen_privilege));
        list.add(new RollingModel("5", R.drawable.listen_power));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mAutoRollingManager.onRollingStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAutoRollingManager.onRollingStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAutoRollingManager.onRollingDestroy();
    }

}