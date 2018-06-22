package com.mmm.comic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.fragment.BookBoxFragment;
import com.mmm.comic.fragment.ClassFragment;
import com.mmm.comic.fragment.IndexFragment;
import com.mmm.comic.fragment.MineFragment;

/**
 * Created by 浪漫樱花 on 2018/6/19.
 */
public class HomeActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private FragmentManager mfragmentManager;
    private FragmentTransaction mfragmentTransaction;
    private IndexFragment indexFragment;
    private ClassFragment classFragment;
    private BookBoxFragment bookBoxFragment;
    private MineFragment mineFragment;

    private RadioButton index_rb;
    private RadioButton class_rb;
    private RadioButton bookbox_rb;
    private RadioButton mine_rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.activity_home);
        initView();
    }

    /**
     *
     */
    public void initView() {
        mfragmentManager = getSupportFragmentManager();
        indexFragment = (IndexFragment) mfragmentManager.findFragmentById(R.id.index_fragment);
        classFragment = (ClassFragment) mfragmentManager.findFragmentById(R.id.class_fragment);
        bookBoxFragment = (BookBoxFragment) mfragmentManager.findFragmentById(R.id.bookbox_fragment);
        mineFragment = (MineFragment) mfragmentManager.findFragmentById(R.id.mine_fragment);
        switchFragment(indexFragment);

        index_rb = (RadioButton) findViewById(R.id.index_rb);
        index_rb.setOnCheckedChangeListener(this);
        class_rb = (RadioButton) findViewById(R.id.class_rb);
        class_rb.setOnCheckedChangeListener(this);
        bookbox_rb = (RadioButton) findViewById(R.id.bookbox_rb);
        bookbox_rb.setOnCheckedChangeListener(this);
        mine_rb = (RadioButton) findViewById(R.id.mine_rb);
        mine_rb.setOnCheckedChangeListener(this);
    }

    /**
     * 隐藏和显示 Fragment
     *
     * @param mfragment
     */
    public void switchFragment(Fragment mfragment) {
        mfragmentTransaction = mfragmentManager.beginTransaction();
        mfragmentTransaction.hide(indexFragment);
        mfragmentTransaction.hide(classFragment);
        mfragmentTransaction.hide(bookBoxFragment);
        mfragmentTransaction.hide(mineFragment);
        mfragmentTransaction.show(mfragment);
        mfragmentTransaction.commit();
    }

    public void switchIndex() {
        switchFragment(indexFragment);
        switchRadioButton(index_rb);
    }

    /**
     * 切换底部导航
     *
     * @param radio
     */
    public void switchRadioButton(RadioButton radio) {
        index_rb.setChecked(false);
        class_rb.setChecked(false);
        bookbox_rb.setChecked(false);
        mine_rb.setChecked(false);
        radio.setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            switch (compoundButton.getId()) {
                case R.id.index_rb:
                    switchRadioButton(index_rb);
                    switchFragment(indexFragment);
                    break;
                case R.id.class_rb:
                    switchRadioButton(class_rb);
                    switchFragment(classFragment);
                    break;
                case R.id.bookbox_rb:
                    switchRadioButton(bookbox_rb);
                    switchFragment(bookBoxFragment);
                    break;
                case R.id.mine_rb:
                    switchRadioButton(mine_rb);
                    switchFragment(mineFragment);
                    break;
            }
        }
    }
}
