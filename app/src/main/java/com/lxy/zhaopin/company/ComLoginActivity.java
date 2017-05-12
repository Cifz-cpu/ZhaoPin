package com.lxy.zhaopin.company;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.company.bean.ComLogin;
import com.lxy.zhaopin.utils.Config;
import com.lxy.zhaopin.utils.SpUtils;
import com.lxy.zhaopin.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/5/1.
 */

@Route(path = "/act/comlogin")
public class ComLoginActivity extends BaseActivity {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    ProgressDialog dialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录请稍后");
        dialog.setCancelable(false);
        dialog.create();
    }

    @OnClick({R.id.btn_login, R.id.btn_regist})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = etName.getText().toString().trim();
                String psw = etPsw.getText().toString().trim();
                comLogin(name, psw);
                break;
            case R.id.btn_regist:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            ARouter.getInstance().build("/act/comregist").withString("comphone",phone).navigation();
                        }
                    }
                });
                registerPage.show(this);
                break;
        }
    }

    private void comLogin(String name, String psw) {
        dialog.show();
        Map<String,String> map = new HashMap<>();
        map.put("telPhone",name);
        map.put("password",psw);
        JSONObject json = new JSONObject(map);
        String str = json.toString();
        OkHttpUtils
                .postString()
                .url(Config.BaseUrl+"zhaopin/v1/company/login")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(str)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        ToastUtils.showShort("网络出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        ComLogin comLogin = gson.fromJson(response,ComLogin.class);
                        if(comLogin.getResult().getDispalyMsg().equals("success")){
                            ToastUtils.showShort("登录成功");
                            SpUtils.putString(getApplicationContext(),"comId",comLogin.getCompany().getId()+"");
                            ARouter.getInstance().build("/act/comact").withString("name",comLogin.getCompany().getName()).navigation();
                            finish();
                            dialog.dismiss();
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
