package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 线程池的使用测试
 */
public class ThreadPollTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.bt_fixed_thread)
    Button mBtFixedThread;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.bt_auto_thread)
    Button mBtAutoThread;
    @Bind(R.id.bt_only_thread)
    Button mBtOnlyThread;
    @Bind(R.id.bt_contor_thread)
    Button mBtContorThread;
    @Bind(R.id.bt_contor_one_thread)
    Button mBtContorOneThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_poll_test);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBtFixedThread.setOnClickListener(this);
        mBtAutoThread.setOnClickListener(this);
        mBtOnlyThread.setOnClickListener(this);
        mBtContorThread.setOnClickListener(this);
        mBtContorOneThread.setOnClickListener(this);

        new ThreadPoolExecutor(3,3,0l,TimeUnit.MINUTES,new PriorityBlockingQueue());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //单个线程最大的空闲时间都是60秒，60秒之后直接移除
            case R.id.bt_fixed_thread:
                //该方式代表传入一个固定的线程池的大小，该线程池的大小不会改变，这个代表是最大的并发是5个线程
                ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(5);
                for (int i=0;i<10;i++){
                    final int finalI = i;
                    mFixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.e("zxy", "线程："+threadName+",正在执行第" + finalI + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.bt_auto_thread:
                //创建一个只有一个线程的线程池，每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，等待线程处理完再依次处理任务队列中的任务
                ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
                for (int i=0;i<10;i++){
                    final int finalI = i;
                    singleThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.e("zxy", "线程："+threadName+",正在执行第" + finalI + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.bt_only_thread:
                //可以根据实际情况调整线程池中线程的数量的线程池
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                for (int i=0;i<10;i++){
                    final int finalI = i;
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.e("zxy", "线程："+threadName+",正在执行第" + finalI + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.bt_contor_thread:
                //该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
                ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
                //延迟2秒后执行该任务
                scheduledThreadPool.schedule(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2, TimeUnit.SECONDS);
                //延迟1秒后，每隔2秒执行一次该任务
                scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1, 2, TimeUnit.SECONDS);
                break;
            case R.id.bt_contor_one_thread:
                //该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。只不过和上面的区别是该线程池大小为1
                ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
                //延迟1秒后，每隔2秒执行一次该任务
                singleThreadScheduledPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        Log.e("zxy", "线程：" + threadName + ",正在执行");
                    }
                },1,2,TimeUnit.SECONDS);

                break;
        }
    }
}
