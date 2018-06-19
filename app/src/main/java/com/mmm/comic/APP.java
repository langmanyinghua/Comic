package com.mmm.comic;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.view.View;

import com.mmm.comic.network.Helper;
import com.mmm.comic.util.MessageDigestUtil;
import com.mmm.comic.util.SystemUtil;

/**
 * Created by 浪漫樱花 on 2018/6/6.
 */
public class APP extends Application {
    private static final String TAG = APP.class.getSimpleName();
    public static APP application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 初始化session
        Helper.uuid = MessageDigestUtil.md5(SystemUtil.getAndroidId(APP.application));
        Helper.start();
    }

    public void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
