package com.example.administrator.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者：Administrator on 2016/3/20 18:34
 * 邮箱：906514731@qq.com
 */
public interface IGanKPostService {
    //这种请求方式适用于
  // http://api.avatardata.cn/Constellation/Query 方式: POST
    @POST("Query")
    //这个实体类就是传递的参数的封装类
    Call<ConseName> updateData(@Body ConseEnty mConseEnty);
}
