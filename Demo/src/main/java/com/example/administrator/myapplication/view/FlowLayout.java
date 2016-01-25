package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator on 2016/1/21 21:05
 * 邮箱：906514731@qq.com
 * 流式布局
 */
public class FlowLayout extends ViewGroup {

    private static final String TAG ="FlowLayout";


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
     * 测量自己的大小，既自己在屏幕中的宽度和高度
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


    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        // 遍历所有的孩子
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果已经需要换行
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)
            {
                // 记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }
            /**
             * 如果不需要换行，则累加
             */
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }

    }


    @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}