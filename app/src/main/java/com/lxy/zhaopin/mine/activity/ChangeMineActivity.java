package com.lxy.zhaopin.mine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.load.model.ImageVideoWrapperEncoder;
import com.google.gson.Gson;
import com.lxy.zhaopin.BaseActivity;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.bean.ChangeBack;
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
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/4/30.
 */

@Route(path = "/act/change")
public class ChangeMineActivity extends BaseActivity {
    @BindView(R.id.mine_top)
    TopBarView mineTop;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_nick)
    EditText etNick;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_changemine;
    }

    @Override
    protected void initView(Bundle bundle) {
        mineTop.setTitle("编辑资料");
        mineTop.setOntopBarClickListener(new TopBarView.onTopBarClickListener() {
            @Override
            public void backClick() {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_save)
    public void click(View view){
        String id = SpUtils.getString(getApplicationContext(),"userId","");
        Map<String,String> map = new HashMap<>();
        if(!etName.getText().toString().equals("")){
            map.put("name",etName.getText().toString().trim());
        }
        if(!etMail.getText().toString().equals("")){
            map.put("email",etMail.getText().toString().trim());
        }
        if(!etNick.getText().toString().equals("")){
            map.put("nickName",etNick.getText().toString().trim());
        }
        if(!etSex.getText().toString().equals("")){
            if(etSex.getText().toString().trim().equals("男")){
                map.put("gender",1+"");
            }else if(etSex.getText().toString().trim().equals("女")){
                map.put("gender",0+"");
            }else{
                ToastUtils.showShort("请正确输入性别");
            }
        }

        map.put("id",id);
        String url = Config.BaseUrl+"zhaopin/v1/user/"+id;
        JSONObject jsonObject = new JSONObject(map);
        String string = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),string);
        OkHttpUtils
                .patch()
                .url(url)
                .requestBody(requestBody)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("error",e.getMessage());
                        ToastUtils.showShort("网络错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        ChangeBack changeBack = gson.fromJson(response,ChangeBack.class);
                        if(changeBack.getResult().getDispalyMsg().equals("success")){
                            ToastUtils.showShort("保存成功");
                            SpUtils.putString(getApplicationContext(),"usernick",changeBack.getUser().getName());

                            finish();
                        }else{
                            ToastUtils.showShort("保存失败");
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
