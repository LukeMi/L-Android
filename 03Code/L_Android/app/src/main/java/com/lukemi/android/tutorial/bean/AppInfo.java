package com.lukemi.android.tutorial.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * 手机应用信息类
 * Created by mzchen on 2017/2/3.
 */

/**
 * 手机应用信息类
 * Created by mzchen on 2017/2/3.
 */
public class AppInfo implements Serializable {
    // 程序名
    public String appName;
    //版本名
    public String vn = "";
    // 程序包名
    public String pn;
    //    public Intent intent;// 启动Intent
    // 程序图标
    public Drawable icon;
    //版本号
    public long vc;
    //上次更新时间
    public long ld;
    //首次安装时间
    public long fd;
//    private PermissionInfo[] permissions;//权限

    public AppInfo() {
    }

    public AppInfo(String appName, String pn, String vn, Intent intent, Drawable icon, int vc, long ld, long fd) {
        this.appName = appName;
        this.pn = pn;
        this.vn = vn;
        this.icon = icon;
        this.vc = vc;
        this.ld = ld;
        this.fd = fd;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
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

    public long getVc() {
        return vc;
    }

    public void setVc(long vc) {
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
