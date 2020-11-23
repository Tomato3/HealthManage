package com.example.healthmanage.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.request.target.ViewTarget;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.utils.Utils;
import com.hdl.CrashExceptioner;
import com.jeremyliao.liveeventbus.LiveEventBus;

import static com.example.healthmanage.utils.Constants.HTAG;

public class BaseApplication extends Application {

    private static Application sInstance;

    //token
    private static String token = "";
    //用户信息
    private static LoginResponse.DataBean.UserInfoBean userInfoBean;

    public static String getToken() {
        return token == null ? "token is null" : token;
    }

    public static void setToken(String token) {
        BaseApplication.token = token;
    }

    public static LoginResponse.DataBean.UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public static void setUserInfoBean(LoginResponse.DataBean.UserInfoBean userInfoBean) {
        BaseApplication.userInfoBean = userInfoBean;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setApplication(this);

        //LiveEventBus配置
        LiveEventBus
                .config()
                .autoClear(true)
                .lifecycleObserverAlwaysActive(true);

        //glide报错缺失
        ViewTarget.setTagId(R.id.glide_tag);

        //奔溃页面
        CrashExceptioner.init(this);

    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(@NonNull Application application) {
        sInstance = application;
        //初始化工具类
        Utils.init(application);
        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
                Log.d(HTAG, "onActivityCreated==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(HTAG, "onActivityStarted==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(HTAG, "onActivityResumed==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(HTAG, "onActivityPaused==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(HTAG, "onActivityStopped==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(HTAG, "onActivitySaveInstanceState==========>: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
                Log.d(HTAG, "onActivityDestroyed==========>: " + activity.getClass().getSimpleName());
            }
        });
    }

    /**
     * 获得当前app运行的Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

}
