package com.example.healthmanage.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import java.util.List;


public class LocationUtil {
    private Context context;
    private LocationChangedListener listener;
    /**
     * LocationListener监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    private LocationManager locationManager;
    private int locationTimes;

    public LocationUtil(Context context) {
        this.context = context;
    }

    public void setListener(LocationChangedListener listener) {
        this.listener = listener;
    }

    public void getLocation() {
        String locationProvider;
        //获取地理位置管理器
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
//            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
//        //获取Location
//        Location location = locationManager.getLastKnownLocation(locationProvider);
//        if (location != null) {
//            //不为空,显示地理位置经纬度
//            showLocation(location);
//        }
//        //监视地理位置变化
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
    }

    /**
     * 显示地理位置经度和纬度信息
     *
     * @param location
     */
    private void showLocation(Location location) {
//        if(locationTimes > 1 && locationManager != null){
        locationManager.removeUpdates(locationListener);
//            return;
//        }
//        String locationStr = "纬度：" + location.getLatitude() + "\n"
//                + "经度：" + location.getLongitude();
//        updateVersion(location.getLatitude() + "", location.getLongitude() + "");
//        ToastUtil.showShort(context, location.getLatitude() + ","+ location.getLongitude() );
        if (listener != null) {
            listener.onLocationChanged(location.getLatitude() + "," + location.getLongitude());
        }
    }

    //wd 纬度
//jd 经度
//    public void updateVersion(String wd, String jd) {
//        String path = "http://api.map.baidu.com/geocoder?output=json&location=" + wd + "," + jd + "&key="LRCNlmj6eRo0SDCSyrRBIGLfllQzvLbm";
//    }
    public interface LocationChangedListener {
        void onLocationChanged(String location);
    }

    @SuppressLint("MissingPermission")
    public static String getLngAndLat(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getAllProviders();
        for (String provider : providers) {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                return latitude + "," + longitude;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0F, locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (lastKnownLocation != null) {
            double latitude = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();

            return latitude + "," + longitude;
        } else {
            return "";
        }
    }


    private static LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    };

    /**
     * 检查定位服务有没有开启，没有开启就引导用户去开启定位服务
     */
    private void checkLocation() {
        if (!locationIsOpen(context)) {
            //没有打开则弹出对话框
            //下面是个dialog，请自己写，我的封装dialog不贴出来
//            val tipDialog = TipDialog(this)
//                    .setTitle("提示")
//                    .setContent("当前应用需要打开定位功能。\n\n请点击\"设置\"-\"定位服务\"-打开定位功能。")
//                    .setLeftBtnText("取消")
//                    .setRightBtnText("去设置")
//            tipDialog.setOnBtnClickListener(object : TipDialog.OnDialogBtnClickListener {
//                override fun onLeftBtnClicked(paramTipDialog: TipDialog?) {
//                    tipDialog.dismiss()
//                }
//
//                override fun onRightBtnClicked(paramTipDialog: TipDialog?) {
//                    tipDialog.dismiss()
//                    //跳转GPS设置界面
//                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivityForResult(intent, GPS_REQUEST_CODE);
//                }
//            }).show()
        }
    }

    public static boolean locationIsOpen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean newWorkLocationEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean GPSLocationEnabled1 =
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return newWorkLocationEnabled || GPSLocationEnabled1;
    }

}
