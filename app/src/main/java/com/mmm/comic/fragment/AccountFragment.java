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
import com.mmm.comic.bean.GameOption;
import com.mmm.comic.util.Constant;

import java.util.Iterator;
import java.util.List;

/**
 * Created by panlaixing on 2018/6/19.
 */
public class AccountFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
