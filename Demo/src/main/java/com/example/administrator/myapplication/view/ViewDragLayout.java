package com.example.administrator.myapplication.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 作者：liujingyuan on 2015/12/23 11:21
 * 邮箱：906514731@qq.com
 * ViewDraghelp的初步使用
 */
public class ViewDragLayout  extends RelativeLayout {
    public final String TAG="ViewDragLayout";
    ViewDragHelper mviewDragHelper;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    int childCount;
    private static final float SCALE_STEP = 0.05f; // view叠加缩放的步长
    private int yOffsetStep = 40; // view叠加垂直偏移量的步长
    //创建一个点
    private Point mAutoBackOriginPos = new Point();

    public ViewDragLayout(Context context) {
        super(context);
        initViewDargHelp(context);
    }

    public ViewDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewDargHelp(context);
    }

    public ViewDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewDargHelp(context);
    }

    //初始化ViewDargHelp
    private void initViewDargHelp(Context context) {
        /**
         * @param 必须为viewgroup
         *  @param 1.0f是敏感度参数参数越大越敏感
         *   @param context
         */
        mviewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            /**
             * @param child 当前获取的view
             * @param pointerId
             * @return
             */
            //确定当前子view是否可拖动，返回ture则表示可以捕获该view就代表当前的view是可以被移动的
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
              return true;

            }
            //此方法是用来控制水平方向移动的范围,不能超出屏幕的范围，这样约束
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.e(TAG,left+"");
              /*  final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mDragView.getWidth() - leftBound;
                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);*/
                return left;
            }
            //此方法是用来控制竖直方向的范围,控制不超出屏幕的上下的范围
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
               /* final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - mDragView.getHeight();
                final int newTop = Math.min(Math.max(top, topBound), bottomBound);*/
                return top;
            }
            //获取边界的方法
            @Override
            public int getViewVerticalDragRange(View child) {
                return super.getViewVerticalDragRange(child);
            }
            //子view位置发生改变时都会被调用，可以在这个方法中做一些拖动过程中渐变的动画等操作
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }
            //在手势拖动释放的时候被调用，可以在这里设置子View预期到达的位置
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                Log.e(TAG,"xvel="+releasedChild.getLeft());
                Log.e(TAG,"xvel="+releasedChild.getRight());
                Log.e(TAG,"xvel="+releasedChild.getTop());
                Log.e(TAG,"xvel="+releasedChild.getBottom());
               /* //mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView) {
                    //内部还是调用scroview的移动的方法
                    mviewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }*/
            }
            // 这个用来控制拖拽过程中松手后，自动滑行的速度
            @Override
            public int getViewHorizontalDragRange(View child) {
                return 100;
            }

            //就是当你触碰到边界时就会被调用。
            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

        });
    }
    @Override
    public void computeScroll() {
        if (mviewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
    //尺寸改变的时候会调用，记录住该view距离左上的原始距离
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed,left,  top, right, bottom);
        Log.e(TAG,"onLayout");
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
        //实现卡片的叠加效果
        for (int i = 0; i < childCount; i++) {
            int offset = yOffsetStep * i;
            float scale = 1 - SCALE_STEP * i;
            if (i==0){
                mDragView = getChildAt(0);
                setScan(mDragView,offset, scale);
            }if (i==1){
                mAutoBackView = getChildAt(1);
                setScan(mAutoBackView,offset, scale);
            }if (i==2){
                mEdgeTrackerView = getChildAt(2);
                setScan(mEdgeTrackerView,offset, scale);
            }
          //  Log.e(TAG,"onFinishInflate=="+offset);
        }
    }
    //在加载完布局后会调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i==0){
                mDragView = getChildAt(0);
            }if (i==1){
                mAutoBackView = getChildAt(1);
            }if (i==2){
                mEdgeTrackerView = getChildAt(2);
            }
        }
        Log.e(TAG,"onFinishInflate=="+getChildCount());
    }

    /**
     * @param mDragView 需要改变的vie
     * @param offset 距离底部的距离
     * @param scale 缩放的大小
     */
    private void setScan(View mDragView,int offset, float scale) {
        mDragView.setScaleX(scale);
        mDragView.setScaleY(scale);
        mDragView.offsetTopAndBottom(offset);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mviewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mviewDragHelper.processTouchEvent(event);
        return true;
    }
}
