package com.seoulapp.manifesto;

public class ListViewItem_comment {
    private String idStr ;
    private String contStr ;
    private String dateStr;
    private String agStr;
    private String opStr;

    public void setId(String id) {idStr = id ;}

    public void setCont(String cont) {
        contStr = cont ;
    }

    public void setDate(String cdate){
        dateStr = cdate;
    }

    public String getId() {
        return this.idStr ;
    }

    public String getCont() {
        return this.contStr ;
    }

    public String getDate(){
        return this.dateStr;
    }

    public String getAgStr() {
        return agStr;
    }

    public void setAgStr(String agStr) {
        this.agStr = agStr;
    }

    public String getOpStr() {
        return opStr;
    }

    public void setOpStr(String opStr) {
        this.opStr = opStr;
    }
}
