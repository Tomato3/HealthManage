package com.example.healthmanage.data.network;


import com.example.healthmanage.data.network.exception.ExceptionHandle;

import io.reactivex.Observer;


public abstract class MyObserver<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception) {
            onError(ExceptionHandle.handleException(e));
        } else {
            onError(new ExceptionHandle.ResponseException(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ExceptionHandle.ResponseException responseException);
}
