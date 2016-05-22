package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.administrator.myapplication.retrofit.ConseEnty;
import com.example.administrator.myapplication.retrofit.ConseName;
import com.example.administrator.myapplication.retrofit.GankAntry;
import com.example.administrator.myapplication.retrofit.IGanKPostService;
import com.example.administrator.myapplication.retrofit.IGanKService;
import com.example.administrator.myapplication.retrofit.IMoveService;
import com.example.administrator.myapplication.retrofit.MovieEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Bind(R.id.bt_gt) Button mBtGt;
    @Bind(R.id.bt_pt) Button mBtPt;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_gt) public void sendGet() {
        //getGankData();
        getGankData2();
    }


    @OnClick(R.id.bt_pt) public void sendPost() {
        postGankData();
    }


    //进行Get网络请求
    private void getGankData() {
        //这种请求是这样的形式  http://gank.io/api/data/Android/10/1
        String baseUrl = "http://gank.io/api/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create())
                                                  .build();
        IGanKService ganKService = retrofit.create(IGanKService.class);
        Call<GankAntry> gankData = ganKService.getGankData(10, 1);
        gankData.enqueue(new Callback<GankAntry>() {
            @Override
            public void onResponse(Call<GankAntry> call, Response<GankAntry> response) {
                Log.e("tag", response.body().results.size() + "");
                Log.e("tag", response.body().results.get(0).desc);
            }


            @Override public void onFailure(Call<GankAntry> call, Throwable t) {
                Log.e("tag", t.getMessage());
            }
        });
    }


    //进行Get网络请求
    private void getGankData2() {
        //还可以配置okhttpclient
        //这种请求是这样的形式  https://api.douban.com/v2/movie/top250?start=0&count=10


        //http://api.bdqn.cn/services/youke?mechanism=kgc&method=login&osType=android&osVersion=3.8&passport=15022010530&password=e10adc3949ba59abbe56e057f20f883e&auth=dfc87567a6af67b9cc7543bc39322c61
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create())
                                                  .build();
        IMoveService movieService = retrofit.create(IMoveService.class);
        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
        call.enqueue(new Callback<MovieEntity>() {
            //他回掉在主线程
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Toast.makeText(RetrofitActivity.this,
                        response.body().toString(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {

            }
        });
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        //调用 cancle
    }


    //进行post网络请求
    private void postGankData() {
        ConseEnty mConseEnty=new ConseEnty();
        mConseEnty.setKey("525216ae4f994a3abb8bffbacfec7f08");
        mConseEnty.setConsName("金牛座");
        mConseEnty.setDtype("JSON");
        mConseEnty.setFormat(true);
        mConseEnty.setType("today");
        //还可以配置okhttpclient
        //这种请求是这样的形式  https://gank.io/api/add2gank 方式: POST
        String baseUrl = "http://api.avatardata.cn/Constellation/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create())
                                                  .build();
        IGanKPostService ganKService = retrofit.create(IGanKPostService.class);
        Call<ConseName> stringCall = ganKService.updateData(
                mConseEnty);
        stringCall.enqueue(new Callback<ConseName>() {
            @Override
            public void onResponse(Call<ConseName> call, Response<ConseName> response) {
                Log.e("tag", response.body().error_code+"");
            }


            @Override public void onFailure(Call<ConseName> call, Throwable t) {
                Log.e("tag", t.getMessage() + "");
            }
        });
    }
}
