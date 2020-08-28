// ServerAidlInterface.aidl
package com.lukemi.android.toturial.aidl;

// Declare any non-default types here with import statements

interface ServerAidlInterface {

    int add(int a ,int b);

    int multi(int a, int b);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}
