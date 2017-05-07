package com.lxy.zhaopin.mainfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.adapter.JobAdapter;
import com.lxy.zhaopin.bean.JobList;
import com.lxy.zhaopin.swipe.SwipeRecyclerView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.SpUtils;
import com.lxy.zhaopin.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/13.
 */

public class JobsFragment extends Fragment implements SwipeRecyclerView.OnLoadListener {

    @BindView(R.id.search_job)
    EditText searchJob;
    @BindView(R.id.job_srv)
    SwipeRecyclerView jobSrv;
    @BindView(R.id.banner)
    Banner banner;
    private JobAdapter adapter;

    private String[] img = new String[]{"http://pic11.nipic.com/20101214/213291_155243023914_2.jpg","http://pic.qiantucdn.com/58pic/13/61/25/90T58PICvY4_1024.jpg","http://img1.3lian.com/2015/w7/85/d/21.jpg"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_job, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }



    private void initBanner() {

        List<String> list = new ArrayList<>();
        List<String> listT = new ArrayList<>();
        for(int i = 0;i < img.length;i ++){
            list.add(img[i]);
            listT.add("");
        }

        //初始化banner
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        banner.setBannerTitles(listT);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    /**
     * 初始化view
     */
    private void initView() {
        adapter = new JobAdapter(getActivity());
        jobSrv.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        jobSrv.setFootTextColor(R.color.mine_text_color);
        jobSrv.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        jobSrv.setAdapter(adapter);
        jobSrv.setRefreshing(true);
        jobSrv.setOnLoadListener(this);

        searchJob.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if(searchJob.getText().toString().trim().equals("")){
                        ToastUtils.showShort("请输入工作");
                    }else{
                        ARouter.getInstance().build("/act/search").withString("job",searchJob.getText().toString().trim()).navigation();
                    }
                    return true;
                }
                return false;
            }
        });

        loadJob();
    }


    private void loadJob() {

        OkHttpUtils
                .get()
                .url(Config.BaseUrl + "zhaopin/v1/position")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_LONG).show();
                        jobSrv.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        final JobList joblist = gson.fromJson(response, JobList.class);
                        adapter.clear();
                        adapter.addData(joblist.getPositionDataList());
                        adapter.notifyDataSetChanged();
                        jobSrv.setRefreshing(false);
                        adapter.setOnItemClickLitener(new JobAdapter.OnItemClickLitener() {
                            @Override
                            public void JobClick(int pos) {
                                ARouter.getInstance().build("/act/position").withInt("posID",joblist.getPositionDataList().get(pos).getId()).navigation();
                            }
                        });
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBanner();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadJob();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            String nowPath = (String) path;
            if (nowPath != null && nowPath.length() > 0) {
                Picasso.with(context).load(nowPath).placeholder(R.mipmap.ic_launcher).into(imageView);
            }
        }
    }

}
