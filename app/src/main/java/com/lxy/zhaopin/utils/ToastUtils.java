package com.lxy.zhaopin.utils;


import android.view.Gravity;
import android.widget.Toast;

import com.lxy.zhaopin.GlobeApplication;


/**
 * Created by cifz on 2017/2/22.
 * e_mail wangzhen1798@gmail.com
 */

public class ToastUtils {
    private static Toast toast;

    public static void showLong(String info) {
        toast = Toast.makeText(GlobeApplication.getApplication(), info, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showShort(String info) {
        toast = Toast.makeText(GlobeApplication.getApplication(), info, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
