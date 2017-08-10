package com.seoulapp.manifesto.model;

import java.io.Serializable;

/**
 * Created by wjcho on 2017-07-07.
 */

public class KnowContent implements Serializable{
    private String title;
    private String contents;
    private int goodSum;
    private int commentSum;

    public KnowContent(String title, String contents, int goodSum,int commentSum){
        this.title = title;
        this.contents =contents;
        this.goodSum = goodSum;
        this.commentSum = commentSum;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getGoodSum() {
        return goodSum;
    }

    public int getCommentSum() {
        return commentSum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setGoodSum(int goodSum) {
        this.goodSum = goodSum;
    }

    public void setCommentSum(int commentSum) {
        this.commentSum = commentSum;
    }
}
