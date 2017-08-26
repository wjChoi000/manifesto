package com.seoulapp.manifesto;

import android.graphics.drawable.Drawable;

public class ListViewItem_help {
    private int titleImage;
    private String titleStr ;
    private String comStr ;
    private String goodStr;


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

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
