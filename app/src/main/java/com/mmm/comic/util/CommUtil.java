package com.mmm.comic.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import com.mmm.comic.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 浪漫樱花 on 2018/6/23.
 */
public class CommUtil {

    /**
     * 截取后面十位
     *
     * @param content
     * @return
     */
    public static String SubContent(String content) {
        if (TextUtils.isEmpty(content) || content.length() <= 10) {
            return content;
        } else {
            return content.substring(content.length() - 10, content.length());
        }
    }

    /**
     * 获取星期信息
     * @param step
     * @return
     */
    public static String GetWeek(int step) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        long day_conver = step * 1000 * 60 * 60 * 24;
        date.setTime(System.currentTimeMillis() - day_conver);
        c.setTime(date);

        int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
        String result = "";
        switch (weekday) {
            case 0:
                result = "周日";
                break;
            case 1:
                result = "周一";
                break;
            case 2:
                result = "周二";
                break;
            case 3:
                result = "周三";
                break;
            case 4:
                result = "周四";
                break;
            case 5:
                result = "周五";
                break;
            case 6:
                result = "周六";
                break;
        }
        return result;
    }

    /**
     * 设置文字的顶部图标
     * @param context
     * @param drawable
     * @param textView
     */
    public static void SetTextViewCompoundDrawableTop(Context context, int drawable, TextView textView){
        Drawable top = context.getResources().getDrawable(drawable);
        top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());// 一定要设置setBounds();
        textView.setCompoundDrawables(null, top, null, null);
    }
}
