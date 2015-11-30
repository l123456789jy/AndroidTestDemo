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
        Log.e("MyRealLayout", "dispatchTouchEvent==="+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyRealLayout", "onInterceptTouchEvent==="+super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyRealLayout", "onTouchEvent==="+super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
