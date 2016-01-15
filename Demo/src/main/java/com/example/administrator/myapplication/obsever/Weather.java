package com.example.administrator.myapplication.obsever;

/**
 * 作者：liujingyuan on 2016/1/13 18:39
 * 邮箱：906514731@qq.com
 */
public class Weather {
    private String description;


    public Weather(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    @Override public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                '}';
    }
}
