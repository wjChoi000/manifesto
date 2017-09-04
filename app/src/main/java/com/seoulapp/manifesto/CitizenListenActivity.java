package com.seoulapp.manifesto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CitizenListenActivity extends ActionBarActivity implements AbsListView.OnScrollListener{
    private int lastTopValue = 0;
    private ListView listview;
    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_listen);

        //actionbar title
        View view = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText("들려줘요");

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title


        //back button
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //리스트뷰
        listview = (ListView) findViewById(R.id.listview_listen);


        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        adapter.addItem(R.drawable.listen_thaad, "대한민국의 사드 배치는 누구를 위해서인가?",
                "대한민국의 사드 배치 논란은 주한 미군이 대한민국 경상북도 성주군에 사드를 배치한 것에 대하여 발생한 논란이다.",
                "사드는 '국가 안보·국민 안전 위한 방어 체계'이며, 북한 핵·미사일 등 위협 대응할 수 있고 미국과의 관계·한미 동맹 강화를 위해서 필요하다.",
                "사드는 효과가 없으며 국익에 도움이 안되고 국민 공감 부족·일방적 추진이므로 필요없다.",
                "2016.08.18",
                "63","49","32") ;
        adapter.addItem(R.drawable.listen_tax, "‘대기업+고소득자 증세‘ 무엇이 옳은 걸까?",
                "문재인 정부가 부자증세의 칼을 빼들었다. 늘어나는 일자리 및 복지관련 재정수요에 대응해 초대기업과 초고소득자에 대한 법인세와 소득세 최고세율을 올리고 과표를 조정해 연간 5조5000억원 규모의 세수를 확충할 방침이다.",
                "소득재분배를 통한 공정경제를 지양하고 일자리를 창출하겠다는 국정철학이 반영됐다.",
                "증세 효과도 크지 않으면서 근로 의욕을 떨어트리고 조세 저항을 불러온다.",
                "2017.02.19",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_skktlg, "‘단통법 폐지’ 무엇이 옳은 걸까?",
                "단통법은 2014년 박근혜 정부가 차별적인 휴대폰 지원금 지급을 금지해 제조사의 단말기 출고가 인하와 통신사의 요금인하 여력을 높인다는 취지로 만들어졌다. 그러나 소비자가 기대하는 만큼 가계통신비 인하가 이뤄지지 않았다.",
                "단통법은 소비자 혜택은 줄어들고, 가계통신비도 인하 효과가 없으며 통신사 배만 불렸다.",
                "단통법 덕분에 공시지원금에 대한 이용자 차별이 줄어들고 유통시장이 투명화가 된다.",
                "2017.06.09",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_trump, "트럼프정권 ‘대북 선제타격론’ 무엇이 옳은 걸까?",
                "도널드 트럼프 신행정부의 대북정책이 설계되고 있는 시점에 미국 내에서 대북 선제타격 주장이 고개를 들면서 파문이 커지고 있다. \n대북선제타격론이 곧 나오게 될 트럼프 대북정책의 기조에 반영된다면 북미관계와 남북관계 뿐아니라 미중관계 등 동북아 전체 안보환경에도 엄청난 폭풍이 몰아칠 가능성이 크다는 우려도 제기되고 있다.",
                "북한의 핵.미사일 위협에 맞서 방어만으로는 불충분하다기 때문에 대북 압박수단의 하나로 군사적 옵션도 배제해선 안된다.",
                "선제타격이 전면전으로 확대돼 수십만명 이상의 사상자가 발생할 것이다.",
                "2017.02.18",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_power, "‘탈 원전’ 무엇이 옳은 걸까?",
                "문재인 정부의 '탈 원전 정책'에 대해서 찬반 논란이 뜨겁다. 2011년 발생한 후쿠시마 원사고로 수많은 사상자가 생겼기 때문에 탈 원전해야한다는 의견과 기존의 원자력 발전소가 생산해오던 전력생산의 공백을 무엇으로 채울 것이냐는 의견이 대립하고 있다.",
                "원전에서 인체에 치명적인 방사성 물질이 유출될 수 있기 때문에 국민의 안전을 보호하기 위해 ‘탈 원전‘ 해야 한다.",
                "경제적 에너지인 원전을 없애면 전력공급이 불안정해지고 전기 요금이 인상될 것이며 신재생에너지의 원활한 공급확대가 힘들다.",
                "2017.08.18",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_book, "‘역사교과서 국정화’ 무엇이 옳은 걸까?",
                "국정 교과서는 국가에서 직접적으로 교과서 저작에 관여해 그 내용 등을 결정하는 교과서이다. 이 국정교과서는 국가가 저작권을 가지며 국가적 통일성이 필요한 교과목 위주로 개발한다. \n국정화 이전까지 한국사 교과서는 중학교용 11종, 고등학교용 11종 등 총 22종이 있었으나 국정화 이후부터 1종의 국정 교과서로 통일됐다.",
                "기존 8권의 한국사 교과서는 오류가 많고 이념적으로 편향돼 있기 때문에 학생에게 통일된 하나의 역사교과서로 가르쳐야 한다.",
                "교육의 정치적 중립이라는 원칙을 근본적으로 훼손하는 퇴행적 행위이며 독재와 친일을 미화할 것이다.",
                "2017.01.06",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_privilege, "‘국회의원 특권 내려놓기’ 무엇이 옳은 걸까?",
                "우리나라의 고질적인 병폐 가운데 하나인 ‘갑질’ 이를 바로잡기 위해 작지만 의미있는 첫 걸음이 시작됐다. 국회의원의 특권인 불체포특권, 면책특권, 세비 제도 개선, 4촌 이내 친인척 보좌직원 채용 금지, 금배지 폐지 등을 내려놓았다.",
                "국회가 제자리를 찾는 정치를 실현하고 국민들의 사랑과 신뢰를 받게 되는 밑거름이 될 것이다.",
                "특권 내려놓기는 포퓰리즘에 불과하고 7개로는 부족하다.",
                "2016.10.17",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_religion, "‘종교인 과세 2년 유예’ 무엇이 옳은 걸까?",
                "종교인 과세는 이미 지난 2015년 12월 국회 본회의에서 목사,스님 등 종교인에게 과세하는 소득세법 개정안이 처리되면서 결정된 일이었습니다.\n내년으로 예정되어 있는 종교인 과세 시행을 2년 더 유예하는 소득세법 일부개정법률안을 더불어민주당 김진표 의원을 비롯해 28명의 국회의원이 발의를 하자 이에 대한 찬반으로 논란이 일고 있습니다.",
                "과세 대상 소득을 파악하기 쉽지 않고 홍보 및 교육이 이뤄지지 않아 종교계에 큰 혼란이 야기될 수 있다.",
                "국민평등 사상에도 맞지 않는 논리이며 외국에서도 이미 오래전부터 종교인 과세가 당연한 제도로 운영되고 있다. 종교인의 소득은 근로소득이라는 대법원 판례와 조세 심판원은 결정례를 볼　때 당연히 소득세 납부 대상에 해당된다.",
                "2017.08.10",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_out, "‘국회의원 국민소환제’ 무엇이 옳은 걸까?",
                "국민소환제는 유권자들이 국회의원 등 선출직 공무원을 임기 중이라도 투표를 통해 파면시킬 수 있는 제도이다.\n현행 헌법과 법률에는 국회의원 탄핵이나 소환에 대한 내용은 없다. 이에 문재인 정부는 개헌을 통해, 국회는 입법을 통해 국회의원에 대한 국민소환제 도입을 추진하고 있다. ",
                "국회의원을 한번 뽑고 나서는 국회의원이 어떤 행동을 해도 국민들이 직접 영향력을 행사할 수 있는 제도가 될 것이다.",
                "대의민주주의 상징인 국회의 역할과 위상이 추락해 의회민주주의의 후퇴를 가져올 수 있으며 지나치게 잦은 투표가 국민 혈세의 낭비를 초래할 수 있다.",
                "2017.08.11",
                "423","299","362") ;
        adapter.addItem(R.drawable.listen_moon100, "文 대통령 ‘100일 기자회견’ 무엇이 옳은 걸까?",
                "17일 문재인 대통령의 취임 100일 기자회견 내용에 대한 평가를 두고 여야의 입장차가 확연히 드러난 가운데 각본없이 진행된 기자회견 속 문재인 대통령이 보여준 대답들엔 어떤 것들이 있었는지 관심이 쏠리고 있다.",
                "참모들과 활발히 소통하고, 공약사항을 성실히 지키기 위한 문재인 대통령의 국정운영 행보를 여실히 보여주었다.",
                "문재인 정부는 오만과 독선, 포퓰리즘과 아마추어리즘이며 100일의 시간동안 정신없이 많은 것을 쏟아냈지만 그때마다 사회혼란과 국민갈등은 심화됐을 뿐이다.",
                "2017.08.17",
                "423","299","362") ;

        listview.setAdapter(adapter);

        // inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.custom_header, listview, false);
        listview.addHeaderView(header, null, false);



        // we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage);
        ImageView del = (ImageView) header.findViewById(R.id.listHeaderImage_help);
        del.setVisibility(View.GONE);
        listview.setOnScrollListener(this);


         //위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                Intent intentRow = new Intent(CitizenListenActivity.this, CitizenListenContentActivity.class);

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
