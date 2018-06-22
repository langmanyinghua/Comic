package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmm.comic.R;
import com.mmm.comic.bean.SectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public class IndexFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private List<String> listTitles = null;
    private ArrayList<Fragment> fragments = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        tablayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        listTitles = new ArrayList<>();
        listTitles.add("男生");
        listTitles.add("女生");
        listTitles.add("推荐");
        listTitles.add("更新");
        listTitles.add("排行");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragments = new ArrayList<>();
        fragments.add(new IndexItemFragment());
        fragments.add(new IndexItemFragment());
        fragments.add(new IndexItemFragment());
        fragments.add(new IndexUpdateFragment());
        fragments.add(new RankFragment());
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

    private void initEvent() {


    }


}
