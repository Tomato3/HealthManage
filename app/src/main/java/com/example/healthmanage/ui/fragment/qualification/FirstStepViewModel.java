package com.example.healthmanage.ui.fragment.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.QualificationInputItem;

import java.util.List;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 17:01
 */
public class FirstStepViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("实名资质认证");
    public MutableLiveData<String> operationText = new MutableLiveData<>("下一步");
    public MutableLiveData<String> profession = new MutableLiveData<>("请选择您的职业");
    public MutableLiveData<List<QualificationInputItem>> qualificationInputItemListMutableLiveData = new MutableLiveData<>();
}
