package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mmm.comic.R;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录
 * Created by link on 2018/6/21.
 */

public class ClassCatalogFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class_catalog, null);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + (i + 1));
        }
        CommonAdapter mAdapter = new CommonAdapter<String>(getActivity(), R.layout.adapter_catalog, list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.catalog_tv, s);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
