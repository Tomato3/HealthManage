package com.example.healthmanage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.healthmanage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:bwang
 * Date:2020/11/27 17:38
 */
public class StepView extends View {

    private static final int DEFAULT_CIRCLE_RADIUS = 12; // 默认半径为 15dp
    private static final int DEFAULT_PADDING = 15;  // 默认的步骤和文字之间的间距
    private static final int DEFAULT_TV_SIZE_SMALL = 14; // 默认的文字小
    private static final int DEFAULT_TV_SIZE = 17; // 默认的文字大小
    private float mCircleRadius; // 圆半径
    private float mCenterLineWidth; // 中间线的长度
    private float mCircleTextPadding; // 下部文字和圆圈的间距
    private Paint mPaint; // 画笔
    private Rect mRect;
    private float mTvSize, mTvSizeSmall;   // 文字大小
    private List<String> strings;
    private int position;

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        strings = new ArrayList<>();
        mCircleRadius = dp2px(DEFAULT_CIRCLE_RADIUS);
        mCircleTextPadding = dp2px(DEFAULT_PADDING);
        mTvSize = sp2px(DEFAULT_TV_SIZE);
        mTvSizeSmall = sp2px(DEFAULT_TV_SIZE_SMALL);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        mPaint.setStyle(Paint.Style.STROKE); // 设置为线条模式
        strings.add("基本资料");
        strings.add("身份认证");
        strings.add("等待认证");


    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getReallyWidth(widthMeasureSpec), getReallyHeight(heightMeasureSpec));
    }

    /**
     * 获取真实的宽度
     */
    private int getReallyWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {// 至多，用于 wrap_content
            int reallyWidth = (int) (getPaddingLeft() + getPaddingRight()
                    + mCircleRadius * 2 * strings.size() + mCenterLineWidth * (strings.size() - 1));
            result = Math.min(reallyWidth, size);
        } else {
            result = size;
            // 中间线长度 = (总长度 - 左间距 - 右间距 - 前后两根线 - 圆直径 * 圆数量)/(圆数量 - 1)
            mCenterLineWidth = (result - getPaddingLeft() - getPaddingRight()
                    - mCircleRadius * 2 * strings.size()) / (strings.size() - 1);
        }
        return result;
    }

    /**
     * 获取真实的高度
     */
    private int getReallyHeight(int heightMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {// 至多，用于 wrap_content
            int reallyHeight = (int) (getPaddingBottom() + getPaddingTop() + mCircleRadius * 2
                    + mCircleTextPadding + (mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top));
            result = Math.min(reallyHeight, size);
        } else { //
            result = size;
        }
        return result;
    }

    public void setPosition(int position) {
        this.position = position;
        //refresh
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 中间线的起始X坐标
        float startX;
        float endX;
        //圆的Y坐标
        float cy = getPaddingTop() + mCircleRadius;
        for (int i = 0; i < strings.size(); i++) {
            //圆的X坐标
            float cx = getPaddingLeft() + mCircleRadius + i * (mCenterLineWidth + mCircleRadius * 2);
            startX =
                    getPaddingLeft() + mCircleRadius * 2 * (i + 1) + mCenterLineWidth * i + dp2px(12);
            endX =
                    getPaddingLeft() + mCircleRadius * 2 * (i + 1) + mCenterLineWidth * (i + 1) - dp2px(12);
            if (i <= position) {
                //画圆
                mPaint.setColor(getContext().getColor(R.color.colorBlue));
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
                //画线
                if (i < 2) {
                    canvas.drawLine(startX, cy, endX, cy, mPaint);
                }
                // 绘制下面的文字
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setTextSize(mTvSize);
                int textHeight = mRect.height();
                //文字Y坐标
                float y = getPaddingTop() + mCircleRadius * 2 + mCircleTextPadding + textHeight;
                drawVerticalText(canvas, cx, y, strings.get(i));

                // 画数字
                mPaint.setColor(getContext().getColor(R.color.colorWhite));
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setTextSize(mTvSizeSmall);
                drawVerticalText(canvas, cx, cy, String.valueOf(i + 1));
            } else {
                //画圆
                mPaint.setColor(getContext().getColor(R.color.colorTxtGrey));
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
                //画线
                if (i < 2) {
                    canvas.drawLine(startX, cy, endX, cy, mPaint);
                }

                //画数字
                mPaint.setColor(getContext().getColor(R.color.colorBlack));
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setTextSize(mTvSizeSmall);
                drawVerticalText(canvas, cx, cy, String.valueOf(i + 1));
                // 绘制下面的文字
                mPaint.setTextSize(mTvSize);
                int textHeight = mRect.height();
                float y = getPaddingTop() + mCircleRadius * 2 + mCircleTextPadding + textHeight;
                drawVerticalText(canvas, cx, y, strings.get(i));
            }
        }
    }

    private void drawVerticalText(Canvas canvas, float centerX, float centerY, String text) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine = -(fontMetrics.ascent + fontMetrics.descent) / 2;
        float textWidth = mPaint.measureText(text);
        float startX = centerX - textWidth / 2;
        float endY = centerY + baseLine;
        canvas.drawText(text, startX, endY, mPaint);
    }

    private int getTextWidth(String text, Paint paint) {
        Rect rect = new Rect(); // 文字所在区域的矩形
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

}
