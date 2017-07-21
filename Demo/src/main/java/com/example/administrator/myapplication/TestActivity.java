package com.example.administrator.myapplication;

import static com.example.administrator.myapplication.R.id.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.administrator.myapplication.adapter.BaseFragmentAdapter;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

  ViewPager mViewPager;
  List<Fragment> mFragments;

  String[] mTitles = new String[]{
      "主页", "微博", "相册"
  };
  private TabLayout mTabLayout;
  Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_toobal);
    mToolbar = (Toolbar) findViewById(toolbar);//设置
    CollapsingToolbarLayout coll = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
    coll.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
    coll.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
    coll.setTitle("苍井空");//设置标题的名字
    coll.setExpandedTitleColor(Color.TRANSPARENT);
    // 该属性必须在setSupportActionBar之前 调用
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // 第一步，初始化ViewPager和TabLayout
    mViewPager = (ViewPager) findViewById(R.id.viewpager);
    mTabLayout = (TabLayout) findViewById(R.id.tabs);
    setupViewPager();

    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_search) {
          Toast.makeText(TestActivity.this, "action_search", Toast.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.action_notification) {
          Toast.makeText(TestActivity.this, "action_notification", Toast.LENGTH_SHORT).show();
        }
        return true;
      }
    });



  }

  private void setupViewPager() {

    mFragments = new ArrayList<>();
    for (int i = 0; i < mTitles.length; i++) {
      ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
      mFragments.add(listFragment);
    }
    // 第二步：为ViewPager设置适配器
    BaseFragmentAdapter adapter =
        new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);

    mViewPager.setAdapter(adapter);
    //  第三步：将ViewPager与TableLayout 绑定在一起
    mTabLayout.setupWithViewPager(mViewPager);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
    getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true;
  }

}
