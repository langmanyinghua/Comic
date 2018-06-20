package com.mmm.comic.activity.set;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.base.CommonAdapter;
import com.mmm.comic.base.ViewHolder;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.bean.HelpBean;
import com.mmm.comic.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/4.
 */
public class HelpActivity extends AppCompatActivity {

    private CommonAdapter commonAdapter;
    private List<HelpBean> list = null;
    private ListView listView;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_activity_help);
        ((TextView) findViewById(R.id.naviga_title)).setText(getString(R.string.mkz_help_title));

        list = new ArrayList<>();
        list.add(new HelpBean("常见问题", " https://m.mkzhan.com/help/10001.html"));
        list.add(new HelpBean("VIP相关问题", " https://m.mkzhan.com/help/10002.html"));
        list.add(new HelpBean("漫画类型", " https://m.mkzhan.com/help/10008.html"));
        list.add(new HelpBean("元宝相关类型", " https://m.mkzhan.com/help/10009.html"));
        list.add(new HelpBean("我除了看漫画还能干什么", " https://m.mkzhan.com/help/10010.html"));

        listView = (ListView) findViewById(R.id.help_list);
        listView.setAdapter(commonAdapter = new CommonAdapter<HelpBean>(this, list, R.layout.mkz_activity_help_item) {
            @Override
            public void convert(ViewHolder helper, HelpBean item) {
                helper.setText(R.id.tv_name, item.getTitle());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(HelpActivity.this, HelpWebActivity.class).putExtra("title", list.get(i).getTitle()).putExtra("url",list.get(i).getUrl()));
            }
        });
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
