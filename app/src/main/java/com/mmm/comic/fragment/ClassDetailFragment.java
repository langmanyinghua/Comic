package com.mmm.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.VIPActivity;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.util.ScreenUtils;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 详情
 * Created by link on 2018/6/21.
 */

public class ClassDetailFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private TextView touyuepiao_tv;
    private TextView dashang_tv;
    private ExpandableTextView expand_text_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class_detail, null);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.class_detail_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 10, 0, 10));
        mRecyclerView.setLayoutManager(linearLayoutManager);

        expand_text_view = view.findViewById(R.id.expand_text_view);
        expand_text_view.setText(getString(R.string.class_detail_text));

        touyuepiao_tv = view.findViewById(R.id.touyuepiao_tv);
        dashang_tv = view.findViewById(R.id.dashang_tv);
    }

    private void initData() {
        List<ComicBean> list = APP.application.comicList;
        CommonAdapter<ComicBean> mAdapter = new CommonAdapter<ComicBean>(getActivity(), R.layout.adapter_class_detail, list) {
            @Override
            protected void convert(ViewHolder holder, ComicBean item, int position) {
                holder.setText(R.id.detail_tv, item.getTitle());
                Glide.with(getActivity()).load(item.getThumb()).into((ImageView) holder.getView(R.id.detail_iv));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initEvent() {
        // 投月票
        touyuepiao_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), VIPActivity.class));
            }
        });

        // 打赏
        dashang_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), VIPActivity.class));
            }
        });
    }
}
