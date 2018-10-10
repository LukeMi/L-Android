package com.lukemi.android.tutorial.volum;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicBean implements Parcelable {
    private String name ;
    private String dec;

    public MusicBean() {
    }

    public MusicBean(String name, String dec) {
        this.name = name;
        this.dec = dec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.dec);
    }

    protected MusicBean(Parcel in) {
        this.name = in.readString();
        this.dec = in.readString();
    }

    public static final Parcelable.Creator<MusicBean> CREATOR = new Parcelable.Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel source) {
            return new MusicBean(source);
        }

        @Override
        public MusicBean[] newArray(int size) {
            return new MusicBean[size];
        }
    };
}
