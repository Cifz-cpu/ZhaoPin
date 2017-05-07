package com.lxy.zhaopin.utils;

import android.app.Activity;
import android.app.ProgressDialog;


/**
 * Created by cifz on 2017/4/5.
 * e_mail wangzhen1798@gmail.com
 */

public class ShowProgressDialogUtils {

    static ProgressDialog progressDialog;

    public static void ShowYuyueDialog(Activity act,String message){
        progressDialog = new ProgressDialog(act);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void cancleDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
