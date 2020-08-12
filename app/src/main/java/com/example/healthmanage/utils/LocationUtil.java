package com.example.healthmanage.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.healthmanage.base.BaseApplication;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;

import static com.example.healthmanage.utils.Constants.HTAG;

/**
 * @author Wang
 * @date 2019/2/19
 * des
 */
public class LocationUtil {


    public static String cityName;   //城市名
    private static Geocoder geocoder;  //此对象能通过经纬度来获取相应的城市等信息

    //通过地理坐标获取城市名 其中CN分别是city和name的首字母缩写
    public static void getCNBylocation(Context context) {
        geocoder = new Geocoder(context);
        //用于获取Location对象，以及其他
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        //实例化一个LocationManager对象
        locationManager = (LocationManager) context.getSystemService(serviceName);
        //provider的类型
        String provider = LocationManager.NETWORK_PROVIDER;

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);    //低精度   高精度：ACCURACY_FINE
        criteria.setAltitudeRequired(false);       //不要求海拔
        criteria.setBearingRequired(false);       //不要求方位
        criteria.setCostAllowed(false);      //不允许产生资费
        criteria.setPowerRequirement(Criteria.POWER_LOW);   //低功耗

        //通过最后一次的地理位置来获取Location对象
        if (ActivityCompat.checkSelfPermission(BaseApplication.getInstance(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BaseApplication.getInstance(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        String queryed_name = updateWithNewLocation(location);
        Log.d(HTAG, "getCNBylocation==========>: "+queryed_name);
        if((queryed_name!=null)&&(0!=queryed_name.length())){
            cityName = queryed_name;
            Log.d(HTAG, "getCNBylocation==========>: "+cityName);
        }
		/*
		第二个参数表示更新的周期，单位为毫秒，
		第三个参数的含义表示最小距离间隔，单位是米，设定每30秒进行一次自动定位
		*/
        locationManager.requestLocationUpdates(provider, 30000, 50, locationListener);
        //移除监听器，在只有一个widget的时候，这个还是适用的
        locationManager.removeUpdates(locationListener);
    }
    //方位改变是触发，进行调用
    private final static LocationListener locationListener = new LocationListener() {
        String tempCityName;
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
            tempCityName = updateWithNewLocation(null);
            if((tempCityName!=null)&&(tempCityName.length()!=0)){
                cityName = tempCityName;
            }
        }
        @Override
        public void onLocationChanged(Location location) {
            tempCityName = updateWithNewLocation(location);
            if((tempCityName!=null)&&(tempCityName.length()!=0)){
                cityName = tempCityName;
            }
        }
    };
    //更新location  return cityName
    private static String updateWithNewLocation(Location location){
        String mcityName = "";
        double lat = 0;
        double lng = 0;
        List<Address> addList = null;
        if(location!=null){
            lat = location.getLatitude();
            lng = location.getLongitude();
        }else{
            cityName = "无法获取地理信息";
        }
        try {
            addList = geocoder.getFromLocation(lat, lng, 1);    //解析经纬度
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(addList!=null&&addList.size()>0){
            for(int i=0;i<addList.size();i++){
                Address add = addList.get(i);
                mcityName += add.getLocality();
            }
        }
        if(mcityName.length()!=0){
            return mcityName.substring(0, (mcityName.length()-1));
        }else{
            return mcityName;
        }
    }
}
//    private static OnLocationChangeListener mListener;
//
//    private static MyLocationListener myLocationListener;
//
//    private static LocationManager mLocationManager;
//
//    private LocationUtil() {
//    }
//
//    /**
//     * 判断Gps是否可用
//     *
//     * @return {@code true}: 是<br>{@code false}: 否
//     */
//    public static boolean isGpsEnabled(Context context) {
//        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//    }
//
//    /**
//     * 判断定位是否可用
//     *
//     * @return {@code true}: 是<br>{@code false}: 否
//     */
//    public static boolean isLocationEnabled(Context context) {
//        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//    }
//
//    /**
//     * 打开Gps设置界面
//     */
//    public static void openGpsSettings(Context context) {
//        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 注册
//     * <p>使用完记得调用{@link #unregister()}</p>
//     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
//     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>}</p>
//     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>}</p>
//     * <p>如果{@code minDistance}为0，则通过{@code minTime}来定时更新；</p>
//     * <p>{@code minDistance}不为0，则以{@code minDistance}为准；</p>
//     * <p>两者都为0，则随时刷新。</p>
//     *
//     * @param minTime     位置信息更新周期（单位：毫秒）
//     * @param minDistance 位置变化最小距离：当位置距离变化超过此值时，将更新位置信息（单位：米）
//     * @param listener    位置刷新的回调接口
//     * @return {@code true}: 初始化成功<br>{@code false}: 初始化失败
//     */
//    public static boolean register(Context context, long minTime, long minDistance, OnLocationChangeListener listener) {
//        if (listener == null)
//            return false;
//        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        mListener = listener;
//        if (!isLocationEnabled(context)) {
//            Toast.makeText(context, "无法定位，请打开定位服务", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        String provider = mLocationManager.getBestProvider(getCriteria(), true);
//        Location location = mLocationManager.getLastKnownLocation(provider);
//
//        if (location != null)
//            listener.getLastKnownLocation(location);
//        if (myLocationListener == null)
//            myLocationListener = new MyLocationListener();
//        /* 绑定监听
//         * 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，前者是GPS,后者是GPRS以及WIFI定位
//         * 参数2，位置信息更新周期.单位是毫秒
//         * 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
//         * 参数4，监听
//         * 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
//         */
//        mLocationManager.requestLocationUpdates(provider, minTime, minDistance, myLocationListener);
//        return true;
//    }
//
//
//    /**
//     * 注销
//     */
//    public static void unregister() {
//        if (mLocationManager != null) {
//            if (myLocationListener != null) {
//                mLocationManager.removeUpdates(myLocationListener);
//                myLocationListener = null;
//            }
//            mLocationManager = null;
//        }
//    }
//
//    /**
//     * 设置定位参数
//     *
//     * @return {@link Criteria}
//     */
//    private static Criteria getCriteria() {
//        Criteria criteria = new Criteria();
//        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        //设置是否要求速度
//        criteria.setSpeedRequired(false);
//        // 设置是否允许运营商收费
//        criteria.setCostAllowed(false);
//        //设置是否需要方位信息
//        criteria.setBearingRequired(false);
//        //设置是否需要海拔信息
//        criteria.setAltitudeRequired(false);
//        // 设置对电源的需求
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//        return criteria;
//    }
//
//    /**
//     * 根据经纬度获取地理位置
//     *
//     * @param context   上下文
//     * @param latitude  纬度
//     * @param longitude 经度
//     * @return {@link Address}
//     */
//    public static Address getAddress(Context context, double latitude, double longitude) {
//        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//        // 韩国
////        longitude=128.825319;
////        latitude= 36.459188;
//
//        //非洲
////        longitude=40.251533;
////        latitude=9.172123;
//
//        //美国地址
////        longitude=-77.02;
////        latitude=38.54;
//
//        try {
//            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            if (addresses.size() > 0)
//                return addresses.get(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 根据经纬度获取所在国家
//     *
//     * @param context   上下文
//     * @param latitude  纬度
//     * @param longitude 经度
//     * @return 所在国家
//     */
//    public static String getCountryName(Context context, double latitude, double longitude) {
//        Address address = getAddress(context, latitude, longitude);
//        return address == null ? "unknown" : address.getCountryName();
//    }
//
//    /**
//     * 根据经纬度获取所在地
//     *
//     * @param context   上下文
//     * @param latitude  纬度
//     * @param longitude 经度
//     * @return 所在地
//     */
//    public static String getLocality(Context context, double latitude, double longitude) {
//        Address address = getAddress(context, latitude, longitude);
//        return address == null ? "unknown" : address.getLocality();
//    }
//
//    /**
//     * 根据经纬度获取所在街道
//     *
//     * @param context   上下文
//     * @param latitude  纬度
//     * @param longitude 经度
//     * @return 所在街道
//     */
//    public static String getStreet(Context context, double latitude, double longitude) {
//        Address address = getAddress(context, latitude, longitude);
//        return address == null ? "unknown" : address.getAddressLine(0);
//    }
//
//    private static class MyLocationListener implements LocationListener {
//        /**
//         * 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
//         *
//         * @param location 坐标
//         */
//        @Override
//        public void onLocationChanged(Location location) {
//            if (mListener != null) {
//                mListener.onLocationChanged(location);
//            }
//        }
//
//        /**
//         * provider的在可用、暂时不可用和无服务三个状态直接切换时触发此函数
//         *
//         * @param provider 提供者
//         * @param status   状态
//         * @param extras   provider可选包
//         */
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            if (mListener != null) {
//                mListener.onStatusChanged(provider, status, extras);
//            }
//            switch (status) {
//                case LocationProvider.AVAILABLE:
//                    Log.e("onStatusChanged", "当前GPS状态为可见状态");
//                    break;
//                case LocationProvider.OUT_OF_SERVICE:
//                    Log.e("onStatusChanged", "当前GPS状态为服务区外状态");
//                    break;
//                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    Log.e("onStatusChanged", "当前GPS状态为暂停服务状态");
//                    break;
//            }
//        }
//
//        /**
//         * provider被enable时触发此函数，比如GPS被打开
//         */
//        @Override
//        public void onProviderEnabled(String provider) {
//        }
//
//        /**
//         * provider被disable时触发此函数，比如GPS被关闭
//         */
//        @Override
//        public void onProviderDisabled(String provider) {
//        }
//    }
//
//    public interface OnLocationChangeListener {
//
//        /**
//         * 获取最后一次保留的坐标
//         *
//         * @param location 坐标
//         */
//        void getLastKnownLocation(Location location);
//
//        /**
//         * 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
//         *
//         * @param location 坐标
//         */
//        void onLocationChanged(Location location);
//
//        /**
//         * provider的在可用、暂时不可用和无服务三个状态直接切换时触发此函数
//         *
//         * @param provider 提供者
//         * @param status   状态
//         * @param extras   provider可选包
//         */
//        void onStatusChanged(String provider, int status, Bundle extras);//位置状态发生改变
//    }
