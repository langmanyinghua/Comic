package com.mmm.comic.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.util.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {
    private static final String TAG = PayActivity.class.getSimpleName();
    private LoadingDialog loadingDialog;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);

        setContentView(R.layout.activity_pay);
        loadingDialog = new LoadingDialog(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.show();
            }
        }, 100);

        newWebView(getIntent().getStringExtra("url"));
    }

    /**
     * 创建一个 webview
     *
     * @param url
     */
    public void newWebView(final String url) {
        WebView webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);//设置支持缩放
        settings.setDefaultTextEncodingName("UTF-8");

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("weixin://wap/pay?")) {
                    startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), 1000);
                    return true;
                } else if (url.startsWith("alipays://platformapi/startApp?")) {
                    startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), 1000);
                    return true;
                } else {
                    Map<String, String> extraHeaders = new HashMap<String, String>();
                    extraHeaders.put("Referer", "http://weixin.xy0ay.cn/");
                    view.loadUrl(url, extraHeaders);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.hide();
                    }
                }, 1500);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
                // 重写此方法可以让webview处理https请求
                handler.proceed();
            }
        };
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode, data);
        finish();
    }
}