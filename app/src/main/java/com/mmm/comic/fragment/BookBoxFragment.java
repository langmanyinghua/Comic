package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mmm.comic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public class BookBoxFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private List<String> listTitles = null;
    private ArrayList<BookBoxItemFragment> fragments = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookbox, null);
        tablayout = view.findViewById(R.id.bookbox_tablayout);
        viewPager = view.findViewById(R.id.bookbox_viewpager);

        listTitles = new ArrayList<>();
        listTitles.add("历史");
        listTitles.add("收藏");
        listTitles.add("缓存");
        listTitles.add("订购");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragments = new ArrayList<>();
        for (String title : listTitles) {
            fragments.add(BookBoxItemFragment.newInstance(title));
        }
        setViewPager();
    }

    public void setViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return listTitles.get(position);
            }
        });
        tablayout.setupWithViewPager(viewPager);
    }
}
