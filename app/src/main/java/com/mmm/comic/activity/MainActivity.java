package com.mmm.comic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.set.KeFuActivity;
import com.mmm.comic.network.Helper;
import com.mmm.comic.network.HttpCallBack;
import com.mmm.comic.util.APPType;
import com.mmm.comic.util.ChooseDialog;
import com.mmm.comic.util.Constant;
import com.mmm.comic.util.LoadingDialog;
import com.mmm.comic.util.PreferenceUtils;
import com.mmm.comic.util.ToastUtil;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView main_background;
    private ProgressBar progressBar;

    private Integer progress = 0;
    private Integer COUNT = 100;

    private RelativeLayout pb_rl;
    private RelativeLayout pay_rl;
    private RelativeLayout notice_rl;
    private TextView notice_content_tv;

    private Button commit_bt;

    private int postation = 1;

    private RelativeLayout one_rl;
    private ImageView one_icon;
    private LinearLayout one_border_ll;
    private TextView one_number;
    private TextView one_title_tv;
    private TextView one_sub_title_tv;

    private RelativeLayout two_rl;
    private ImageView two_icon;
    private LinearLayout two_border_ll;
    private TextView two_number;
    private TextView two_title_tv;
    private TextView two_sub_title_tv;

    private RelativeLayout three_rl;
    private ImageView three_icon;
    private LinearLayout three_border_ll;
    private TextView three_number;
    private TextView three_title_tv;
    private TextView three_sub_title_tv;

    private RelativeLayout four_rl;
    private ImageView four_icon;
    private LinearLayout four_border_ll;
    private TextView four_number;
    private TextView four_title_tv;
    private TextView four_sub_title_tv;

    public static MainActivity activity;
    private LoadingDialog loadingDialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (PreferenceUtils.getPrefBoolean(MainActivity.activity, Constant.IS_PAY, false)) {
                    paySuccess();
                } else {
                    pb_rl.setVisibility(View.GONE);
                    pay_rl.setVisibility(View.VISIBLE);
                   // main_background.setImageResource(R.drawable.pay);
                    setGameOption();
                }
            }
        }
    };

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_main);

        activity = this;
        loadingDialog = new LoadingDialog(this);
        if (TextUtils.isEmpty(Helper.session) || Constant.GameOptionList.size() == 0) {
            Helper.start();
        }
        initView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setProgressBar();
            }
        }, 500);
    }

    private void initView() {
        main_background = (ImageView) findViewById(R.id.main_background);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pb_rl = (RelativeLayout) findViewById(R.id.pb_rl);
        pb_rl.setVisibility(View.VISIBLE);

        pay_rl = (RelativeLayout) findViewById(R.id.pay_rl);
        pay_rl.setVisibility(View.GONE);
        notice_rl = (RelativeLayout) findViewById(R.id.notice_rl);
        notice_rl.setVisibility(View.GONE);

        commit_bt = (Button) findViewById(R.id.commit_bt);
        commit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.PM.size() == 1) {
                    createOrder(Constant.PM.get(0));
                } else if (Constant.PM.size() > 1) {
                    final ChooseDialog chooseDialog = new ChooseDialog(activity).builder();
                    chooseDialog.setOnClickListenerSure(new ChooseDialog.OnClickDialogListenerSure() {
                        @Override
                        public void onClick(int id) {
                            if (id == R.id.wechat_tv) {
                                createOrder("wx");
                            } else if (id == R.id.alipay_tv) {
                                createOrder("ali");
                            }
                            chooseDialog.hide();
                        }
                    });
                    chooseDialog.show();
                } else {
                    createOrder("wx");
                }
            }
        });

        notice_content_tv = (TextView) findViewById(R.id.content_tv);
        if (Constant.apptype == APPType.game) {
            notice_content_tv.setText(getString(R.string.notice_content_game));
        } else if (Constant.apptype == APPType.comic) {
            notice_content_tv.setText(getString(R.string.notice_content_comic));
        } else {
            notice_content_tv.setText(getString(R.string.notice_content_game));
        }
        // 客服
        findViewById(R.id.kefu_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, KeFuActivity.class));
            }
        });

        // 其他
        findViewById(R.id.other_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, WebActivity.class));
            }
        });

        // 付款 1
        one_rl = (RelativeLayout) findViewById(R.id.one_rl);
        one_icon = (ImageView) findViewById(R.id.one_icon);
        one_border_ll = (LinearLayout) findViewById(R.id.one_border_ll);
        one_number = (TextView) findViewById(R.id.one_number);
        one_title_tv = (TextView) findViewById(R.id.one_title_tv);
        one_sub_title_tv = (TextView) findViewById(R.id.one_sub_title_tv);
        one_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 1) {
                    postation = Constant.GameOptionList.get(0).getId();
                }
                switchPostation();
            }
        });

        // 付款 2
        two_rl = (RelativeLayout) findViewById(R.id.two_rl);
        two_icon = (ImageView) findViewById(R.id.two_icon);
        two_border_ll = (LinearLayout) findViewById(R.id.two_border_ll);
        two_number = (TextView) findViewById(R.id.two_number);
        two_title_tv = (TextView) findViewById(R.id.two_title_tv);
        two_sub_title_tv = (TextView) findViewById(R.id.two_sub_title_tv);
        two_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 2) {
                    postation = Constant.GameOptionList.get(1).getId();
                }
                switchPostation();
            }
        });

        // 付款 3
        three_rl = (RelativeLayout) findViewById(R.id.three_rl);
        three_icon = (ImageView) findViewById(R.id.three_icon);
        three_border_ll = (LinearLayout) findViewById(R.id.three_border_ll);
        three_number = (TextView) findViewById(R.id.three_number);
        three_title_tv = (TextView) findViewById(R.id.three_title_tv);
        three_sub_title_tv = (TextView) findViewById(R.id.three_sub_title_tv);
        three_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 3) {
                    postation = Constant.GameOptionList.get(2).getId();
                }
                switchPostation();
            }
        });

        // 付款 4
        four_rl = (RelativeLayout) findViewById(R.id.four_rl);
        four_icon = (ImageView) findViewById(R.id.four_icon);
        four_border_ll = (LinearLayout) findViewById(R.id.four_border_ll);
        four_number = (TextView) findViewById(R.id.four_number);
        four_title_tv = (TextView) findViewById(R.id.four_title_tv);
        four_sub_title_tv = (TextView) findViewById(R.id.four_sub_title_tv);
        four_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 4) {
                    postation = Constant.GameOptionList.get(3).getId();
                }
                switchPostation();
            }
        });


    }

    public void setGameOption() {
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 1) {
            one_number.setText(Constant.GameOptionList.get(0).getAmount());
            one_title_tv.setText(Constant.GameOptionList.get(0).getName());
            one_sub_title_tv.setText(Constant.GameOptionList.get(0).getMemo());
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 2) {
            two_number.setText(Constant.GameOptionList.get(1).getAmount());
            two_title_tv.setText(Constant.GameOptionList.get(1).getName());
            two_sub_title_tv.setText(Constant.GameOptionList.get(1).getMemo());
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 3) {
            three_number.setText(Constant.GameOptionList.get(2).getAmount());
            three_title_tv.setText(Constant.GameOptionList.get(2).getName());
            three_sub_title_tv.setText(Constant.GameOptionList.get(2).getMemo());
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 4) {
            four_number.setText(Constant.GameOptionList.get(3).getAmount());
            four_title_tv.setText(Constant.GameOptionList.get(3).getName());
            four_sub_title_tv.setText(Constant.GameOptionList.get(3).getMemo());
        }

        postation = 1;
        switchPostation();
    }

    /**
     * 设置进度条
     */
    private void setProgressBar() {
        new Thread() {
            @Override
            public void run() {
                int pro = 0;
                while (true) {
                    if (progress <= COUNT * 50 / 100) {
                        //前50% 快速
                        pro = rand.nextInt(10) * 2 * 4;
                    } else if (progress <= COUNT * 80 / 100) {
                        //后30% 正常
                        pro = rand.nextInt(10) * 5 * 4;
                    } else if (progress <= COUNT) {
                        //最后20% 较慢
                        pro = rand.nextInt(10) * 10 * 4;
                    } else {
                        handler.sendEmptyMessage(1);
                        break;
                    }
                    progress++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(pro);
                        Log.i(" progress ", progress + " " + pro);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    /**
     * 设置选项
     */
    public void switchPostation() {
        one_icon.setVisibility(View.GONE);
        one_border_ll.setBackgroundResource(R.drawable.custome_pay_item);

        two_icon.setVisibility(View.GONE);
        two_border_ll.setBackgroundResource(R.drawable.custome_pay_item);

        three_icon.setVisibility(View.GONE);
        three_border_ll.setBackgroundResource(R.drawable.custome_pay_item);

        four_icon.setVisibility(View.GONE);
        four_border_ll.setBackgroundResource(R.drawable.custome_pay_item);

        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 1) {
            if (postation == Constant.GameOptionList.get(0).getId()) {
                one_icon.setVisibility(View.VISIBLE);
                one_border_ll.setBackgroundResource(R.drawable.custome_pay_item_choose);
            }
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 2) {
            if (postation == Constant.GameOptionList.get(1).getId()) {
                two_icon.setVisibility(View.VISIBLE);
                two_border_ll.setBackgroundResource(R.drawable.custome_pay_item_choose);
            }
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 3) {
            if (postation == Constant.GameOptionList.get(2).getId()) {
                three_icon.setVisibility(View.VISIBLE);
                three_border_ll.setBackgroundResource(R.drawable.custome_pay_item_choose);
            }
        }
        if (Constant.GameOptionList != null && Constant.GameOptionList.size() >= 4) {
            if (postation == Constant.GameOptionList.get(3).getId()) {
                four_icon.setVisibility(View.VISIBLE);
                four_border_ll.setBackgroundResource(R.drawable.custome_pay_item_choose);
            }
        }
    }

    /**
     * 下单
     *
     * @param pm
     */
    public void createOrder(String pm) {
        loadingDialog.show();
        Helper.order(postation, pm, new HttpCallBack() {
            @Override
            public void callback(Object object) {
                Log.i("callback", object + "  ");
                startActivity(new Intent(MainActivity.activity, PayActivity.class).putExtra("url", object.toString()));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        APP.application.hideBottomUIMenu(this);
        Helper.order_status(new HttpCallBack() {
            @Override
            public void callback(Object object) {
                paySuccess();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadingDialog.hide();
    }

    /**
     * 支付成功
     */
    public void paySuccess() {
        // 如果支付过，直接跳转公告页面
        pb_rl.setVisibility(View.GONE);
        pay_rl.setVisibility(View.GONE);

        if (Constant.apptype == APPType.game) {
            loadingDialog.ShowDialog(getString(R.string.toast_content_one_game));
        } else if (Constant.apptype == APPType.comic) {
            loadingDialog.ShowDialog(getString(R.string.toast_content_one_comic));
        } else {
            loadingDialog.ShowDialog(getString(R.string.toast_content_one_game));
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.hide();
                ToastUtil.showToast(getString(R.string.toast_content_two));
                if (Constant.apptype == APPType.game) {
                    loadingDialog.ShowDialog(getString(R.string.toast_content_three_game));
                } else if (Constant.apptype == APPType.comic) {
                    loadingDialog.ShowDialog(getString(R.string.toast_content_three_comic));
                } else {
                    loadingDialog.ShowDialog(getString(R.string.toast_content_three_game));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.hide();
                        loadingDialog.ShowDialog(getString(R.string.loading));
                        ToastUtil.showToast(getString(R.string.toast_content_four));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.hide();
                                pb_rl.setVisibility(View.GONE);
                                pay_rl.setVisibility(View.GONE);
                                notice_rl.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                    }
                }, 4000);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        Log.i("onBackPressed", "onBackPressed");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.i("onKeyDown", "onKeyDown");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
