package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

//学习rxjava
public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        IterListform();
        lodeMearge();
    }

    //rxjav的from函数可以循环一个集合
    private void IterListform() {
        List mList = new ArrayList();
        mList.add(1);
        mList.add(2);
        mList.add(3);
        mList.add(4);
        mList.add(4);
        mList.add(5);
        loadList(mList);
        FilteList(mList);

    }

    //合并同一个请求
    private void lodeMearge() {
        //合并多个请求按顺序执行
        Observable.concat(Observable.just("a"), Observable.just("b"), Observable.just("c")).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("concat", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("concat", e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("concat", "onNext s : " + s);
                    }
                });


    }

    //利用from循环一个集合
    public void loadList(List mList) {
      /*  RxJava提供了5种调度器：
                .io() I/O操作
                .computation()  计算工作默认的调度器
                .immediate()  调度器允许你立即在当前线程执行你指定的工作
                .newThread()  为指定任务启动一个新的线程。
                .trampoline() ，并不是立即，我们可以用.trampoline()将它入队   */
        Observable.from(mList)
                .subscribeOn(Schedulers.io()) //循环的耗时操作在Io线程
                .observeOn(AndroidSchedulers.mainThread())//结果回掉在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer name) {
                        Toast.makeText(RxJavaActivity.this, name + "", Toast.LENGTH_SHORT).show();
                        Log.e("from", name + "");
                    }
                });
    }

    //过滤集合take()和takeLast()
    public void FilteList(List mList) {
        Observable.from(mList)
                .take(3)//代表只要集合中前3个数据
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer name) {
                        Log.e("take", name + "");
                    }
                });

        Observable.from(mList)
                .takeLast(3)//代表只要集合中后3个数据
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer name) {
                        Log.e("takeLast", name + "");
                    }
                });


        Observable.from(mList)
//                .repeat(3)//重复添加这个集合三次
                .distinct()//去重将集合中的重复元素去掉
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer name) {
                        Log.e("distinct", name + "");
                    }
                });

        Observable.from(mList)
                .distinctUntilChanged()//忽略掉所有的重复并且只发射出新的值。适用于跟新较快的时候
//                .repeat(3)//重复添加这个集合三次
                .distinct()//去重将集合中的重复元素去掉
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer name) {
                        Log.e("distinctUntilChanged", name + "");
                    }
                });
        //每隔3秒执行一次
        Observable<Long> interval = Observable.interval(3, TimeUnit.SECONDS);
        interval.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.e("interval", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("interval", "onError");
            }

            @Override
            public void onNext(Long aLong) {
                Log.e("interval", "onNext");

            }
        });


        //2秒之后发出一个动作
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.e("timer", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("timer", "onError");
                    }

                    @Override
                    public void onNext(Long number) {
                        Log.e("timer", "onNext");
                    }
                });

        //将输入的数字按顺序输出
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.e("just", "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("just", "onNext");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("just", "integer" + integer);
                    }
                });


        //将类型改变成string
        Observable.just(1, 2, 3)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //转换成string
                        return integer + "";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("map", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("map", "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(String integer) {
                        Log.e("map", "integer" + integer);
                    }
                });

    }

}
