package com.mmm.comic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.R;
import com.mmm.comic.activity.ClassDetailActivity;
import com.mmm.comic.base.recycler.SpaceClassItemDecoration;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.bean.TabBead;
import com.mmm.comic.window.ClassTabWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public class ClassFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private View mHeadView;
    private GridLayoutManager gridLayoutManager;
    private CommonAdapter<ComicBean> mAdapter;
    private RecyclerView mHeadRecyclerView;
    private int index;
    private LinearLayout mPullView;
    private View tabLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.class_recyclerview);
        mPullView = view.findViewById(R.id.class_pull_view);
        tabLine = view.findViewById(R.id.class_tab_line);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.addItemDecoration(new SpaceClassItemDecoration(20, 3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mHeadView = View.inflate(getActivity(), R.layout.class_tab_head, null);
        RadioButton mTypeAll = mHeadView.findViewById(R.id.tab_type_all);
        RadioButton mCostAll = mHeadView.findViewById(R.id.tab_cost_all);
        RadioButton mTypeHot = mHeadView.findViewById(R.id.tab_type_hot);
        mTypeAll.setChecked(true);
        mCostAll.setChecked(true);
        mTypeHot.setChecked(true);
    }

    private void initData() {
        List<ComicBean> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            ComicBean comic = new ComicBean();
            comic.setTitle("斗破苍穹");
            comic.setThumb("https://i1s.kkmh.com/image/180429/i2immgnut.webp-w750.jpg");
            comic.setDetail("更新至36集");
            list.add(comic);
        }
        mAdapter = new CommonAdapter<ComicBean>(getActivity(), R.layout.adapter_class, list) {


            @Override
            protected void convert(ViewHolder holder, ComicBean item, int position) {
                holder.setText(R.id.item_class_name, item.getTitle());
                holder.setText(R.id.item_class_text, item.getDetail());
                Glide.with(getActivity()).load(item.getThumb()).into((ImageView) holder.getView(R.id.item_class_image));
            }
        };
        setHeadView();
    }

    private void setHeadView() {
        mHeadRecyclerView = mHeadView.findViewById(R.id.class_tb_recyclerview);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(mHeadView);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
        setHeadRecyclerView();
    }

    private void setHeadRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mHeadRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        final List<TabBead> textList = new ArrayList<>();
        textList.add(new TabBead("全部", true));
        textList.add(new TabBead("霸总", false));
        textList.add(new TabBead("修真", false));
        textList.add(new TabBead("恋爱", false));
        textList.add(new TabBead("校园", false));
        textList.add(new TabBead("冒险", false));
        textList.add(new TabBead("搞笑", false));
        textList.add(new TabBead("生活", false));
        textList.add(new TabBead("热血", false));
        textList.add(new TabBead("架空", false));
        textList.add(new TabBead("后宫", false));
        index = 0;
        CommonAdapter<TabBead> tAdapter = new CommonAdapter<TabBead>(getActivity(), R.layout.adapter_tab_text, textList) {
            @Override
            protected void convert(ViewHolder holder, final TabBead item, final int position) {
                final TextView mButton = holder.getView(R.id.tab_text);
                mButton.setText(item.getText());
                isCheck(mButton, item.isCheck());
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!item.isCheck()) {
                            item.setCheck(true);
                            textList.get(index).setCheck(false);
                            notifyItemChanged(index);
                            notifyItemChanged(position);
                            index = position;
                        }
                    }
                });
            }

        };
        mHeadRecyclerView.setAdapter(tAdapter);
    }

    private void isCheck(TextView mButton, boolean isCheck) {
        if (isCheck) {
            mButton.setBackgroundResource(R.drawable.shape_class_tab_bg_p);
            mButton.setTextColor(getActivity().getResources().getColor(R.color.mkz_red));
        } else {
            mButton.setBackgroundResource(R.drawable.shape_class_tab_bg_n);
            mButton.setTextColor(getActivity().getResources().getColor(R.color.mkz_black2));
        }
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                getActivity().startActivity(new Intent(getActivity(), ClassDetailActivity.class));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
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
        mPullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassTabWindow.getInstance(getActivity()).onShow(tabLine);
            }
        });
    }


}
