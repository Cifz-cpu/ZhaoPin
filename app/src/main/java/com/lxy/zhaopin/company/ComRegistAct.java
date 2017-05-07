package com.lxy.zhaopin.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.company.bean.ComRegist;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

@Route(path = "/act/comregist")
public class ComRegistAct extends BaseActivity {
    @BindView(R.id.com_top)
    TopBarView comTop;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    String phone;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.et_address)
    EditText etAddress;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_comregist;
    }

    @Override
    protected void initView(Bundle bundle) {

        Intent intent = getIntent();
        phone = intent.getStringExtra("comphone");

        comTop.setTitle("公司注册");
        comTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                ARouter.getInstance().build("/act/comlogin").navigation();
                finish();
            }
        });
        etPhone.setText(phone);
    }

    @OnClick(R.id.btn_regist)
    public void click() {
        String name = etName.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String dessc = etDesc.getText().toString().trim();

        if (name.equals("") || psw.equals("") || address.equals("") || dessc.equals("")) {
            ToastUtils.showShort("请正确输入");
        } else {
            RgistCoM(name, phone, psw,address,dessc);
        }

    }

    private void RgistCoM(String name, String phone, String psw,String address,String desc) {
        Map<String, String> map = new HashMap<>();
        map.put("telPhone", phone);
        map.put("password", psw);
        map.put("name", name);
        map.put("address", address);
        map.put("desc", desc);
        JSONObject js = new JSONObject(map);
        String str = js.toString();
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(Config.BaseUrl + "zhaopin/v1/company/regist")
                .content(str)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        ComRegist comRegist = gson.fromJson(response, ComRegist.class);
                        if (comRegist.getResult().getDispalyMsg().equals("success")) {
                            ToastUtils.showShort("注册成功");
                            ARouter.getInstance().build("/act/comlogin").navigation();
                            finish();
                        } else {
                            ToastUtils.showShort("注册失败");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ARouter.getInstance().build("/act/comlogin").navigation();
        }
        return false;
    }

}
