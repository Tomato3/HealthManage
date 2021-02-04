package com.example.healthmanage.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class NewHomeFragmentViewModel extends BaseViewModel {
    public MutableLiveData<List<MyMemberResponse.DataBean>> mMyMemberResponseMutableLiveData = new MutableLiveData<>();

    public void myFocus(String sysId) {
        ApiWrapper.getInstance().loadMyFocus(sysId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            if (myMemberResponse.getData() != null) {
                                mMyMemberResponseMutableLiveData.postValue(myMemberResponse.getData());
                            } else {
                                mMyMemberResponseMutableLiveData.postValue(null);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVip(String sysId, int rank) {
        ApiWrapper.getInstance().selectMember(sysId, rank)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            if (myMemberResponse.getData() != null) {
                                mMyMemberResponseMutableLiveData.postValue(myMemberResponse.getData());
                            } else {
                                mMyMemberResponseMutableLiveData.postValue(null);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
