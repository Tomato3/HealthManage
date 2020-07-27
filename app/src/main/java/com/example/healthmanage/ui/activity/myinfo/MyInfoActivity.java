package com.example.healthmanage.ui.activity.myinfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.AppManager;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMyInfoBinding;
import com.example.healthmanage.ui.activity.login.LoginActivity;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyInfoActivity extends BaseActivity<ActivityMyInfoBinding, MyInfoViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener, EasyPermissions.PermissionCallbacks {

    TitleToolBar titleToolBar = new TitleToolBar();
    private BottomSheet.Builder builder = null;
    private String permission = Manifest.permission.CAMERA;
    //打开系统相机的意图
    private static final Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //打开系统相机RequestCode
    private static final int REQCODEOPENCAMEARA = 0x01;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("账户信息");
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
        builder = new BottomSheet.Builder(this, R.style.MenuMyInfoStyle)
                .sheet(R.menu.menu_ny_info)
                .listener(((dialog, which) -> {
                    switch (which) {
                        case R.id.menu_take_picture:
                            if (!EasyPermissions.hasPermissions(this, permission)) {
                                EasyPermissions.requestPermissions(this, "请求必要的权限,拒绝权限可能会无法使用app", 0, permission);
                            } else {
                                openCamera();
                            }
                            break;
                        case R.id.menu_select_album:
                            Log.d(HTAG, "initViewParameters==========>: ");
                            break;
                        case R.id.menu_cancel:
                            Log.d(HTAG, "initViewParameters==========>: ");
                            break;
                    }
                }));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);//将第三方权限框架与activity绑定
    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvFinish.setOnClickListener(this::onClick);
        dataBinding.rlAvatar.setOnClickListener(this::onClick);
    }


    @Override
    public void onRightIconClick() {
    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_finish:
                AppManager.getAppManager().finishAllActivity();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                startActivity(LoginActivity.class);
                break;
            case R.id.rl_avatar:
                if (builder != null) {
                    builder.show();
                }
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        openCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        viewModel.getUiChangeEvent().getToastTxt().setValue("未获取相机权限!");
    }

    private void openCamera() {
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
            startActivityForResult(takePhotoIntent, REQCODEOPENCAMEARA);//启动相机
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_info;
    }
}
