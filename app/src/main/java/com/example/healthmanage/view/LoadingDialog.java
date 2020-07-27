package com.example.healthmanage.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthmanage.R;

public class LoadingDialog extends Dialog {

    Context context;
    ImageView imageView;
    TextView textView;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialogStyle);
        this.context = context;

        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_tip, null);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);
        textView.setText("正在加载");
        imageView.setImageResource(R.drawable.toast_tip_loading);
        startRotateAnimation(imageView);
        super.setContentView(view);
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
}
