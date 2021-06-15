package com.example.healthmanage.ui.activity.delegate.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreateDelegateTaskBinding;
import com.example.healthmanage.ui.activity.delegate.response.DelegateBean;
import com.example.healthmanage.ui.activity.mytask.DealTaskActivity;
import com.example.healthmanage.ui.activity.referral.ChooseMemberActivity;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.List;

/**
 * 创建委派任务
 */
public class CreateDelegateTaskActivity extends BaseActivity<ActivityCreateDelegateTaskBinding,DelegateViewModel> implements
        TitleToolBar.OnTitleIconClickCallBack , View.OnClickListener,CalendarView.OnCalendarInterceptListener,
        CalendarView.OnCalendarRangeSelectListener,
        CalendarView.OnMonthChangeListener{
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    PopupWindow popupWindow;
    private CalendarView mCalendarView;
    private TextView dialogTitle;
    private List<Calendar> calendars;
    private String startDate,endDate;
    private int startYear,startMonth,startDay;
    private String transferId;
    private String transferName;
    private String memberName;
    private String memberId;
    private DelegateBean delegateBean;

    @Override
    protected void initData() {
        context = CreateDelegateTaskActivity.this;
        delegateBean = new DelegateBean();
        titleToolBar.setTitle("委派业务");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        /**选择委派对象*/
        dataBinding.tvChooseDelegatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChooseDelegatePersonActivity.class);
                startActivityForResult(intent,2);
            }
        });
        /**选择服务对象*/
        dataBinding.tvChooseServicePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChooseMemberActivity.class);
                startActivityForResult(intent,1);
            }
        });
        /**选择日期*/
        dataBinding.tvChooseTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
        dataBinding.btnQueryDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(transferId) || TextUtils.isEmpty(viewModel.delegateDetail.getValue())||TextUtils.isEmpty(viewModel.delegateTime.getValue())
                        ||TextUtils.isEmpty(viewModel.delegateSpecificTime.getValue())||TextUtils.isEmpty(viewModel.delegateAddress.getValue())){
                    if (TextUtils.isEmpty(transferId)){
                        ToastUtil.showShort("请选择委派对象");
                        return;
                    }
                    if (TextUtils.isEmpty(viewModel.delegateDetail.getValue())){
                        ToastUtil.showShort("请输入委派内容");
                        return;
                    }
                    if (TextUtils.isEmpty(viewModel.delegateTime.getValue())){
                        ToastUtil.showShort("请选择任务时间");
                        return;
                    }
                    if (TextUtils.isEmpty(viewModel.delegateSpecificTime.getValue())){
                        ToastUtil.showShort("请输入具体时间");
                        return;
                    }
                    if (TextUtils.isEmpty(viewModel.delegateAddress.getValue())){
                        ToastUtil.showShort("请输入详细地址");
                        return;
                    }
                }else {
                    delegateBean.setCreateUserId(String.valueOf(BaseApplication.getUserInfoBean().getSysId()));
                    delegateBean.setSystemUserId(transferId);
                    delegateBean.setContent(viewModel.delegateDetail.getValue());
                    delegateBean.setDate(viewModel.delegateTime.getValue());
                    delegateBean.setTime(viewModel.delegateSpecificTime.getValue());
                    delegateBean.setAddr(viewModel.delegateAddress.getValue());
                    if (!TextUtils.isEmpty(memberId)){
                        delegateBean.setUserId(memberId);
                    }
                    viewModel.addBusineService(delegateBean);
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
                    tvTitle.setText("成功委派业务");
                    tvContent.setText("您可在委派业务中查看委派记录");
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
//                            Intent intent = new Intent();
//                            if (startMonth<10){
//                                intent.putExtra("month","0"+startMonth);
//                            }else {
//                                intent.putExtra("month",String.valueOf(startMonth));
//                            }
//                            intent.putExtra("year",String.valueOf(startYear));
//                            setResult(RESULT_OK,intent);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 2){
                if (resultCode == RESULT_OK){
                    transferName = data.getStringExtra("transferName");
                    dataBinding.tvChooseDelegatePerson.setText(transferName+"\u3000|\u3000"+data.getStringExtra("transferRank"));
                    transferId = data.getStringExtra("transferId");
                }
            }else if (requestCode == 1){
                if (resultCode==RESULT_OK){
                    memberName = data.getStringExtra("memberName");
                    dataBinding.tvChooseServicePerson.setText(memberName+"\u3000|\u3000"+data.getStringExtra("rank"));
                    memberId = data.getStringExtra("memberId");
                }
            }
        }
    }

    private void initPopupWindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_choose_calendarview, null, false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(this));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });

        Calendar calendar = new Calendar();
        mCalendarView = view.findViewById(R.id.calendarView);
        dialogTitle = view.findViewById(R.id.tv_date_title);
        mCalendarView.setFixMode();
        dialogTitle.setText(calendar.getYear()+"年"+calendar.getMonth()+"月");
        mCalendarView.setOnCalendarRangeSelectListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        //设置日期拦截事件，当前有效
        mCalendarView.setOnCalendarInterceptListener(this);
        mCalendarView.setRange(2000, 1, 1,

                2999, 12, 31

        );
        mCalendarView.post(new Runnable() {
            @Override
            public void run() {
                mCalendarView.scrollToCurrent();
            }
        });
        Button btnConfirm = view.findViewById(R.id.btn_confirm_date);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendars = mCalendarView.getSelectCalendarRange();
                if (mCalendarView.getSelectedCalendar()!=null && calendars.size()==0){
                    startYear = mCalendarView.getSelectedCalendar().getYear();
                    startMonth = mCalendarView.getSelectedCalendar().getMonth();
                    startDay = mCalendarView.getSelectedCalendar().getDay();
                    startDate = startYear +"年"+startMonth+"月"+startDay+"日";
                    endDate = mCalendarView.getSelectedCalendar().getYear()+"年"+mCalendarView.getSelectedCalendar().getMonth()+"月"+mCalendarView.getSelectedCalendar().getDay()+"日";
                    viewModel.delegateTime.postValue(startDate);
                }else {
                    if (calendars == null || calendars.size() == 0) {
                        return;
                    }
                    startYear = calendars.get(0).getYear();
                    startMonth = calendars.get(0).getMonth();
                    startDay = calendars.get(0).getDay();
                    startDate = startYear +"年"+startMonth+"月"+startDay+"日";
                    endDate = calendars.get(calendars.size()-1).getYear()+"年"+calendars.get(calendars.size()-1).getMonth()+"月"+calendars.get(calendars.size()-1).getDay()+"日";
                    viewModel.delegateTime.postValue(startDate+"~"+endDate);
                }
                popupWindow.dismiss();
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
        return R.layout.activity_create_delegate_task;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        return false;
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {

    }

    @Override
    public void onCalendarSelectOutOfRange(Calendar calendar) {

    }

    @Override
    public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {
        Toast.makeText(this,
                calendar.toString() + (isOutOfMinRange ? "小于最小选择范围" : "超过最大选择范围"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {

    }

    @Override
    public void onMonthChange(int year, int month) {
        dialogTitle.setText(year+"年"+month+"月");
    }
}
