package com.example.healthmanage.ui.activity.integral.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityAddressWebviewBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.ui.LocalJsonResolutionUtils;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.response.AddressBean;

import static android.content.ContentValues.TAG;

/**
 * desc:选择地址
 * date:2021/7/19 14:56
 * author:bWang
 */
public class ChooseAddressActivity extends BaseActivity<ActivityAddressWebviewBinding, IntegralViewModel> {
    private Context mContext;
    private WebSettings webSettings;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initData() {
        mContext = ChooseAddressActivity.this;
        dataBinding.webviewAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        dataBinding.webviewAddress.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return false;
            }
        });
        webSettings = dataBinding.webviewAddress.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);//存储的最大容量
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        dataBinding.webviewAddress.setWebViewClient(new MyWebViewClient());
        dataBinding.webviewAddress.loadUrl("http://shop.yi-shoukang.com/pages/address/index");
//        dataBinding.webviewAddress.loadUrl("http://192.168.199.148:8081/pages/address/index");
//        webView.loadUrl("http://www.baidu.com");

        dataBinding.webviewAddress.addJavascriptInterface(this, "androidjs");
        dataBinding.webviewAddress.setWebContentsDebuggingEnabled(true);
        dataBinding.webviewAddress.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.e(TAG, "onReceivedError: " + errorCode + "====>" + description + "====>" + failingUrl);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e(TAG, "onPageStarted: " + url);
        }

    }

    @JavascriptInterface
    public String setMessage() {
        Log.e(TAG, "setMessage: " + "sendToken");
        return BaseApplication.getToken();
    }

    @JavascriptInterface
    public void getAddress(String addressData){
//        SPUtil.saveChooseAddress(addressData,mContext);
        AddressBean addressBean = LocalJsonResolutionUtils.JsonToObject(addressData,AddressBean.class);
        Intent intent = new Intent();
        intent.putExtra("addressBean",addressBean);
        setResult(RESULT_OK,intent);
        finish();
    }

    @JavascriptInterface
    public void finishActivity(){
        finish();
    }

//    @JavascriptInterface
//    public void saveAddress(String addressData){
//        SPUtil.saveAddress(addressData,mContext);
//    }
//
//    @JavascriptInterface
//    public void deleteAddress(){
//        SPUtil.deleteAddress(mContext);
//    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_address_webview;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
            if (dataBinding.webviewAddress.canGoBack())
            {
                dataBinding.webviewAddress.goBack(); //goBack()表示返回WebView的上一页面
            }else
            {
                finish();
            }
            return true;

        }
        return false;
    }

}
