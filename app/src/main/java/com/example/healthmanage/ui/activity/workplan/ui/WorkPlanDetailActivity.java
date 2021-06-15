package com.example.healthmanage.ui.activity.workplan.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityWorkDetailBinding;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 工作计划详情
 */
public class WorkPlanDetailActivity extends BaseActivity<ActivityWorkDetailBinding,WorkPlanViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener{
    private TitleToolBar titleToolBar = new TitleToolBar();
    private String content;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        titleToolBar.setTitle("工作计划详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        content = getIntent().getStringExtra("planContent");
        initWebView(content);
    }

    private void initWebView(String content) {
        WebSettings settings = dataBinding.webViewPlan.getSettings();

        //settings.setUseWideViewPort(true);//调整到适合webview的大小，不过尽量不要用，有些手机有问题
        settings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
        dataBinding.webViewPlan.setVerticalScrollBarEnabled(false);//不能垂直滑动
        dataBinding.webViewPlan.setHorizontalScrollBarEnabled(false);//不能水平滑动
        settings.setTextSize(WebSettings.TextSize.NORMAL);//通过设置WebSettings，改变HTML中文字的大小
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        //设置WebView属性，能够执行Javascript脚本
        dataBinding.webViewPlan.getSettings().setJavaScriptEnabled(true);//设置js可用
        dataBinding.webViewPlan.setWebViewClient(new WebViewClient());
        dataBinding.webViewPlan.addJavascriptInterface(new AndroidJavaScript(getApplication()), "android");//设置js接口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局

/******  22222222  ***********************************************************************/
        content = "</Div><head><style>img{ width:100% !important;}</style></head>" + content;//给图片设置一个样式，宽满屏
/******  2222222222  ***********************************************************************/

        dataBinding.webViewPlan.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    /**
     * AndroidJavaScript
     * 本地与h5页面交互的js类，这里写成内部类了
     * returnAndroid方法上@JavascriptInterface一定不能漏了
     */
    private class AndroidJavaScript {
        Context mContxt;

        public AndroidJavaScript(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void returnAndroid(String name) {//从网页跳回到APP，这个方法已经在上面的HTML中写上了
            if (name.isEmpty() || name.equals("")) {
                return;
            }
            Toast.makeText(getApplication(), name, Toast.LENGTH_SHORT).show();
            //这里写你的操作///////////////////////
            //MainActivity就是一个空页面，不影响
            Intent intent = new Intent(WorkPlanDetailActivity.this, WorkPlanActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_work_detail;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
