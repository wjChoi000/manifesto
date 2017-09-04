package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
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
    private ListView L_listview1, L_listview2, L_listview3, H_listview1, H_listview2, H_listview3, N_listview1, N_listview2, N_listview3;




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

        //리스트뷰1
        L_listview1 = (ListView) rootView.findViewById(R.id.listview_listen_tab1);
        ListViewAdapter L_adapter1;
        L_adapter1 = new ListViewAdapter() ;
        L_adapter1.addItem(R.drawable.listen_thaad, "대한민국의 사드 배치는 누구를 위해서인가?",
                "대한민국의 사드 배치 논란은 주한 미군이 대한민국 경상북도 성주군에 사드를 배치한 것에 대하여 발생한 논란이다.",
                "사드는 '국가 안보·국민 안전 위한 방어 체계'이며, 북한 핵·미사일 등 위협 대응할 수 있고 미국과의 관계·한미 동맹 강화를 위해서 필요하다.",
                "사드는 효과가 없으며 국익에 도움이 안되고 국민 공감 부족·일방적 추진이므로 필요없다.",
                "2016.08.18",
                "63","49","32") ;
        L_listview1.setAdapter(L_adapter1);

        //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        L_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();

                String subtitle = item.getSubcontext();
                String ag_context = item.getAg_context();
                String op_context = item.getOp_context();
                String c_date = item.getC_date();
                String comNum = item.getComment();
                String ag_Num = item.getAg();
                String op_Num = item.getOp();

                Intent intentRow = new Intent(getActivity(), CitizenListenContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);

                intentRow.putExtra("subtitle",subtitle);
                intentRow.putExtra("ag_context",ag_context);
                intentRow.putExtra("op_context",op_context);
                intentRow.putExtra("c_date",c_date);
                intentRow.putExtra("comNum",comNum);

                intentRow.putExtra("agNum",ag_Num);
                intentRow.putExtra("opNum",op_Num);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰2
        L_listview2 = (ListView) rootView.findViewById(R.id.listview_listen_tab2);

        ListViewAdapter L_adapter2;
        L_adapter2 = new ListViewAdapter() ;

        L_adapter2.addItem(R.drawable.listen_tax, "‘대기업+고소득자 증세‘ 무엇이 옳은 걸까?",
                "문재인 정부가 부자증세의 칼을 빼들었다. 늘어나는 일자리 및 복지관련 재정수요에 대응해 초대기업과 초고소득자에 대한 법인세와 소득세 최고세율을 올리고 과표를 조정해 연간 5조5000억원 규모의 세수를 확충할 방침이다.",
                "소득재분배를 통한 공정경제를 지양하고 일자리를 창출하겠다는 국정철학이 반영됐다.",
                "증세 효과도 크지 않으면서 근로 의욕을 떨어트리고 조세 저항을 불러온다.",
                "2017.02.19",
                "423","299","362") ;
        L_listview2.setAdapter(L_adapter2);
        //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        L_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();

                String subtitle = item.getSubcontext();
                String ag_context = item.getAg_context();
                String op_context = item.getOp_context();
                String c_date = item.getC_date();
                String comNum = item.getComment();
                String ag_Num = item.getAg();
                String op_Num = item.getOp();

                Intent intentRow = new Intent(getActivity(), CitizenListenContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);

                intentRow.putExtra("subtitle",subtitle);
                intentRow.putExtra("ag_context",ag_context);
                intentRow.putExtra("op_context",op_context);
                intentRow.putExtra("c_date",c_date);
                intentRow.putExtra("comNum",comNum);

                intentRow.putExtra("agNum",ag_Num);
                intentRow.putExtra("opNum",op_Num);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰3
        L_listview3 = (ListView) rootView.findViewById(R.id.listview_listen_tab3);

        ListViewAdapter L_adapter3;
        L_adapter3 = new ListViewAdapter() ;

        L_adapter3.addItem(R.drawable.listen_skktlg, "‘단통법 폐지’ 무엇이 옳은 걸까?",
                "단통법은 2014년 박근혜 정부가 차별적인 휴대폰 지원금 지급을 금지해 제조사의 단말기 출고가 인하와 통신사의 요금인하 여력을 높인다는 취지로 만들어졌다. 그러나 소비자가 기대하는 만큼 가계통신비 인하가 이뤄지지 않았다.",
                "단통법은 소비자 혜택은 줄어들고, 가계통신비도 인하 효과가 없으며 통신사 배만 불렸다.",
                "단통법 덕분에 공시지원금에 대한 이용자 차별이 줄어들고 유통시장이 투명화가 된다.",
                "2017.06.09",
                "423","299","362") ;
        L_listview3.setAdapter(L_adapter3);
        //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        L_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();

                String subtitle = item.getSubcontext();
                String ag_context = item.getAg_context();
                String op_context = item.getOp_context();
                String c_date = item.getC_date();
                String comNum = item.getComment();
                String ag_Num = item.getAg();
                String op_Num = item.getOp();

                Intent intentRow = new Intent(getActivity(), CitizenListenContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);

                intentRow.putExtra("subtitle",subtitle);
                intentRow.putExtra("ag_context",ag_context);
                intentRow.putExtra("op_context",op_context);
                intentRow.putExtra("c_date",c_date);
                intentRow.putExtra("comNum",comNum);

                intentRow.putExtra("agNum",ag_Num);
                intentRow.putExtra("opNum",op_Num);

                startActivity(intentRow);

            }
        }) ;


        //들려줘요 더보기 레이아웃
        ViewGroup ListenAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_listenAdd);
        ListenAddlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickListenAddlayout(view);
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

        //리스트뷰1
        H_listview1 = (ListView)rootView.findViewById(R.id.listview_help_tab1);
        ListViewAdapter_help H_adapter1;
        H_adapter1 = new ListViewAdapter_help() ;
        H_adapter1.addItem(R.drawable.citizen_help_trash, "쓰레기통 설치를 어떻게 해야 효율적일까?",
                "서초구는 2012년부터 대로변에 설치된 거리 쓰레기통 140개를 모두 없앴다. 쓰레기통을 설치해 놓으면 무단으로 생활쓰레기를 버리고 가는 주민들이 많아져 도로가 더 지저분해진다는 이유였다. 서초구청 관계자는 “쓰레기통이 있으면 그 안에 음식물쓰레기부터 아이들 기저귀까지 각종 처치 곤란한 쓰레기들이 버려져 처리 비용이 만만치 않다. 이는 쓰레기 종량제라는 제도 자체를 무색하게 만드는 행위”라고 설명했다.\n\n반면 강남구에는 934개의 쓰레기통이 있다. 유동인구가 100만 명에 이르는 강남대로의 경우엔 최소 하루 두 번 쓰레기통을 비운다. 강남구청 관계자는 “쓰레기통이 없으면 화단이나 도로변에 쓰레기를 버리기 때문에 도로가 지저분해지고 주민들도 불편을 느끼기 때문”이라고 말했다.",
                "2017.08.04","47","100","123");
        H_listview1.setAdapter(H_adapter1);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        H_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_help item = (ListViewItem_help) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();
                String subContext = item.getSubcontext();
                String goodNum = item.getGoodStr();
                String hitNum = item.getHitStr();
                String comNum = item.getComment();
                String C_Date = item.getC_date();

                Intent intentRow = new Intent(getActivity(), CitizenHelpContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);
                intentRow.putExtra("subContext",subContext);
                intentRow.putExtra("hitNum",hitNum);
                intentRow.putExtra("goodNum",goodNum);
                intentRow.putExtra("comNum",comNum);
                intentRow.putExtra("C_date",C_Date);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰2
        H_listview2 = (ListView)rootView.findViewById(R.id.listview_help_tab2);
        ListViewAdapter_help H_adapter2;
        H_adapter2 = new ListViewAdapter_help() ;
        H_adapter2.addItem(R.drawable.help_pay, "최저임금의 적정 수준은 얼마일까?",
                "최저임금 대폭 인상은 양극화와 불평등의 늪에 빠져들던 한국 사회가 변화할 수 있는 물꼬를 텄다는 점에서도 중요하다.\n\n최저임금위원회가 내년 최저임금을 시간당 올해보다 16.4% 오른 7530원으로 결정한 데 대해 국민 44.3%가 ‘적정하다’는 고 응답했다. ‘현재 인상 수준은 낮다(매우 낮다 + 낮은 편이다)’가 31.8%, ‘현재 인상 수준은 높다(매우 높다 + 높은 편이다)’가 23.9%로 나타났다.  ",
                "2017.07.01",
                "179","101","27");
        H_listview2.setAdapter(H_adapter2);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        H_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_help item = (ListViewItem_help) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();
                String subContext = item.getSubcontext();
                String goodNum = item.getGoodStr();
                String hitNum = item.getHitStr();
                String comNum = item.getComment();
                String C_Date = item.getC_date();

                Intent intentRow = new Intent(getActivity(), CitizenHelpContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);
                intentRow.putExtra("subContext",subContext);
                intentRow.putExtra("hitNum",hitNum);
                intentRow.putExtra("goodNum",goodNum);
                intentRow.putExtra("comNum",comNum);
                intentRow.putExtra("C_date",C_Date);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰3
        H_listview3 = (ListView)rootView.findViewById(R.id.listview_help_tab3);
        ListViewAdapter_help H_adapter3;
        H_adapter3 = new ListViewAdapter_help() ;
        H_adapter3.addItem(R.drawable.help_edu, "‘공교육 경쟁력’을 위해 외고·자사고 폐지가 최선일까?",
                "문재인 정부는 외국어고등학교·자율형사립고등학교가 고교 서열화를 유발하고 과도한 입시경쟁을 부추긴다며 자사고와 외고, 국제고 등을 폐지하는 정책을 추진 중이며, 일부 학생들과 학부모의 반대에 부딪힌 상태다.\n\n이재정 경기교육감은 2019년부터 도내 10곳 외고·자사고를 단계적으로 모두 폐지하겠다고 밝혔고, 조희연 서울시교육감은 서울시교육감이 자사고와 특목고의 폐지를 위한 법 개정을 촉구한 바 있다.",
                "2017.06.12",
                "37","102","95");
        H_listview3.setAdapter(H_adapter3);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        H_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_help item = (ListViewItem_help) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                int image = item.getTitleImage();
                String subContext = item.getSubcontext();
                String goodNum = item.getGoodStr();
                String hitNum = item.getHitStr();
                String comNum = item.getComment();
                String C_Date = item.getC_date();

                Intent intentRow = new Intent(getActivity(), CitizenHelpContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("img",image);
                intentRow.putExtra("subContext",subContext);
                intentRow.putExtra("hitNum",hitNum);
                intentRow.putExtra("goodNum",goodNum);
                intentRow.putExtra("comNum",comNum);
                intentRow.putExtra("C_date",C_Date);

                startActivity(intentRow);

            }
        }) ;



        //필요해요 더보기 레이아웃
        ViewGroup NeedAddlayout = (ViewGroup) rootView.findViewById(R.id.layout_needAdd);
        NeedAddlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                onClickNeedAddlayout(view);
            }
        });

        //리스트뷰1
        N_listview1 = (ListView)rootView.findViewById(R.id.listview_need_tab1);
        ListViewAdapter_need N_adapter1;
        N_adapter1 = new ListViewAdapter_need() ;
        N_adapter1.addItem("[복지]", "고속터미널 지하상가와 반포역에 노숙자 쉼터 개설","서초구","Won","2017.04.27",
                "고속터미널 지하상가에 노숙자들이 많습니다. 시민들의 쉼터에 누워서 자는것을 보면 지나가는 시민들도 불편합니다.\n\n고속터미널 지하상가는 서울시 소유로 알고 있습니다.\n\n고속터미널 지하상가에 가장 사람들이 많은 지하철 9호선 출입구 근처에 노숙자 쉼터와 목욕시설을 갖추어 운영해주시길 바랍니다.\n\n특히 고속터미널 지하상가와 연결되는 7호선 반포역 가보면 텅텅 빈 공간들이 너무 많습니다. 거기에 박원순 시장님이 시청역에 만든 노숙자 쉼터같이 만들어주시면 좋을거 같습니다.",
                "154","100","41") ;
        N_listview1.setAdapter(N_adapter1);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        N_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_need item = (ListViewItem_need) parent.getItemAtPosition(position) ;

                String titleStr = item.getNeed_title() ;
                String u_id = item.getU_id();
                String guStr = item.getGu();
                String C_date = item.getC_date();
                String need_context = item.getNeed_context();
                String GoodNum = item.getGoodStr();
                String HitNum = item.getHitNum();
                String ComNum = item.getCommentStr();

                Intent intentRow = new Intent(getActivity(), CitizenNeedContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("u_id",u_id);
                intentRow.putExtra("gu",guStr);
                intentRow.putExtra("C_date",C_date);
                intentRow.putExtra("Need_context",need_context);
                intentRow.putExtra("GoodNum",GoodNum);
                intentRow.putExtra("HitNum",HitNum);
                intentRow.putExtra("ComNum",ComNum);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰2
        N_listview2 = (ListView)rootView.findViewById(R.id.listview_need_tab2);
        ListViewAdapter_need N_adapter2;
        N_adapter2 = new ListViewAdapter_need() ;
        N_adapter2.addItem("[경제]", "청년 일자리 창출을 위한 청년벤처기업 조성", "종로구","Bogyu","2017.08.28",
                "대한민국은 다시 일어날 수 있습니다.\n청년 일자리 창출을 위해서 서울시에서도 노력이 필요합니다.\n서울에 있는 창업복지관을 통해서 많은 청년일자리 창출이 있는 것은 알고 있지만, 많은 기업들이 입주하기 위해서 기다리고 있는 실정입니다.\n\n벤처기업 전용 아파트형 공장을 서울시와 시민이 같이 펀드를 조성하여 임대료 없이 건물을 지어서 청년기업에 제공하면 어떨까 생각이 듭니다.\n\n이왕 지을거면 태양열,태양광,LED등 신재생 설비를 이용한 에너지자립형 건물을 만들어 입주기관의 관리비 부담을 덜어주어 세계일류 청년 벤처기업가들이 열심히 연구하고 개발할 수 있는 원동력을 만들어 세계속의 대한민국을 만들면 어떨까 생각이 듭니다.\n\n서울시가 조금만 도와주면 많은 청년일자리 창출이 가능하다고 생각합니다. ",
                "23","101","18") ;
        N_listview2.setAdapter(N_adapter2);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        N_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_need item = (ListViewItem_need) parent.getItemAtPosition(position) ;

                String titleStr = item.getNeed_title() ;
                String u_id = item.getU_id();
                String guStr = item.getGu();
                String C_date = item.getC_date();
                String need_context = item.getNeed_context();
                String GoodNum = item.getGoodStr();
                String HitNum = item.getHitNum();
                String ComNum = item.getCommentStr();

                Intent intentRow = new Intent(getActivity(), CitizenNeedContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("u_id",u_id);
                intentRow.putExtra("gu",guStr);
                intentRow.putExtra("C_date",C_date);
                intentRow.putExtra("Need_context",need_context);
                intentRow.putExtra("GoodNum",GoodNum);
                intentRow.putExtra("HitNum",HitNum);
                intentRow.putExtra("ComNum",ComNum);

                startActivity(intentRow);

            }
        }) ;

        //리스트뷰3
        N_listview3 = (ListView)rootView.findViewById(R.id.listview_need_tab3);
        ListViewAdapter_need N_adapter3;
        N_adapter3 = new ListViewAdapter_need() ;
        N_adapter3.addItem("[경제]", "상암 롯데몰 빠른 진행을 바랍니다.", "마포구","아이디1","2017.05.18",
                "언제까지 시간을 끄시려는지 모르겠습니다.\n이렇게 몇년을 질질 끄시는건지 가뜩이나 발전이 없는 지역인데\n롯데쇼핑몰 더이상 진행안한다는 기사까지 나왔군요\n시장님을 선택한 한사람으로서 정말이지 실망이 큼니다.\n왜 지역주민의 말에는 귀를 막으시고 망원시장의 말에만 귀를 기울이시는건지\n이해가 되지 않고요 이러다 또 선거 때가되면 하겠다 어쩐다 할거 같군요\n차라리 빨리 투표하는 날이 왔으면 좋겠습니다. ",
                "81","102","102") ;
        N_listview3.setAdapter(N_adapter3);
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        N_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem_need item = (ListViewItem_need) parent.getItemAtPosition(position) ;

                String titleStr = item.getNeed_title() ;
                String u_id = item.getU_id();
                String guStr = item.getGu();
                String C_date = item.getC_date();
                String need_context = item.getNeed_context();
                String GoodNum = item.getGoodStr();
                String HitNum = item.getHitNum();
                String ComNum = item.getCommentStr();

                Intent intentRow = new Intent(getActivity(), CitizenNeedContentActivity.class);

                intentRow.putExtra("title",titleStr);
                intentRow.putExtra("u_id",u_id);
                intentRow.putExtra("gu",guStr);
                intentRow.putExtra("C_date",C_date);
                intentRow.putExtra("Need_context",need_context);
                intentRow.putExtra("GoodNum",GoodNum);
                intentRow.putExtra("HitNum",HitNum);
                intentRow.putExtra("ComNum",ComNum);

                startActivity(intentRow);

            }
        }) ;





        return rootView;
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

        list.add(new RollingModel("1", R.drawable.listen_book_issue));
        list.add(new RollingModel("2", R.drawable.listen_moon100_issue));
        list.add(new RollingModel("3", R.drawable.listen_power_issue));
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