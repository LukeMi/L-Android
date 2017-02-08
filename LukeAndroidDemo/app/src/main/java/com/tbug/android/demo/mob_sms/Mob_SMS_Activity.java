package com.tbug.android.demo.mob_sms;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mob.tools.utils.ResHelper;
import com.tbug.android.demo.R;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.mob.tools.utils.R.getStyleRes;

public class Mob_SMS_Activity extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private String APPKEY = "1b2b176de7070";
    private String APPSECRET = "cab84ab513afb6732f5fa19fcf8dfcb1";
    // 短信注册，随机产生头像
    private static final String[] AVATARS = {
            "http://tupian.qqjay.com/u/2011/0729/e755c434c91fed9f6f73152731788cb3.jpg",
            "http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
            "http://img1.touxiang.cn/uploads/allimg/111029/2330264224-36.png",
            "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
            "http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
            "http://img1.touxiang.cn/uploads/20121224/24-054837_708.jpg",
            "http://img1.touxiang.cn/uploads/20121212/12-060125_658.jpg",
            "http://img1.touxiang.cn/uploads/20130608/08-054059_703.jpg",
            "http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
            "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
            "http://img1.touxiang.cn/uploads/20130515/15-080722_514.jpg",
            "http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg"
    };

    private boolean gettingFriends = false;
    private boolean ready = false;

    private Dialog pd;
    private EditText defined_Code_ET;
    private EditText security_code_ET;
    private Button sendRequestBTN;
    private Button submit_secrityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob__sms);
        initSDK();
        initViews();
    }

    private void initViews() {
        defined_Code_ET = ((EditText) findViewById(R.id.defined_Code_ET));
        security_code_ET = ((EditText) findViewById(R.id.security_code_ET));
        sendRequestBTN = ((Button) findViewById(R.id.sendRequestBTN));
        submit_secrityCode = ((Button) findViewById(R.id.submit_secrityCode));

        sendRequestBTN.setOnClickListener(this);
        submit_secrityCode.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        if (ready) {
            // 销毁回调监听接口
            SMSSDK.unregisterAllEventHandler();
        }
        super.onDestroy();
    }

    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(this, APPKEY, APPSECRET, true);
        if (APPKEY.equalsIgnoreCase("f3fc6baa9ac4")) {
            Toast.makeText(this, "此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY", Toast.LENGTH_SHORT).show();
        }
        final Handler handler = new Handler(Mob_SMS_Activity.this);
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        ready = true;

        // 获取新好友个数
        showDialog();
        SMSSDK.getNewFriendsCount();
        gettingFriends = true;
    }

    // 弹出加载框
    private void showDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
        pd = CommonDialog.ProgressDialog(this);
        pd.show();
    }

    @Override
    public boolean handleMessage(Message msg) {

        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;
        if (event == SMSSDK.EVENT_SUBMIT_USER_INFO) {
            // 短信注册成功后，返回MainActivity,然后提示新好友
            if (result == SMSSDK.RESULT_COMPLETE) {
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
            } else {
                ((Throwable) data).printStackTrace();
            }
        } else if (event == SMSSDK.EVENT_GET_NEW_FRIENDS_COUNT) {
            if (result == SMSSDK.RESULT_COMPLETE) {
//                refreshViewCount(data);
                gettingFriends = false;
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sendRequestBTN:
                //获取验证码

                break;
            case R.id.submit_secrityCode:
                //提交验证信息
                registerUser("CN",  );
                break;
        }
    }

    // 提交用户信息
    private void registerUser(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = AVATARS[id % 12];
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }
}

class CommonDialog {
    /**
     * 加载对话框
     */
    public static final Dialog ProgressDialog(Context context) {
        int resId = getStyleRes(context, "CommonDialog");
        if (resId > 0) {
            final Dialog dialog = new Dialog(context, resId);
            LinearLayout layout = ProgressDialogLayout.create(context);
            if (layout != null) {
                dialog.setContentView(layout);
                return dialog;
            }
        }
        return null;
    }

}

/**
 * 进度dialog
 */
class ProgressDialogLayout {

    public static LinearLayout create(Context context) {
        LinearLayout root = new LinearLayout(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(params);
        root.setOrientation(LinearLayout.VERTICAL);

        ProgressBar bar = new ProgressBar(context);
        LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bar.setLayoutParams(barParams);
        SizeHelper.prepare(context);
        int padding = SizeHelper.fromPxWidth(20);
        bar.setPadding(padding, padding, padding, padding);

        root.addView(bar);

        return root;
    }
}

class SizeHelper {
    public static float designedDensity = 1.5f;
    public static int designedScreenWidth = 540;
    private static Context context = null;

    protected static SizeHelper helper;

    private SizeHelper() {
    }

    public static void prepare(Context c) {
        if (context == null || context != c.getApplicationContext()) {
            context = c;
        }
    }

    /**
     * 根据density转换设计的px到目标机器，返回px大小
     *
     * @return 像素大小
     */
    public static int fromPx(int px) {
        return ResHelper.designToDevice(context, designedDensity, px);
    }

    /**
     * 根据屏幕宽度转换设计的px到目标机器，返回px大小
     *
     * @return 像素大小
     */
    public static int fromPxWidth(int px) {
        return ResHelper.designToDevice(context, designedScreenWidth, px);
    }

    /**
     * 根据density转换设计的dp到目标机器，返回px大小
     *
     * @return 像素大小
     */
    public static int fromDp(int dp) {
        int px = ResHelper.dipToPx(context, dp);
        return ResHelper.designToDevice(context, designedDensity, px);
    }

}


