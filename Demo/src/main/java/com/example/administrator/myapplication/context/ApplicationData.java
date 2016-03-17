package com.example.administrator.myapplication.context;

import android.app.Application;
import org.xutils.DbManager;
import org.xutils.x;

/**
 * 作者：liujingyuan on 2016/1/28 15:07
 * 邮箱：906514731@qq.com
 */
public class ApplicationData extends Application {
    @Override public void onCreate() {
        super.onCreate();
        initConfig();

    }


    private void initConfig() {
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

    }


    /**
     * 初始化db的配置
     * @return
     */
    public static  DbManager.DaoConfig getDbConfig(){

        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("data.db")
                // 不设置dbDir时, 默认存储在app的私有目录.
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                       // db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });
        return daoConfig;

    }
}
