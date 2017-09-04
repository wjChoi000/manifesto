package com.seoulapp.manifesto;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CitizenNeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_need);


        //spinner_category
        Spinner cateSpinner = (Spinner)findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> cateAdapter = ArrayAdapter.createFromResource(
                this,R.array.category_array, android.R.layout.simple_spinner_item);
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //spinner_sort
        Spinner spinner_sort = (Spinner)findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter_sort = ArrayAdapter.createFromResource(
                this,R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter_sort);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("필요해요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //리스트
        ListView listview ;
        ListViewAdapter_need adapter;

        // Adapter 생성
        adapter = new ListViewAdapter_need() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_need);
        listview.setAdapter(adapter);

        adapter.addItem("[복지]", "고속터미널 지하상가와 반포역에 노숙자 쉼터 개설","서초구","Won","2017.04.27",
                "고속터미널 지하상가에 노숙자들이 많습니다. 시민들의 쉼터에 누워서 자는것을 보면 지나가는 시민들도 불편합니다.\n\n고속터미널 지하상가는 서울시 소유로 알고 있습니다.\n\n고속터미널 지하상가에 가장 사람들이 많은 지하철 9호선 출입구 근처에 노숙자 쉼터와 목욕시설을 갖추어 운영해주시길 바랍니다.\n\n특히 고속터미널 지하상가와 연결되는 7호선 반포역 가보면 텅텅 빈 공간들이 너무 많습니다. 거기에 박원순 시장님이 시청역에 만든 노숙자 쉼터같이 만들어주시면 좋을거 같습니다.",
                "154","100","41") ;
        adapter.addItem("[경제]", "청년 일자리 창출을 위한 청년벤처기업 조성", "종로구","Bogyu","2017.08.28",
                "대한민국은 다시 일어날 수 있습니다.\n청년 일자리 창출을 위해서 서울시에서도 노력이 필요합니다.\n서울에 있는 창업복지관을 통해서 많은 청년일자리 창출이 있는 것은 알고 있지만, 많은 기업들이 입주하기 위해서 기다리고 있는 실정입니다.\n\n벤처기업 전용 아파트형 공장을 서울시와 시민이 같이 펀드를 조성하여 임대료 없이 건물을 지어서 청년기업에 제공하면 어떨까 생각이 듭니다.\n\n이왕 지을거면 태양열,태양광,LED등 신재생 설비를 이용한 에너지자립형 건물을 만들어 입주기관의 관리비 부담을 덜어주어 세계일류 청년 벤처기업가들이 열심히 연구하고 개발할 수 있는 원동력을 만들어 세계속의 대한민국을 만들면 어떨까 생각이 듭니다.\n\n서울시가 조금만 도와주면 많은 청년일자리 창출이 가능하다고 생각합니다. ",
                "23","101","18") ;
        adapter.addItem("[경제]", "상암 롯데몰 빠른 진행을 바랍니다.", "마포구","아이디1","2017.05.18",
                "언제까지 시간을 끄시려는지 모르겠습니다.\n이렇게 몇년을 질질 끄시는건지 가뜩이나 발전이 없는 지역인데\n롯데쇼핑몰 더이상 진행안한다는 기사까지 나왔군요\n시장님을 선택한 한사람으로서 정말이지 실망이 큼니다.\n왜 지역주민의 말에는 귀를 막으시고 망원시장의 말에만 귀를 기울이시는건지\n이해가 되지 않고요 이러다 또 선거 때가되면 하겠다 어쩐다 할거 같군요\n차라리 빨리 투표하는 날이 왔으면 좋겠습니다. ",
                "81","102","102") ;
        adapter.addItem("[도시]", "강서구 화곡본동 공원근처 가로등", "강서구","아이디2","2017.06.06",
                "늘 야간이 되면 서울시 강서구 화곡본동 공원근처 가로등조명이 꺼져있는것 같습니다. 여성이 홀로다니기엔 위험한 요소가 크다고생각되어 민원올립니다.\n이에\n1. 가로등이 제대로 설치되었는지\n2.제대로 작동되는지\n3. 위험한 사각지대는 없는지 확인해주시면 감사하겠습니다. ",
                "41","103","23") ;
        adapter.addItem("[경제]", "이화여대5길 상점 활성화를 위해서 꼭좀 부탁드립니다~!", "서대문구","아이디3","2017.07.16",
                "<이화여대 5길 전체에대한 의견입니다>\n\n이화여대5길 업종제한으로 인해서 일부상가공실과 거리 활성화가 되질 않습니다\n\n현재 이화여대5길은 의류및 일부잡화 판매업만으로 업종 제한이 되어있습니다\n\n화장품이나 편의점 마켓등 일반 판매시설과\n\n커피브랜드, 패스트푸드, 해외전통음식점등 식음료업을 유치가능하게\n\n업종 변경을 부탁드립니다.\n\n젊고 다양한 업종들이 이화여대5길 더나아가서 3길까지 활성화 시킬수 있다고 생각합니다\n\n꼭좀 적극 검토 부탁드립니다.\n\n강남의 가로수길 경우에도 의류,잡화업 만으로 잘된것이 아니고 젊고 신선한 다체로운 업종들이 서로 시너지를 낸것이라고 생각합니다.\n\n꼭좀 부탁드립니다.",
                "73","104","32") ;
        adapter.addItem("[도시]", "마천3구역 비오면 하수구냄새", "송파구","아이디3","2017.07.29",
                "마천3구역 환경이 열악합니다\n제발 개발 좀 해주십시오\n깨끗한 환경에서 살 수 있도록 개발해 주세요\n개발이 지연되는 사유를 알고 싶습니다\n주변 주민들 73%가 개발을 원한다고 들었습니다\n주민의견을 져버리지 마세요\n개발하고 싶습니다\n주민들의 희망~\n개발입니다",
                "11","105","24") ;
        adapter.addItem("[환경]", "무궁화 나무 자르지 않도록 지시", "중랑구","아이디4","2017.08.15",
                "'무궁화 나무 싹뚝 싹둑 자르지 않도록 지시'\n\n이유\n과거 일제 시대 때\n벚꽃 나무는 자르지 않고 애지 중지 키우고\n한국의 나라 꽃 무궁화는 싹뚝 싹둑 자르게 유도하였음\n\n 서울시부터 모범적으로 시정할 필요\n\n(향후에 무궁화 싹뚝 싹둑 자르면 자연환경 훼손으로 과태료 부과한다고 경범죄 처벌법 적용 필요)",
                "72","106","8") ;
        adapter.addItem("[문화]", "신촌물총축제 폐지해주세요.", "서대문구","아이디5","2017.08.03",
                "매해 가뭄피해가 있고 물부족 상태로 접어들 위험이 있는 국가에서\n또한 물절약에 대한 시민 의식도 굉장히 부족한데 수도세까지 저렴한 국가에서\n단지 몇몇 시민들의 단순한 재미를 위해 시내 한복판에서\n비참여 일반인들에게 피해를 주며\n공공재에 대한 아무런 윤리의식 없이 행해지고있는\n민폐나 다름없는 신촌물총축제를 하루빨리 폐지해주시기를 간곡히 요청합니다. ",
                "26","107","85") ;
        adapter.addItem("[문화]", "휴일 도로 점거하는 마라톤", "광진구","아이디6","2017.08.23",
                "구의동 사는 000 엄마 60대 아지매입니다.\n자양동에 있는 갈보리교회를 주일마다 가는데요.\n아침일찍부터 열심히 준비하고 나가보면.\n정류장엔 버스가 지연된다고 하고.\n이쪽 저쪽으로 택시잡으려 안간힘을 써도 택시또한 잡히지 않고.\n무척 고생을 하다가 결국은 교회도 늦고 그런적이 심심치 않게 있습니다.\n미리 사전에 방송이나 공고를 하는 것도 아니고, 무슨 그렇게 도로 점거를 해서 주민들을 불편하게 하는지 속이 심히 상합니다.\n시외곽으로 나가서 하든지. 굳이 그렇게 도심에서 하는 이유를 모르겠습니다.\n부디 선처 부탁드립니다. ",
                "90","108","12") ;
        adapter.addItem("[행정]", "120 다산콜센터 민원처리 문제","강서구","아이디7","2017.09.01",
                "120 다산콜센터에 전화하기가 하늘에 별따기 만큼 너무 힘듭니다.\n전화를 5번정도를 하면 1번정도는 받아줬으면 합니다.\n오늘 주차문제로 9시부터30분간 전화를 걸어도 통화를 할 수가 없었고, 출근하는 입장으로써 앞차가 막고 있고 민원처리 속도가 너무느려서 불편을 겪었습니다.\n신고 후 기다리는 시간이 너무 오래 걸려 120에 연락을 수번을 했지만, 받지않았고 강서구청 주차관리과에다가 연락을 하였으나 이곳도 받지 않았습니다. 차라리 경찰이 주차를 단속했을때가 훨씬 신속하고 정확했습니다. 이곳은 다산콜센터가 아니라 고충센터 입니다. 해결하지도 못할거 그냥 경찰청이 다시 했으면 좋겠습니다. 무작정 주차단속이 아니라 신고된 주차문제를 빨리 해결해줬으면 합니다. ",
                "52","109","38") ;





        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                Intent intentRow = new Intent(CitizenNeedActivity.this, CitizenNeedContentActivity.class);

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
    }
    //back button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_writing:
                Intent intent = new Intent(this,CitizenNeedWritingActivity.class);
                startActivity(intent);
                return true;
//            case R.id.action_search:
        }
        return super.onOptionsItemSelected(item);
    }
    //메뉴 리소스를 팽창한다. 액션바에 항목들을 추가한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_citizen_need,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
