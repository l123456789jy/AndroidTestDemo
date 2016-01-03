package com.example.administrator.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * 作者：Administrator on 2015/12/27 16:06
 * 邮箱：906514731@qq.com
 * 自定义drawable
 */
public class CustomDrawable extends Drawable {
    Paint mPaint;

    //不知为什么需要设置背景才行
    public CustomDrawable() {
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }


    @Override public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        float x = bounds.exactCenterX();
        float y = bounds.exactCenterY();
        canvas.drawCircle(x, y, Math.max(x, y), mPaint);
    }


    @Override public void setAlpha(int alpha) {
         mPaint.setAlpha(alpha);
        invalidateSelf();
    }


    @Override public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }


    @Override public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
