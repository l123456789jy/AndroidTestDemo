package com.example.administrator.myapplication.obsever;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liujingyuan on 2016/1/13 18:31
 * 邮箱：906514731@qq.com
 * 观察着
 */
public class Observale<T> {
    List<Observer<T>> mObservers = new ArrayList<Observer<T>>();

    /**
     * 注册当前的接口
     * @param observer
     */
    public void register(Observer<T> observer) {

        if (observer == null) {
            throw new NullPointerException("observer == null");
        }
        synchronized (this) {
            if (!mObservers.contains(observer)) mObservers.add(observer);
        }
    }


    public synchronized void unregister(Observer<T> observer) {
        mObservers.remove(observer);
    }


    /**
     * 调用所有的注册接口的监听着
     * @param data
     */
    public void notifyObservers(T data) {
        for (Observer<T> observer : mObservers) {
            observer.onUpdate(this, data);
        }
    }
}
