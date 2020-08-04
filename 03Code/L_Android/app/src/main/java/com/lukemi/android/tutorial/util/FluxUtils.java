package com.lukemi.android.tutorial.util;

import android.net.TrafficStats;

/**
 * Created by mzchen on 2017/1/11.
 */

public class FluxUtils {

    /**
     * 统计某个UID程序的流量(从手机开机开始计算)
     *
     * @return 单位（千字节 kb）
     */
    public static long getCurrentTraffic(int uid) {
        try {
            TrafficStats ts = new TrafficStats();
            long uidRxBytes = TrafficStats.getUidRxBytes(uid);
            long uidTxBytes = TrafficStats.getUidTxBytes(uid);
            return (uidRxBytes + uidTxBytes) / 1024;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
