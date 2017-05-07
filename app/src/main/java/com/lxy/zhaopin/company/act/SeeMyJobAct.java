package com.lxy.zhaopin.company.act;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.adapter.SearchJobAdapter;
import com.lxy.zhaopin.company.MyJobAdapter;
import com.lxy.zhaopin.company.bean.MyJob;
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
 * Created by Administrator on 2017/5/2.
 */

@Route(path = "/act/commyjob")
public class SeeMyJobAct extends BaseActivity {
    @BindView(R.id.my_top)
    TopBarView myTop;
    @BindView(R.id.lv)
    ListView lv;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_myjob;
    }

    @Override
    protected void initView(Bundle bundle) {
        myTop.setTitle("我发布的职位");
        myTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
        String id = SpUtils.getString(getApplicationContext(),"comId","");
        loadJobs(id);
    }

    private void loadJobs(String id) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl+"zhaopin/v1/position/company/"+id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        final MyJob myjob = gson.fromJson(response,MyJob.class);
                        MyJobAdapter adapter = new MyJobAdapter(myjob.getPositionDataList(),getApplicationContext());
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ARouter.getInstance().build("/act/seeapply").withString("jobid",myjob.getPositionDataList().get(position).getId()+"").navigation();
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
