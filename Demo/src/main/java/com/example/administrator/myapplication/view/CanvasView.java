package com.example.administrator.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：liujingyuan on 2016/1/28 13:32
 * 邮箱：906514731@qq.com
 * canvas 使用详解
 */
public class CanvasView extends View {
    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00,0xFF7CFd00,0xFF8CFC00,0xFd7CFC00,};
    Paint mpaint;
    int  angle=0;
    public CanvasView(Context context) {
        super(context);
        init();
    }


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mpaint = new Paint(Color.BLUE);
        mpaint.setStrokeWidth(10);
        mpaint.setStyle(Paint.Style.FILL);//填充方式为填满
    }


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
        weight=800;
        height=400;
        //如果父布局已经知道了具体的大小，那么就是用具体的值，没有我们据需要使用测量的数值
        setMeasuredDimension(
                (widthMode == MeasureSpec.EXACTLY) ? widthSize : weight,
                (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
    }


    /**
     *
     * @param canvas
     */
    @Override protected void onDraw(Canvas canvas) {
        //在这里指定了右下角和左上角为位置绘制一个矩形
        RectF rectF = new RectF(100,100,500,400);
        //绘制圆角矩形 这个代表了，圆角长半径和短半径
        // canvas.drawRoundRect(rectF,30,30,mpaint);
        //绘制矩形
        //canvas.drawRect(rectF,mpaint);
        //绘制圆形
        canvas.drawCircle(300,300,20,mpaint);
        for (int i = 0; i < mColors.length; i++) {
            //绘制圆弧
            mpaint.setColor(mColors[i]);
            //绘制圆弧 2和第3个参数代表圆弧开始的角度和结束的角度
            canvas.drawArc(rectF,angle,30,true,mpaint);
            angle+=30;
        }


    }
}
