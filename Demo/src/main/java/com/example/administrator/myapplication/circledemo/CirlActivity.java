package com.example.administrator.myapplication.circledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.circledemo.adputer.CirlAdputer;
import com.example.administrator.myapplication.circledemo.bean.CirlBean;
import com.example.administrator.myapplication.circledemo.utiles.AssetDatabaseOpenHelper;
import com.example.administrator.myapplication.circledemo.utiles.GsonUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class CirlActivity extends AppCompatActivity
        implements XRecyclerView.LoadingListener {

    @Bind(R.id.rv_cirl) RecyclerView mRvCirl;
    @Bind(R.id.recyclerview) XRecyclerView mRecyclerview;
    @Bind(R.id.text_empty) TextView mTextEmpty;
    CirlAdputer cirlAdputer;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirl);
        ButterKnife.bind(this);
        initData();
        initXRc();
    }


    private void initXRc() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(cirlAdputer);
        mRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerview.setLaodingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerview.setLoadingMoreEnabled(true);
        mRecyclerview.setLoadingListener(this);

    }


    private void initData() {
        String fromAssets = AssetDatabaseOpenHelper.getFromAssets("cril.json",
                this);
        CirlBean cirlBean = GsonUtils.getInstance()
                                     .getGson()
                                     .fromJson(fromAssets, CirlBean.class);
        Log.e("tag", cirlBean.getComments().size() + "");


        cirlAdputer = new CirlAdputer(cirlBean,CirlActivity.this);
    }


    @Override public void onRefresh() {
        Toast.makeText(this,"shuaixnle",Toast.LENGTH_SHORT).show();
        mRecyclerview.refreshComplete();
    }


    @Override public void onLoadMore() {
        Toast.makeText(this,"上啦加载了",Toast.LENGTH_SHORT).show();
        mRecyclerview.loadMoreComplete();
    }
}
