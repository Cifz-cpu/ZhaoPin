package com.lxy.zhaopin.myview;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.lxy.zhaopin.R;


/**自定义加载进度对话框
 * Created by Rem on 2017/1/9.
 */

public class LoadingAlertDialog extends Dialog {
    private TextView tv_text;

    public LoadingAlertDialog(final Activity context) {
        super(context);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.loading);
        tv_text = (TextView) findViewById(R.id.loading_text);
        setCanceledOnTouchOutside(false);
        try{
            int dividerID=context.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider=findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
            e.printStackTrace();
        }
//        setCancelable(false);
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                context.finish();
            }
        });
//        Animation myAlphaAnimation=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        setAni
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     *
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    public LoadingAlertDialog setMessage(String message) {
        if(message.equals("loading")){

        }else{
            tv_text.setText(message);
        }
        return this;
    }
}
