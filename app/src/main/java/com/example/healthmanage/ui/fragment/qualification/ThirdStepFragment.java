package com.example.healthmanage.ui.fragment.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentStepThirdBinding;
import com.example.healthmanage.ui.activity.main.MainActivity;
import com.example.healthmanage.utils.SPUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 17:00
 */
public class ThirdStepFragment extends BaseFragment<FragmentStepThirdBinding, ThirdStepViewModel> {
    MutableLiveData<String> type = new MutableLiveData<>();

    @Override
    protected void initData() {
        type.setValue(getActivity().getIntent().getStringExtra("type"));
    }

    @Override
    protected void initListener() {
//        dataBinding.btnToMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),MainActivity.class);
//                intent.putExtra("phone", SPUtil.getPhone(getActivity()));
//                intent.putExtra("pwd", SPUtil.getPassword(getActivity()));
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initViewModel() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initObserver() {
        LiveEventBus.get("type1",String.class).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("wait")){
                    type.setValue(s);
//                    Log.e("type==========",type);
                }
            }
        });
        type.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s==null){
                    dataBinding.tvRenzhengTitle.setText("认证资料已提交！");
                    dataBinding.tvRenzhengFailed.setText("审核人员将在3天内进行资料审核，请耐心等待！");
                    dataBinding.btnRePost.setText("等待审核结果");
                }else if (s.contains("wait")){
                    dataBinding.tvRenzhengTitle.setText("认证资料已提交！");
                    dataBinding.tvRenzhengFailed.setText("审核人员将在3天内进行资料审核，请耐心等待！");
                    dataBinding.btnRePost.setText("等待审核结果");
                    dataBinding.btnRePost.setEnabled(false);
                }else if (s.contains("failed")){
                    dataBinding.tvRenzhengTitle.setText("审核认证失败！");
                    dataBinding.tvRenzhengFailed.setText("请重新上传您的认证资料，确保证件内容与本人信息相符。");
                    dataBinding.btnRePost.setText("请重新认证");
                    dataBinding.btnRePost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LiveEventBus.get("ChangeFragment").post(0);
                        }
                    });
                }
            }
        });

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_step_third;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
