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
import java.util.Collections;
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
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void initData() {
        comicList = new ArrayList<>();
        // 1
        ComicBean comic1 = new ComicBean();
        comic1.setTitle("斗破苍穹");
        comic1.setThumb("https://oss.mkzcdn.com/comic/cover/20180412/5acebcc67f6f0-600x800.!cover-400-x");
        comic1.setUpdatetag("更新至700话");
        comic1.setHeat("5.54亿");
        comic1.setDetail("作品简介：这是一个属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！在斗气大陆上，真正的强者都是用实力说话的！");
        comic1.setTag("热血 玄幻 动作");
        comicList.add(comic1);

        // 2
        ComicBean comic2 = new ComicBean();
        comic2.setTitle("绝世武神");
        comic2.setThumb("https://oss.mkzcdn.com/comic/cover/20170824/599e35b1101a9-420x560.jpg!cover-400-x");
        comic2.setUpdatetag("更新至98话");
        comic2.setHeat("2.64亿");
        comic2.setDetail("作品简介：武道，决定命运，决定生死，弱者，受人欺凌，强者，俯瞰天下。这是一个恃强凌弱的世界，人们以武道修为来定等级论高低。各大门派为了争夺最强之名，一直明争暗斗。在这里，只有强者有权利生存下去，即使是同族同门之人，若是实力弱小，也只有被驱赶欺凌的下场。");
        comic2.setTag("热血");
        comicList.add(comic2);

        // 3
        ComicBean comic3 = new ComicBean();
        comic3.setTitle("偷星九月天");
        comic3.setThumb("https://oss.mkzcdn.com/comic/cover/20170526/59284be2e3ed1-600x800.jpg!cover-400-x");
        comic3.setUpdatetag("更新至700话");
        comic3.setHeat("5.54亿");
        comic3.setDetail("作品简介：一场爱与梦想的奇妙冒险…… 　　是男仆还是热血的少年侦探？江洋大盗竟是如花美眷？！ 迷雾一层接一层，悬念一环紧接一环，喘不过气了? 黑月铁骑魅影闪现，是兄妹的情谊还是死敌的较量？");
        comic3.setTag("搞笑 热血 玄武");
        comicList.add(comic3);

        // 4
        ComicBean comic4 = new ComicBean();
        comic4.setTitle("斗罗大陆2绝世唐门");
        comic4.setThumb("https://oss.mkzcdn.com/comic/cover/20180330/5abdb2a19b6fd-600x800.!cover-400-x");
        comic4.setUpdatetag("更新至151话");
        comic4.setHeat("1.3亿");
        comic4.setDetail("作品简介：斗罗大陆2绝世唐门简介这里没有魔法，没有斗气，没有武术，却有武魂。唐门创立万年之后的斗罗大陆上，唐门式微。一代天骄横空出世，新一代史莱克七怪能否重振唐门，谱写一曲绝世唐门之歌百万年魂兽，手握日月摘星辰的死灵圣法神，导致唐门衰落的全新魂导器体系。一切的神奇都将一一展现。唐门暗器能否重振雄风，唐门能否重现辉煌，一切尽在绝世唐门！");
        comic4.setTag("热血 玄幻");
        comicList.add(comic4);

        comicList.addAll(comicList);
        comicList.addAll(comicList);
        comicList.addAll(comicList);

        Collections.shuffle(comicList);
    }
}
