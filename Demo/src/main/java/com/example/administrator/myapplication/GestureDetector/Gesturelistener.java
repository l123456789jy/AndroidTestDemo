package com.example.administrator.myapplication.gestureDetector;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * 作者：liujingyuan on 2016/1/14 16:59
 * 邮箱：906514731@qq.com
 * GestureDetector（Gesture：手势Detector：识别）类，通过这个类我们可以识别很多的手势，主要是通过他的onTouchEvent(event)方法完成了不同手势的识别。
 */
public class Gesturelistener implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    //移动的位移，和移动的速度
    final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
    private final String TAG="Gesturelistener";
    public Context mContext;
    public Gesturelistener(Context mContext){
        this.mContext=mContext;
    }
    /**
     * 用户按下屏幕就会触发
     * @param e
     * @return
     */
    @Override public boolean onDown(MotionEvent e) {
        Log.e(TAG, "onDown: ");
        return false;

    }


    /**
     * 如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动的，
     * 那么onShowPress就会执行，具体这个瞬间是多久，我也不清楚呃……
     * @param e
     */
    @Override public void onShowPress(MotionEvent e) {
        Log.e(TAG, "onShowPress: ");
    }


    /**
     *从名子也可以看出,一次单独的轻击抬起操作,也就是轻击一下屏幕，立刻抬起来，才会有这个触发，
     * 当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以也就不会触发这个事件
     * @param e
     * @return
     */
    @Override public boolean onSingleTapUp(MotionEvent e) {
        Log.e(TAG, "onSingleTapUp: ");
        return true;

    }


    /**
     * 在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll: ");
        return true;

    }


    /**
     * 长按触摸屏，超过一定时长，就会触发这个事件
     * @param e
     */
    @Override public void onLongPress(MotionEvent e) {
        Log.e(TAG, "onLongPress: ");
    }


    /**
     * 滑屏，用户按下触摸屏、快速移动后松开
     * @param e1 第1个ACTION_DOWN MotionEvent
     * @param e2 最后一个ACTION_MOVE MotionEvent
     * @param velocityX  X轴上的移动速度，像素/秒
     * @param velocityY  Y轴上的移动速度，像素/秒
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //代表向左滑动
        if (e1.getX()-e2.getX()>FLING_MIN_DISTANCE&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
            Log.e(TAG, "onFling: 向左滑动啦！");
        }
        return true;

    }


    /**
     * ：单击事件。用来判定该次点击是SingleTap而不是DoubleTap
     * @param e
     * @return
     */
    @Override public boolean onSingleTapConfirmed(MotionEvent e) {

        Log.e(TAG, "onSingleTapConfirmed: ");
        return false;
    }


    /**
     * 双击事件
     * @param e
     * @return
     */
    @Override public boolean onDoubleTap(MotionEvent e) {

        Log.e(TAG, "onDoubleTap: ");
        return false;
    }


    /**
     * 双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件
     * @param e
     * @return
     */
    @Override public boolean onDoubleTapEvent(MotionEvent e) {

        Log.e(TAG, "onDoubleTapEvent: ");
        return false;
    }
}
