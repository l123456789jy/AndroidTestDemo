package com.example.administrator.myapplication;

import android.os.Bundle;
import android.app.Activity;

import com.example.administrator.myapplication.model.NutritionFacts;

public class ViewTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        //建造者模式
        NutritionFacts.Builder builder = new NutritionFacts.Builder(20, 0);
        builder.fat(50).Builder();
    }

}
