package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by panlaixing on 2018/6/19.
 */
public class IndexItemFragment extends Fragment {
    private ListView listView;
    private CommonAdapter commonAdapter;
    private List<ComicBean> comicList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index_man, null);
        listView = view.findViewById(R.id.man_listview);
        return view;
    }

    /**
     * 绑定数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comicList = APP.application.comicList;
        listView.setAdapter(commonAdapter = new CommonAdapter<ComicBean>(getContext(), comicList, R.layout.fragment_index_man_item) {
            @Override
            public void convert(ViewHolder helper, ComicBean item) {
                Glide.with(getActivity()).load(item.getThumb()).into((ImageView) helper.getView(R.id.image));
                helper.setText(R.id.name,item.getTitle());
                helper.setText(R.id.feature,item.getDetail());
                helper.setText(R.id.author,item.getAuthor());

                LinearLayout label_layout = helper.getView(R.id.label_layout);
                label_layout.removeAllViews();
                TextView tag = new TextView(getContext());
                tag.setTextSize(7);
                tag.setBackgroundResource(R.drawable.shape_tag);
                tag.setText(item.getTag());
                label_layout.addView(tag);
            }
        });
    }
}
