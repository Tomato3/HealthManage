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
import androidx.lifecycle.Observer;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.AppManager;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityMyInfoBinding;
import com.example.healthmanage.ui.activity.login.LoginActivity;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.dialog.EditTextDialog;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyInfoActivity extends BaseActivity<ActivityMyInfoBinding, MyInfoViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener, EasyPermissions.PermissionCallbacks {

    TitleToolBar titleToolBar = new TitleToolBar();
    private BottomSheet.Builder builder = null;
    private String[] permission = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //打开系统相机的意图
    private static final Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //打开系统相机RequestCode
    private static final int REQCODEOPENCAMEARA = 0x01;
    //打开系统相册RequestCode
    private static final int REQCODEOPENPICK = 0x02;

    EditTextDialog editTextDialog;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("账户信息");
        viewModel.setTitleToolBar(titleToolBar);

        Log.d(HTAG, "initData==========>: " + BaseApplication.getUserInfoBean().getInvitationCode());
        if (BaseApplication.getUserInfoBean().getInvitationCode() == null || BaseApplication.getUserInfoBean().getInvitationCode() == "") {
            viewModel.inviteCodeTip.setValue("生成邀请码");
        } else {
            viewModel.inviteCode.setValue(BaseApplication.getUserInfoBean().getInvitationCode());
            viewModel.inviteCodeTip.setValue("复制邀请码");
        }
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
        builder = new BottomSheet.Builder(this, R.style.MenuMyInfoStyle)
                .sheet(R.menu.menu_ny_info)
                .listener(((dialog, which) -> {
                    switch (which) {
                        case R.id.menu_take_picture:
                            if (!EasyPermissions.hasPermissions(this, permission[0])) {
                                EasyPermissions.requestPermissions(this, "请求必要的权限,拒绝权限可能会无法使用app", 0, permission);
                            } else {
                                openCamera();
                            }
                            break;
                        case R.id.menu_select_album:
                            if (!EasyPermissions.hasPermissions(this, permission[1])) {
                                EasyPermissions.requestPermissions(this, "请求必要的权限,拒绝权限可能会无法使用app", 0, permission);
                            } else {
                                choosePhoto();
                            }
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
        dataBinding.tvLicenseRegistrationNumber.setOnClickListener(this::onClick);
        dataBinding.tvInviteCode.setOnClickListener(this::onClick);
        dataBinding.tvTest.setOnClickListener(this::onClick);
        dataBinding.tvBindInviteCode.setOnClickListener(this::onClick);
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
            case R.id.tv_license_registration_number:
                editTextDialog = new EditTextDialog(this,
                        R.layout.dialog_write_invite_code, "");
                editTextDialog.show();
                break;
            case R.id.tv_test:
                startActivity(QualificationActivity.class);
                break;
            case R.id.tv_invite_code:
                viewModel.inviteCodeTip.observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.d(HTAG, "onChanged==========>: " + s);
                    }
                });
                Log.d(HTAG, "onClick==========>: " + viewModel.inviteCodeTip.getValue());
                if (viewModel.inviteCodeTip.getValue().equals("生成邀请码")) {
                    viewModel.getInviteCode();
                } else {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, viewModel.inviteCode.getValue());
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "Send to ..."));
                }
                break;
            case R.id.tv_bind_invite_code:
                editTextDialog = new EditTextDialog(this, R.layout.dialog_write_invite_code, "");
                editTextDialog.show();
                editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                    @Override
                    public void doCreate(List<String> content) {
                        viewModel.writeInviteCode(content.get(0));
                    }

                    @Override
                    public void doSend() {

                    }
                });
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case REQCODEOPENCAMEARA:
                openCamera();
                break;
            case REQCODEOPENPICK:
                choosePhoto();
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case REQCODEOPENCAMEARA:
                viewModel.getUiChangeEvent().getToastTxt().setValue("未获取相机权限!");
                break;
            case REQCODEOPENPICK:
                viewModel.getUiChangeEvent().getToastTxt().setValue("未获取存储权限!");
                break;
        }
    }

    private void openCamera() {
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
            startActivityForResult(takePhotoIntent, REQCODEOPENCAMEARA);//启动相机
        }
    }

    private void choosePhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
        startActivityForResult(intentToPickPic, REQCODEOPENPICK);
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
