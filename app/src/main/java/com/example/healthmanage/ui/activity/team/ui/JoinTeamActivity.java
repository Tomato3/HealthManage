package com.example.healthmanage.ui.activity.team.ui;

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
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.aries.ui.widget.alert.UIAlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityJoinTeamBinding;
import com.example.healthmanage.ui.activity.team.TeamViewModel;
import com.example.healthmanage.ui.activity.team.response.SearchTeamResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 加入团队页面
 */
public class JoinTeamActivity extends BaseActivity<ActivityJoinTeamBinding, TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private AppCompatActivity context;
    private SearchTeamResponse.DataBean manageDataBean;

    @Override
    protected void initData() {
        context = JoinTeamActivity.this;
        titleToolBar.setTitle("加入团队");
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
//                viewModel.teamInvitationList("");
            }
        });
        dataBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewModel.phone.getValue())){
                    ToastUtil.showShort("请输入健康管理师手机号码");
                    dataBinding.layoutHealthManager.setVisibility(View.GONE);
                }else {
                    viewModel.teamInvitationList(viewModel.phone.getValue());
                }
            }
        });
        viewModel.searchTeamResponseMutableLiveData.observe(this, new Observer<SearchTeamResponse.DataBean>() {
            @Override
            public void onChanged(SearchTeamResponse.DataBean dataBean) {
                if (dataBean != null){
                    manageDataBean = dataBean;
                    dataBinding.layoutHealthManager.setVisibility(View.VISIBLE);
                    Glide.with(context).load(dataBean.getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                            .into((dataBinding.ivAvatar));
                    viewModel.healthName.setValue(dataBean.getName());
                    viewModel.healthRank.setValue(dataBean.getRank()+"\u3000|\u3000"+dataBean.getPhone());
                }else {
                    dataBinding.layoutHealthManager.setVisibility(View.GONE);
                    ToastUtil.showShort("请输入正确健康管理师账号");
                }
                dataBinding.editSearchPhone.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(dataBinding.editSearchPhone.getWindowToken(), 0);
            }
        });
        dataBinding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sendApply(manageDataBean.getSysId());
            }
        });
        viewModel.isPostSignSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    UIAlertDialog uiAlertDialog= new UIAlertDialog.DividerIOSBuilder(JoinTeamActivity.this).setTitle("已向"+manageDataBean.getName()+"发起签约")
                            .setTitleTextSize(18)
                            .setMessage("请耐心等待签约结果,签约结果将以消息通知的形式告知您。")
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
        return R.layout.activity_join_team;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
