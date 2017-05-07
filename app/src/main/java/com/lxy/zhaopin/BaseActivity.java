package com.lxy.zhaopin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseActivity extends FragmentActivity{

    //true表示标题栏适应某种颜色，false表示标题栏全透明
    private boolean topType = true;
    private String topColor = "#212121";

    private List<Activity> listActivity;

    protected static final String EXIT = "exit_application";
    protected static final String EXIT_ACTION = "com.lxy.zhaopin.baseactivity";
    public SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);  //注入

        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        // create our manager instance after the content view is set
        tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        if (topType){
            tintManager.setTintColor(Color.parseColor(topColor));
        }else {
            tintManager.setStatusBarAlpha(0);
        }

        //注册广播（退出登录用）
        IntentFilter intentFilter = new IntentFilter(EXIT_ACTION);
        registerReceiver(broadToLogOff,intentFilter);
        initView(savedInstanceState);
    }

    //任务栏类型/颜色
    public void topType(boolean topType ,String topColor){
        this.topType = topType;
        if (null!=topColor){
            this.topColor = topColor;
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public BroadcastReceiver broadToLogOff = new  BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra(EXIT).equals(EXIT)){
                finish();
            }
        }
    };

    /**
     * 布局文件ID
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化组件
     */
    protected abstract void initView(Bundle bundle);

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(broadToLogOff);
        super.onDestroy();
    }    @Override
    protected void onPause() {
        super.onPause();
    }

}
