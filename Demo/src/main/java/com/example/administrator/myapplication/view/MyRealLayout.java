package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/11/29.
 */
public class MyRealLayout extends RelativeLayout {
    public MyRealLayout(Context context) {
        super(context);
    }
    public MyRealLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyRealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("MyRealLayout", "dispatchTouchEvent==="+b);
        return b;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Log.e("MyRealLayout", "onInterceptTouchEvent==="+b);
        return b;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.e("MyRealLayout", "onTouchEvent==="+b);
        return b;
    }
}
