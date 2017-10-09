package com.seoulapp.manifesto;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.tsengvn.typekit.Typekit;

//import com.kakao.auth.KakaoSDK;
/**
 * Created by swelo on 2017-09-30.
 */

public class GlobalApplication extends Application {
    private static volatile GlobalApplication obj = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        KakaoSDK.init(new KakaoSDKAdapter());

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/NotoSans-Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/NotoSans_Bold.ttf"));
        //                .addNormal(Typekit.createFromAsset(this, "fonts/SeoulNamsanB.ttf"))
        //                .addBold(Typekit.createFromAsset(this, "fonts/SeoulNamsanEB.ttf"));
    }

    public static GlobalApplication getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }
}
