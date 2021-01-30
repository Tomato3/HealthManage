package com.example.healthmanage.ui.fragment.education;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentNewEducationBinding;
import com.example.healthmanage.ui.fragment.business.BusinessFragment;
import com.example.healthmanage.ui.fragment.educationchild.AFragment;
import com.example.healthmanage.ui.fragment.educationchild.BFragment;
import com.example.healthmanage.ui.fragment.educationchild.CFragment;
import com.example.healthmanage.ui.fragment.educationchild.DFragment;
import com.example.healthmanage.ui.fragment.educationchild.EFragment;
import com.example.healthmanage.ui.fragment.educationchild.FFragment;
import com.example.healthmanage.ui.fragment.qualification.FirstStepFragment;
import com.google.android.material.tabs.TabLayout;

public class NewEducationFragment extends BaseFragment<FragmentNewEducationBinding, NewEducationViewModel> {


    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
//        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                TextView textView = new TextView(getContext());
//                textView.setGravity(Gravity.CENTER);
//                float selectSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, getResources().getDisplayMetrics());
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, selectSize);
//                textView.setTextColor(getResources().getColor(R.color.colorWhite));
//                textView.setText(tab.getText());
//                tab.setCustomView(textView);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.setCustomView(null);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeFM(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new AFragment()).commit();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    private void changeFM(int position) {
        switch (position) {
            case 0:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new AFragment()).commit();
                break;
            case 1:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new BFragment()).commit();
                break;
            case 2:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new CFragment()).commit();
                break;
            case 3:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new DFragment()).commit();
                break;
            case 4:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new EFragment()).commit();
                break;
            case 5:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new FFragment()).commit();
                break;
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_education;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}