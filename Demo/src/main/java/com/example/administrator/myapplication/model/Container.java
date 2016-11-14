package com.example.administrator.myapplication.model;

/**
 * 作者：Administrator on 2016/11/14 11:05
 * 邮箱：906514731@qq.com
 */
public class Container<k, v> {
    private k key;
    private v value;


    public Container(k k, v v) {
        key = k;
        value = v;
    }


    public k getKey() {
        return key;
    }


    public void setKey(k key) {
        this.key = key;
    }
}