package com.lxy.zhaopin.position;

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
import com.lxy.zhaopin.adapter.SearchJobAdapter;
import com.lxy.zhaopin.bean.Search;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/1.
 */

@Route(path = "/act/search")
public class SearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.search_top)
    TopBarView searchTop;
    @BindView(R.id.search_lv)
    ListView searchLv;
    Search search;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_search;
    }

    @Override
    protected void initView(Bundle bundle) {

        Intent intent = getIntent();
        String pos = intent.getStringExtra("job");
        loadPos(pos);
        searchTop.setTitle("搜索结果");
        searchTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
        searchLv.setOnItemClickListener(this);

    }

    private void loadPos(String pos) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl+"zhaopin/v1/position/parameter/"+pos)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("搜索失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        search = gson.fromJson(response,Search.class);
                        SearchJobAdapter adapter = new SearchJobAdapter(search.getPositionDataList(),getApplicationContext());
                        searchLv.setAdapter(adapter);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ARouter.getInstance().build("/act/position").withInt("posID",search.getPositionDataList().get(position).getId()).navigation();
    }
}
