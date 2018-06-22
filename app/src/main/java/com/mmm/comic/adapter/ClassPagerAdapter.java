package com.mmm.comic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.List;

/**
 * Created by link on 2018/6/21.
 */

public class ClassPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private List<Fragment> list;
    private List<RadioButton> radioButtons;
    private List<RadioButton> radioButtonReds;

    public ClassPagerAdapter(FragmentManager fm, List<Fragment> list, List<RadioButton> radioButtons, List<RadioButton> radioButtonReds) {
        super(fm);
        this.list = list;
        this.radioButtons = radioButtons;
        this.radioButtonReds = radioButtonReds;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        radioButtons.get(position).setChecked(true);
        radioButtonReds.get(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
