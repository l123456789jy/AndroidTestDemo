package com.example.administrator.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {
    private static final String TAG = "WebviewActivity";
    private final String MESSAGE = "我是从安卓界面传递过来的";
    private  final String PATH="btn-paizhao.png";
    WebView wv;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.wv) WebView mWv;
    @Bind(R.id.bt) Button mBt;
    @Bind(R.id.fab) FloatingActionButton mFab;


    /**
     * 总结，如果只是单独传递字符串，可以不用把远端的html页面下载下来，直接调用，如果涉及到显示图片
     * 就需要下载下来了，你显示的是本地的照片他找不到照片的！
     * @param savedInstanceState
     */
    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
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
          //wv.loadUrl("http://l123456789jy.github.io/blog/2015/01/16/test" +
              //  ".html");
        wv.loadUrl("file:///android_asset/main.html");
        //这样让js与webview建立起链接，webview里面的界面就可以使用 jsObj来调用安卓里面的写好的方法了
        wv.addJavascriptInterface(new JavaScriptObject(this), "jsObj");     //
        // jsObj 为桥连对象

        //监听加载完毕的事件！
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                //  view.loadUrl("javascript:setData(" + detailshtml + ")");
                // 调用js里面写入好的方法
                view.loadUrl("javascript:showFromHtml()");
                Log.e(TAG, "onPageFinished");
            }
        });
        //调用网页的方法并且将数值传递过去
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                wv.loadUrl("javascript: showFromHtml2('" + MESSAGE + "')");
                //设置图片
                wv.loadUrl("javascript: setImage('" + PATH + "')");
            }
        });
    }


    //这个是让js调用安卓的方法！
    public class JavaScriptObject {
        Context mContxt;


        JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }


        //4.4以上必须加上
        @JavascriptInterface public void SetTitle(String titileName) {//获取标题！
            Toast.makeText(getApplicationContext(), titileName,
                    Toast.LENGTH_LONG).show();
        }


        @JavascriptInterface public void callNatvie() {
            Toast.makeText(getApplicationContext(), "收到网页的调用啦！",
                    Toast.LENGTH_LONG).show();
        }
    }
}
