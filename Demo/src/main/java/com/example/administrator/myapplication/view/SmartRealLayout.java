package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：liujingyuan on 2016/1/18 17:45
 * 邮箱：906514731@qq.com
 */
public class SmartRealLayout extends ViewGroup {
    private final String TAG="SmartRealLayout";
    public SmartRealLayout(Context context) {
        super(context);
    }


    public SmartRealLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public SmartRealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;
        //获取子view的个数
        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
        int bWidth = 0;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            //获取也就是支持margin的设置
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            //这里获取的是像素都是px所以是界面设置的宽度的2倍
            Log.e(TAG, "cWidth:=="+cWidth);
            Log.e(TAG, "cHeight:=="+cHeight);
            // 上面两个childView  cParams.leftMargin得到界面设置的距离左边的值，
            if (i == 0 || i == 1) {
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                Log.e(TAG, "tWidth:=="+tWidth);
                Log.e(TAG, "cParams.leftMargin:=="+cParams.leftMargin);
                Log.e(TAG, "cParams.rightMargin:=="+cParams.rightMargin);
            }

            if (i == 2 || i == 3) {
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                Log.e(TAG, "bWidth:=="+bWidth);
            }

            if (i == 0 || i == 2) {
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                Log.e(TAG, "lHeight:=="+lHeight);
                Log.e(TAG, "cParams.topMargin:=="+cParams.topMargin);
                Log.e(TAG, "cParams.bottomMargin:=="+cParams.bottomMargin);
            }

            if (i == 1 || i == 3) {
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                Log.e(TAG, "rHeight:=="+rHeight);
            }
        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension(
                (widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width,
                (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }


    // abstract method in viewgroup  onLayout对其所有childView进行定位（设置childView的绘制区域）
    //相对于父控件left、top，right、bottom的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            //得到子view的测量宽和高
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i) {
                case 0:
                    //算出第一个view的位置
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 1:
                    //第二个位置在右上角，这个计算就是屏幕的宽度简易自己的宽度，和左边的宽度的到自己距离左边的位置
                    cl = getWidth() - cWidth - cParams.leftMargin -
                            cParams.rightMargin;
                    ct = cParams.topMargin;
                    Log.e(TAG, "cl: "+cl);
                    break;
                case 2:
                    cl = cParams.leftMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;
                case 3:
                    cl = getWidth() - cWidth - cParams.leftMargin -
                            cParams.rightMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;
            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            //调用每个子view来指定他们的位置
            childView.layout(cl, ct, cr, cb);
        }
    }


    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
