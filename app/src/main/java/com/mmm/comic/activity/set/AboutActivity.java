package com.mmm.comic.activity.set;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_about);

        ((TextView)findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_about_us_title));
    }

    public void onClickBack(View v) {
        finish();
    }
}
