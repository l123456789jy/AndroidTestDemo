package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {
    WebView wv;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }


    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        //设置编码
        wv.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://github.com/l123456789jy/AndroidTestDemo/blob/master/Demo/src/main/assets/main.html");
    }
}
