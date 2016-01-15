package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2015/12/13.
 */
public class CircleView extends View {
    private int mColor = Color.RED;
    //创建一个画笔，实心的
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        a.recycle();
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        a.recycle();
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }
  /*  EXACTLY：一般是设置了明确的值或者是MATCH_PARENT，我们直接使用测量出来的数值就行
    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
    UNSPECIFIED：表示子布局想要多大就多大，很少使用*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到宽度的测量模式和高度
        int widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMeasureSpecMode == MeasureSpec.AT_MOST) {
            Log.e("widthMeasureSpecMode==", "AT_MOST");
            Log.e("widthMeasureSpecSize==", widthMeasureSpecSize + "");
        }
        if (widthMeasureSpecMode == MeasureSpec.EXACTLY) {
            Log.e("widthMeasureSpecMode==", "EXACTLY");
            Log.e("widthMeasureSpecSize==", widthMeasureSpecSize + "");
        }

        if (heightMeasureSpecMode == MeasureSpec.AT_MOST) {
            Log.e("heightMeasureSpecMode==", "AT_MOST");
            Log.e("heightMeasureSpecSize==", heightMeasureSpecSize + "");
        }
        if (heightMeasureSpecMode == MeasureSpec.EXACTLY) {
            Log.e("heightMeasureSpecMode==", "EXACTLY");
            Log.e("heightMeasureSpecSize==", heightMeasureSpecSize + "");
        }

        //代表当前的宽和高都是warp，直接自己指定宽高就行了
        if (widthMeasureSpecMode == MeasureSpec.AT_MOST && heightMeasureSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);

        } else if (widthMeasureSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightMeasureSpecSize);

        } else if (heightMeasureSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSpecSize, 200);
        }
    }


    @Override protected void drawableStateChanged() {
        super.drawableStateChanged();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2,
                radius, mPaint);

    }
}
