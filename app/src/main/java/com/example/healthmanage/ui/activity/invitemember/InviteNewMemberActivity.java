package com.example.healthmanage.ui.activity.invitemember;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityInviteNewMemberBinding;
import com.example.healthmanage.ui.activity.vipmanager.response.InviteMemberResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:邀请会员
 * date:2021/6/22 16:52
 * author:bWang
 */
public class InviteNewMemberActivity extends BaseActivity<ActivityInviteNewMemberBinding,InviteMemberViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private AppCompatActivity context;
    private InviteMemberResponse.DataBean memberDataBean;

    @Override
    protected void initData() {
        context = InviteNewMemberActivity.this;
        titleToolBar.setTitle("邀请成员");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.editSearchPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    dataBinding.ivClear.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.ivClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dataBinding.ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.editSearchPhone.setText("");
            }
        });
        dataBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewModel.phone.getValue())){
                    ToastUtil.showShort("请输入手机号码");
                    dataBinding.layoutHealthManager.setVisibility(View.GONE);
                }else {
                    viewModel.inviteSearchMember(viewModel.phone.getValue());
                }
            }
        });
        viewModel.mInviteMemberResponseMutableLiveData.observe(this, new Observer<InviteMemberResponse.DataBean>() {
            @Override
            public void onChanged(InviteMemberResponse.DataBean dataBean) {
                if (dataBean!=null){
                    memberDataBean = dataBean;
                    dataBinding.layoutHealthManager.setVisibility(View.VISIBLE);
                    Glide.with(context).load(dataBean.getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                            .into((dataBinding.ivAvatar));
                    switch (dataBean.getSex()){
                        case 0:
                            viewModel.name.setValue(dataBean.getNickName()+"\u3000|\u3000"+"保密");
                            break;
                        case 1:
                            viewModel.name.setValue(dataBean.getNickName()+"\u3000|\u3000"+"男");
                            break;
                        case 2:
                            viewModel.name.setValue(dataBean.getNickName()+"\u3000|\u3000"+"女");
                            break;
                    }
                    switch (dataBean.getRank()){
                        case 0:
                            viewModel.rank.setValue("普通会员");
                            break;
                        case 1:
                            viewModel.rank.setValue("高级会员");
                            break;
                        case 2:
                            viewModel.rank.setValue("贵宾会员");
                            break;
                        case 3:
                            viewModel.rank.setValue("至尊会员");
                            break;
                    }
                }else {
                    dataBinding.layoutHealthManager.setVisibility(View.GONE);
                }
                dataBinding.editSearchPhone.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(dataBinding.editSearchPhone.getWindowToken(), 0);
            }
        });
        dataBinding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.inviteMember(memberDataBean.getId());
            }
        });
        viewModel.inviteSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    UIAlertDialog uiAlertDialog= new UIAlertDialog.DividerIOSBuilder(InviteNewMemberActivity.this).setTitle("邀请成功")
                            .setTitleTextSize(18)
                            .setMessage(memberDataBean.getNickName()+"已加入您的团队")
                            .setMessageTextGravity(Gravity.CENTER)
                            .setPadding(SizeUtil.dp2px(20))
                            .setMessageTextSize(14)
                            .setMessageTextColorResource(R.color.colorBlack)
                            .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
                    Window window = uiAlertDialog.getWindow();
                    WindowManager.LayoutParams lp = window.getAttributes();
                    WindowManager manager= getWindowManager();
                    Display display= manager.getDefaultDisplay();
                    //params.height= (int) (display.getHeight()* 0.8);
                    lp.width= (int) (display.getWidth()* 0.6);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    uiAlertDialog.setWidth(lp.width).show();
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_invite_new_member;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
