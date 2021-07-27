package com.example.healthmanage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthmanage.ui.activity.integral.response.AddressBean;


/**
 * SharePreference 工具类
 */
public final class SPUtil {

    public static void saveSignName(String url,Context context){
        SharedPreferences share = context.getSharedPreferences("signName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("signUrl", url);
        editor.apply();
    }
    public static String getSignName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("signName", Context.MODE_PRIVATE);
        return sp.getString("signUrl", "");
    }


    /**
     * 保存号码
     *
     * @param phone
     * @param context
     */
    public static void savePhone(String phone, Context context) {
        SharedPreferences share = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("Phone", phone);
        editor.apply();
    }

    /**
     * 获取号码
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        return sp.getString("Phone", "");
    }

    /**
     * 保存密码
     *
     * @param password
     * @param context
     */
    public static void savePassword(String password, Context context) {
        SharedPreferences share = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("Password", password);
        editor.apply();
    }

    /**
     * 获取密码
     *
     * @param context
     * @return
     */
    public static String getPassword(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        return sp.getString("Password", "");
    }

    /**
     * 保存自动登录状态
     *
     * @param autoLogin
     * @param context
     */
    public static void saveAutoLogin(boolean autoLogin, Context context) {
        SharedPreferences share = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean("AutoLogin", autoLogin);
        editor.apply();
    }

    /**
     * 获取自动登录状态
     *
     * @param context
     * @return
     */
    public static boolean getAutoLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        return sp.getBoolean("AutoLogin", false);
    }

    /**
     * 保存角色Id
     *
     * @param roleId
     * @param context
     */
    public static void saveRoleId(String roleId, Context context) {
        SharedPreferences share = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("RoleId", roleId);
        editor.apply();
    }

    /**
     * 获取角色Id
     *
     * @param context
     * @return
     */
    public static String getRoleId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        return sp.getString("RoleId", "");
    }

    /**
     * 保存角色Id
     *
     * @param id
     * @param context
     */
    public static void saveId(String id, Context context) {
        SharedPreferences share = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("id", id);
        editor.apply();
    }

    /**
     * 获取角色Id
     *
     * @param context
     * @return
     */
    public static String getId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
        return sp.getString("id", "");
    }

    /**
     * 保存上次选择的地址
     * @param addressData
     * @param context
     */
    public static void saveChooseAddress(String addressData,Context context){
        SharedPreferences sp = context.getSharedPreferences("chooseAddressData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("chooseAddressData",addressData);
        editor.apply();
    }

    public static String getChooseAddressData(Context context){
        SharedPreferences sp = context.getSharedPreferences("chooseAddressData",Context.MODE_PRIVATE);
        return sp.getString("chooseAddressData","");
    }

    /**
     * 保存默认地址
     * @param addressData
     * @param context
     */
    public static void saveAddress(String addressData,Context context){
        SharedPreferences sp = context.getSharedPreferences("addressData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("addressData",addressData);
        editor.apply();
    }

    public static String getAddressData(Context context){
        SharedPreferences sp = context.getSharedPreferences("addressData",Context.MODE_PRIVATE);
        return sp.getString("addressData","");
    }

    /**
     * 删除地址
     * @param context
     */
    public static void deleteAddress(Context context){
        SharedPreferences sp = context.getSharedPreferences("addressData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }


}
