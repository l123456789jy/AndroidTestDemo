package com.example.administrator.myapplication.retrofit;

/**
 * 作者：Administrator on 2016/3/20 20:09
 * 邮箱：906514731@qq.com
 */
public class ConseEnty {
    private String key;
    private String consName;
    private String type;


    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getConsName() {
        return consName;
    }


    public void setConsName(String consName) {
        this.consName = consName;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getDtype() {
        return dtype;
    }


    public void setDtype(String dtype) {
        this.dtype = dtype;
    }


    public boolean isFormat() {
        return format;
    }


    public void setFormat(boolean format) {
        this.format = format;
    }


    private String dtype;
    private boolean format;
}