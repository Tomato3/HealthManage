package com.example.healthmanage.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.healthmanage.R;

public class TipToast extends Toast {

    Context context;
    public int icon;
    public String message;
    ImageView imageView;
    TextView textView;
    int state, SUCCEED = 0, FAILED = 1, LOADING = 10000;

    public TipToast(@NonNull Context context, int state, String message) {
        super(context);
        this.context = context;
        this.state = state;
        this.message = message;
        initView(state);
    }

    public void initView(int state) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_tip, null);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);
        if (state == LOADING) {
            imageView.setImageResource(R.drawable.toast_tip_loading);
            startRotateAnimation(imageView);
        } else if (state == SUCCEED) {
            imageView.setImageResource(R.drawable.toast_tip_success);
        } else if (state == FAILED) {
            imageView.setImageResource(R.drawable.toast_tip_fail);
        }
        textView.setText(message);
        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.CENTER, 0, 0);
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setView(view);
        toastStart.show();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static void startRotateAnimation(View v) {
        RotateAnimation mRotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 创建旋转动画对象，从0°旋转到360°，以自身中心点为旋转轴
        mRotateAnimation.setDuration(1000);
        // 从0°旋转到360°所需要的时间
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        // 线性插入器（匀速旋转）
        mRotateAnimation.setRepeatCount(-1);
        // 重复次数，-1无限循环
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        // 重复模式：restart重新开始
        v.startAnimation(mRotateAnimation);
        // 开始动画
    }

    public void refreshView(int state) {
        if (state == LOADING) {
            imageView.setImageResource(R.drawable.toast_tip_loading);
            startRotateAnimation(imageView);
        } else if (state == SUCCEED) {
            imageView.setImageResource(R.drawable.toast_tip_success);
        } else if (state == FAILED) {
            imageView.setImageResource(R.drawable.toast_tip_fail);
        }
    }
}
