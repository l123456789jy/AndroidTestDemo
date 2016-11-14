package com.example.administrator.myapplication.model;

import android.util.Log;

/**
 * 作者：Administrator on 2016/11/14 11:55
 * 邮箱：906514731@qq.com
 */
public class Main {
    public static <T> void out(T t) {
        Log.e("out", t + "");
    }


    public static <T> void outMultiple(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }
}