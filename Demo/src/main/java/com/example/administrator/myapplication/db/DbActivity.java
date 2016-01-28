package com.example.administrator.myapplication.db;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.context.ApplicationData;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * xutiles的数据库测试
 */
public class DbActivity extends AppCompatActivity
        implements View.OnClickListener {
    Child mchild;
    DbManager db;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.bt_add) Button mBtAdd;
    @Bind(R.id.bt_del) Button mBtDel;
    @Bind(R.id.bt_cg) Button mBtCg;
    @Bind(R.id.bt_fd) Button mBtFd;
    @Bind(R.id.fab) FloatingActionButton mFab;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }


    private void init() {
        mBtAdd.setOnClickListener(this);
        mBtDel.setOnClickListener(this);
        mBtCg.setOnClickListener(this);
        mBtFd.setOnClickListener(this);

        //得到db
        db = x.getDb(ApplicationData.getDbConfig());
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                mchild = new Child();
                mchild.setData("adasagdf4484498");
                mchild.setOwnid("1");
                mchild.setVersion("17151651");
                try {
                    db.save(mchild);
                } catch (DbException e) {
                    e.printStackTrace();
                    LogUtil.e("保存出错===" + e.getMessage());
                }
                Toast.makeText(getApplicationContext(),"保存成功",Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.bt_del:
                try {
                    db.delete(Child.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"删除成功",Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.bt_cg:
                try {
                    Selector<Child> id = db.selector(Child.class).where("id", "=", 3);
                    List<Child> all = id.findAll();
                    for (int i = 0; i < all.size(); i++) {
                        all.get(i).setVersion("5566");
                        db.update(all.get(i));
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                    LogUtil.e("更新失败"+e.getMessage());
                }
                Toast.makeText(getApplicationContext(),"更新成功",Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.bt_fd:
                try {
                    List<Child> children = db.selector(Child.class).findAll();
                    for (int i = 0; i < children.size(); i++) {
                        LogUtil.e("id"+children.get(i).getId()
                                +'\''+"version"+children.get(i).getVersion());
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"查找成功",Toast
                        .LENGTH_SHORT).show();
                break;
        }
    }
}
