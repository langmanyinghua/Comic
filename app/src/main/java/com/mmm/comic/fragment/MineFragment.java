package com.mmm.comic.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmm.comic.R;
import com.mmm.comic.activity.AccountActivity;
import com.mmm.comic.activity.VIPActivity;
import com.mmm.comic.activity.set.KeFuActivity;
import com.mmm.comic.activity.set.HelpActivity;
import com.mmm.comic.activity.set.SetActivity;
import com.mmm.comic.util.ToastUtil;

/**
 * Created by Administrator on 2017/2/8.
 */
public class MineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        setEvent(view);
        return view;
    }

    public void setEvent(View view) {
        // VIP
        view.findViewById(R.id.vip_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VIPActivity.class));
            }
        });

        // 账户
        view.findViewById(R.id.account_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountActivity.class));
            }
        });

        // 问题反馈
        view.findViewById(R.id.feedback_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KeFuActivity.class));
            }
        });

        // 评价
        view.findViewById(R.id.rate_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    ToastUtil.showToast("您的手机没有安装Android应用市场");
                    e.printStackTrace();
                }

            }
        });

        // 帮助
        view.findViewById(R.id.help_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
            }
        });

        // 设置
        view.findViewById(R.id.setting_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SetActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
