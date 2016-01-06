package com.example.administrator.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 动画的测试界面
 */
public class AnimationActivity extends AppCompatActivity {

    @Bind(R.id.iv) ImageView mIv;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_alp) void startAlp() {
        initAnimation();
    }

    @OnClick(R.id.bt_tran) void startTransLiation() {
        initAnimation2();
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(300);
        mIv.startAnimation(alphaAnimation);
    }

    private void initAnimation2() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mIv,
                "translationY", -mIv.getHeight());
        translationY.start();
    }
}
