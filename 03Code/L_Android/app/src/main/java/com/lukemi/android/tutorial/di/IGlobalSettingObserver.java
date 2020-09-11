package com.lukemi.android.tutorial.di;

import android.content.SharedPreferences;

public interface IGlobalSettingObserver {
    void onSaveData(SharedPreferences.Editor editor);

    void onLoadData(SharedPreferences sp);
}
