package com.example.healthmanage.ui.activity.consultation;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityConsultationDetailBinding;
import com.example.healthmanage.ui.activity.consultation.adapter.GridPicAdapter;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class ConsultationDetailActivity extends BaseActivity<ActivityConsultationDetailBinding,ConsultationViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private ConsultationListResponse.DataBean dataBean;
    private List<String> names;
    private GridPicAdapter gridPicAdapter;

    @Override
    protected void initData() {
        context = ConsultationDetailActivity.this;
        titleToolBar.setTitle("患者会诊详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        names = new ArrayList<>();
        dataBean = (ConsultationListResponse.DataBean) getIntent().getSerializableExtra("dataBean");
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        if (TextUtils.isEmpty(dataBean.getBriefHistory())){
            dataBinding.tvPatientDescribe.setText("暂无患者简要病史");
        }else {
            dataBinding.tvPatientDescribe.setText(dataBean.getBriefHistory());
        }
        if (TextUtils.isEmpty(dataBean.getPatientInitialDecide())){
            dataBinding.tvPrimaryDiagnosis.setText("暂无患者初步诊断");
        }else {
            dataBinding.tvPrimaryDiagnosis.setText(dataBean.getPatientInitialDecide());
        }
        if (TextUtils.isEmpty(dataBean.getExamineGoalRequest())){
            dataBinding.tvConsultationPurpose.setText("暂无会诊目的与要求");
        }else {
            dataBinding.tvConsultationPurpose.setText(dataBean.getExamineGoalRequest());
        }
        if (names != null && names.size()>0){
            names.clear();
        }
        if (dataBean.getExmineName()!=null && dataBean.getExmineName().size()>0){
            for (ConsultationListResponse.DataBean.ExmineNameBean exmineNameBean : dataBean.getExmineName()) {
                if (exmineNameBean!=null){
                    names.add(exmineNameBean.getName());
                }
            }
        }
        dataBinding.tvChooseMeetPerson.setText(getStr(names));
        dataBinding.tvChooseMeetStartTime.setText(ToolUtil.timeStampToMonth(String.valueOf(dataBean.getStartTimeQuery()),null));
        dataBinding.tvChooseMeetEndTime.setText(ToolUtil.timeStampToMonth(String.valueOf(dataBean.getEndTimeQuery()),null));
        if (dataBean.getAppNinePictures()!=null && dataBean.getAppNinePictures().size()>0){
            dataBinding.gridRecyclerview.setVisibility(View.VISIBLE);
            dataBinding.tvPicNull.setVisibility(View.GONE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
            dataBinding.gridRecyclerview.setLayoutManager(gridLayoutManager);
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false);
            if (dataBinding.gridRecyclerview.getItemDecorationCount()==0){
                dataBinding.gridRecyclerview.addItemDecoration(gridSpacingItemDecoration);
            }
            gridPicAdapter = new GridPicAdapter(context,dataBean.getAppNinePictures());
            dataBinding.gridRecyclerview.setAdapter(gridPicAdapter);
            gridPicAdapter.notifyDataSetChanged();
        }else {
            dataBinding.gridRecyclerview.setVisibility(View.GONE);
            dataBinding.tvPicNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_consultation_detail;
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

    /**
     * list中的字段拼接成字符串
     * @param strList
     * @return
     */
    public static String getStr(List<String> strList){
        String result = "";
        if (strList != null && strList.size()>0){
            for (int i = 0; i < strList.size(); i++) {
                result = result + strList.get(i)+',';
            }
            result = result.substring(0,result.length()-1);
        }
        return result;
    }
}
