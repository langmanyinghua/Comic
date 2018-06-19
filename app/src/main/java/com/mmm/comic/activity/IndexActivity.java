package com.mmm.comic.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.base.CommonAdapter;
import com.mmm.comic.base.ViewHolder;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.util.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/19.
 */
public class IndexActivity extends AppCompatActivity {
    private ListView index_list;
    private List<ComicBean> comicList;
    private CommonAdapter commonAdapter;
    private IndexActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //全屏
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_index);
        activity = this;
        index_list = (ListView) findViewById(R.id.index_list);

        setData();


        index_list.setAdapter(commonAdapter = new CommonAdapter<ComicBean>(this, comicList, R.layout.index_list_item) {
            @Override
            public void convert(ViewHolder helper, ComicBean item) {
//                Glide.with(activity).load(item.getThumb()).into((ImageView) helper.getView(R.id.thumb));

//                Glide.with(activity).load(item.getThumb()).into((ImageView) helper.getView(R.id.thumb));
                Glide.with(activity).load(item.getThumb()).centerCrop().transform(new GlideRoundTransform(activity, 5)).into((ImageView) helper.getView(R.id.thumb));

                //ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + CommonUtil.SubImageUrl(item.getImg_index()), icon, Options.getListOptions());
//                helper.setText(R.id.title_tv, item.getResource_content());
//                helper.setText(R.id.author_tv, "作者:" + item.getResource_content());
            }
        });
    }

    public void setData() {
        comicList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ComicBean comic = new ComicBean();
            comic.setTitle("怦然心动");
            comic.setThumb("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg");
            comic.setAuthor("kid岁");
            comic.setDetail("少男少女同住一个屋檐下，一同历经青春的懵懂和烦恼。画风温馨的超人气少女漫，感受青春悸动的萌芽，这一部就够！【独家/每周二更新，责编：00】");
            comic.setHeat("148.36亿");
            comic.setTag("青春");
            comicList.add(comic);
        }
    }


}
