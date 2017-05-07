package com.lxy.zhaopin.company.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.company.bean.SendBacK;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.SpUtils;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/5/1.
 */

@Route(path = "/act/sendpos")
public class SendpositionAct extends BaseActivity {
    @BindView(R.id.mine_top)
    TopBarView mineTop;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_jianjie)
    EditText etJianjie;
    @BindView(R.id.et_detail)
    EditText etDetail;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.et_min)
    EditText etMin;
    @BindView(R.id.et_max)
    EditText etMax;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sendjob;
    }

    @Override
    protected void initView(Bundle bundle) {
        mineTop.setTitle("发布职位");
        mineTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });

    }

    @OnClick(R.id.btn_save)
    public void click(View view){
        String name = etName.getText().toString().trim();
        String jianjie = etJianjie.getText().toString().trim();
        String detail = etDetail.getText().toString().trim();
        String amount = etAmount.getText().toString().trim();
        String min = etMin.getText().toString().trim();
        String max = etMax.getText().toString().trim();

        if(name.equals("")||jianjie.equals("")||detail.equals("")||amount.equals("")||min.equals("")||max.equals("")){
            ToastUtils.showShort("请输入完全");
        }else{
            saveJob(name,jianjie,detail,amount,min,max);
        }

    }

    private void saveJob(String name, String jianjie, String detail, String amount, String min, String max) {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("resume",jianjie);
        map.put("desc",detail);
        map.put("requireNumber",amount);
        String salary = "min:"+min+","+"max:"+max;
        map.put("salary",salary);
        JSONObject json = new JSONObject(map);
        String str = json.toString();
        String comid = SpUtils.getString(getApplicationContext(),"comId","");
        String url = Config.BaseUrl+"zhaopin/v1/company/"+comid+"/position";
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(str)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("保存失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SendBacK send = gson.fromJson(response,SendBacK.class);
                        if(send.getResult().getDispalyMsg().equals("success")){
                            ToastUtils.showShort("发布成功");
                            finish();
                        }
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
