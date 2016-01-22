package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.administrator.myapplication.gestureDetector.Gesturelistener;

/**
 * GestureDetector通过这个类我们可以识别很多的手势
 */
public class GestureDetectorActivity extends AppCompatActivity
        implements View.OnTouchListener {
    GestureDetector gestureDetector;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tv) TextView mTv;
    @Bind(R.id.fab) FloatingActionButton mFab;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }


    private void init() {
        gestureDetector = new GestureDetector(new Gesturelistener(this));
        //以下配置必须设置要不然onFling其他事件不响应
        mTv.setOnTouchListener(this);
        mTv.setFocusable(true);
        mTv.setClickable(true);
        mTv.setLongClickable(true);

    }

    @Override public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
