package com.seoulapp.manifesto;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private int titleImage;
    private String titleStr ;
    private String agStr ;
    private String opStr ;
    private String comStr ;

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setAg(String ag) {
        agStr = ag ;
    }

    public void setOp(String op) {
        opStr = op ;
    }

    public void setComment(String comment) {
        comStr = comment ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getAg() {
        return this.agStr ;
    }

    public String getOp() {
        return this.opStr ;
    }

    public String getComment() {
        return this.comStr ;
    }

}
