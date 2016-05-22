package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.circledemo.CirlActivity;
import com.example.administrator.myapplication.db.DbActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.bt) Button mBt;
    Intent mIntent;
    @Bind(R.id.bt_event) Button btEvent;
    @Bind(R.id.bt_thread) Button mBtThread;
    @Bind(R.id.bt_test_view) Button btTestView;
    @Bind(R.id.bt_refresh) Button mBtRefresh;
    @Bind(R.id.bt_viewdrag_help) Button mBtViewdragHelp;
    @Bind(R.id.bt_animation) Button mBtAnimation;
    @Bind(R.id.bt_handel) Button mBtHandel;
    @Bind(R.id.bt_obse) Button mBtObse;
    @Bind(R.id.bt_gesture) Button mBtGesture;
    @Bind(R.id.bt_tv) Button mBtTv;
    @Bind(R.id.bt_view_group) Button mBtViewGroup;
    @Bind(R.id.bt_view_float) Button mBtViewFloat;
    @Bind(R.id.bt_canvas) Button mBtCanvas;
    @Bind(R.id.bt_db) Button mBtDb;
    @Bind(R.id.bt_des) Button mBtDes;
    @Bind(R.id.bt_re) Button mBtRe;
    @Bind(R.id.bt_post) Button mBtPost;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_move);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBt.setOnClickListener(this);
        btEvent.setOnClickListener(this);
        mBtThread.setOnClickListener(this);
        btTestView.setOnClickListener(this);
        mBtRefresh.setOnClickListener(this);
        mBtViewdragHelp.setOnClickListener(this);
        mBtAnimation.setOnClickListener(this);
        mBtHandel.setOnClickListener(this);
        mBtObse.setOnClickListener(this);
        mBtGesture.setOnClickListener(this);
        mBtTv.setOnClickListener(this);
        mBtViewGroup.setOnClickListener(this);
        mBtViewFloat.setOnClickListener(this);
        mBtCanvas.setOnClickListener(this);
        mBtDb.setOnClickListener(this);
        mBtDes.setOnClickListener(this);
        mBtRe.setOnClickListener(this);
        mBtPost.setOnClickListener(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            //打开View移动的测试界面
            case R.id.bt:
                mIntent = new Intent(MainActivity.this, TestMoveActivity.class);
                startActivity(mIntent);
                break;
            //打开View事件测试界面
            case R.id.bt_event:
                mIntent = new Intent(MainActivity.this,
                        TestEventActivity.class);
                startActivity(mIntent);
                break;
            //测试线程池
            case R.id.bt_thread:
                mIntent = new Intent(MainActivity.this,
                        ThreadPollTestActivity.class);
                startActivity(mIntent);
                break;
            //自定义view
            case R.id.bt_test_view:
                mIntent = new Intent(MainActivity.this, ViewTestActivity.class);
                startActivity(mIntent);
                break;
            //刷新的listview
            case R.id.bt_refresh:
                mIntent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(mIntent);
                break;
            //ViewDragHelper使用
            case R.id.bt_viewdrag_help:
                mIntent = new Intent(MainActivity.this,
                        ViewDragHelperActivity.class);
                startActivity(mIntent);
                break;
            //动画的使用
            case R.id.bt_animation:
                mIntent = new Intent(MainActivity.this,
                        AnimationActivity.class);
                startActivity(mIntent);
                break;
            //handelthread
            case R.id.bt_handel:
                mIntent = new Intent(MainActivity.this,
                        HandelThreadActivity.class);
                startActivity(mIntent);
                break;
            //观察者模式
            case R.id.bt_obse:
                mIntent = new Intent(MainActivity.this, ObseverActivity.class);
                startActivity(mIntent);
                break;
            //GestureDetector手势类探索
            case R.id.bt_gesture:
                mIntent = new Intent(MainActivity.this,
                        GestureDetectorActivity.class);
                startActivity(mIntent);
                break;
            //webview
            case R.id.bt_tv:
                mIntent = new Intent(MainActivity.this, WebviewActivity.class);
                startActivity(mIntent);
                break;
            //自定义viewgroup
            case R.id.bt_view_group:
                mIntent = new Intent(MainActivity.this,
                        ViewGroupActivity.class);
                startActivity(mIntent);
                break;
            //自定义流式布局
            case R.id.bt_view_float:
                mIntent = new Intent(MainActivity.this, FlowActivity.class);
                startActivity(mIntent);
                break;
            //Canvas基本api使用
            case R.id.bt_canvas:
                mIntent = new Intent(MainActivity.this, CanvasActivity.class);
                startActivity(mIntent);
                break;
            //db测试
            case R.id.bt_db:
                mIntent = new Intent(MainActivity.this, DbActivity.class);
                startActivity(mIntent);
                break;
            //朋友圈
            case R.id.bt_des:
                mIntent = new Intent(MainActivity.this, CirlActivity.class);
                startActivity(mIntent);
                break;
            //retrofit使用
            case R.id.bt_re:
                mIntent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(mIntent);
                break;
            //社区
            case R.id.bt_post:
                mIntent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(mIntent);
                break;


        }
    }
}
