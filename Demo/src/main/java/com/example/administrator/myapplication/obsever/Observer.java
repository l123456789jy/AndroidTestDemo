package com.example.administrator.myapplication.obsever;

/**
 * 作者：liujingyuan on 2016/1/13 18:34
 * 邮箱：906514731@qq.com
 * 接口
 */
public interface Observer<T> {
    void onUpdate(Observale<T> observable, T data);
}
