package com.seoulapp.manifesto;


import android.graphics.Bitmap;

public class ListViewItem {
    private Bitmap titleImage;
    private String titleStr ;
    private String subcontext;
    private String ag_context;
    private String op_context;
    private String c_date;
    private String agStr ;
    private String opStr ;
    private String comStr ;

    public Bitmap getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(Bitmap titleImage) {
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

    public String getSubcontext() {
        return subcontext;
    }

    public void setSubcontext(String subcontext) {
        this.subcontext = subcontext;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getAg_context() {
        return ag_context;
    }

    public void setAg_context(String ag_context) {
        this.ag_context = ag_context;
    }

    public String getOp_context() {
        return op_context;
    }

    public void setOp_context(String op_context) {
        this.op_context = op_context;
    }
}
