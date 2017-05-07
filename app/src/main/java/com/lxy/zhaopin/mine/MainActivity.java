package com.lxy.zhaopin.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.mainfragment.JobsFragment;
import com.lxy.zhaopin.mainfragment.MineFragment;
import com.lxy.zhaopin.myview.TabGroup;
import com.lxy.zhaopin.myview.TabItem;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/act/main")
public class MainActivity extends BaseActivity {


    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.jobs)
    TabItem jobs;
    @BindView(R.id.mine)
    TabItem mine;
    @BindView(R.id.tab_group)
    TabGroup tabGroup;
    private JobsFragment jobsFragment = null;
    private MineFragment mineFragment = null;
    private int currentIndex = 0;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        tabGroup.check(currentIndex);
        tabGroup.setOnTabGroupCheckedListener(new TabGroup.OnTabGroupCheckedListener() {
            @Override
            public void onChecked(int checkedIndex) {
                currentIndex = checkedIndex;
                updateFragment();
            }
        });
        updateFragment();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle bundle) {
        tintManager.setTintColor(Color.parseColor("#F8F8F8"));
    }

    private void updateFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragment();
        switch (currentIndex){
            case 0:
                if (jobsFragment == null) {
                    jobsFragment = new JobsFragment();
                    ft.add(R.id.frame, jobsFragment);
                }
                ft.show(jobsFragment);
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.frame, mineFragment);
                }
                ft.show(mineFragment);
                break;
        }

        ft.commit();

    }

    private void hideFragment() {
        if (jobsFragment != null) {
            ft.hide(jobsFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

}
