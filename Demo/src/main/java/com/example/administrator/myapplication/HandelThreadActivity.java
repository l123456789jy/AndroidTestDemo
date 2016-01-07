package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class HandelThreadActivity extends AppCompatActivity {

    private HandlerThread mHandlerThread;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handel_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }


    /**
     * 首先Handler和HandlerThread的主要区别是：Handler与Activity在同一个线程中
     * HandlerThread与Activity不在同一个线程，而是别外新的线程中(Handler中不能做耗时的操作)。
     */
    private void init() {
       mHandlerThread = new HandlerThread("MyHandlerThread");
        mHandlerThread.start();
        //这个地方获取的loop是子线程的loop
        final Handler mhandler = new Handler(mHandlerThread.getLooper(),
                new Handler.Callback() {
                    @Override public boolean handleMessage(Message msg) {
                        Toast.makeText(getApplicationContext(), "Message",
                                Toast.LENGTH_LONG).show(); return false;
                    }
                });

        //运行在非ui线程
        mhandler.post(new Runnable() {
            @Override public void run() {
                try {
                    new Thread().sleep(1000);
                    mhandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.getLooper().quit();
    }
}
