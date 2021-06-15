package com.example.healthmanage.ui.activity.consultation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityChatroomBinding;
import com.example.healthmanage.ui.activity.mytask.TransMitDealActivity;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.modules.chat.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * 患者会诊
 */
public class EMCChatActivity extends BaseActivity<ActivityChatroomBinding,ConsultationViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private String createId;
    private int id;
    private String type;
    private UIAlertDialog uiAlertDialog;
    private EMConversation conversation;

    @Override
    protected void initData() {
        createId = getIntent().getStringExtra("createId");
        id = getIntent().getIntExtra("id",0);
        type = getIntent().getStringExtra("type");
        titleToolBar.setTitle("患者会诊");
        titleToolBar.setLeftIconVisible(true);
        if (type.equals("chat")){
            if (!TextUtils.isEmpty(createId)){
                if (Integer.valueOf(createId) == BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()){
                    titleToolBar.setRightTitleVisible(true);
                    titleToolBar.setRightTitle("结束会诊");
                    titleToolBar.setRightTitleColor(getResources().getColor(R.color.colorTxtGrey));
                }
            }
        }else {
            titleToolBar.setRightTitleVisible(false);
        }

        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);


        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.chat_frameLayout, chatFragment, "chat").commit();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.layoutTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(EMCChatActivity.this,R.layout.dialog_transmit_consultation_plan,null);
                uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(EMCChatActivity.this)
                        .setView(view)
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
                EditText editPlan = view.findViewById(R.id.edit_consultation_plan);
                Button button = view.findViewById(R.id.btn_success_transmit);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(editPlan.getText().toString())){
                            ToastUtil.showShort("请输入诊疗方案");
                        }else {
                            viewModel.updatePatientExamine(id,editPlan.getText().toString());
                        }
                    }
                });
            }
        });
        viewModel.isUpdateSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    uiAlertDialog.dismiss();
                    finish();
                }else {
                    uiAlertDialog.dismiss();
                    ToastUtil.showShort("服务器失败");
                }
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_chatroom;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
