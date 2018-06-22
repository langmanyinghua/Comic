package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.mmm.comic.R;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情
 * Created by link on 2018/6/21.
 */

public class ClassDetailFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class_detail, null);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.class_detail_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 10, 0, 10));
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        list.add("偷星九月天");
        list.add("凤逆天下");
        list.add("封神榜");
        list.add("卡灵");
        list.add("虚幻王座");
        list.add("科长是龙王");
        list.add("龙虎门");
        CommonAdapter<String> mAdapter = new CommonAdapter<String>(getActivity(), R.layout.adapter_class_detail, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.detail_tv, s);
                Glide.with(getActivity()).load("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg").into((ImageView) holder.getView(R.id.detail_iv));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }
}
