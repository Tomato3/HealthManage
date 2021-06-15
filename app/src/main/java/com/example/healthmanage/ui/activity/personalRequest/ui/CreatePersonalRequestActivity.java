package com.example.healthmanage.ui.activity.personalRequest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreatePersonalRequestBinding;
import com.example.healthmanage.ui.activity.personalRequest.response.AddRequestBean;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 创建个人请求
 */
public class CreatePersonalRequestActivity extends BaseActivity<ActivityCreatePersonalRequestBinding,PersonalViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private String type;
    private AddRequestBean addRequestBean;
    @Override
    protected void initData() {
        context = CreatePersonalRequestActivity.this;
        titleToolBar.setTitle("个人请求");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        addRequestBean = new AddRequestBean();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.groupChooseRequestType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt_choose_life:
                        type = "生活";
                        break;
                    case R.id.rbt_choose_medical:
                        type = "医疗";
                        break;
                    case R.id.rbt_choose_education:
                        type = "教育";
                        break;
                    case R.id.rbt_choose_law:
                        type = "法律";
                        break;
                    case R.id.rbt_choose_other:
                        type = "其它";
                        break;
                }
            }
        });

        dataBinding.btnPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBinding.rbtChooseLife.isChecked()|| dataBinding.rbtChooseMedical.isChecked()
                        ||dataBinding.rbtChooseEducation.isChecked()||dataBinding.rbtChooseLaw.isChecked()||dataBinding.rbtChooseOther.isChecked()){
                    addRequestBean.setContent(viewModel.describeContent.getValue());
                    addRequestBean.setSystemUserId(BaseApplication.getUserInfoBean().getSysId());
                    addRequestBean.setType(type);
                    viewModel.addPersonalRequest(addRequestBean);
                }else {
                    ToastUtil.showShort("请选择请求类型");
                }
            }
        });

        viewModel.addSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(context,R.layout.dialog_task_deal,null);
                    TextView tvTitle = view.findViewById(R.id.tv_success);
                    TextView tvContent = view.findViewById(R.id.tv_tips_task);
                    tvTitle.setText("请求发送成功");
                    tvContent.setText("请耐心等待平台回复");
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
                            .setView(view)
                            .setCanceledOnTouchOutside(false)//设置空白处不消失
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
                    uiAlertDialog.show();
                    Window window = uiAlertDialog.getWindow();
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.gravity = Gravity.CENTER;
                    //dialog宽高适应子布局xml
                    //lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
                    //dialog宽高适应屏幕
                    WindowManager manager= getWindowManager();
                    Display display= manager.getDefaultDisplay();
                    //params.height= (int) (display.getHeight()* 0.8);
                    lp.width= (int) (display.getWidth()* 0.5);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    Button button = view.findViewById(R.id.btn_success_task);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }else {
                    return;
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
        return R.layout.activity_create_personal_request;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
