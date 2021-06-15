package com.example.healthmanage.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.healthmanage.R;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    private Context context;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(TextView view, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒后重发");  //设置倒计时时间s
        mTextView.setTextColor(mTextView.getResources().getColor(R.color.colorTxtGrey));
        mTextView.setBackgroundResource(R.drawable.shape_solid_grey_radius_5); //设置按钮为灰色，这时是不能点击的
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setBackgroundResource(R.drawable.shape_solid_white_stroke_blue_radius_5);
        mTextView.setTextColor(mTextView.getResources().getColor(R.color.colorTxtBlue));
        mTextView.setClickable(true);//重新获得点击
    }
}
