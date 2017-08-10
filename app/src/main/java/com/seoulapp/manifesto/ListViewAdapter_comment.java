package com.seoulapp.manifesto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter_comment extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem_comment> listViewItemList_comment = new ArrayList<ListViewItem_comment>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter_comment() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList_comment.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_comment, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView idTextView = (TextView) convertView.findViewById(R.id.tv_comment_id) ;
        TextView dateTextView = (TextView) convertView.findViewById(R.id.tv_Date) ;
        TextView contentTextView = (TextView) convertView.findViewById(R.id.tv_comment_content) ;
        TextView agTextView = (TextView) convertView.findViewById(R.id.tv_c_ag) ;
        TextView opTextView = (TextView) convertView.findViewById(R.id.tv_c_op) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem_comment listViewItem_comment = listViewItemList_comment.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        idTextView.setText(listViewItem_comment.getId());
        dateTextView.setText(listViewItem_comment.getDate());
        contentTextView.setText(listViewItem_comment.getCont());
        agTextView.setText(listViewItem_comment.getAgStr());
        opTextView.setText(listViewItem_comment.getOpStr());


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList_comment.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String id, String agStr, String opStr, String cdate, String cont) {
        ListViewItem_comment item = new ListViewItem_comment();

        item.setId(id);
        item.setAgStr(agStr);
        item.setOpStr(opStr);
        item.setDate(cdate);
        item.setCont(cont);

        listViewItemList_comment.add(item);
    }

}
