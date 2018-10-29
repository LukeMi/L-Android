package com.lukemi.android.tutorial.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import static android.os.Build.SERIAL;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UNKNOWN;

/**
 * 获取安卓终端的基本参数
 * Created by mzchen on 2016/12/1.
 */

public class DeviceUtil {

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

    public static final String NETWORK_TYPE_NAME_2G = "2G";
    public static final String NETWORK_TYPE_NAME_3G = "3G";
    public static final String NETWORK_TYPE_NAME_4G = "4G";
    public static final String NETWORK_TYPE_NAME_WIFI = "wifi";
    public static final String NETWORK_TYPE_NAME_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_NAME_NONENETWORK = "nonenetwork";


    /**
     * 获取手机的IMEI号码(适配6.0权限,API23及以上需要请求权限的操作)
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
     * 获取手机第一次运动时产生和存储的64bit的一个数
     *
     * @param context 上下文
     * @return
     */
    public static String getANDROID_ID(Context context) {
        return android.provider.Settings.Secure.getString(context.getContentResolver()
                , android.provider.Settings.Secure.ANDROID_ID);
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
                type = NETWORK_TYPE_NAME_NONENETWORK;
                break;
            case NETWORK_CLASS_WIFI:
                type = NETWORK_TYPE_NAME_WIFI;
                break;
            case NETWORK_CLASS_2_G:
                type = NETWORK_TYPE_NAME_2G;
                break;
            case NETWORK_CLASS_3_G:
                type = NETWORK_TYPE_NAME_3G;
                break;
            case NETWORK_CLASS_4_G:
                type = NETWORK_TYPE_NAME_4G;
                break;
            case NETWORK_CLASS_UNKNOWN:
                type = NETWORK_TYPE_NAME_UNKNOWN;
                break;
            default:
                type = NETWORK_TYPE_NAME_NONENETWORK;
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
    public static String getUdid(Context context) {
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


    /**
     * Android 6.0 判断权限是否被申请（6.0以下需在清单文件注册）
     *
     * @param context    上下文
     * @param permission 权限
     * @return true 表示有该权限；false表示没有该权限
     */
    public static boolean isGetParamPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 请求权限的操作
     *
     * @param context    Activity
     * @param permission 所请求的权限
     */
    public static void requestPermission(Context context, String permission) {
        if (!isGetParamPermission(context, permission)) {
            if (((Activity) context) instanceof Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((Activity) context).requestPermissions(new String[]{permission}, 1 << 4);
            }
        }
    }

    /**
     * 获取手机内存大小
     *
     * @param context 上下文
     * @return
     */
    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 获取当前可用内存大小
     *
     * @param context 上下文
     * @return
     */
    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     * 获取CPU名字
     *
     * @return CPU名字
     */
    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取CPU最大频率（单位KHZ）
     */
    public static String getMaxCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim() + "KHz";
    }

    /**
     * 获取CPU最小频率（单位KHZ）
     */
    public static String getMinCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim() + "KHz";
    }

