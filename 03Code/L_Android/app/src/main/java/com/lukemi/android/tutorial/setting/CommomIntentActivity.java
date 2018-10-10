package com.lukemi.android.tutorial.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

import java.io.File;

public class CommomIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_intent);
        initView();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/4/9 20:53
     */
    private void initView() {
        findViewById(R.id.ACTION_CALL).setOnClickListener(this);
        findViewById(R.id.ACTION_DIAL).setOnClickListener(this);
        findViewById(R.id.intallAPK).setOnClickListener(this);
        findViewById(R.id.ACTION_DELETE).setOnClickListener(this);
        findViewById(R.id.APPLICATION_DETAILS_SETTINGS).setOnClickListener(this);
        findViewById(R.id.webSuffering).setOnClickListener(this);
        findViewById(R.id.toDesktop).setOnClickListener(this);
        findViewById(R.id.dialRecord).setOnClickListener(this);
        findViewById(R.id.contact).setOnClickListener(this);
        findViewById(R.id.selectCcontact).setOnClickListener(this);
        findViewById(R.id.sms).setOnClickListener(this);
        findViewById(R.id.sendColorSMS).setOnClickListener(this);
        findViewById(R.id.sendEmail).setOnClickListener(this);
        findViewById(R.id.playMedia).setOnClickListener(this);
        findViewById(R.id.openCamera).setOnClickListener(this);
        findViewById(R.id.openGallery).setOnClickListener(this);
        findViewById(R.id.RECORD_SOUND_ACTION).setOnClickListener(this);
    }

    /**
     * ACTION_CALL  --> add Permission{@link android.Manifest.permission#SET_WALLPAPER}
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ACTION_CALL:
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + 10086));
                break;
            case R.id.ACTION_DIAL:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + 10086));
                break;
            case R.id.intallAPK:
                intent.setAction(Intent.ACTION_VIEW);
                String filePath = "/android_asset/bizhi.apk";
                intent.setDataAndType(Uri.parse("file://" + filePath),
                        "application/vnd.android.package-archive");
            case R.id.ACTION_DELETE:
                //卸载某应用
                String packageName = "com.tbug.android.mlibrary";
                Uri packageUri = Uri.parse("package:" + packageName);//包名，指定该应用
                intent.setAction(Intent.ACTION_DELETE);
                intent.setData(packageUri);
            case R.id.APPLICATION_DETAILS_SETTINGS:
                //查看某一应用程序的信息
                String pn = "com.tbug.android.mlibrary";
                Uri pkurl = Uri.parse("package:" + pn);//包名，指定该应用
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(pkurl);
                break;
            case R.id.webSuffering:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                //启动系统自带的浏览器打开上面的网址
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                break;
            case R.id.toDesktop:
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                break;
            case R.id.dialRecord:
                intent.setAction(Intent.ACTION_CALL_BUTTON);
                break;
            case R.id.contact:
                //调用联系人界面：
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Contacts.People.CONTENT_URI);
//            这个有问题    intent.setData(ContactsContract.AUTHORITY_URI);
                break;
            case R.id.selectCcontact:
                //选择联系人
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(Contacts.People.CONTENT_URI);
                break;
            case R.id.sms:
                intent.setAction(Intent.ACTION_VIEW);
                intent.putExtra("sms_body", "The SMS text");
//                intent.setType("vnd.android-dir/mms-sms");
                intent.setData(Uri.parse("smsto:" + "10086"));//此为号码
                break;
            case R.id.sendColorSMS:
                Uri uri = Uri.parse("content://media/external/images/media/23");
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("sms_body", "some text");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                /*startActivity(it);
                StringBuilder sb = new StringBuilder();
                sb.append("file://");
                sb.append(fd.getAbsoluteFile());
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mmsto", number, null));
// Below extra datas are all optional.
                intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_SUBJECT, subject);
                intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_BODY, body);
                intent.putExtra(Messaging.KEY_ACTION_SENDTO_CONTENT_URI, sb.toString());
                intent.putExtra(Messaging.KEY_ACTION_SENDTO_COMPOSE_MODE, composeMode);
                intent.putExtra(Messaging.KEY_ACTION_SENDTO_EXIT_ON_SENT, exitOnSent);
                startActivity(intent);*/
                break;
            case R.id.sendEmail:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:xxx@abc.com"));
//                intent.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
//                intent.putExtra(Intent.EXTRA_TEXT, "The email body text");
//                String[] ccs={"you@abc.com"};
//                intent.putExtra(Intent.EXTRA_CC, ccs);
//                intent.setType("text/plain");
//                intent.setType("message/rfc822");
//                startActivity( Intent.createChooser(intent, "Choose Email Client"));
                //格式2
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
                it.putExtra(Intent.EXTRA_TEXT, "The email body text");
                it.setType("text/plain");
                startActivity(Intent.createChooser(it, "Choose Email Client"));
                //格式3
                Intent it2 = new Intent(Intent.ACTION_SEND);
                String[] tos = {"me@abc.com"};
                String[] ccs = {"you@abc.com"};
                it2.putExtra(Intent.EXTRA_EMAIL, tos);
                it2.putExtra(Intent.EXTRA_CC, ccs);
                it2.putExtra(Intent.EXTRA_TEXT, "The email body text");
                it2.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
                it2.setType("message/rfc822");
                startActivity(Intent.createChooser(it2, "Choose Email Client"));
                //格式4
                Intent it4 = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
                it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
                it.setType("audio/mp3");
                startActivity(Intent.createChooser(it, "Choose Email Client"));
                break;
            case R.id.playMedia:
                Intent p1 = new Intent(Intent.ACTION_VIEW);
                p1.setDataAndType(Uri.parse("file:///sdcard/song.mp3"), "audio/mp3");
                startActivity(p1);

                Intent p2 = new Intent(Intent.ACTION_VIEW, Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1"));
                startActivity(p2);
                break;
            case R.id.openCamera:
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent,2);
                break;
            case R.id.openGallery:
                Toast.makeText(this, "http://blog.csdn.net/lilu_leo/article/details/6938729", Toast.LENGTH_SHORT).show();
                break;
            case R.id.RECORD_SOUND_ACTION:
                intent.setAction(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                break;
            case 0:
                break;
            default:
                break;
        }
        if (!TextUtils.isEmpty(intent.getAction())) {
            startActivity(intent);
        }
    }


}
