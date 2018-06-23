package com.mmm.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.ClassDetailActivity;
import com.mmm.comic.base.CommonAdapter;
import com.mmm.comic.base.ViewHolder;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.util.CommUtil;
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
                helper.setText(R.id.name, item.getTitle());
                helper.setText(R.id.feature, CommUtil.SubContent(item.getDetail()));
                helper.setText(R.id.author, item.getAuthor());

                if(!TextUtils.isEmpty(item.getTag())){
                    LinearLayout label_layout = helper.getView(R.id.label_layout);
                    label_layout.removeAllViews();

                    String[] tagArray = item.getTag().split(" ");
                    for (String tag : tagArray){
                        TextView tag_tv = (TextView) View.inflate(getContext(), R.layout.tv_index_tag, null);
                        tag_tv.setText(tag);
                        label_layout.addView(tag_tv);
                    }
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity().startActivity(new Intent(getActivity(), ClassDetailActivity.class));
            }
        });
    }
}
