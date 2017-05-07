package com.lxy.zhaopin.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.bean.SaveWJL;
import com.lxy.zhaopin.myview.TopBarView;
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
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/4/23.
 */

@Route(path = "/act/wjl")
public class WJLActivity extends BaseActivity {

    @BindView(R.id.wjl_top)
    TopBarView wjlTop;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_education)
    EditText etEducation;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_want)
    EditText etWant;
    @BindView(R.id.et_min)
    EditText etMin;
    @BindView(R.id.et_max)
    EditText etMax;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_ex)
    EditText etEx;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.et_jlm)
    EditText etJlm;
    String id;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wjl;
    }

    @Override
    protected void initView(Bundle bundle) {
        wjlTop.setTitle("编辑微简历");
        tintManager.setTintColor(Color.parseColor("#F8F8F8"));
        wjlTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_save)
    public void click(View view) {

        id = SpUtils.getString(getApplicationContext(),"userId","");

        String name = etName.getText().toString().trim();
        String education = etEducation.getText().toString().trim();
        String expectCity = etCity.getText().toString().trim();
        String expectPosition = etWant.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String min = etMin.getText().toString().trim();
        String max = etMax.getText().toString().trim();
        String jlm = etJlm.getText().toString().trim();
        String ex = etEx.getText().toString().trim();

        if(name.equals("")||education.equals("")||expectCity.equals("")||expectPosition.equals("")||phone.equals("")||email.equals("")||min.equals("")||max.equals("")||jlm.equals("")||ex.equals("")){
            ToastUtils.showShort("请全部输入");
        }else{
            String connect = "phone:"+phone+","+"email:"+email;
            String salary = "min:"+min+","+"max:"+max;
            Map<String,String> map = new HashMap<>();
            map.put("education",education);
            map.put("expectCity",expectCity);
            map.put("birthday","145784003");
            map.put("expectPosition",expectPosition);
            map.put("expectSalary",salary);
            map.put("experience",ex);
            map.put("mayContact",connect);
            map.put("name",jlm);
            map.put("userId",id);
            map.put("userName",name);

            JSONObject jsonObject = new JSONObject(map);

            creatJl(jsonObject.toString());

        }

    }

    private void creatJl(String s) {
        String url = Config.BaseUrl+"zhaopin/v1/user/"+id+"/resume";
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(s)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("eeeeeee",e.getMessage());
                        ToastUtils.showShort("保存失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SaveWJL whl = gson.fromJson(response,SaveWJL.class);
                        if(whl.getResult().getDispalyMsg().equals("success")){
                            ToastUtils.showShort("保存成功");
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
