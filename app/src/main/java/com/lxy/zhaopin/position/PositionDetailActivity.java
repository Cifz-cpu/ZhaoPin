package com.lxy.zhaopin.position;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.bean.ApplyBack;
import com.lxy.zhaopin.bean.ApplyPosition;
import com.lxy.zhaopin.bean.Position;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.SpUtils;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/4/30.
 */

@Route(path = "/act/position")
public class PositionDetailActivity extends BaseActivity {
    @BindView(R.id.detail_top)
    TopBarView detailTop;
    @BindView(R.id.position_desc)
    TextView positionDesc;
    @BindView(R.id.position_amount)
    TextView positionAmount;
    @BindView(R.id.position_salary)
    TextView positionSalary;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.company_address)
    TextView companyAddress;
    @BindView(R.id.company_desc)
    TextView companyDesc;
    @BindView(R.id.company_phone)
    TextView companyPhone;
    @BindView(R.id.btn_shenqing)
    Button btnShenqing;
    int id;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position;
    }

    @Override
    protected void initView(Bundle bundle) {
        tintManager.setTintColor(Color.parseColor("#F8F8F8"));
        detailTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
        Intent intent = getIntent();
        id = intent.getIntExtra("posID",0);
        if(id != 0){
            loadPosition(id);
        }

    }

    private void loadPosition(int id) {
        OkHttpUtils
                .get()
                .url(Config.BaseUrl+"zhaopin/v1/position/"+id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showLong("网络出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Position position = gson.fromJson(response,Position.class);
                        setData(position);
                    }
                });
    }

    private void setData(Position position) {
        detailTop.setTitle(position.getName());
        positionDesc.setText(position.getDesc().toString());
        positionSalary.setText(position.getSalary().getMin()+"-"+position.getSalary().getMax());
        positionAmount.setText(position.getRequireNumber()+"");

        companyAddress.setText(position.getCompany().getAddress().toString());
        companyDesc.setText(position.getCompany().getDesc().toString());
        companyName.setText(position.getCompany().getName().toString());
        companyPhone.setText(position.getCompany().getTelPhone().toString());

    }

    @OnClick(R.id.btn_shenqing)
    public void click(View view){
        String userId = SpUtils.getString(getApplicationContext(), "userId", "");
        List<Integer> listid = new ArrayList<>();
        listid.add(id);
        final Gson gson = new Gson();
        ApplyPosition applyPosition = new ApplyPosition();
        applyPosition.setPositionId(listid);
        String jsonObject = gson.toJson(applyPosition);
        OkHttpUtils
                .postString()
                .url(Config.BaseUrl+"zhaopin/v1/user/"+userId+"/position")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonObject)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ApplyBack applyBack = gson.fromJson(response,ApplyBack.class);
                        if(applyBack.getDispalyMsg().equals("success")){
                            ToastUtils.showShort("申请成功");
                            finish();
                        }else{
                            ToastUtils.showShort("申请失败");
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
