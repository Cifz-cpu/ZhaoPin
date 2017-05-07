package com.lxy.zhaopin.company.act;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.company.bean.SAeeResume;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/2.
 */

@Route(path = "/act/userresume")
public class ResumeAct extends BaseActivity {
    @BindView(R.id.rsume_top)
    TopBarView rsumeTop;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_ex)
    TextView tvEx;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_resume;
    }

    @Override
    protected void initView(Bundle bundle) {
        rsumeTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });

        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        loadData(id);
    }

    private void loadData(String id) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl+"zhaopin/v1/user/"+id+"/resume")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SAeeResume see = gson.fromJson(response,SAeeResume.class);
                        tvCity.setText(see.getExpectCity()+"");
                        tvEmail.setText(see.getContact().getEmail()+"");
                        tvEx.setText(see.getExperience()+"");
                        tvJob.setText(see.getExpectPosition()+"");
                        tvName.setText(see.getUserName()+"");
                        tvPhone.setText(see.getContact().getPhone()+"");
                        tvSalary.setText(see.getSalary().getMin()+"--"+see.getSalary().getMax());
                        tvSchool.setText(see.getEducation());
                        rsumeTop.setTitle(see.getName());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
