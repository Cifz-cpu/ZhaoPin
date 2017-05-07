package com.lxy.zhaopin.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lxy.zhaopin.R;
import com.lxy.zhaopin.myview.TopBarView;
import com.lxy.zhaopin.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MineFragment extends Fragment {

    @BindView(R.id.mine_top)
    TopBarView mineTop;
    @BindView(R.id.user_img)
    CircleImageView userImg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.weijianli)
    RelativeLayout weijianli;
    @BindView(R.id.zhiweishenqing)
    RelativeLayout zhiweishenqing;
    @BindView(R.id.setting)
    RelativeLayout setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        mineTop.setTitle("个人主页");
        mineTop.setBackBtn(true);
        String nick = SpUtils.getString(getActivity(),"usernick","");
        userName.setText(nick);
        return view;
    }

    @OnClick({R.id.weijianli,R.id.user_img,R.id.setting,R.id.zhiweishenqing})
    public void click(View v){
        switch (v.getId()){
            case R.id.weijianli:
                ARouter.getInstance().build("/act/wjl").navigation();
                break;
            case R.id.zhiweishenqing:
                ARouter.getInstance().build("/act/shenqing").navigation();
                break;
            case R.id.setting:
                ARouter.getInstance().build("/act/set").navigation();
                break;
        }
    }

}
