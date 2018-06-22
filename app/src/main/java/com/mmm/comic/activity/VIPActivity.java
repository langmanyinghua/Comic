package com.mmm.comic.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.base.CommonAdapter;
import com.mmm.comic.base.ViewHolder;
import com.mmm.comic.bean.GameOption;
import com.mmm.comic.network.Helper;
import com.mmm.comic.network.HttpCallBack;
import com.mmm.comic.util.Constant;
import com.mmm.comic.util.HttpUtil;
import com.mmm.comic.util.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class VIPActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private LoadingDialog loadingDialog = null;

    private ListView vip_price_list;
    private CommonAdapter commonAdapter;
    private TextView btn_pay;
    private int postation = 1;
    private String pm = "wx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_activity_charge_vip);
        initView();

        if (Constant.GameOptionList.size() > 0) {
            initData();
        } else {
            Helper.start();
            loadingDialog.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.hide();
                    initData();
                }
            }, 1500);
        }
        initEvent();
    }

    /**
     * 初始化 视图
     */
    private void initView() {
        ((TextView) findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_account_charge_vip));

        loadingDialog = new LoadingDialog(this);
        vip_price_list = (ListView) findViewById(R.id.vip_price_list);
        btn_pay = (TextView) findViewById(R.id.btn_pay);
    }

    private void initData() {
        vip_price_list.setAdapter(commonAdapter = new CommonAdapter<GameOption>(this, Constant.GameOptionList, R.layout.mkz_vip_price_item) {
            @Override
            public void convert(ViewHolder helper, GameOption item) {
                if (helper.getPosition() != 0) {
                    helper.getView(R.id.tv_tag).setVisibility(View.GONE);
                    helper.getView(R.id.root_rl).setBackgroundColor(Color.TRANSPARENT);
                } else {
                    helper.getView(R.id.root_rl).setBackground(getResources().getDrawable(R.drawable.payoptation_list_item_bg));
                }
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_price, "¥ " + item.getAmount());
                helper.setText(R.id.tv_discount, item.getMemo());
            }
        });
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        // item 事件
        vip_price_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                postation = Constant.GameOptionList.get(position).getId();
                for (int i = 0; i < parent.getChildCount(); i++) {
                    RelativeLayout root_rl = parent.getChildAt(i).findViewById(R.id.root_rl);
                    root_rl.setBackgroundColor(Color.TRANSPARENT);
                }
                RelativeLayout root_rl = view.findViewById(R.id.root_rl);
                root_rl.setBackground(getResources().getDrawable(R.drawable.payoptation_list_item_bg));
            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();
                Helper.order(postation, pm, new HttpCallBack() {
                    @Override
                    public void callback(Object object) {
                        Log.i("callback", object + "  ");
                        newWebView(object.toString());
                    }
                });
            }
        });
        findViewById(R.id.wechat_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm = "wx";
                ((ImageView) findViewById(R.id.wechat_choose_iv)).setImageResource(R.drawable.pay_choose);
                ((ImageView) findViewById(R.id.alipay_choose_iv)).setImageResource(R.drawable.pay_no_choose);
            }
        });
        findViewById(R.id.alipay_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm = "ali";
                ((ImageView) findViewById(R.id.wechat_choose_iv)).setImageResource(R.drawable.pay_no_choose);
                ((ImageView) findViewById(R.id.alipay_choose_iv)).setImageResource(R.drawable.pay_choose);
            }
        });
    }

    /**
     * 创建一个 webview 执行支付操作
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

    /**
     * 返回
     *
     * @param v
     */
    public void onClickBack(View v) {
        finish();
    }
}
