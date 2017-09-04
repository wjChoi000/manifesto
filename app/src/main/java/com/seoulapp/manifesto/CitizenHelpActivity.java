package com.seoulapp.manifesto;

import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class CitizenHelpActivity extends ActionBarActivity implements AbsListView.OnScrollListener{

    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_help);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("도와줘요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_help);


        ListViewAdapter_help adapter;
        adapter = new ListViewAdapter_help() ;

        adapter.addItem(R.drawable.citizen_help_trash, "쓰레기통 설치를 어떻게 해야 효율적일까?",
                "서초구는 2012년부터 대로변에 설치된 거리 쓰레기통 140개를 모두 없앴다. 쓰레기통을 설치해 놓으면 무단으로 생활쓰레기를 버리고 가는 주민들이 많아져 도로가 더 지저분해진다는 이유였다. 서초구청 관계자는 “쓰레기통이 있으면 그 안에 음식물쓰레기부터 아이들 기저귀까지 각종 처치 곤란한 쓰레기들이 버려져 처리 비용이 만만치 않다. 이는 쓰레기 종량제라는 제도 자체를 무색하게 만드는 행위”라고 설명했다.\n\n반면 강남구에는 934개의 쓰레기통이 있다. 유동인구가 100만 명에 이르는 강남대로의 경우엔 최소 하루 두 번 쓰레기통을 비운다. 강남구청 관계자는 “쓰레기통이 없으면 화단이나 도로변에 쓰레기를 버리기 때문에 도로가 지저분해지고 주민들도 불편을 느끼기 때문”이라고 말했다.",
                "2017.08.04",
                "47","100","123");
        adapter.addItem(R.drawable.help_pay, "최저임금의 적정 수준은 얼마일까?",
                "최저임금 대폭 인상은 양극화와 불평등의 늪에 빠져들던 한국 사회가 변화할 수 있는 물꼬를 텄다는 점에서도 중요하다.\n\n최저임금위원회가 내년 최저임금을 시간당 올해보다 16.4% 오른 7530원으로 결정한 데 대해 국민 44.3%가 ‘적정하다’는 고 응답했다. ‘현재 인상 수준은 낮다(매우 낮다 + 낮은 편이다)’가 31.8%, ‘현재 인상 수준은 높다(매우 높다 + 높은 편이다)’가 23.9%로 나타났다.  ",
                "2017.07.01",
                "179","101","27");
        adapter.addItem(R.drawable.help_edu, "‘공교육 경쟁력’을 위해 외고·자사고 폐지가 최선일까?",
                "문재인 정부는 외국어고등학교·자율형사립고등학교가 고교 서열화를 유발하고 과도한 입시경쟁을 부추긴다며 자사고와 외고, 국제고 등을 폐지하는 정책을 추진 중이며, 일부 학생들과 학부모의 반대에 부딪힌 상태다.\n\n이재정 경기교육감은 2019년부터 도내 10곳 외고·자사고를 단계적으로 모두 폐지하겠다고 밝혔고, 조희연 서울시교육감은 서울시교육감이 자사고와 특목고의 폐지를 위한 법 개정을 촉구한 바 있다.",
                "2017.06.12",
                "37","102","95");
        adapter.addItem(R.drawable.help_date_abuse, "‘데이트 폭력’ 어떻게 해결해야 할까?",
                "미혼의 연인 사이에서 한쪽이 가하는 폭력이나 위협을 말한다. 폭력적인 행위를 암시하면서 정신적인 압박을 가하여 권력관계에서 우위를 차지하는 것이나 언어폭력 등 비물리적인 행위도 포함된다. 연인이라는 친밀한 관계의 특징상 지속적, 반복적으로 발생하고 재범률 또한 약 76%로 높은 편이다.\n\n성적인 폭력뿐 아니라 과한 통제 · 감시 · 폭언 · 협박 · 폭행 · 상해 · 갈취 · 감금 · 납치 · 살인미수 등 복합적인 범죄로 나타난다. 2015년에 신고 접수된 사건을 기준으로 7,692건이 발생하였고 이는 하루에 21건 이상의 범죄가 발생한 것이다. 사적인 문제로 생각하여 가볍게 넘어가는 인식 때문에 더 큰 피해를 불러일으키기도 하므로 사소한 증상에도 주의가 필요하다.",
                "2017.08.14",
                "100","103","271");
        adapter.addItem(R.drawable.help_baby, "저출산·고령화사회 대책은 무엇일까?",
                "현재는 단순히 출생율이 줄어드는 것뿐만 아니라 총 출생아의 수도 지속적으로 감소하고 있기 때문에 이러한 추세가 계속된다면 생산 인구의 감소와 경제 위축, 국가 재정 지출 증가 등의 문제가 심각해질 수 있습니다.\n\n또한 고령 인구의 증가로 인하여 사회의 활력이 저하되거나 소득을 벌 수 있는 인구에 비해 연금을 받으며 생활하는 사람들의 비율이 높아져 사회 보장 비용이 증가할 수 있습니다. 이러한 문제는 세대 간 갈등과 같은 사회적인 문제로도 이어질 수 있습니다.",
                "2017.07.07",
                "279","104","53");
        adapter.addItem(R.drawable.help_ai, "인간과 인공지능은 공존할 수 있을까?",
                "인공지능으로 인한 예고되는 부작용, 혹은 현재로선 해결 할 수 없는 다양한 문제들은 인류에게 행복과 편리를 준다. 하지만 이러한 인공지능의 최대 장점을 뛰어넘을 정도로 그 위험성도 큰 것이 현실이다.\n\n로봇은 우리의 친구일까, 적일까?",
                "2017.04.15",
                "108","105","21");
        adapter.addItem(R.drawable.help_guui, "제 2의 구의역 스크린도어 사고가 발생하지 않으려면?",
                "구의역 스크린도어 사망 사고는 2016년 5월 28일 서울 지하철 2호선 구의역 내선순환 승강장에서 스크린도어를 혼자 수리하던 외주 업체 직원 김 모(1997년생, 향년 19세)가 출발하던 전동열차에 치어 사망한 사고이다.\n\n안전 수칙에 따르면 스크린도어 수리 작업은 2인 1조로 진행해야 하지만, 사망자는 사고 당시 혼자 작업하고 있었던 것으로 알려졌다. 이 사건이 단순히 개인 과실에 의해 발생한 것이 아니라 근본적으로 열악한 작업 환경과 관리 소홀 때문에 발생한 것으로 지적되었다.",
                "2017.05.28",
                "127","106","141");
        adapter.addItem(R.drawable.help_noise, "층간소음, 해결할 방법은 없는가?",
                "층간소음이란 다세대 주택 및 아파트 등 공동주거 공간에서 주로 발생하는 소음 공해이다. 아이들 뛰는 소리, 발걸음 소리, 화장실 물소리, 가구 끄는 소리, 피아노 소리, 오디오 소리, TV 소리 등이 그 예이다. 층간소음은 가볍고 딱딱한 경량충격음, 무겁고 충격이 큰 중량충격음, 그리고 기체를 통해 전달되는 가벼운 소리인 공기전달음 등으로 나눌 수 있다.\n\n층간소음은 도시화 및 산업화로 주거양식이 아파트 등 공동주거 형태로 변하면서 일상생활에서 가장 자주 노출되는 환경오염원이 되었다. 층간소음은 소득수준이 높아지면서 쾌적하게 살고 싶은 개인적 욕구에 반하게 삶의 질을 떨어뜨리는 주요 요인이기 때문에 심리적 저항이 크다.",
                "2017.07.24",
                "187","107","121");
        adapter.addItem(R.drawable.help_gab, "‘갑질 문화’ 어떻게 해결할까?",
                "갑질이란 권력의 우위에 있는 갑이 권리관계에서 약자인 을에게 하는 부당 행위를 통칭한다.\n\n우린 모두 평범한 사람이자 특별한 사람이다. 흔히 일컬어 사회적으로 ‘성공한 사람’ 혹은 어느 정도 자신이 속해있는 집단 내에서 높은 위치에 있는 사람, 재력이 남다른 사람들의 행동과 언행이 사회적으로 문제가 되는 경우가 많다.",
                "2017.08.07",
                "88","108","791");
        adapter.addItem(R.drawable.help_elec, "바람직한 전기요금 누진세는 무엇일까?",
                "전기 사용량에 따라 전기요금 단가를 높이는 제도로, 고유가 상황에서 에너지 절약을 유도하기 위해 1974년 처음 실시됐다.\n\n현행 전기 요금은 전기를 사용하는 용도에 따라 주택용, 일반용, 교육용, 산업용 등으로 구분하여 차등 적용하고 있는데, 주택용 전기요금에만 누진제가 적용돼 있어 형평성 논란이 계속됐다. 주택용 누진제는 2004년 이후 6단계, 11.7배의 누진 구조로 시행됐다.\n\n그러다 정부는 2016년 12월 주택용 누진제를 6단계에서 3단계로 축소하는 방안을 최종 확정했다. 즉, 100㎾h 단위로 세분돼 있던 6단계 누진구간을 필수사용 구간인 0∼200㎾h(1단계), 평균사용 구간인 201∼400㎾h(2단계), 다소비 구간인 401㎾h 이상 등 3단계로 줄인 것이다.\n\n구간별 요율은 1단계 ㎾h당 93.3원, 2단계 187.9원, 3단계 280.6원을 적용해 요금 단가 차이를 11.7배에서 3배로 축소했다. ",
                "2017.07.20",
                "270","109","123");


        listview.setAdapter(adapter);

        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header, listview, false);
        listview.addHeaderView(header, null, false);


        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage_help);
        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage);
        del.setVisibility(View.GONE);
        listview.setOnScrollListener(this);



        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                Intent intentRow = new Intent(CitizenHelpActivity.this, CitizenHelpContentActivity.class);

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



    }
    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }



    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Rect rect = new Rect();
        backgroundImage.getLocalVisibleRect(rect);
        if (lastTopValue != rect.top) {
            lastTopValue = rect.top;
            backgroundImage.setY((float) (rect.top / 2.0));
        }

    }

}
