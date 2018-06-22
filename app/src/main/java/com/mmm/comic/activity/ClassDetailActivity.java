package com.mmm.comic.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.adapter.ClassPagerAdapter;
import com.mmm.comic.bean.TabBead;
import com.mmm.comic.fragment.ClassCatalogFragment;
import com.mmm.comic.fragment.ClassDetailFragment;
import com.mmm.comic.fragment.ClassFragment;
import com.mmm.comic.fragment.IndexFragment;
import com.mmm.comic.fragment.MineFragment;
import com.mmm.comic.view.WrapContentHeightViewPager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by link on 2018/6/21.
 */

public class ClassDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView nestedScrollView;
    private RelativeLayout mHeadView;
    private LinearLayout mTabView;
    private RelativeLayout mTitleGoneView;
    private ImageButton mIbBackRed;
    private AlphaAnimation alphaAnimation;
    private ImageButton mIbDownRed;
    private ImageButton mIbShareRed;
    private TextView mTvTitleRed;
    private RadioButton mRbDetail;
    private RadioButton mRbCatalog;
    private RadioButton mRbDetailRed;
    private RadioButton mRbCatalogRed;
    private FragmentManager mfragmentManager;
    private ClassCatalogFragment catalongFragment;
    private ClassDetailFragment detailFragment;
    private FragmentTransaction mfragmentTransaction;
    private RadioGroup mRgTabView;
    private RadioGroup mRgRedView;
    private View statusView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_class_detail);
        init();
    }

    private void init() {
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mHeadView = (RelativeLayout) findViewById(R.id.class_head_view);
        mTitleGoneView = (RelativeLayout) findViewById(R.id.class_gone_title_view);
        mIbBackRed = (ImageButton) findViewById(R.id.class_ib_back_red);
        mIbDownRed = (ImageButton) findViewById(R.id.class_ib_down_red);
        mIbShareRed = (ImageButton) findViewById(R.id.class_tab_share_red);
        mTvTitleRed = (TextView) findViewById(R.id.class_tv_title_red);
        mRgTabView = (RadioGroup) findViewById(R.id.tab_view);
        statusView = findViewById(R.id.status_view);
        mRbDetail = mRgTabView.findViewById(R.id.tab_detail);
        mRbCatalog = mRgTabView.findViewById(R.id.tab_catalog);
        mRgRedView = (RadioGroup) findViewById(R.id.red_tab);
        mRbDetailRed = mRgRedView.findViewById(R.id.tab_detail);
        mRbCatalogRed = mRgRedView.findViewById(R.id.tab_catalog);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        mTabView = (LinearLayout) findViewById(R.id.class_tab_view);
        ImageView mIvHeadIcon = (ImageView) findViewById(R.id.class_detail_head_icon);
        Glide.with(this).load("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg").into(mIvHeadIcon);
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ClassDetailFragment());
        fragments.add(new ClassCatalogFragment());
        List<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(mRbDetail);
        radioButtons.add(mRbCatalog);
        List<RadioButton> radioButtonReds = new ArrayList<>();
        radioButtonReds.add(mRbDetailRed);
        radioButtonReds.add(mRbCatalogRed);
        mRbDetail.setChecked(true);
        mRbDetailRed.setChecked(true);

        mfragmentManager = getSupportFragmentManager();
        catalongFragment = (ClassCatalogFragment) mfragmentManager.findFragmentById(R.id.class_catalog_fragment);
        detailFragment = (ClassDetailFragment) mfragmentManager.findFragmentById(R.id.class_detail_fragment);
        switchFragment(detailFragment);
    }

    public void switchFragment(Fragment mfragment) {
        mfragmentTransaction = mfragmentManager.beginTransaction();
        mfragmentTransaction.hide(catalongFragment);
        mfragmentTransaction.hide(detailFragment);
        mfragmentTransaction.show(mfragment);
        mfragmentTransaction.commit();
    }

    private void initEvent() {
        findViewById(R.id.class_ib_back).setOnClickListener(this);
        mIbBackRed.setOnClickListener(this);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if
                        ((mHeadView.getHeight() - mTitleGoneView.getHeight() - statusView.getHeight()) > scrollY) {
                    if (mTabView.getVisibility() == View.VISIBLE) {
                        mTabView.setVisibility(View.GONE);
                    }
                } else if (mTabView.getVisibility() == View.GONE) {
                    mTabView.setVisibility(View.VISIBLE);
                    mIbBackRed.startAnimation(setAnimation());
                    mIbDownRed.startAnimation(setAnimation());
                    mIbShareRed.startAnimation(setAnimation());
                    mTvTitleRed.startAnimation(setTitleAnimaiont());
                }
            }
        });
        mRbDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(detailFragment);
                mRbDetailRed.setChecked(true);
            }
        });
        mRbDetailRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(detailFragment);
                mRbDetail.setChecked(true);
            }
        });
        mRbCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(catalongFragment);
                mRbCatalogRed.setChecked(true);
            }
        });
        mRbCatalogRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(catalongFragment);
                mRbCatalog.setChecked(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.class_ib_back:
            case R.id.class_ib_back_red:
                finish();
                break;
        }
    }

    private AlphaAnimation setAnimation() {
        //初始化
        if (alphaAnimation == null) {
            alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
            alphaAnimation.setDuration(500);
        }
        return alphaAnimation;
    }

    private Animation setTitleAnimaiont() {
        //初始化
        Animation translateAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f);
        //设置动画时间
        translateAnimation.setDuration(500);
        return translateAnimation;
    }
}
