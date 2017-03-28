package com.tbug.android.collection.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * 手机应用信息类
 * Created by mzchen on 2017/2/3.
 */
@Table(name = "AppInfo", id = "_id")
public class AppInfo extends Model implements Serializable {
    @Column(name = "appName")
    public String appName;// 程序名
    @Column(name = "pn")
    public String pn; // 程序包名
    @Column(name = "vn")
    public String vn = "";//版本名
    //    public Intent intent;// 启动Intent
//    public Drawable icon;// 程序图标
    @Column(name = "vc")
    public int vc;//版本号
    @Column(name = "ld")
    public long ld;//上次更新时间
    @Column(name = "fd")
    public long fd;//首次安装时间
//    private PermissionInfo[] permissions;//权限


    public AppInfo() {
    }

    public AppInfo(String appName, String pn, String vn, Intent intent, Drawable icon, int vc, long ld, long fd) {
        this.appName = appName;
        this.pn = pn;
        this.vn = vn;
        this.vc = vc;
        this.ld = ld;
        this.fd = fd;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }


    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public long getLd() {
        return ld;
    }

    public void setLd(long ld) {
        this.ld = ld;
    }

    public long getFd() {
        return fd;
    }

    public void setFd(long fd) {
        this.fd = fd;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appName='" + appName + '\'' +
                ", pn='" + pn + '\'' +
                ", vn='" + vn + '\'' +
                ", vc=" + vc +
                ", ld=" + ld +
                ", fd=" + fd +
                '}';
    }


}
