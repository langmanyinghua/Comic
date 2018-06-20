package com.mmm.comic.activity.set;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.util.Constant;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class HelpWebActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progress;
    private String title = "标题";
    private String url = Constant.URI.PLAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_activity_webview);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("title")))
            title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(getIntent().getStringExtra("url")))
            url = getIntent().getStringExtra("url");

        ((TextView) findViewById(R.id.naviga_title)).setText(title);

        webView = (WebView) findViewById(R.id.webview);
        progress = (ProgressBar) findViewById(R.id.progress);


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);//设置支持缩放

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progress.setProgress(newProgress);//设置进度值
                }
            }
        });
        webView.loadUrl(url);
    }

    /**
     * 返回
     *
     * @param view
     */
    public void onClickBack(View view) {
        finish();
    }
}
