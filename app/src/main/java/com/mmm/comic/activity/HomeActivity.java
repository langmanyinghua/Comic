package com.mmm.comic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.fragment.BookBoxFragment;
import com.mmm.comic.fragment.ClassFragment;
import com.mmm.comic.fragment.IndexFragment;
import com.mmm.comic.fragment.MineFragment;

/**
 * Created by 浪漫樱花 on 2018/6/19.
 */
public class HomeActivity extends AppCompatActivity {

    private FragmentManager mfragmentManager;
    private FragmentTransaction mfragmentTransaction;
    private IndexFragment indexFragment;
    private ClassFragment classFragment;
    private BookBoxFragment bookBoxFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();
        APP.application.hideBottomUIMenu(this);
        setContentView(R.layout.mkz_activity_main);
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
    }

    // 选择fragment
    public void switchFragment(Fragment mfragment) {
        mfragmentTransaction = mfragmentManager.beginTransaction();
        mfragmentTransaction.hide(indexFragment);
        mfragmentTransaction.hide(classFragment);
        mfragmentTransaction.hide(bookBoxFragment);
        mfragmentTransaction.hide(mineFragment);
        mfragmentTransaction.show(mfragment);
        mfragmentTransaction.commit();
    }
}
