package com.lukemi.android.tutorial.di;

import android.content.SharedPreferences;

import com.jeferry.android.annotation.SettingKey;

public class GarageGlobalSetting implements IGlobalSettingObserver {

    @SettingKey(defaultInt = 1)
    public int anInt;

    @SettingKey(defaultString = "anString")
    public String anString;

    @SettingKey(defaultLong = 100)
    public long anLong;

    @SettingKey(defaultFloat = 1.03F)
    public float anFloat;

    @SettingKey(defaultBoolean = true)
    public boolean anBoolean;

    @Override
    public void onSaveData(SharedPreferences.Editor editor) {

    }

    @Override
    public void onLoadData(SharedPreferences sp) {

    }
}
