package com.lukemi.android.toturial.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lukemi.android.toturial.aidl.ServerAidlInterface;

public class AidlServerService extends Service {

    public AidlServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("AidlServerService","onBind");
        return mBinder;
    }

    private ServerAidlInterface.Stub mBinder = new ServerAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
