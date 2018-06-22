package com.mmm.comic.window;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.mmm.comic.R;
import com.mmm.comic.util.DensityUtil;

/**
 * Created by link on 2018/6/22.
 */

public class RankTabWindow {
    private Context context;
    private static RankTabWindow instance;
    private RecyclerView mRecyclerView;
    private int index;
    private PopupWindow mPopupWindow;

    public RankTabWindow(Context context) {
        this.context = context;
        init();
    }

    public static RankTabWindow getInstance(Context context) {
        if (instance == null)
            instance = new RankTabWindow(context);
        return instance;
    }


    private void init() {
        View cView = View.inflate(context, R.layout.rank_head_view, null);
        RadioButton mRbPopular = cView.findViewById(R.id.rank_popular);
        RadioButton mRbAscension = cView.findViewById(R.id.rank_ascension);
        RadioButton mRbCollect = cView.findViewById(R.id.rank_collection);
        RadioButton mRbExclusive = cView.findViewById(R.id.rank_exclusive);
        RadioButton mRbLatest = cView.findViewById(R.id.rank_latest);
        RadioButton mRbTicket = cView.findViewById(R.id.rank_ticket);
        mPopupWindow = new PopupWindow(cView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
    }


    public void onShow(View view) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            if (Build.VERSION.SDK_INT < 24) {
                mPopupWindow.showAsDropDown(view, 0, DensityUtil.px2dip(context, view.getHeight()) / 4);
            } else {
                int[] location = new int[2];  // 获取控件在屏幕的位置
                view.getLocationOnScreen(location);
                if (Build.VERSION.SDK_INT == 25) {
                    int tempheight = mPopupWindow.getHeight();
                    int screenHeight = DensityUtil.screenHeight(context);
                    if (tempheight == WindowManager.LayoutParams.MATCH_PARENT || screenHeight <= tempheight) {
                        mPopupWindow.setHeight(screenHeight - location[1] - view.getHeight());
                    }
                    mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + view.getHeight());
                } else {
                    mPopupWindow.showAsDropDown(view, Gravity.NO_GRAVITY, location[0], -location[1] + view.getHeight());
                }
            }
        }
    }

    public void onDismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
