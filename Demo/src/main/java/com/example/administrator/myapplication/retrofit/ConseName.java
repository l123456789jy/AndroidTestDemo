package com.example.administrator.myapplication.retrofit;

/**
 * 作者：Administrator on 2016/3/20 20:04
 * 邮箱：906514731@qq.com
 */
public class ConseName {

    /**
     * resultcode : 200
     * error_code : 0
     * name : 狮子座
     * datetime : 2015年11月26日
     * date : 20151126
     * all : 60%
     * color : 银色
     * health : 80%
     * love : 40%
     * money : 60%
     * number : 9
     * QFriend : 天秤座
     * summary : 无论是有无对象的狮子，今天的恋爱运都不是很好。单身的人虽有满腔的热血，但是落花有意流水无情，付出的感情也不太容易得到回应；而有伴侣的人，与对方的感情开始出现考验，情海生变的机率大增，不可不慎！
     * work : 60%
     */

    public ResultEntity result;
    /**
     * result : {"resultcode":"200","error_code":"0","name":"狮子座","datetime":"2015年11月26日","date":"20151126","all":"60%","color":"银色","health":"80%","love":"40%","money":"60%","number":"9","QFriend":"天秤座","summary":"无论是有无对象的狮子，今天的恋爱运都不是很好。单身的人虽有满腔的热血，但是落花有意流水无情，付出的感情也不太容易得到回应；而有伴侣的人，与对方的感情开始出现考验，情海生变的机率大增，不可不慎！","work":"60%"}
     * error_code : 0
     * reason : Succes
     */

    public int error_code;
    public String reason;

    public static class ResultEntity {
        public String resultcode;
        public String error_code;
        public String name;
        public String datetime;
        public String date;
        public String all;
        public String color;
        public String health;
        public String love;
        public String money;
        public String number;
        public String QFriend;
        public String summary;
        public String work;
    }
}