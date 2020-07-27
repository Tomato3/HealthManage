package com.example.healthmanage.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.healthmanage.base.BaseApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 其他工具类
 */
public class ToolUtil {

    /**
     * 根据手机的分辨率从px(像素)的单位转成为dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 隐藏虚拟键盘
     *
     * @param v
     */
    public static void HideKeyboard(View v) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) BaseApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        v.clearFocus();
    }
}
