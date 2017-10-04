package com.seoulapp.manifesto.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wjcho on 2017-07-07.
 */

public class KnowContent implements Serializable{
    private int id;
    private String title;
    private String contents;
    private int goodSum;
    private int commentSum;
    private int hits;
    private String create_date;
    private String priture;


    public KnowContent(int id, String title, String contents, int goodSum, int commentSum, int hits, String create_date, String priture) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.goodSum = goodSum;
        this.commentSum = commentSum;
        this.hits = hits;
        this.create_date = create_date;
        this.priture = priture;
    }

    public static KnowContent convertJsonToKnowledge(JSONObject jsonObject){
        try {
            KnowContent say = new KnowContent(jsonObject.getInt("id"), jsonObject.getString("title"),
                    jsonObject.getString("contents"), jsonObject.getInt("good"),jsonObject.getInt("comment_num"),
                    jsonObject.getInt("hits"), jsonObject.getString("create_date"), jsonObject.getString("priture"));
            return say;
        }catch (Exception e){
            Log.i("knowledge","knoweldge convert error",e);
            return null;
        }
    };


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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPriture() {
        return priture;
    }

    public void setPriture(String priture) {
        this.priture = priture;
    }
}
