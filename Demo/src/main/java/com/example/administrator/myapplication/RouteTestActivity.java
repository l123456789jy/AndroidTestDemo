package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 路由跳转
 */
public class RouteTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_test);
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = i_getvalue.getData();
            if (uri != null) {
                String name = uri.getQueryParameter("name");
                String age = uri.getQueryParameter("age");
                String from = uri.getQueryParameter("from");
                Toast.makeText(RouteTestActivity.this, "name is :" + name + "==age is :" + age + "==from is :" + from, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
