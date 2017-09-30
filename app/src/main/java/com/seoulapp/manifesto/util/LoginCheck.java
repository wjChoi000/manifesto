package com.seoulapp.manifesto.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by swelo on 2017-09-30.
 */

public class LoginCheck {
    private SharedPreferences pref;

    public LoginCheck(Context context) {
        pref = context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }
    public boolean isItLogin(){
        return pref.getBoolean("login", false);
    }

    public int getID(){
        return pref.getInt("id", 0);
    }

    public String getNickname(){
        return pref.getString("nickname", "미등록");
    }

    public void login(int id,String nickname){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login", true);
        editor.putInt("id",id);
        editor.putString("nickname",nickname);
        editor.commit();
    }
    public void logout(){
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
