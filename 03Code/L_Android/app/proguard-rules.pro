# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Android\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-dontoptimize

#mob短信验证开始
-keep class cn.smssdk.**{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.smssdk.**
#mob短信验证开结束

#Glide加载图片开始
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only签名打包出异常
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#Glide加载图片结束

#GreenDao数据库开始
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**
#GreenDao数据库结束


#BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
   *;
}

-keep com.jcodecraeer.xrecyclerview.XRecyclerView.**{
}

