package com.example.administrator.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：Administrator on 2016/3/20 18:34
 * 邮箱：906514731@qq.com
 */
public interface IGanKService {
    //这种请求方式适用于
  //  http://gank.io/api/data/Android/10/1
    @GET("data/Android/{id}/{postion}")
    Call<GankAntry> getGankData(
            @Path("id") int groupId, @Path("postion") int index);
}