    /**
     * 实时获取CPU当前频率（单位KHZ）
     */
    public static String getCurCpuFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader(
                    "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim() + "KHz";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取CPU核心数
     *
     * @return
     */
    public static int getCPUNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Print exception
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }


    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getXX(Context context) {
        StringBuilder phoneInfo = new StringBuilder();
        phoneInfo.append("Product: " + android.os.Build.PRODUCT + System.getProperty("line.separator"));
        phoneInfo.append("CPU_ABI: " + android.os.Build.CPU_ABI + System.getProperty("line.separator"));
        phoneInfo.append("TAGS: " + android.os.Build.TAGS + System.getProperty("line.separator"));
        phoneInfo.append("VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + System.getProperty("line.separator"));
        phoneInfo.append("MODEL: " + android.os.Build.MODEL + System.getProperty("line.separator"));
        phoneInfo.append("SDK: " + android.os.Build.VERSION.SDK + System.getProperty("line.separator"));
        phoneInfo.append("VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + System.getProperty("line.separator"));
        phoneInfo.append("DEVICE: " + android.os.Build.DEVICE + System.getProperty("line.separator"));
        phoneInfo.append("DISPLAY: " + android.os.Build.DISPLAY + System.getProperty("line.separator"));
        phoneInfo.append("BRAND: " + android.os.Build.BRAND + System.getProperty("line.separator"));
        phoneInfo.append("BOARD: " + android.os.Build.BOARD + System.getProperty("line.separator"));
        phoneInfo.append("FINGERPRINT: " + android.os.Build.FINGERPRINT + System.getProperty("line.separator"));
        phoneInfo.append("ID: " + android.os.Build.ID + System.getProperty("line.separator"));
        phoneInfo.append("MANUFACTURER: " + android.os.Build.MANUFACTURER + System.getProperty("line.separator"));
        phoneInfo.append("USER: " + android.os.Build.USER + System.getProperty("line.separator"));
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        phoneInfo.append("DeviceId(IMEI) = " + tm.getDeviceId() + System.getProperty("line.separator"));
//        phoneInfo.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + System.getProperty("line.separator"));
//        phoneInfo.append("Line1Number = " + tm.getLine1Number() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkCountryIso = " + tm.getNetworkCountryIso() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperator = " + tm.getNetworkOperator() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperatorName = " + tm.getNetworkOperatorName() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkType = " + tm.getNetworkType() + System.getProperty("line.separator"));
        phoneInfo.append("PhoneType = " + tm.getPhoneType() + System.getProperty("line.separator"));
        phoneInfo.append("SimCountryIso = " + tm.getSimCountryIso() + System.getProperty("line.separator"));
        phoneInfo.append("SimOperator = " + tm.getSimOperator() + System.getProperty("line.separator"));
        phoneInfo.append("SimOperatorName = " + tm.getSimOperatorName() + System.getProperty("line.separator"));
//        phoneInfo.append("SimSerialNumber = " + tm.getSimSerialNumber() + System.getProperty("line.separator"));
        phoneInfo.append("SimState = " + tm.getSimState() + System.getProperty("line.separator"));
//        phoneInfo.append("SubscriberId(IMSI) = " + tm.getSubscriberId() + System.getProperty("line.separator"));
//        phoneInfo.append("VoiceMailNumber = " + tm.getVoiceMailNumber() + System.getProperty("line.separator"));

        return phoneInfo.toString();
    }

    /**
     * 获取   android.os.Build.下面的android设备信息
     *
     * @return 以Map键值对形式返回的Build下面的信息<br />
     */
    public static JSONObject getBuildInfo() {
        Map<String, String> buildMap = new LinkedHashMap<>();
        buildMap.put("ID", Build.ID);
        buildMap.put("DISPLAY", Build.DISPLAY);
        buildMap.put("PRODUCT", Build.PRODUCT);
        buildMap.put("DEVICE", Build.DEVICE);

        buildMap.put("BOARD", Build.BOARD);
        buildMap.put("CPU_ABI", Build.CPU_ABI);
        buildMap.put("CPU_ABI2", Build.CPU_ABI2);
        buildMap.put("MANUFACTURER", Build.MANUFACTURER);
        buildMap.put("BRAND", Build.BRAND);
        buildMap.put("MODEL", Build.MODEL);
        buildMap.put("BOOTLOADER", Build.BOOTLOADER);
        buildMap.put("RADIO", Build.RADIO);
        buildMap.put("HARDWARE", Build.HARDWARE);
        buildMap.put("SERIAL", SERIAL);
        buildMap.put("VERSION_INCREMENTAL", Build.VERSION.INCREMENTAL);
        buildMap.put("VERSION_RELEASE", Build.VERSION.RELEASE);
        buildMap.put("VERSION_BASE_OS", Build.VERSION.BASE_OS);
        buildMap.put("VERSION_SECURITY_PATCH", Build.VERSION.SECURITY_PATCH);
        buildMap.put("VERSION_SDK", Build.VERSION.SDK);
        buildMap.put("VERSION_SDK_INT", String.valueOf(Build.VERSION.SDK_INT));
        buildMap.put("VERSION_PREVIEW_SDK_INT", String.valueOf(Build.VERSION.PREVIEW_SDK_INT));
        buildMap.put("VERSION_CODENAME", Build.VERSION.CODENAME);

        JSONObject jsonObject = JavaCTransUtil.map2JsonObject(buildMap);
        return jsonObject;
    }

    /**
     * 手机硬件序列号
     *
     * @return 序列号
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    /**
     * 获取CPU序列号
     *
     * @return CPU序列号(16位)
     * <p>
     * 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (IOException ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * 获取应用的UID
     *
     * @param context 上下文
     * @return UID
     */
    public static int getUID(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        List<ActivityManager.RunningAppProcessInfo> run = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningProcess : run) {
            if ((runningProcess.processName != null) && runningProcess.processName.equals(applicationInfo.processName)) {
                return runningProcess.uid;
            }
        }
        return 0;
    }

    /**
     * 获取应用的PID数组
     *
     * @param context 上下文
     * @return List<String>
     */
    public static List<String> getPID(Context context) {
        List<String> list = new ArrayList<>();
        String processName = context.getPackageName();
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            list.add(appProcess.processName);
            if (appProcess.pid == pid && appProcess.processName.equals(processName)) {

            }
        }
        return list;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return 单位（px）
     */
    public int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return 单位（px）
     */
    public int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return 单位（px）
     */
    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public float getScaleDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
