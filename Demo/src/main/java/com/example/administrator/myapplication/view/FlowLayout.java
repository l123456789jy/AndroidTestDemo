package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Administrator on 2016/1/21 21:05
 * 邮箱：906514731@qq.com
 * 流式布局
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量自己的大小和子容器的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //得到测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //得到测量的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int weight = 0;
        int height = 0;

        int lineHeight = 0;
        int lineWeight = 0;
        //我们需要遍历所有的子view进行测量
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //测量子view
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //得到child的lp因为需要得到padding的距离所以使用MarginLayoutParams
            MarginLayoutParams lp
                    = (MarginLayoutParams) childView.getLayoutParams();
            //得到每个子view的实际占的空间大小
            int childHeight = childView.getMeasuredHeight() + lp.leftMargin +
                    lp.rightMargin;
            //算出每个view的实际的宽度
            int childWeight = childView.getMeasuredWidth() + lp.bottomMargin +
                    lp.topMargin;
            //判断当前的宽度，是否大于给定的最大宽度，因为如果模式是atmost的话允许的最大宽度就是测量的到的最大宽度
            //大于就要重新开启一行
            if (lineWeight + childWeight > widthSize) {
                //取最大的宽度为测量的宽度
                weight = Math.max(lineWeight, childWeight);
                lineWeight=childWeight;//重新记录新一行的宽度
                //高度是整个view的高度所以要叠加高度
                height+=lineHeight;
                //记录新的一行的高度
                lineHeight=childHeight;
            }
            else {
                // 否则累加值lineWidth,lineHeight取最大高度
                lineWeight+=childWeight;
                //高度取最高的
                lineHeight=Math.max(lineHeight,childHeight);
            }
            //代表是最后一个子view计算出最后整体的view的高度和宽度
            if (i==getChildCount()-1){
                weight = Math.max(lineWeight, childWeight);
                //高度是整个view的高度所以要叠加高度
                height+=lineHeight;
            }
        }

        //如果父布局已经知道了具体的大小，那么就是用具体的值，没有我们据需要使用测量的数值
        setMeasuredDimension(
                (widthMode == MeasureSpec.EXACTLY) ? widthSize : weight,
                (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}