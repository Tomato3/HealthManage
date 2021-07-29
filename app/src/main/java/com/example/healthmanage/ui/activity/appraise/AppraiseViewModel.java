package com.example.healthmanage.ui.activity.appraise;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.appraise.response.AppraiseListResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * desc:
 * date:2021/7/29 9:36
 * author:bWang
 */
public class AppraiseViewModel extends BaseViewModel {
    public MutableLiveData<AppraiseListResponse> mAppraiseListMutableLiveData = new MutableLiveData<>();
    public void appraiseList(){
        ApiWrapper.getInstance().appraiseList(BaseApplication.getToken()).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AppraiseListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {

                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull AppraiseListResponse appraiseListResponse) {
                        if (appraiseListResponse.getStatus()==0){
                            mAppraiseListMutableLiveData.setValue(appraiseListResponse);
                        }else {
                            mAppraiseListMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
