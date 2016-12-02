package com.luke.android.demo.util;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import static android.telephony.TelephonyManager.NETWORK_TYPE_UNKNOWN;

/**
 * Created by mzchen on 2016/12/1.
 */

public class DeviceInfoUtil {

    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    private static final int NETWORK_TYPE_WIFI = -101;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_WIFI = -101;
    /**
     * Unknown network class.
     */
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * Class of broadly defined "2G" networks.
     */
    private static final int NETWORK_CLASS_2_G = 1;
    /**
     * Class of broadly defined "3G" networks.
     */
    private static final int NETWORK_CLASS_3_G = 2;
    /**
     * Class of broadly defined "4G" networks.
     */
    private static final int NETWORK_CLASS_4_G = 3;


    /**
     * 获取手机的IMEI号码(适配6.0权限)
     *
     * @param context 上下文
     * @return IMEI号码
     */
    public static String getIMEI(Context context) {
        String imei = "";
        String sim = "";//实时
        String imsi = "";//实时
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                imei = mtm.getDeviceId();
                sim = mtm.getSimSerialNumber();//实时
                imsi = mtm.getSubscriberId();//实时
            }
        } else {
            imei = mtm.getDeviceId();
            sim = mtm.getSimSerialNumber();//实时
            imsi = mtm.getSubscriberId();//实时
        }
        return imei;
    }

    /**
     * 获取手机的IMSI号码(适配6.0权限)
     *
     * @param context 上下文
     * @return IMSI号码
     */
    public static String getIMSI(Context context) {
        String imsi = "";//实时
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                imsi = mtm.getSubscriberId();//实时
            }
        } else {
            imsi = mtm.getSubscriberId();//实时
        }
        return imsi;
    }

    /**
     * 获取手机的SIM号码(适配6.0权限)
     *
     * @param context 上下文
     * @return SIM号码
     */
    public static String getSIM(Context context) {
        String sim = "";//实时
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                sim = mtm.getSimSerialNumber();//实时
            }
        } else {
            sim = mtm.getSimSerialNumber();//实时
        }
        return sim;
    }

    /**
     * 获取手机的手机号码(适配6.0权限)
     *
     * @param context 上下文
     * @return 手机号码
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = "";
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                phoneNumber = mtm.getLine1Number();
            }
        } else {
            phoneNumber = mtm.getLine1Number();
        }
        return phoneNumber;
    }

    /**
     * 获取手机MacID
     *
     * @return macID
     */
    public static String getMacID(Context context) {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        //当上述方法获取不到就用下面的方法取macId
        if (TextUtils.isEmpty(macSerial)) {
            WifiManager wifi = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String macAddress = info.getMacAddress().replace(":", "");
            macSerial = macAddress == null ? "" : macAddress;
        }
        return macSerial;
    }

    /**
     * 手机型号
     *
     * @return 手机型号
     */
    public static String getPhoneMode() {
        return Build.MODEL;
    }

    /**
     * 手机品牌
     *
     * @return 手机品牌
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取当前系统的版本号
     *
     * @return 版本号（int）
     */
    public static String getOSVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * App 包名
     *
     * @param context 上下文
     * @return 包名
     */
    public static String getApppakg(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取当前手机的语言
     *
     * @param context 上下文
     * @return
     */
    public static String getLanguage(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    /**
     * 获取当前网络类型
     *
     * @param context 上下文
     * @return 网络类型
     */
    public static String getNetType(Context context) {
        int networkClass = getNetworkClass(context);
        String type = "unknown";
        switch (networkClass) {
            case NETWORK_CLASS_UNAVAILABLE:
                type = "noNetwork";
                break;
            case NETWORK_CLASS_WIFI:
                type = "WIFI";
                break;
            case NETWORK_CLASS_2_G:
                type = "2G";
                break;
            case NETWORK_CLASS_3_G:
                type = "3G";
                break;
            case NETWORK_CLASS_4_G:
                type = "4G";
                break;
            case NETWORK_CLASS_UNKNOWN:
                type = "UNKNOWN";
                break;
        }
        return type;
    }

    /**
     * permission.ACCESS_NETWORK_STATE.
     *
     * @param context
     * @return
     */
    private static int getNetworkClass(Context context) {
        int networkType = NETWORK_TYPE_UNKNOWN;
        try {
            ConnectivityManager connectionInfo = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo network = connectionInfo.getActiveNetworkInfo();
            if (network != null && network.isAvailable() && network.isConnected()) {
                int type = network.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE_WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    networkType = telephonyManager.getNetworkType();
                }
            } else {
                networkType = NETWORK_TYPE_UNAVAILABLE;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return getNetworkClassByType(networkType);
    }

    private static int getNetworkClassByType(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case 17:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            case NETWORK_TYPE_WIFI:
                return NETWORK_CLASS_WIFI;
            case NETWORK_TYPE_UNAVAILABLE:
                return NETWORK_CLASS_UNAVAILABLE;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }


    /**
     * 获取当前网络的名字
     *
     * @param context 上下文
     * @return netName
     */
    public static String getNetName(Context context) {
        if ("WIFI".equalsIgnoreCase(getNetType(context))) {
            return getWifiSsid(context);
        } else {
            return getNetOperation(context);
        }
    }

    private static String getNetOperation(Context context) {
        String OperatorsName = "未知";
        String IMSI = "";
        // IMSI号前面3位460是国家，紧接着后面2位00 运营商代码
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                IMSI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
            }
        } else {
            IMSI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        }
        if (IMSI != null && (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007"))) {
            OperatorsName = "中国移动";
        } else if (IMSI != null && (IMSI.startsWith("46001") || IMSI.startsWith("46006"))) {
            OperatorsName = "中国联通";
        } else if (IMSI != null && (IMSI.startsWith("46003") || IMSI.startsWith("46005"))) {
            OperatorsName = "中国电信";
        } else {
            OperatorsName = "未知";
        }
        return OperatorsName;
    }

    private static String getWifiSsid(Context context) {
        String ssid = "";
        try {
            final NetworkInfo network = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (network != null && network.isAvailable() && network.isConnected()) {
                int type = network.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wifiManager = (WifiManager) context
                            .getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    if (wifiInfo != null) {
                        ssid = wifiInfo.getSSID();
                        if (ssid == null) {
                            ssid = "";
                        }
                        ssid = ssid.replaceAll("\"", "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssid;
    }

    /**
     * 获取网络IP
     *
     * @return IP字符串
     */
    public static String getIp(Context context) {
        //判断是否是wifi网络
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            return intToIp(wifiInfo.getIpAddress());
        } else {
            //判断移动网网络状态
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 将 wifi下的 int ip 转换成 String
     *
     * @param i 整形 ip 值
     * @return 字符串IP
     */
    private static String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    /**
     * 获取屏幕的参数（Width，Height，Dentisty）
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取手机的唯一识别码(适配6.0)
     *
     * @param context 上下文
     * @return udid 手机唯一标示
     */
    private String getUdid(Context context) {
        SharedPreferences loadInfoConfigureSPF = context.getSharedPreferences("loadInfoConfigure", Context.MODE_PRIVATE);
        String udid = "", tmSerial, tmPhone, androidId;
        if (TextUtils.isEmpty(udid)) {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String tmDevice = "";
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    tmDevice = "" + tm.getDeviceId();
                }
            } else {
                tmDevice = "" + tm.getDeviceId();
            }
            androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) /*| tmSerial.hashCode()*/);

            String uniqueId = deviceUuid.toString() + new Date().getTime();
            udid = uniqueId;
            loadInfoConfigureSPF.edit().putString("getUdid", udid).commit();
        }
        return udid;
    }

    /**
     * 获取 App 版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static String getAppVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取App标签（名字）
     *
     * @param context 上下文
     * @return
     */
    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        PackageManager packageManager = context.getPackageManager();
        CharSequence appLabel = packageManager.getApplicationLabel(applicationInfo);
        return (String) appLabel;
    }
}
