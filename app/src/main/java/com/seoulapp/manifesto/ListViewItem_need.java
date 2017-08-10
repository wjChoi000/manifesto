package com.seoulapp.manifesto;

public class ListViewItem_need {
    private String need_titleStr ;
    private String cateStr ;
    private String guStr;
    private String goodStr;
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
}
