package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：liujingyuan on 2016/1/19 14:09
 * 邮箱：906514731@qq.com
 * 数值排列的viewgroup
 */
public class VerticalGroup extends ViewGroup{

    public VerticalGroup(Context context) {
        super(context);
    }


    public VerticalGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public VerticalGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //代表设置的是具体的数值，或者fii，那么我们就不需要测量数值，直接使用传递过来的就行了
        if (widthMode==MeasureSpec.EXACTLY){
            width=sizeWidth;
        }if (heightMode==MeasureSpec.EXACTLY){
            height=sizeHeight;
        }else{
            //就需要我们去手动的测量子view的宽和高
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                //得到测量的数值
                int measuredHeight = childAt.getMeasuredHeight();
                int measuredWidth = childAt.getMeasuredWidth();
                width=measuredWidth;
                height=measuredHeight;
            }
        }
        setMeasuredDimension(width,height);
    }


    /**
     * 设置每个view的位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int totalHeight=0;
        //就需要我们去手动的测量子view的宽和高
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //得到测量的每个子view的数值
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            Log.e("onLayout", "onLayout=="+getWidth()/2);
            //这里需要注意的是 距离底部距离是要加上自己的高度的，和距离右边的距离也要加上自己的宽度
            childAt.layout(getWidth()/2-measuredWidth/2, totalHeight, measuredWidth+getWidth()/2-measuredWidth/2, totalHeight + measuredHeight);
            totalHeight+=measuredHeight;
        }
    }
}
