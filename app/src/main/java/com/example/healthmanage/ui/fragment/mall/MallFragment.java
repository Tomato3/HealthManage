package com.example.healthmanage.ui.fragment.mall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentMallBinding;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * 商城
 */
public class MallFragment extends BaseFragment<FragmentMallBinding,MallViewModel> {
    private WebView mWebView;
    private WebSettings webSettings;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        mWebView = getActivity().findViewById(R.id.webview_mall);
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mWebView.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return false;
            }
        });
        webSettings = mWebView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);//存储的最大容量
        String appCachePath = getContext().getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl("http://shop.yi-shoukang.com");
//        mWebView.loadUrl("http://192.168.199.148:8081");

        mWebView.addJavascriptInterface(this, "androidjs");
        mWebView.setWebContentsDebuggingEnabled(true);
    }

    @JavascriptInterface
    public String setMessage() {
        Log.e(TAG, "setMessage: " + "sendToken");
        return BaseApplication.getToken();
    }

    @JavascriptInterface
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }


    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_mall;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    private String referer = "http://shop.yi-shoukang.com";
//    private String referer = "http://192.168.199.148:8081";

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    return super.shouldOverrideUrlLoading(view, url);
            Log.d(TAG, "aaaa: " + url);
            //微信支付
            try {
                if (url.startsWith("weixin://") || url.startsWith("alipays://")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    Log.e(TAG, "微信: " + url);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }

            if (url.contains("https://wx.tenpay.com")) {
                Map<String, String> extraHeaders = new HashMap<>();
                extraHeaders.put("Referer", referer);
                view.loadUrl(url, extraHeaders);
                Log.e(TAG, "extraHeaders: " + url);
                return true;
            }
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

}
