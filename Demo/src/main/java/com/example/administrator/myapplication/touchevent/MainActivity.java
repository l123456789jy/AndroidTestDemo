/*
 * Copyright 2016 GcsSloop
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *    Last modified 16-9-28 上午2:20
 *
 */

package com.example.administrator.myapplication.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.touchevent.test.Static;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Static.TAG1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            //Log.i(TAG, Static.dispatchTouchEvent+"经理,我准备发展一下电商业务,下周之前做一个淘宝出来.");
            //Log.i(TAG, Static.dispatchTouchEvent+"把按钮做的好看一点,要有光泽,给人一种点击的欲望.");
            Log.i(TAG, Static.dispatchTouchEvent+"现在项目做到什么程度了?");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //Log.i(TAG, Static.onTouchEvent+"这么简单都做不了,你们都是干啥的(愤怒).");
            Log.i(TAG, Static.onTouchEvent);
        }
        return super.onTouchEvent(event);
    }

}
