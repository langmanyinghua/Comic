package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.R;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.mmm.comic.base.recycler.SpaceRankItemDecoration;
import com.mmm.comic.window.RankTabWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by link on 2018/6/22.
 */

public class RankFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Map<RadioButton, Boolean> radioButtons;
    private RadioButton mRbPopular;
    private RadioButton mRbAscension;
    private RadioButton mRbCollect;
    private RadioButton mRbExclusive;
    private RadioButton mRbLatest;
    private RadioButton mRbTicket;
    private RadioButton oldRButton;
    private RecyclerView mRecyclerView;
    private View mHeadView;
    private LinearLayout mPullView;
    private View mRankLine;
    private TextView mTvPull;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank, null);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.rank_recycler);
        mHeadView = View.inflate(getActivity(), R.layout.rank_head_view, null);
        mRbPopular = mHeadView.findViewById(R.id.rank_popular);
        mRbAscension = mHeadView.findViewById(R.id.rank_ascension);
        mRbCollect = mHeadView.findViewById(R.id.rank_collection);
        mRbExclusive = mHeadView.findViewById(R.id.rank_exclusive);
        mRbLatest = mHeadView.findViewById(R.id.rank_latest);
        mRbTicket = mHeadView.findViewById(R.id.rank_ticket);
        mPullView = view.findViewById(R.id.rank_pull_view);
        mRankLine = view.findViewById(R.id.rank_pw_line);
        mTvPull = view.findViewById(R.id.rank_pull_tv);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new SpaceRankItemDecoration(0, 0, 20, 0));
        radioButtons = new HashMap<>();
        mRbPopular.setChecked(true);
        oldRButton = mRbPopular;
        radioButtons.put(mRbPopular, true);
        radioButtons.put(mRbAscension, false);
        radioButtons.put(mRbCollect, false);
        radioButtons.put(mRbExclusive, false);
        radioButtons.put(mRbLatest, false);
        radioButtons.put(mRbTicket, false);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((i + 1) + "");
        }
        CommonAdapter<String> mAdapter = new CommonAdapter<String>(getActivity(), R.layout.adapter_rank_item, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                Glide.with(getActivity()).load("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg").into((ImageView) holder.getView(R.id.rank_item_iv));
                holder.setText(R.id.rank_item_number, s);
            }
        };
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(mHeadView);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
    }

    private void initEvent() {
        mRbPopular.setOnClickListener(this);
        mRbAscension.setOnClickListener(this);
        mRbCollect.setOnClickListener(this);
        mRbExclusive.setOnClickListener(this);
        mRbLatest.setOnClickListener(this);
        mRbTicket.setOnClickListener(this);
        mPullView.setOnClickListener(this);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    if (firstItemPosition > 0) {
                        mPullView.setVisibility(View.VISIBLE);
                    } else if (mPullView.getVisibility() == View.VISIBLE) {
                        mPullView.setVisibility(View.GONE);
                    }
                }

            }
        });
    }

    private void switchTab(RadioButton rButton) {
        radioButtons.put(oldRButton, false);
        radioButtons.put(rButton, true);
        oldRButton = rButton;
        for (Map.Entry<RadioButton, Boolean> map : radioButtons.entrySet()) {
            if (map.getValue()) {
                map.getKey().setChecked(true);
                mTvPull.setText(map.getKey().getText());
            } else {
                map.getKey().setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank_ascension:
                switchTab(mRbAscension);
                break;
            case R.id.rank_collection:
                switchTab(mRbCollect);
                break;
            case R.id.rank_exclusive:
                switchTab(mRbExclusive);
                break;
            case R.id.rank_latest:
                switchTab(mRbLatest);
                break;
            case R.id.rank_popular:
                switchTab(mRbPopular);
                break;
            case R.id.rank_ticket:
                switchTab(mRbTicket);
                break;
            case R.id.rank_pull_view:
                RankTabWindow.getInstance(getActivity()).onShow(mRankLine);
                break;
        }
    }
}
