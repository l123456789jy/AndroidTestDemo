package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.bt)
    Button mBt;
    Intent mIntent;
    @Bind(R.id.bt_event)
    Button btEvent;
    @Bind(R.id.bt_thread)
    Button mBtThread;
    @Bind(R.id.bt_test_view)
    Button btTestView;
    @Bind(R.id.bt_refresh)
    Button mBtRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //打开View移动的测试界面
            case R.id.bt:
                mIntent = new Intent(MainActivity.this, TestMoveActivity.class);
                startActivity(mIntent);
                break;
            //打开View事件测试界面
            case R.id.bt_event:
                mIntent = new Intent(MainActivity.this, TestEventActivity.class);
                startActivity(mIntent);
                break;
            //测试线程池
            case R.id.bt_thread:
                mIntent = new Intent(MainActivity.this, ThreadPollTestActivity.class);
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
        }
    }

    public class MyAsyTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
