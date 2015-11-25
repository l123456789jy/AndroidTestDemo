package com.example.administrator.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/11/24.
 * 测试移动view的三种方式。
 * 1.通过改变view的LayoutParams
 * 2.动画位移
 * 3.scroolto和scrollby
 */
public class TranseButton extends Button {
    public TranseButton(Context context) {
        super(context);
    }

    public TranseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TranseButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                move(TranseButton.this, event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
    /**
     * 设置View的布局属性，使得view随着手指移动 注意：view所在的布局必须使用RelativeLayout 而且不得设置居中等样式
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewWithFinger(View view, float rawX, float rawY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                .getLayoutParams();
        params.leftMargin = (int) rawX - view.getWidth() / 2;
        params.topMargin = (int) rawY - 20 - view.getHeight() / 2;
        view.setLayoutParams(params);
    }
    //这两种移动只是移动view的内容，view的位置是不会改变的！
    private void movewScroto(View view, float rawX, float rawY){
        view.scrollTo((int) rawX, (int) rawY);
        // view.scrollBy((int)rawX,(int)rawY);
    }
    //动画进行位移
    private void move(View view,float rawX,float rawY){
        view.setTranslationX(rawX);
    }
}
