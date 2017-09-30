package com.seoulapp.manifesto.model;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by swelo on 2017-09-28.
 */

public class Citizen implements Serializable{
//
    private int id;
    private int u_id;
    private String title;
    private String Category;
    private String Comment;
    private int good;
    private int bad;
    private int hit;
    private String create_date;
    private String agree;
    private String opposite;
    private int count;
    private String gu;
    private String priture;

    public Citizen(int id, int u_id, String title, String category, String comment, int good, int bad, int hit, String create_date, String agree, String opposite, int count, String priture) {
        this.id = id;
        this.u_id = u_id;
        this.title = title;
        Category = category;
        Comment = comment;
        this.good = good;
        this.bad = bad;
        this.hit = hit;
        this.create_date = create_date;
        this.agree = agree;
        this.opposite = opposite;
        this.count = count;
        this.priture =priture;
    }

    public Citizen(int id, int u_id, String title, String category, String comment, int good, int hit, String create_date, int count,String priture) {
        this.id = id;
        this.u_id = u_id;
        this.title = title;
        Category = category;
        Comment = comment;
        this.good = good;
        this.hit = hit;
        this.create_date = create_date;
        this.count = count;
        this.priture =priture;
    }

    public Citizen(int id, int u_id, String title, String category, String comment, int good, int hit, String create_date, int count, String gu,String priture) {
        this.id = id;
        this.u_id = u_id;
        this.title = title;
        Category = category;
        Comment = comment;
        this.good = good;
        this.hit = hit;
        this.create_date = create_date;
        this.count = count;
        this.gu = gu;
        this.priture =priture;
    }

    public static Citizen convertJsonToListen(JSONObject jsonObject){
        try {
            Citizen say = new Citizen(jsonObject.getInt("id"), jsonObject.getInt("u_id"), jsonObject.getString("title"),
                    jsonObject.getString("category"), jsonObject.getString("comments"), jsonObject.getInt("good"),
                    jsonObject.getInt("bad"), jsonObject.getInt("hits"), jsonObject.getString("create_date"), jsonObject.getString("agree"),
                    jsonObject.getString("opposite"),jsonObject.getInt("count"),jsonObject.getString("priture"));
            return say;
        }catch (Exception e){
            Log.i("Citizen","listen convert error",e);
            return null;
        }
    };

    public static Citizen convertJsonToHelp(JSONObject jsonObject){
        try {
            Citizen say = new Citizen(jsonObject.getInt("id"), jsonObject.getInt("u_id"), jsonObject.getString("title"),
                    jsonObject.getString("category"), jsonObject.getString("comments"), jsonObject.getInt("good"),
                    jsonObject.getInt("hits"), jsonObject.getString("create_date"), jsonObject.getInt("count")
                    ,jsonObject.getString("priture"));
            return say;
        }catch (Exception e){
            Log.i("Citizen","Help convert error",e);
            return null;
        }
    };

    public static Citizen convertJsonToNeed(JSONObject jsonObject){
        try {
            Citizen say = new Citizen(jsonObject.getInt("id"), jsonObject.getInt("u_id"), jsonObject.getString("title"),
                    jsonObject.getString("category"), jsonObject.getString("comments"), jsonObject.getInt("good"),
                    jsonObject.getInt("hits"), jsonObject.getString("create_date"), jsonObject.getInt("count"),
                    jsonObject.getString("gu"),jsonObject.getString("priture"));
            return say;
        }catch (Exception e){
            Log.i("Citizen","Need convert error",e);
            return null;
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getOpposite() {
        return opposite;
    }

    public void setOpposite(String opposite) {
        this.opposite = opposite;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getPriture() {
        return priture;
    }

    public void setPriture(String priture) {
        this.priture = priture;
    }
}
