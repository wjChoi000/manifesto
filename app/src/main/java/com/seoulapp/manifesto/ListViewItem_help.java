package com.seoulapp.manifesto;


import android.graphics.Bitmap;

public class ListViewItem_help {
    private Bitmap titleImage;
    private String titleStr ;
    private String subcontext;
    private String c_date;
    private String goodStr;
    private String hitStr;
    private String comStr ;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setComment(String comment) {
        comStr = comment ;
    }
    public void setGoodStr(String goodStr) {
        this.goodStr = goodStr;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getComment() {
        return this.comStr ;
    }
    public String getGoodStr() {
        return goodStr;
    }

    public Bitmap getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(Bitmap titleImage) {
        this.titleImage = titleImage;
    }

    public String getSubcontext() {
        return subcontext;
    }

    public void setSubcontext(String subcontext) {
        this.subcontext = subcontext;
    }

    public String getHitStr() {
        return hitStr;
    }

    public void setHitStr(String hitStr) {
        this.hitStr = hitStr;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
