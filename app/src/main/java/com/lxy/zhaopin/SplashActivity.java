package com.lxy.zhaopin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/1.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.user)
    Button user;
    @BindView(R.id.company)
    Button company;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @OnClick({R.id.user,R.id.company})
    public void click(View v){
        switch (v.getId()){
            case R.id.user:
                ARouter.getInstance().build("/act/login").navigation();
                break;
            case R.id.company:
                ARouter.getInstance().build("/act/comlogin").navigation();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
