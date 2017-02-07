package com.tbug.android.demo.testutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tbug.android.demo.R;
import com.tbug.android.demo.util.Logcat;
import com.tbug.android.demo.util.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 验证ZipUtil
 */
public class ZipUtilActivity extends AppCompatActivity implements View.OnClickListener {

    private Button zipStr_ZIPBTN;
    private Button unZipStr_ZIPBTN;
    private EditText zipStrET_ZIPET;
    private String zipStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_util);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        zipStr_ZIPBTN = ((Button) findViewById(R.id.zipStr_ZIP));
        unZipStr_ZIPBTN = ((Button) findViewById(R.id.unZipStr_ZIP));
        zipStrET_ZIPET = ((EditText) findViewById(R.id.zipStrET_ZIP));

        zipStr_ZIPBTN.setOnClickListener(this);
        unZipStr_ZIPBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.zipStr_ZIP:
                String str = zipStrET_ZIPET.getText().toString();
                File f1 = new File("C:\\Users\\dev\\Desktop\\Akazam_Project\\Migu_SDK\\zipfile\\zipStr.text");
                FileOutputStream fis1 = null;
                try {
                    fis1 = new FileOutputStream(f1);
                    fis1.write(str.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis1 != null) {
                        try {
                            fis1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(this, "压缩字符串不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                FileOutputStream fis2 = null;
                try {
                    zipStr = ZipUtil.zipString(str);
                    File f2 = new File("C:\\Users\\dev\\Desktop\\Akazam_Project\\Migu_SDK\\zipfile\\zipStr.text");
                    fis2 = new FileOutputStream(f2);
                    fis2.write(zipStr.getBytes());
                    String zipStr1 = new String(zipStr.getBytes("ISO-8859-1"), "utf-8");
                    Logcat.log("----压缩字符串前后长度对比---- " + str.length() + " VS " + zipStr1.length() + "\n" +
                            str + " VS " + zipStr1);
                } catch (IOException e) {
                    Toast.makeText(this, "压缩失败", Toast.LENGTH_SHORT).show();
                } finally {
                    if (fis2 != null) {
                        try {
                            fis2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case R.id.unZipStr_ZIP:
                try {
                    String unzipStr = ZipUtil.unzipString(zipStr);
                    Toast.makeText(this, "解压缩成功：" + "解压前：" + zipStr + "；解压后：" + unzipStr, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "解压缩失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
