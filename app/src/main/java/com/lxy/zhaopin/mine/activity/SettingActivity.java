package com.lxy.zhaopin.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/23.
 */

@Route(path = "/act/set")
public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_top)
    TopBarView settingTop;
    @BindView(R.id.my_account)
    RelativeLayout myAccount;
    @BindView(R.id.clear)
    RelativeLayout clear;
    @BindView(R.id.about)
    RelativeLayout about;
    @BindView(R.id.exit)
    RelativeLayout exit;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle bundle) {
        settingTop.setTitle("设置");
        tintManager.setTintColor(Color.parseColor("#F8F8F8"));
        settingTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.my_account,R.id.clear,R.id.about,R.id.exit})
    public void click(View view){
        switch (view.getId()){
            case R.id.my_account:
                ARouter.getInstance().build("/act/change").navigation();
                break;
            case R.id.about:
                ToastUtils.showShort("井大刘馨阳制作");
                break;
            case R.id.clear:
                ToastUtils.showShort("缓存已清除");
                break;
            case R.id.exit:
                ARouter.getInstance().build("/act/login").navigation();
                break;
        }
    }

}
