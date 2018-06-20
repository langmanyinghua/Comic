package com.mmm.comic.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.set.AboutActivity;
import com.mmm.comic.util.ToastUtil;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class AccountActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_fragment_account);

        ((TextView) findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_account_title));
    }

    public void onClickBack(View v) {
        finish();
    }

}
