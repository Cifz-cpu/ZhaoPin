package com.lxy.zhaopin.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.zhaopin.R;


/**
 * Created by cifz on 2017/2/22.
 * e_mail wangzhen1798@gmail.com
 */

public class TopBarView extends RelativeLayout{

    private ImageView back;
    private TextView Title;
    private onTopBarClickListener ontopBarClickListener;
    private RelativeLayout relativeLayout;

    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化view
     * @param context
     */
    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.view_topbar,this,true);
        back = (ImageView) findViewById(R.id.back);
        Title = (TextView) findViewById(R.id.title);
        relativeLayout = (RelativeLayout) findViewById(R.id.back_layout);
        relativeLayout.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                ontopBarClickListener.backClick();
            }
        });

    }

    public void setBackBtn(boolean flag){
        if(flag){
            back.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setOntopBarClickListener(onTopBarClickListener ontopBarClickListener) {
        this.ontopBarClickListener = ontopBarClickListener;
    }

    public interface onTopBarClickListener{
        void backClick();
    }

}
