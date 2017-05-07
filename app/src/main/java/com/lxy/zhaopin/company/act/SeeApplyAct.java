package com.lxy.zhaopin.company.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.company.bean.ApplyJob;
import com.lxy.zhaopin.company.bean.MyApplyAdapter;
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

@Route(path = "/act/seeapply")
public class SeeApplyAct extends BaseActivity {
    @BindView(R.id.apply_top)
    TopBarView applyTop;
    @BindView(R.id.lv)
    ListView lv;
    String jobid;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_apply;
    }

    @Override
    protected void initView(Bundle bundle) {

        Intent intent = getIntent();
        jobid = intent.getStringExtra("jobid");

        applyTop.setTitle("申请者");
        applyTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
        loadApply(jobid);
    }

    private void loadApply(String jobid) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl+"zhaopin/v1/position/"+jobid+"/users")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gsson = new Gson();
                        final ApplyJob apply = gsson.fromJson(response,ApplyJob.class);
                        MyApplyAdapter adapter = new MyApplyAdapter(apply.getUser(),getApplicationContext());
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ARouter.getInstance().build("/act/userresume").withString("userid",apply.getUser().get(position).getId()+"").navigation();
                            }
                        });
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
