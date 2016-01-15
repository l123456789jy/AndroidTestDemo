package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.example.administrator.myapplication.obsever.Observale;
import com.example.administrator.myapplication.obsever.Observer;
import com.example.administrator.myapplication.obsever.Weather;

/**
 * 观察者模式
 */
public class ObseverActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obsever);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }


    private void init() {
        Observale<Weather> weatherObservale = new Observale<>();
        Observer<Weather> weatherObserver = new Observer<Weather>() {
            //实现监听
            @Override
            public void onUpdate(Observale<Weather> observable, Weather data) {
                Log.e("onUpdate", "onUpdate:observable ");
           }
        };
        //注册监听
        weatherObservale.register(weatherObserver);
        Weather mWeather=new Weather("天气不错");
        weatherObservale.notifyObservers(mWeather);
    }
}
