package com.example.healthmanage.ui.fragment.business;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentBusinessBinding;
import com.example.healthmanage.widget.DropdownBar;

public class BusinessFragment extends BaseFragment<FragmentBusinessBinding, BusinessViewModel> {

    DropdownBar dropdownBar = new DropdownBar("休闲生活", false,false,false);
    private String[] news = new String[]{"预防新冠病毒有妙招！", "老年人如何提高体质？", "美国流感持续时间较长的原因。"};

    @Override
    protected void initData() {
        viewModel.setDropdownBar(dropdownBar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
        for (int i = 0; i < news.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.view_flipper, null);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            textView.setText(news[i]);
            dataBinding.viewFlipper.addView(view);
        }
        dataBinding.viewFlipper.setFlipInterval(2000);
        dataBinding.viewFlipper.startFlipping();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_business;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
