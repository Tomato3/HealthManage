package com.example.healthmanage.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;

import java.lang.reflect.Method;
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

    private static String baseImgUrl = "http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg";

    //根据手机的分辨率从px(像素)的单位转成为dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //获取当前时间 格式：yyyy年MM月dd日 HH:mm:ss
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        return day;
    }

    //获取当前日期 格式：yyyy-MM-dd
    public static String getEndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获取当前日期时间
    public static String getCurTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    //时间戳转成日期
    public static String timeStampToDate(String date,String format){
        if (date == null || date.isEmpty() || date.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(date)));//毫秒需加000
    }

    //时间戳转成日期
    public static String timeStampToMonth(String date,String format){
        if (date == null || date.isEmpty() || date.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "MM月dd日 HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(date)));//毫秒需加000
    }

    public static String timeStampToTime(String date,String format){
        if (date == null || date.isEmpty() || date.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(date)));//毫秒需加000
    }

    //将时间戳转成日期格式
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));//毫秒需加000
    }

    public static String formatDate(String time) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        return now;
    }

    //判断是否为null
    public static String isNull(Object content) {
        if (content == null) {
            return "暂无数据";
        } else {
            return String.valueOf(content);
        }
    }

    //
    @BindingAdapter("android:url")
    public static void setUrl(ImageView view, String imgUrl) {
        if (imgUrl != null && !"".equals(imgUrl)) {
            Glide.with(view.getContext()).asBitmap().load(imgUrl).apply(RequestOptions.circleCropTransform()).into(view);
        } else {
            Glide.with(view.getContext()).asBitmap().load(baseImgUrl).apply(RequestOptions.circleCropTransform()).into(view);
        }
    }

    /**
     * 带动画的跳转
     *
     * @param context
     * @param toActivity
     * @param imageView
     */
    public static void startActivityWithTransition(Activity context, Class toActivity,
                                                   ImageView imageView, Bundle bundle) {
        Intent intent = new Intent(context, toActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView,
                        context.getString(R.string.transition_logo_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    public static boolean isMobileNO(String phone) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }

    /**
     * Desc: 获取虚拟按键高度 放到工具类里面直接调用即可
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        Log.e(HTAG, "虚拟键盘高度" + result);
        return result;
    }


    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }
}
