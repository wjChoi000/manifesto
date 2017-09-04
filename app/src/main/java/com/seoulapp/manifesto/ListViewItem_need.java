package com.seoulapp.manifesto;

public class ListViewItem_need {
    private String need_titleStr ;
    private String cateStr ;
    private String u_id;
    private String c_date;
    private String need_context;
    private String guStr;
    private String goodStr;
    private String hitNum;
    private String commentStr;

    public void setNeed_title(String need_title) {
        need_titleStr = need_title ;
    }

    public void setCate(String cate) {
        cateStr = cate ;
    }

    public void setGu(String gu){
        guStr = gu;
    }

    public String getNeed_title() {
        return this.need_titleStr ;
    }

    public String getCate() {
        return this.cateStr ;
    }

    public String getGu(){
        return this.guStr;
    }

    public String getGoodStr() {
        return goodStr;
    }

    public void setGoodStr(String goodStr) {
        this.goodStr = goodStr;
    }

    public String getCommentStr() {
        return commentStr;
    }

    public void setCommentStr(String commentStr) {
        this.commentStr = commentStr;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getNeed_context() {
        return need_context;
    }

    public void setNeed_context(String need_context) {
        this.need_context = need_context;
    }

    public String getHitNum() {
        return hitNum;
    }

    public void setHitNum(String hitNum) {
        this.hitNum = hitNum;
    }
}
