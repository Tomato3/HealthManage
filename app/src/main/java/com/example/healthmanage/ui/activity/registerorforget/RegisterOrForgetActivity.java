package com.example.healthmanage.ui.activity.registerorforget;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityRegisterOrForgetBinding;

public class RegisterOrForgetActivity extends BaseActivity<ActivityRegisterOrForgetBinding, RegisterOrForgetViewModel> {

    @Override
    protected void initData() {
        int type = getIntent().getExtras().getInt("Type");
        viewModel.type.setValue(type);
        if (type == 2) {
            viewModel.title = "设置新密码";
            viewModel.operationTxt = "确认重置";
        }
    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        //密码可见切换
        dataBinding.cbEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dataBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    dataBinding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                dataBinding.etPassword.setSelection(dataBinding.etPassword.getText().length());
            }
        });

        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_register_or_forget;
    }

}
