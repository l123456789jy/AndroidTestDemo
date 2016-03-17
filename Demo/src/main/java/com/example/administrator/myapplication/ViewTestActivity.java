package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.administrator.myapplication.model.NutritionFacts;
import com.example.administrator.myapplication.view.CustomDrawable;

public class ViewTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        ImageView mImageView = (ImageView) findViewById(R.id.CustomDrawable);
        CustomDrawable mCustomDrawable=new CustomDrawable();
        mImageView.setImageDrawable(mCustomDrawable);


        //建造者模式
        NutritionFacts.Builder builder = new NutritionFacts.Builder(20, 0);
        builder.fat(50).sodium(20).Builder();


    }

}
