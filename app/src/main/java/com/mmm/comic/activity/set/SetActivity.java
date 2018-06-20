package com.mmm.comic.activity.set;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.util.ToastUtil;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class SetActivity extends AppCompatActivity {

    private TextView reel;
    private TextView pager;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_set);

        ((TextView) findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_setting_title));

        reel = (TextView) findViewById(R.id.reel);
        reel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reel.setSelected(true);
                pager.setSelected(false);
            }
        });
        pager = (TextView) findViewById(R.id.pager);
        pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reel.setSelected(false);
                pager.setSelected(true);
            }
        });
        pager.setSelected(true);
    }

    public void onClickAbout(View v) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void onClickUpdate(View v) {
        ToastUtil.showToast("当前为最新版");
    }

    public void onClickClearCache(View v) {
        ToastUtil.showToast("已清空缓存");
    }

    public void onClickBack(View v) {
        finish();
    }
}
