package com.mmm.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.ClassDetailActivity;
import com.mmm.comic.base.recycler.SpaceClassItemDecoration;
import com.mmm.comic.base.recycler.SpaceUpdateItemDecoration;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.util.CommUtil;
import com.mmm.comic.util.SystemUtil;
import com.mmm.comic.util.ToastUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by link on 2018/6/22.
 */

public class IndexUpdateFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<ComicBean> comicList = null;
    private View view;
    private List<RadioButton> radioButtons;
    private int oldIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index_update, null);
        init();
        return view;
    }

    private void init() {
        initView();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.update_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.addItemDecoration(new SpaceUpdateItemDecoration(10, 3));
        RadioButton mRButton1 = view.findViewById(R.id.update_tab1);
        RadioButton mRButton2 = view.findViewById(R.id.update_tab2);
        RadioButton mRButton3 = view.findViewById(R.id.update_tab3);
        mRButton3.setText(CommUtil.GetWeek(2));
        RadioButton mRButton4 = view.findViewById(R.id.update_tab4);
        mRButton4.setText(CommUtil.GetWeek(3));
        RadioButton mRButton5 = view.findViewById(R.id.update_tab5);
        mRButton5.setText(CommUtil.GetWeek(4));
        RadioButton mRButton6 = view.findViewById(R.id.update_tab6);
        mRButton6.setText(CommUtil.GetWeek(5));
        RadioButton mRButton7 = view.findViewById(R.id.update_tab7);
        mRButton7.setText(CommUtil.GetWeek(6));
        mRButton1.setOnClickListener(this);
        mRButton2.setOnClickListener(this);
        mRButton3.setOnClickListener(this);
        mRButton4.setOnClickListener(this);
        mRButton5.setOnClickListener(this);
        mRButton6.setOnClickListener(this);
        mRButton7.setOnClickListener(this);
        radioButtons = new ArrayList<>();
        radioButtons.add(mRButton1);
        radioButtons.add(mRButton2);
        radioButtons.add(mRButton3);
        radioButtons.add(mRButton4);
        radioButtons.add(mRButton5);
        radioButtons.add(mRButton6);
        radioButtons.add(mRButton7);
        mRButton1.setChecked(true);
    }

    /**
     * 绑定数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comicList = APP.application.comicList;
        CommonAdapter<ComicBean> mAdapter = new CommonAdapter<ComicBean>(getActivity(), R.layout.adapter_update_item, comicList) {
            @Override
            protected void convert(ViewHolder helper, ComicBean item, int position) {
                Glide.with(getActivity()).load(item.getThumb()).into((ImageView) helper.getView(R.id.update_item_iv));
                helper.setText(R.id.title, item.getTitle());
                helper.setText(R.id.update_tag_tv, item.getUpdatetag());


                helper.getView(R.id.root_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().startActivity(new Intent(getActivity(), ClassDetailActivity.class));
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);

    }

    private void switchTab(int index) {
        radioButtons.get(oldIndex).setChecked(false);
        radioButtons.get(index).setChecked(true);
        oldIndex = index;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_tab1:
                switchTab(0);
                break;
            case R.id.update_tab2:
                switchTab(1);
                break;
            case R.id.update_tab3:
                switchTab(2);
                break;
            case R.id.update_tab4:
                switchTab(3);
                break;
            case R.id.update_tab5:
                switchTab(4);
                break;
            case R.id.update_tab6:
                switchTab(5);
                break;
            case R.id.update_tab7:
                switchTab(6);
                break;
        }
    }
}
