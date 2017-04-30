package com.lukemi.mlibrary.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.DisplayMetrics;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 获取apk 签名信息
 * Created by mzchen on 2016/12/9.
 */
public class APKSignUtil {

    /**
     * 获取外部apk的签名信息
     *
     * @param apkPath apk 路径
     * @return 签名信息
     */
    public static String showUninstallAPKSignatures(String apkPath) {
        String PATH_PackageParser = "android.content.pm.PackageParser";
        try {
            // apk包的文件路径
            // 这是一个Package 解释器, 是隐藏的
            // 构造函数的参数只有一个, apk文件的路径
            // PackageParser packageParser = new PackageParser(apkPath);
            Class pkgParserCls = Class.forName(PATH_PackageParser);
            Class[] typeArgs = new Class[1];
            typeArgs[0] = String.class;
            Constructor pkgParserCt = pkgParserCls.getConstructor(typeArgs);
            Object[] valueArgs = new Object[1];
            valueArgs[0] = apkPath;
            Object pkgParser = pkgParserCt.newInstance(valueArgs);
//            MediaApplication.logD(DownloadApk.class, "pkgParser:" + pkgParser.toString());
            Logcat.log("----showUninstallAPKSignatures----> " + pkgParser.toString());
            // 这个是与显示有关的, 里面涉及到一些像素显示等等, 我们使用默认的情况
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();
            // PackageParser.Package mPkgInfo = packageParser.parsePackage(new
            // File(apkPath), apkPath,
            // metrics, 0);
            typeArgs = new Class[4];
            typeArgs[0] = File.class;
            typeArgs[1] = String.class;
            typeArgs[2] = DisplayMetrics.class;
            typeArgs[3] = Integer.TYPE;
            Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage",
                    typeArgs);
            valueArgs = new Object[4];
            valueArgs[0] = new File(apkPath);
            valueArgs[1] = apkPath;
            valueArgs[2] = metrics;
            valueArgs[3] = PackageManager.GET_SIGNATURES;
            Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser, valueArgs);

            typeArgs = new Class[2];
            typeArgs[0] = pkgParserPkg.getClass();
            typeArgs[1] = Integer.TYPE;
            Method pkgParser_collectCertificatesMtd = pkgParserCls.getDeclaredMethod("collectCertificates",
                    typeArgs);
            valueArgs = new Object[2];
            valueArgs[0] = pkgParserPkg;
            valueArgs[1] = PackageManager.GET_SIGNATURES;
            pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs);
            // 应用程序信息包, 这个公开的, 不过有些函数, 变量没公开
            Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField("mSignatures");
            Signature[] info = (Signature[]) packageInfoFld.get(pkgParserPkg);
            Logcat.log("----showUninstallAPKSignatures----> " + "size:" + info.length + " ; " + info[0].toCharsString());
//            MediaApplication.logD(DownloadApk.class, "size:"+info.length);
//            MediaApplication.logD(DownloadApk.class, info[0].toCharsString());
            return info[0].toCharsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取apk自身签名信息
     *
     * @param context 上下文
     * @return 签名信息
     */
    private String getSelfSign(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();
        while (iter.hasNext()) {
            PackageInfo packageinfo = iter.next();
            String packageName = packageinfo.packageName;
            if (packageName.equals(context.getPackageName())) {
                Logcat.log("----showUninstallAPKSignatures----> " + packageinfo.signatures[0].toCharsString());
//                MediaApplication.logD(DownloadApk.class, packageinfo.signatures[0].toCharsString());
                return packageinfo.signatures[0].toCharsString();
            }
        }
        return null;
    }

    /**
     * 获取指定已安装完整签名信息,包括MD5指纹
     *
     * @param context 上下文
     * @return 返回以Map键值对形式的数据<br/>（key包括 signName；pubKey；signNumber；subjectDN）
     */
    public static Map<String, String> getSingInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.sina,weibo", PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            return parseSignature(sign.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, String> parseSignature(byte[] signature) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));
            String pubKey = cert.getPublicKey().toString();
            String signNumber = cert.getSerialNumber().toString();
            System.out.println("signName:" + cert.getSigAlgName());
            System.out.println("pubKey:" + pubKey);
            System.out.println("signNumber:" + signNumber);
            System.out.println("subjectDN:" + cert.getSubjectDN().toString());
            Map<String, String> signMap = new HashMap<>();
            signMap.put("signName", cert.getSigAlgName());
            signMap.put("pubKey", pubKey);
            signMap.put("signNumber", signNumber);
            signMap.put("subjectDN", cert.getSubjectDN().toString());
            return signMap;
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        }
    }

}
