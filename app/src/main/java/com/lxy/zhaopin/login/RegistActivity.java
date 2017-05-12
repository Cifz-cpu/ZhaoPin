package com.lxy.zhaopin.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.bean.RegistBack;
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

@Route(path = "/act/regist")
public class RegistActivity extends BaseActivity {
    @BindView(R.id.regist_top)
    TopBarView registTop;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    String phone;

    ProgressDialog dialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView(Bundle bundle) {

        dialog = new ProgressDialog(this);
        dialog.setMessage("正在注册");
        dialog.setCancelable(false);
        dialog.create();

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        registTop.setTitle("用户注册");
        registTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                ARouter.getInstance().build("/act/login").navigation();
            }
        });
        etName.setText(phone);
    }

    @OnClick(R.id.btn_regist)
    public void click(View view){
        String psw = etPsw.getText().toString().trim();
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",psw);
        JSONObject json = new JSONObject(map);
        String str = json.toString();
        dialog.show();
        if(!psw.equals("")){
            OkHttpUtils
                    .postString()
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .url(Config.BaseUrl+"zhaopin/v1/user/regist")
                    .content(str)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            dialog.dismiss();
                            ToastUtils.showShort("网络错误");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
                            RegistBack registBack = gson.fromJson(response,RegistBack.class);
                            dialog.dismiss();
                            if(registBack.getResult().getDispalyMsg().equals("success")){
                                ToastUtils.showShort("注册成功");
                                ARouter.getInstance().build("/act/login").navigation();
                            }else{
                                ToastUtils.showShort(registBack.getResult().getDispalyMsg());
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            ARouter.getInstance().build("/act/login").navigation();
        }
        return false;
    }
}
