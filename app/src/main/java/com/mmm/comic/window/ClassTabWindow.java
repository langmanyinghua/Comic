package com.mmm.comic.window;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mmm.comic.R;
import com.mmm.comic.bean.TabBead;
import com.mmm.comic.util.DensityUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by link on 2018/6/21.
 */

public class ClassTabWindow {
    private Context context;
    private static ClassTabWindow instance;
    private RecyclerView mRecyclerView;
    private int index;
    private PopupWindow mPopupWindow;

    public ClassTabWindow(Context context) {
        this.context = context;
        init();
    }

    public static ClassTabWindow getInstance(Context context) {
        if (instance == null)
            instance = new ClassTabWindow(context);
        return instance;
    }


    private void init() {
        View cView = View.inflate(context, R.layout.class_tab_head, null);
        mRecyclerView = cView.findViewById(R.id.class_tb_recyclerview);
        RadioButton mTypeAll = cView.findViewById(R.id.tab_type_all);
        RadioButton mCostAll = cView.findViewById(R.id.tab_cost_all);
        RadioButton mTypeHot = cView.findViewById(R.id.tab_type_hot);
        mTypeAll.setChecked(true);
        mCostAll.setChecked(true);
        mTypeHot.setChecked(true);
        mPopupWindow = new PopupWindow(cView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        setData();
    }


    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        final List<TabBead> textList = new ArrayList<>();
        textList.add(new TabBead("全部", true));
        textList.add(new TabBead("霸总", false));
        textList.add(new TabBead("修真", false));
        textList.add(new TabBead("恋爱", false));
        textList.add(new TabBead("校园", false));
        textList.add(new TabBead("冒险", false));
        textList.add(new TabBead("搞笑", false));
        textList.add(new TabBead("生活", false));
        textList.add(new TabBead("热血", false));
        textList.add(new TabBead("架空", false));
        textList.add(new TabBead("后宫", false));
        index = 0;
        CommonAdapter<TabBead> tAdapter = new CommonAdapter<TabBead>(context, R.layout.adapter_tab_text, textList) {
            @Override
            protected void convert(ViewHolder holder, final TabBead item, final int position) {
                final TextView mButton = holder.getView(R.id.tab_text);
                mButton.setText(item.getText());
                isCheck(mButton, item.isCheck());
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!item.isCheck()) {
                            item.setCheck(true);
                            textList.get(index).setCheck(false);
                            notifyItemChanged(index);
                            notifyItemChanged(position);
                            index = position;
                        }
                    }
                });
            }

        };
        mRecyclerView.setAdapter(tAdapter);
    }

    private void isCheck(TextView mButton, boolean isCheck) {
        if (isCheck) {
            mButton.setBackgroundResource(R.drawable.shape_class_tab_bg_p);
            mButton.setTextColor(context.getResources().getColor(R.color.mkz_red));
        } else {
            mButton.setBackgroundResource(R.drawable.shape_class_tab_bg_n);
            mButton.setTextColor(context.getResources().getColor(R.color.mkz_black2));
        }
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
