package com.mmm.comic;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.view.View;

import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.network.Helper;
import com.mmm.comic.util.MessageDigestUtil;
import com.mmm.comic.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/6.
 */
public class APP extends Application {
    private static final String TAG = APP.class.getSimpleName();
    public static APP application;
    public List<ComicBean> comicList;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 初始化session
        Helper.uuid = MessageDigestUtil.md5(SystemUtil.getAndroidId(APP.application));
        Helper.start();

        initData();
    }

    public void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void initData(){
        comicList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ComicBean comic = new ComicBean();
            comic.setTitle("怦然心动");
            comic.setThumb("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg");
            comic.setAuthor("kid岁");
            comic.setDetail("少男少女同住一个屋檐下，一同历经青春的懵懂和烦恼。画风温馨的超人气少女漫，感受青春悸动的萌芽，这一部就够！【独家/每周二更新，责编：00】");
            comic.setHeat("148.36亿");
            comic.setTag("青春");
            comicList.add(comic);
        }
    }
}
