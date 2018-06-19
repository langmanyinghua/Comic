package com.mmm.comic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
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
        index_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFragment(indexFragment);
//                switchRadioButton(index_rb);
            }
        });
        class_rb = (RadioButton) findViewById(R.id.class_rb);
        class_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFragment(classFragment);
//                switchRadioButton(class_rb);
            }
        });
        bookbox_rb = (RadioButton) findViewById(R.id.bookbox_rb);
        bookbox_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFragment(bookBoxFragment);
//                switchRadioButton(bookbox_rb);
            }
        });
        mine_rb = (RadioButton) findViewById(R.id.mine_rb);
        mine_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFragment(mineFragment);
//                switchRadioButton(mine_rb);
            }
        });
    }

    /**
     * 隐藏和显示 Fragment
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

    public void switchRadioButton(RadioButton radio){
        index_rb.setChecked(false);
        class_rb.setChecked(false);
        bookbox_rb.setChecked(false);
        mine_rb.setChecked(false);

        radio.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.index_rb:
                switchRadioButton(index_rb);
                break;
            case R.id.class_rb:
                switchRadioButton(class_rb);
                break;
            case R.id.bookbox_rb:
                switchRadioButton(bookbox_rb);
                break;
            case R.id.mine_rb:
                switchRadioButton(mine_rb);
                break;
        }
    }
}
