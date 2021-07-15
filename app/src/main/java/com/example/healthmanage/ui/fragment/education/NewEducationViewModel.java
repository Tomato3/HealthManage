package com.example.healthmanage.ui.fragment.education;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.fragment.educationchild.response.BookArticleResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookMenuResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.SubscribeResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.YearResponse;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class NewEducationViewModel extends BaseViewModel {
    public UsersRemoteSource mUsersRemoteSource;
    public MutableLiveData<YearResponse> mYearResponseListMutableData = new MutableLiveData<>();
    public MutableLiveData<List<BookListResponse.DataBean>> mBookListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSubscribeSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCancelSubscribeSucceed = new MutableLiveData<>();
    public MutableLiveData<BookMenuResponse> mBookMenuResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<BookArticleResponse.DataBean> mDataBeanMutableLiveData = new MutableLiveData<>();


    public NewEducationViewModel() {
        mUsersRemoteSource = new UsersRemoteSource();
    }

    public void getYearList(){
        mUsersRemoteSource.getYearList(BaseApplication.getToken(), new UsersInterface.GetYearListCallback() {
            @Override
            public void getSucceed(YearResponse yearResponse) {
                if (yearResponse.getData()!=null && yearResponse.getData().size()>0){
                    mYearResponseListMutableData.setValue(yearResponse);
                }else {
                    mYearResponseListMutableData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mYearResponseListMutableData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mYearResponseListMutableData.setValue(null);
            }
        });
    }

    public void getBookListByYear(String year){
        mUsersRemoteSource.getBookListByYear(year, BaseApplication.getToken(), new UsersInterface.GetBookListByYearCallback() {
            @Override
            public void getSucceed(BookListResponse bookListResponse) {
                if (bookListResponse.getData()!=null && bookListResponse.getData().size()>0){
                    mBookListMutableLiveData.setValue(bookListResponse.getData());
                }else {
                    mBookListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mBookListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mBookListMutableLiveData.setValue(null);
            }
        });
    }

    public void subscribeBook(String bookId){
        mUsersRemoteSource.subscribeBook(bookId, BaseApplication.getToken(), new UsersInterface.SubscribeBookCallback() {
            @Override
            public void getSucceed(SubscribeResponse subscribeResponse) {
                isSubscribeSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isSubscribeSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isSubscribeSucceed.setValue(false);
            }
        });
    }

    public void cancelSubscribeBook(String bookId){
        mUsersRemoteSource.cancelSubscribeBook(bookId, BaseApplication.getToken(), new UsersInterface.CancelSubscribeBookCallback() {
            @Override
            public void getSucceed(SubscribeResponse subscribeResponse) {
                isCancelSubscribeSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isCancelSubscribeSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isCancelSubscribeSucceed.setValue(false);
            }
        });
    }

    public void menuBook(String id) {
        ApiWrapper.getInstance().getBookCatalogList(id, BaseApplication.getToken())
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<BookMenuResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BookMenuResponse bookListTodayResponse) {
                        mBookMenuResponseMutableLiveData.postValue(bookListTodayResponse);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getArticle(String id) {
        mUsersRemoteSource.getBookArticle(id, BaseApplication.getToken(), new UsersInterface.GetBookArticleCallback() {
            @Override
            public void getSucceed(BookArticleResponse bookArticleResponse) {
                mDataBeanMutableLiveData.setValue(bookArticleResponse.getData());
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mDataBeanMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mDataBeanMutableLiveData.setValue(null);
            }
        });
    }

}
