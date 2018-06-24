package com.mmm.comic.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.set.SetActivity;
import com.mmm.comic.network.Helper;
import com.mmm.comic.util.Constant;
import com.mmm.comic.util.PreferenceUtils;
import com.mmm.comic.util.ToastUtil;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private int INTERNET_CODE = 1000;
    private int TIME = 4;
    private TextView count_down_tv;
    private ValueAnimator valueAnimator;
    private Boolean isJump = false;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_splash);
        count_down_tv = (TextView) findViewById(R.id.count_down_tv);
        setOnClick();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                // 无网络权限
                requestPermissions(new String[]{Manifest.permission.INTERNET}, INTERNET_CODE);
            } else {
                starAnimator(count_down_tv);
            }
        } else {
            starAnimator(count_down_tv);
        }
    }

    private void setOnClick() {
        count_down_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJump = true;
                toMain();
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == INTERNET_CODE) {
            if (permissions[0].equals(Manifest.permission.INTERNET) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                starAnimator(count_down_tv);
            } else {
                ToastUtil.showToast("授权失败");
            }
        }
    }

    public void toMain() {
        Helper.start();
        if (PreferenceUtils.getPrefBoolean(this, Constant.IS_FIST, true)) {
            // 第一次打开app
            PreferenceUtils.setPrefBoolean(this, Constant.IS_FIST, false);
            startActivity(new Intent(SplashActivity.this, IntroduceActivity.class));
        } else {
            // 非第一次打开app
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }
        finish();
    }


    //验证码倒计时
    public void starAnimator(final TextView textview) {
        valueAnimator = ValueAnimator.ofInt(TIME, 0);
        valueAnimator.setDuration(TIME * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                textview.setText(value + " 跳转");
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isJump) toMain();
            }
        });
        valueAnimator.start();
    }
}
