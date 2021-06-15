package com.example.healthmanage.ui.activity.temperature.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreatePrescriptionBinding;
import com.example.healthmanage.ui.activity.mytask.DealTaskActivity;
import com.example.healthmanage.ui.activity.temperature.InsertPrescriptionBean;
import com.example.healthmanage.ui.activity.temperature.adapter.AddRpModelAdapter;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建处方
 */
public class CreatePrescriptionActivity extends BaseActivity<ActivityCreatePrescriptionBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack ,AddRpModelAdapter.OnListRemovedListener{
    private AppCompatActivity context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<InsertPrescriptionBean.DrugListBean> prescriptionBeans = new ArrayList<>();
    private AddRpModelAdapter addRpModelAdapter;
    private int type = 0;
    @Override
    protected void initData() {
        context = CreatePrescriptionActivity.this;
        titleToolBar.setTitle("创建处方");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBinding.recyclerviewRp.setLayoutManager(new LinearLayoutManager(this));
        prescriptionBeans.add(new InsertPrescriptionBean.DrugListBean("盒","口服","睡前","一天三次"));
        addRpModelAdapter = new AddRpModelAdapter(context,prescriptionBeans);
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_white));
        if (dataBinding.recyclerviewRp.getItemDecorationCount()==0){
            dataBinding.recyclerviewRp.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerviewRp.setAdapter(addRpModelAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        addRpModelAdapter.setOnListRemovedListener(this);
        dataBinding.btnQuerySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(dataBinding.editModelName.getText())){
                    ToastUtil.showShort("请填写模版名称");
                }else if (check(addRpModelAdapter.list)){
                    InsertPrescriptionBean insertPrescriptionBean = new InsertPrescriptionBean();
                    insertPrescriptionBean.setModelName(dataBinding.editModelName.getText().toString());
                    insertPrescriptionBean.setModelType(type);
                    insertPrescriptionBean.setToken(BaseApplication.getToken());
                    insertPrescriptionBean.setDrugList(addRpModelAdapter.list);
                    viewModel.insertHealthConsultDrugModel(insertPrescriptionBean);
                }else {
                    return;
                }

            }
        });
        dataBinding.groupChoosePrescription.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiobutton_choose_common:
                        type = 0;
                        if (addRpModelAdapter.list.size()>0){
                            addRpModelAdapter.list.clear();
                            addRpModelAdapter.list.add(new InsertPrescriptionBean.DrugListBean("盒","口服","睡前","一天三次"));
                            addRpModelAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.radiobutton_choose_special:
                        type = 1;
                        if (addRpModelAdapter.list.size()>0){
                            addRpModelAdapter.list.clear();
                            addRpModelAdapter.list.add(new InsertPrescriptionBean.DrugListBean("盒","口服","睡前","一天三次"));
                            addRpModelAdapter.notifyDataSetChanged();
                        }
                        break;
                }
            }
        });
        viewModel.isInsertSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(CreatePrescriptionActivity.this,R.layout.dialog_insert_model,null);
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(CreatePrescriptionActivity.this)
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
                    Button button = view.findViewById(R.id.btn_success_insert);
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

    //判断处方内容是否为空
    public boolean check(List<InsertPrescriptionBean.DrugListBean> drugListBeanList){
        for (InsertPrescriptionBean.DrugListBean drugListBean:drugListBeanList) {
            if (TextUtils.isEmpty(drugListBean.getName())){
                ToastUtil.showShort("请填写药品名称");
                return false;
            }else if (drugListBean.getNumber()==0){
                ToastUtil.showShort("请填写药品数量");
                return false;
            }
        }
        return true;
    }


    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_create_prescription;
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

    @Override
    public void onRemoved() {
        addRpModelAdapter.notifyDataSetChanged();
    }
}
