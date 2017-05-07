package com.lxy.zhaopin.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.adapter.ApplyJobAdapter;
import com.lxy.zhaopin.bean.MyApplyPos;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.SpUtils;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/23.
 */

@Route(path = "/act/shenqing")
public class ShenQingActivity extends BaseActivity {
    @BindView(R.id.shenqing_top)
    TopBarView shenqingTop;
    @BindView(R.id.lv)
    ListView lv;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shenqing;
    }

    @Override
    protected void initView(Bundle bundle) {
        shenqingTop.setTitle("申请职位");
        tintManager.setTintColor(Color.parseColor("#F8F8F8"));
        shenqingTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });

        String id = SpUtils.getString(getApplicationContext(), "userId", "");
        loadApply(id);

    }

    private void loadApply(String id) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl + "zhaopin/v1/position/" + id + "/positions")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        MyApplyPos apply = gson.fromJson(response,MyApplyPos.class);
                        ApplyJobAdapter  adapter = new ApplyJobAdapter(apply.getPositionDataList(),getApplicationContext());
                        lv.setAdapter(adapter);
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
