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
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyRealLayout", "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyRealLayout", "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyRealLayout", "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        boolean b = super.dispatchTouchEvent(ev);
        Log.e("MyRealLayout", "dispatchTouchEvent==="+b);
        return b;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyRealLayout", "onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyRealLayout", "onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyRealLayout", "onInterceptTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean b = super.onInterceptTouchEvent(ev);
        Log.e("MyRealLayout", "onInterceptTouchEvent==="+b);
        return b;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyRealLayout", "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyRealLayout", "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyRealLayout", "onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean b = super.onTouchEvent(event);
        Log.e("MyRealLayout", "onTouchEvent==="+b);
        return b;
    }
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {
        Log.e("MyRealLayout", "requestDisallowInterceptTouchEvent ");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
