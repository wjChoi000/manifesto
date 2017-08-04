package com.seoulapp.manifesto;

import android.graphics.drawable.Drawable;

public class ListViewItem_help {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String comStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
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

    public String getComment() {
        return this.comStr ;
    }

}
