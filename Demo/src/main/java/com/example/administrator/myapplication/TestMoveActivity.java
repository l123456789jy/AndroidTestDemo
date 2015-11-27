package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.view.JellyTextView;
import com.example.administrator.myapplication.view.TranseButton;

import butterknife.Bind;
import butterknife.ButterKnife;

//测试移动view的界面
public class TestMoveActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ty)
    TranseButton mTy;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.mJellyTextView)
    JellyTextView mMJellyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VIEW的移动");
        setSupportActionBar(toolbar);
    }

}
