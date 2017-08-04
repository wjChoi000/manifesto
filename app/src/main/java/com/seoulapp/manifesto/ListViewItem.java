package com.seoulapp.manifesto;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String agStr ;
    private String opStr ;
    private String comStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
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

    public Drawable getIcon() {
        return this.iconDrawable ;
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
