package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.R;
import com.mmm.comic.base.recycler.SpaceItemDecoration;
import com.mmm.comic.bean.ComicBean;
import com.mmm.comic.bean.TabBead;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/8.
 */
public class ClassFragment extends Fragment {

    private RelativeLayout aboutme_rl;
    private RelativeLayout feedback_rl;
    private RelativeLayout share_rl;
    private RelativeLayout exit_rl;
    private View view;
    private RecyclerView mRecyclerView;
    private View mHeadView;
    private GridLayoutManager gridLayoutManager;
    private CommonAdapter<ComicBean> mAdapter;
    private RecyclerView mHeadRecyclerView;
    private int index;
    private RadioButton radioButton;
    private Map<TabBead, Boolean> tabMap;
    private TabBead tabBead;
    //
//    public ChioseChildGalleryAdapter chioseChildGalleryAdapter;
//    private ButtomDialog buttomDialog;
//    public MainActivity activity;
//    private Gallery gallery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, null);
//        aboutme_rl = (RelativeLayout) view.findViewById(R.id.aboutme_rl);
//        feedback_rl = (RelativeLayout) view.findViewById(R.id.feedback_rl);
//        share_rl = (RelativeLayout) view.findViewById(R.id.share_rl);
//        exit_rl = (RelativeLayout) view.findViewById(R.id.exit_rl);
//        gallery = (Gallery) view.findViewById(R.id.gallery);
//        return view;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        activity = (MainActivity) getActivity();
//        chioseChildGalleryAdapter = new ChioseChildGalleryAdapter(getContext(), Constant.user.getBaby());
//        setGallery();
//        setCurrGallery();
//        initEvent();
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.class_recyclerview);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
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
        tabMap = new HashMap<>();
        tabBead = textList.get(0);
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
//                            notifyDataSetChanged();
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
            mButton.setTextColor(getActivity().getResources().getColor(R.color.class_tab_text_n));
        }
    }

    private void initEvent() {
        aboutme_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //意见反馈
        feedback_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
