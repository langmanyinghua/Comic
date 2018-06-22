package com.mmm.comic.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.set.AboutActivity;
import com.mmm.comic.fragment.AccountFragment;
import com.mmm.comic.fragment.IndexItemFragment;
import com.mmm.comic.util.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class AccountActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private TabLayout account_tablayout;
    private List<String> listTitles = null;
    private ArrayList<AccountFragment> fragments = null;

    private Handler handler = new Handler();


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_fragment_account);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        ((TextView) findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_account_title));

        view_pager = (ViewPager) findViewById(R.id.view_pager);
        account_tablayout = (TabLayout) findViewById(R.id.account_tablayout);
    }

    private void initData() {
        listTitles = new ArrayList<>();
        listTitles.add("元宝");
        listTitles.add("VIP");
        listTitles.add("月票");
        fragments = new ArrayList<>();
        fragments.add(new AccountFragment());
        fragments.add(new AccountFragment());
        fragments.add(new AccountFragment());
    }

    private void initEvent() {
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return listTitles.get(position);
            }
        });
        account_tablayout.setupWithViewPager(view_pager);


        findViewById(R.id.vip_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, VIPActivity.class));
            }
        });
        findViewById(R.id.yuanbao_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, VIPActivity.class));
            }
        });
        findViewById(R.id.yuepiao_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, VIPActivity.class));
            }
        });
    }


    public void onClickBack(View v) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               // setIndicator(account_tablayout, 0, 50);
            }
        }, 500);
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
