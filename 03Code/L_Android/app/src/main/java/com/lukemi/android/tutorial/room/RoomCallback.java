package com.lukemi.android.tutorial.room;

public interface RoomCallback<T> {

    void onStart();

    void onSuccess(T t);

    void onError(Throwable throwable);

    void onComplete();
}
