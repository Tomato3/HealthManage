package com.example.healthmanage.utils;

import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 这条总线用于把任何类中的数据直接传递到activity或是fragment上
 */

public class LiveDataBus {

    private static LiveDataBus mInstance;
    private static Map<String, MyMutableLiveData> mLiveDatas = new HashMap<>();

    private LiveDataBus() {

    }

    public static LiveDataBus get() {
        if (mInstance == null) {
            synchronized (LiveDataBus.class) {
                if (mInstance == null) {
                    mInstance = new LiveDataBus();
                }
            }
        }
        return mInstance;
    }

    public <T> MyMutableLiveData<T> with(String key, Class<T> type) {
        if (!mLiveDatas.containsKey(key)) {
            mLiveDatas.put(key, new MyMutableLiveData());
        }
        return mLiveDatas.get(key);
    }

    public MyMutableLiveData<Object> with(String key) {
        return with(key, Object.class);
    }

    public <T> void post(String key, T t) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            with(key).setValue(t);
        } else {
            with(key).postValue(t);
        }
    }

    public static class MyMutableLiveData<T> extends MutableLiveData {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void hook(@NonNull Observer<T> observer) throws Exception {
            //get wrapper's version
            Class<LiveData> classLiveData = LiveData.class;
            Field fieldObservers = classLiveData.getDeclaredField("mObservers");
            fieldObservers.setAccessible(true);
            Object objectObservers = fieldObservers.get(this);
            Class<?> classObservers = objectObservers.getClass();
            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);
            Object objectWrapperEntry = methodGet.invoke(objectObservers, observer);
            Object objectWrapper = null;
            if (objectWrapperEntry instanceof Map.Entry) {
                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
            }
            if (objectWrapper == null) {
                throw new NullPointerException("Wrapper can not be bull!");
            }
            Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
            Field fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion");
            fieldLastVersion.setAccessible(true);
            //get livedata's version
            Field fieldVersion = classLiveData.getDeclaredField("mVersion");
            fieldVersion.setAccessible(true);
            Object objectVersion = fieldVersion.get(this);
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, objectVersion);
        }
    }
}
