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
            //算出每个view的真是的宽度
            int childWeight=childView.getMeasuredWidth()+lp.bottomMargin+lp
                    .topMargin;


        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}