package com.example.healthmanage.ui.fragment.qualification;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.bean.QualificationInputItem;
import com.example.healthmanage.databinding.FragmentStepFirstBinding;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 16:55
 */
public class FirstStepFragment extends BaseFragment<FragmentStepFirstBinding, FirstStepViewModel> implements View.OnClickListener {

    PopupWindow popupWindow;
    List<QualificationInputItem> qualificationInputItemList;
    String profession;

    @Override
    protected void initData() {
        qualificationInputItemList = new ArrayList<>();
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_name),
                getString(R.string.activity_qualification_hint_name), false));
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_IdCard),
                getString(R.string.activity_qualification_hint_IdCard), false));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_department),
                getString(R.string.activity_qualification_hint_department), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_title),
                getString(R.string.activity_qualification_hint_title), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_hospital),
                getString(R.string.activity_qualification_hint_hospital), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);

        dataBinding.includeName.etInput.setInputType(InputType.TYPE_CLASS_TEXT);
        dataBinding.includeIdCard.etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        String html = "我已阅读并了解 " + "<a href=\"" +
                "https://www.cnblogs.com/csn0721/archive/2013/01/23/2873682.html" + "\">" +
                "《保健医苑医生端服务协议》" + "与" + "<a href=\"" +
                "https://www.cnblogs.com/csn0721/archive/2013/01/23/2873682.html" + "\">" +
                "《保健医苑医生端隐私协议政策》";
        CharSequence charSequence = Html.fromHtml(html);
        dataBinding.text.setMovementMethod(LinkMovementMethod.getInstance());
        dataBinding.text.setText(charSequence);
    }

    @Override
    protected void initListener() {
        dataBinding.btnOperation.setOnClickListener(this::onClick);
        dataBinding.tvSelectProfession.setOnClickListener(this::onClick);
        dataBinding.text.setMovementMethod(LinkMovementMethod.getInstance());
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
    protected void initObserver() {

    }

    @Override
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        viewModel.profession.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("请选择您的职业")) {
                    dataBinding.linearLayoutTip.setVisibility(View.GONE);
                }

                if (s.equals("医师")) {
                    dataBinding.linearLayoutDoctor.setVisibility(View.VISIBLE);
                } else {
                    dataBinding.linearLayoutDoctor.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_step_first;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_operation:
                if (TextUtils.isEmpty(dataBinding.includeName.etInput.getText())) {
                    ToastUtil.showShort("姓名不能为空");
                } else if (TextUtils.isEmpty(dataBinding.includeIdCard.etInput.getText())) {
                    ToastUtil.showShort("身份证号不能为空");
                } else if (viewModel.profession.equals("请选择您的职业")) {
                    ToastUtil.showShort("职业不能为空");
                } else if (!dataBinding.cbAgree.isChecked()) {
                    ToastUtil.showShort("请先勾选同意协议");
                } else {
                    LiveEventBus.get("ChangeFragment").post(1);
                }
                break;
            case R.id.tv_select_profession:
                initPopup();
                break;
        }

    }

    private void initPopup() {
        ToolUtil.hideKeyboard(dataBinding.includeName.etInput);
        ToolUtil.hideKeyboard(dataBinding.includeIdCard.etInput);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_popup, null, false);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(getActivity()));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });

        Button button = view.findViewById(R.id.btn_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        RadioGroup radioGroup = view.findViewById(R.id.rg_select_profession);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_doctor:
                        profession = "医师";
                        break;
                    case R.id.rb_manager:
                        profession = "健康管理师";
                        break;
                    case R.id.rb_dietitian:
                        profession = "健康营养师";
                        break;
                    case R.id.rb_nurse:
                        profession = "健康护理师";
                        break;
                }
            }
        });

        Button button1 = view.findViewById(R.id.btn_confirm);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(profession)) {
                    ToastUtil.showShort("请选择您的职业");
                } else {
                    popupWindow.dismiss();
                    viewModel.profession.setValue(profession);
                    dataBinding.tvSelectProfession.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                }
            }
        });

    }


    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }
}
