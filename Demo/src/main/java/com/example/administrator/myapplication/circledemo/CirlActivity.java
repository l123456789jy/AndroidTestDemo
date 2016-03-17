package com.example.administrator.myapplication.circledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.administrator.myapplication.R;

public class CirlActivity extends AppCompatActivity {

    @Bind(R.id.rv_cirl) RecyclerView mRvCirl;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirl);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {

    }
}
