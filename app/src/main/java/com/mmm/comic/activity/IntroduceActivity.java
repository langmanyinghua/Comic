package com.mmm.comic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmm.comic.APP;
import com.mmm.comic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class IntroduceActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private Handler handler = new Handler();
    private LinearLayout viewGroup;
    private Integer[] mImages = new Integer[]{
            R.drawable.mkz_pic_boot_img1,
            R.drawable.mkz_pic_boot_img2,
            R.drawable.mkz_pic_boot_img3,
            R.drawable.mkz_pic_boot_img4
    };
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_introduce);

        view_pager = (ViewPager) findViewById(R.id.viewPager);
        viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
        addView(viewGroup);

        initData();
        iniEvent();
    }

    public void initData() {
        imageViewList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImages[i]);
            imageViewList.add(imageView);
        }
    }

    private void iniEvent() {
        view_pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int lastPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                // 页面被选中
                // 设置当前页面选中
                viewGroup.getChildAt(position).setSelected(true);
                // 设置前一页不选中
                viewGroup.getChildAt(lastPosition).setSelected(false);
                // 替换位置
                lastPosition = position;

                if (position == imageViewList.size()-1) {
                    findViewById(R.id.tv_follow).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.tv_follow).setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /**
         * 进入
         */
        findViewById(R.id.tv_follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroduceActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void addView(LinearLayout pointGroup) {
        for (int i = 0; i < mImages.length; i++) {
            // 制作底部小圆点
            ImageView pointImage = new ImageView(this);
            pointImage.setImageResource(R.drawable.shape_point_selector);

            // 设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);
            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            // 添加到容器里
            pointGroup.addView(pointImage);
        }
    }

}
