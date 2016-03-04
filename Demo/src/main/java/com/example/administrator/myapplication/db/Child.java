package com.example.administrator.myapplication.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者：Administrator on 2016/3/4 14:25
 * 邮箱：906514731@qq.com
 */
@Table(name = "child") public class Child {

    @Column(name = "id", isId = true) private int id;

    @Column(name = "ownid") private String ownid;

    @Column(name = "versions") private String version;

    // 这个属性被忽略，不存入数据库
    private String willIgnore;

    @Column(name = "datas") private String data;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getOwnid() {
        return ownid;
    }


    public void setOwnid(String ownid) {
        this.ownid = ownid;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public String getWillIgnore() {
        return willIgnore;
    }


    public void setWillIgnore(String willIgnore) {
        this.willIgnore = willIgnore;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    @Override public String toString() {
        return "Child{" +
                "id=" + id +
                ", ownid='" + ownid + '\'' +
                ", version='" + version + '\'' +
                ", willIgnore='" + willIgnore + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}