package com.example.healthmanage.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityMainBinding;
import com.example.healthmanage.ui.activity.login.LoginNewActivity;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.ui.fragment.business.NewBusinessFragment;
import com.example.healthmanage.ui.fragment.education.NewEducationFragment;
import com.example.healthmanage.ui.fragment.home.NewHomeFragment;
import com.example.healthmanage.ui.fragment.mall.MallFragment;
import com.example.healthmanage.ui.fragment.my.NewMyFragment;
import com.example.healthmanage.utils.StatusBarUitils;
import com.example.healthmanage.widget.TitleToolBar;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private List<Fragment> mFragments;
    private int[] tabIconsNormal = {R.drawable.activity_main_bottom_home_normal,
            R.drawable.activity_main_bottom_business_normal,
            R.drawable.activity_main_bottom_education_normal,
            R.drawable.activity_main_bottom_mall_normal,
            R.drawable.activity_main_bottom_my_normal};
    private int[] tabIconsSelected = {R.drawable.activity_main_bottom_home_selected,
            R.drawable.activity_main_bottom_business_selected,
            R.drawable.activity_main_bottom_education_selected,
            R.drawable.activity_main_bottom_mall_selected,
            R.drawable.activity_main_bottom_my_selected};
    private String[] tabTexts = {"首页", "业务服务", "教育培训", "商城", "我的"};
    private int colorNormal = 0xFF6C6C6C;
    private int colorSelected = 0xFF00A2FF;
    private String phone;
    private String pwd;


    @Override
    protected void initData() {

        viewModel.setUserInfo(BaseApplication.getToken());
        StatusBarUitils.setStatusBar(R.color.colorBlue, false, this);
        initFragment();
        initBottomTab();

        phone = getIntent().getStringExtra("phone");
        pwd = getIntent().getStringExtra("pwd");
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)){
            EMClient.getInstance().login(phone, pwd, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    Log.e("login======","EMC登录成功");
                }

                @Override
                public void onError(int code, String error) {
                    Log.e("login=======",error);
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }


    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
    }

    /**
     * 初始化底部Tab
     */
    private void initBottomTab() {

        NavigationController navigationController = dataBinding.pagerBottomTab.custom()
                .addItem(newItem(tabIconsNormal[0], tabIconsSelected[0], tabTexts[0]))
                .addItem(newItem(tabIconsNormal[1], tabIconsSelected[1], tabTexts[1]))
                .addItem(newItem(tabIconsNormal[2], tabIconsSelected[2], tabTexts[2]))
                .addItem(newItem(tabIconsNormal[3], tabIconsSelected[3], tabTexts[3]))
                .addItem(newItem(tabIconsNormal[4], tabIconsSelected[4], tabTexts[4]))
                .build();
//        navigationController.setMessageNumber(0,20);
        //底部tab选择监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                changeFragment(index);
                changeTitleBar(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    /**
     * 切换TitleToolbar
     *
     * @param index
     */
    private void changeTitleBar(int index) {
        TitleToolBar titleToolBar = new TitleToolBar();
        switch (index) {
            case 0:
                titleToolBar.setTitle("");
                titleToolBar.setRightIconVisible(false);
                titleToolBar.setRightIconSrc(R.drawable.activity_main_title_news);
                viewModel.setTitleToolBar(titleToolBar);
                dataBinding.toolbar.tvTitle.setText("");
                dataBinding.toolbar.ivRight.setVisibility(View.INVISIBLE);
                dataBinding.toolbar.ivRight.setImageResource(R.drawable.activity_main_title_news);

                dataBinding.toolbar.toolbarTitle.setVisibility(View.GONE);
                break;
            case 1:
                dataBinding.toolbar.toolbarTitle.setVisibility(View.VISIBLE);

                dataBinding.toolbar.ivRight.setVisibility(View.GONE);
                dataBinding.toolbar.tvTitle.setText("业务服务");
                break;
            case 2:
                dataBinding.toolbar.toolbarTitle.setVisibility(View.VISIBLE);

                dataBinding.toolbar.ivRight.setVisibility(View.GONE);
                dataBinding.toolbar.tvTitle.setText("教育培训");
                break;
            case 3:
                dataBinding.toolbar.toolbarTitle.setVisibility(View.VISIBLE);

                dataBinding.toolbar.ivRight.setVisibility(View.GONE);
                dataBinding.toolbar.tvTitle.setText("商城");
                break;
            case 4:
                dataBinding.toolbar.toolbarTitle.setVisibility(View.VISIBLE);

                dataBinding.toolbar.tvTitle.setText("");
                dataBinding.toolbar.ivRight.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 底部tab
     *
     * @param normal
     * @param selected
     * @param text
     * @return
     */
    private BaseTabItem newItem(int normal, int selected, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(normal, selected, text);
        normalItemView.setTextDefaultColor(colorNormal);
        normalItemView.setTextCheckedColor(colorSelected);
        return normalItemView;
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new NewHomeFragment());
        mFragments.add(new NewBusinessFragment());
        mFragments.add(new NewEducationFragment());
        mFragments.add(new MallFragment());
        mFragments.add(new NewMyFragment());

        changeFragment(0);
        changeTitleBar(0);
    }

    /**
     * 切换fragment
     *
     * @param position
     */
    private void changeFragment(int position) {
        hideAllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(position + "");
        if (currentFragment != null) {
            transaction.show(currentFragment);
        } else {
            currentFragment = mFragments.get(position);
            transaction.add(R.id.frameLayout, currentFragment, position + "");
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有Fragment
     */
    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(i + "");
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            if (EMClient.getInstance().isLoggedInBefore()){
                EMClient.getInstance().logout(true);
            }
        }
        return true;
    }
}
