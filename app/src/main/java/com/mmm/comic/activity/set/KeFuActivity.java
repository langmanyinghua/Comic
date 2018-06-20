package com.mmm.comic.activity.set;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.network.Helper;
import com.mmm.comic.network.HttpCallBack;
import com.mmm.comic.util.ToastUtil;

public class KeFuActivity extends AppCompatActivity {
    private static final String TAG = KeFuActivity.class.getSimpleName();
    private EditText phone_et;
    private EditText content_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_kefu);
        ((TextView)findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_feedback_title));

        phone_et = (EditText) findViewById(R.id.phone_et);
        content_et = (EditText) findViewById(R.id.content_et);
    }

    /**
     * 返回
     *
     * @param view
     */
    public void onClickBack(View view) {
        finish();
    }

    /**
     * 提交反馈
     *
     * @param view
     */
    public void onClickSubmit(View view) {
        if (TextUtils.isEmpty(phone_et.getText().toString())) {
            ToastUtil.showToast("请填写手机号码");
            return;
        }
        if (phone_et.getText().toString().length() != 11) {
            ToastUtil.showToast("手机号码格式错误");
            return;
        }
        if (TextUtils.isEmpty(content_et.getText().toString())) {
            ToastUtil.showToast("请填写内容");
            return;
        }

        Helper.feedback(phone_et.getText().toString(), content_et.getText().toString(), new HttpCallBack() {
            @Override
            public void callback(Object o) {
                ToastUtil.showToast("提交反馈成功");
                phone_et.setText("");
                content_et.setText("");
            }
        });
    }
}
