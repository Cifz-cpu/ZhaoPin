package com.lxy.zhaopin.company.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.myview.TopBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/1.
 */

@Route(path = "/act/comact")
public class CompanyAct extends BaseActivity {
    @BindView(R.id.company_top)
    TopBarView companyTop;
    @BindView(R.id.send_job)
    Button sendJob;
    @BindView(R.id.see_user)
    Button seeUser;
    @BindView(R.id.see_job)
    Button seeJob;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_company;
    }

    @Override
    protected void initView(Bundle bundle) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        companyTop.setTitle(name);
        companyTop.setBackBtn(false);
    }

    @OnClick({R.id.send_job,R.id.see_job,R.id.see_user})
    public void click(View view){
        switch (view.getId()){
            case R.id.send_job:
                ARouter.getInstance().build("/act/sendpos").navigation();
                break;
            case R.id.see_job:
                ARouter.getInstance().build("/act/commyjob").navigation();
                break;
            case R.id.see_user:
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
