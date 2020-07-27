package com.example.healthmanage.ui.activity.splash;

import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.example.healthmanage.utils.Constants.HTAG;

public class SplashViewModel extends BaseViewModel {
    private static final int time = 3;

    public MutableLiveData<Integer> splashLogo = new MutableLiveData<>(R.drawable.activity_login_logo);
    public MutableLiveData<String> countdown = new MutableLiveData<>("3s");

    public void checkToken() {

    }

    public void setCountDownSkip() {
        //每秒设置一次倒计时，时间归零后跳到主界面
        Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::checkToken)
                .doOnNext(aLong -> {
                            Log.i(HTAG,
                                    "onNext:along= " + aLong + "     剩余时间：" + (time - aLong) + " Thread:" + Thread.currentThread());
                            countdown.setValue((time - aLong - 1) + "s");
                        }
                )
                .subscribe();
    }
}
