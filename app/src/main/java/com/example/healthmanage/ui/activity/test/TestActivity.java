package com.example.healthmanage.ui.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityTestBinding;

public class TestActivity extends BaseActivity<ActivityTestBinding, TestViewModel> implements View.OnClickListener {
    @Override
    protected void initData() {

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvTest.setOnClickListener(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_test;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, " ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Send to ..."));
                break;
        }
    }
}
