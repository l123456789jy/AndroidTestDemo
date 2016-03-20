package com.example.administrator.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：Administrator on 2016/3/20 19:10
 * 邮箱：906514731@qq.com
 */
public interface IMoveService {
    //这种请求是这样的形式  https://api.douban.com/v2/movie/top250?start=0&count=10
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
