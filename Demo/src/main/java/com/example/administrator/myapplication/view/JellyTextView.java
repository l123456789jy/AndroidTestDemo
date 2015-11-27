package com.example.administrator.myapplication.view;

/**
 * 作者：liujingyuan on 2015/11/27 11:49
 * 邮箱：906514731@qq.com
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.OverScroller;
import android.widget.TextView;

/**
 * Created by zhaokaiqiang on 15/2/28.
 */
public class JellyTextView extends TextView {

    private OverScroller mScroller;

    private float lastX;
    private float lastY;

    private float startX;
    private float startY;

    public JellyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //BounceInterpolator反弹插补器    interpolator插补器加上那个这个就是果冻类型了！
        mScroller = new OverScroller(context, new BounceInterpolator());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float disX = event.getRawX() - lastX;
                float disY = event.getRawY() - lastY;

                offsetLeftAndRight((int) disX);
                offsetTopAndBottom((int) disY);
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //getRawX 获取的是以屏幕左上角为坐标原点计算的Ｘ轴坐标直．
                //getX是获取以widget左上角为坐标原点计算的Ｘ轴坐标直．
                Log.e("startX",getX()+"");
                Log.e("startRX",event.getRawX()+"");
                //int startX,开始的位置
                // int startY,结束的位置
                // int dx,
                // int dy,
                // int duration执行完毕需要的时间
                mScroller.startScroll((int) getX(), (int) getY(), -(int) (getX() - startX),-(int) (getY() - startY),20000);
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            setX(mScroller.getCurrX());
            setY(mScroller.getCurrY());
            invalidate();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("w",w+"");
        startX = getX();
        startY = getY();
    }

    public void spingBack() {

        if (mScroller.springBack((int) getX(), (int) getY(), 0, (int) getX(), 0,
                (int) getY() - 100)) {
            Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
            invalidate();
        }

    }


}
