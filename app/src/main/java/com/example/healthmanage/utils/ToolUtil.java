package com.example.healthmanage.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.healthmanage.base.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

/**
 * 其他工具类
 */
public class ToolUtil {

    //根据手机的分辨率从px(像素)的单位转成为dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //获取当前时间 格式：yyyy年MM月dd日 HH:mm:ss
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //隐藏虚拟键盘
    public static void hideKeyboard(View v) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) BaseApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        v.clearFocus();
    }

    //获取当前日期之前第七天 格式：yyyy-MM-dd
    public static String getStartTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);
        Log.d(HTAG, "initData==========>: " + day);
        return day;
    }

    //获取当前日期 格式：yyyy-MM-dd
    public static String getEndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获取当前日期 格式：list(year,month,day)
    public static List<Integer> currentDate() {
        int mYear, mMonth, mDay;
        List<Integer> date = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        date.add(mYear);
        date.add(mMonth);
        date.add(mDay);
        return date;
    }

    //比较两个日期大小
    public static boolean compareDate(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }
}
