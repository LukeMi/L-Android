package com.lukemi.android.tutorial.user.userInfo.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfoBean implements Parcelable {


    /**
     * 简介
     */
    private String profile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 0：女性 ；1：男性
     */
    private int gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 学历
     */
    private String degree;

    public UserInfoBean() {
    }

    /**
     * @param name   姓名
     * @param gender 性别
     * @param age    年龄
     * @param mobile 手机号
     * @param degree 学历
     */
    public UserInfoBean(String name, int gender, int age, String mobile, String degree) {
        this.profile = name + "的初识";
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mobile = mobile;
        this.degree = degree;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profile);
        dest.writeString(this.name);
        dest.writeInt(this.gender);
        dest.writeInt(this.age);
        dest.writeString(this.mobile);
        dest.writeString(this.degree);
    }

    protected UserInfoBean(Parcel in) {
        this.profile = in.readString();
        this.name = in.readString();
        this.gender = in.readInt();
        this.age = in.readInt();
        this.mobile = in.readString();
        this.degree = in.readString();
    }

    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel source) {
            return new UserInfoBean(source);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };
}
