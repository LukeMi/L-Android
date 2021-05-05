package com.lukemi.android.tutorial.set;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IconSetActivity extends AppCompatActivity {

    @BindView(R.id.btn_change)
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_set);
        ButterKnife.bind(this);
//        Log.i("TAG"," "+isTwin("Hello", "world")); // false
//        Log.i("TAG"," "+isTwin("acb", "bca")); // true
//        Log.i("TAG"," "+isTwin("Lookout", "Outlook")); // true


        int[] i0 = new int[3];
        i0[0]= 0;
        i0[1]= -1;
        for (int i =0;i<i0.length;i++){
            Log.i("TAG","i0["+i+"] = "+i0[i]);
        }

        int[] i1 = new int[]{-1, -1};

        Log.i("TAG", "result : " + closeZero(i1));
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String name = "com.example.archermind.myapplication.set.NewLaunchActivity";
            changeLaunchIcon(name);
        }
//        gotToLogin();
    }

    private void gotToLogin() {
        String cls = "com.example.archermind.myapplication.user.login.ui.LoginActivity";
        ComponentName componentName = new ComponentName(getPackageName(), cls);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void changeLaunchIcon(String name) {
        ComponentName componentName = new ComponentName(getPackageName(), name);
        PackageManager pm = getPackageManager();
        //删除原来应用图标
        pm.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        //新建要设置的图标
        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        //Intent 重启 Launcher 应用
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }

        int[] a = new int[]{1, 2, 34, 5};


    }

    private boolean isTwin(String a, String b) {
        boolean isTwin = true;
        if (a == null || b == null || a.length() != b.length()) {
            isTwin = false;
        } else if(a.equals(b)){
            isTwin = true;
        } else {
            char[] aArray = a.toCharArray();
            char[] bArray = b.toCharArray();
            for (int i = 0; i < a.length(); i++) {
                char ac = a.charAt(i);
                char bc = b.charAt(i);
                isTwin = Character.isLowerCase(ac) && Character.isLowerCase(bc) || Character.isUpperCase(ac) && Character.isUpperCase(bc);
                if (!isTwin) {
                    break;
                }

                int aaCount = 0;
                int abCount = 0;
                int baCount = 0;
                int bbCount = 0;

                for (int j = 0; j < aArray.length; j++) {
                    String achar = String.valueOf(aArray[j]);
                    String bchar = String.valueOf(bArray[j]);
                    if (String.valueOf(ac).equalsIgnoreCase(achar)) {
                        aaCount++;
                    }
                    if (String.valueOf(ac).equalsIgnoreCase(bchar)) {
                        abCount++;
                    }

                    if (String.valueOf(bc).equalsIgnoreCase(achar)) {
                        baCount++;
                    }
                    if (String.valueOf(bc).equalsIgnoreCase(bchar)) {
                        bbCount++;
                    }
                }
                isTwin = aaCount == abCount && baCount == bbCount;
                if (!isTwin) {
                    break;
                }
            }
        }
        return isTwin;
    }


    private int closeZero(int[] ints) {
        int result = 0;
        if (ints == null || ints.length == 0) {
            return 0;
        }
        int length = ints.length;
        int[] absDelt = new int[ints.length];
        for (int i = 0; i < length; i++) {
            absDelt[i] = Math.abs(ints[i]);
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int a = absDelt[j];
                int b = absDelt[j + 1];
                int temp = 0;
                if (a > b) {
                    temp = a;
                    absDelt[j] = b;
                    absDelt[j + 1] = temp;
                }
            }
        }

        int max = absDelt[0];

        int count = 0;

        for (int i = 0; i < length; i++) {
            if (absDelt[i] == max) {
                count++;
            }
        }

        if (count >= 1) {
            int[] suit = new int[count];
            for (int i = 0; i < length; i++) {
                if (Math.abs(ints[i]) == max) {
                    int index =  suit.length  ;
                    for(int j =0;j< suit.length;j++){
                        if (suit[j] == 0){
                            index = j;
                            break;
                        }
                    }
                    suit[index] = ints[i];

                }
            }

            for (int i = 0; i < suit.length; i++) {
                for (int j = 0; j < suit.length - i - 1; j++) {
                    int a = suit[j];
                    int b = suit[j + 1];
                    int temp = 0;
                    if (a > b) {
                        temp = a;
                        suit[j] = b;
                        suit[j + 1] = temp;
                    }
                }
            }
            result = suit[suit.length  - 1];
        }
        return result;
    }


    /**
     * Locates the universe-formula​​​​​​​‌​‌‌‌‌‌​‌‌​​​​‌‌‌‌‌​​​‌‌ file.
     */
    private String locateUniverseFormula() {
        String path = null ;
        File file = new File("/tmp/documents");
        if (file != null){
            if (file.isDirectory()){
                String[] list = file.list();
            }
            path = file.getAbsolutePath();
        }
        return  path;


    }
}
