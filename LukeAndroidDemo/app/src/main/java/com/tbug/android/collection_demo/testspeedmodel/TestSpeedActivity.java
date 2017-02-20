package com.tbug.android.collection_demo.testspeedmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbug.android.collection_demo.R;
import com.tbug.android.collection_demo.util.Logcat;
import com.senter.support.openapi.SpeedTestOpenApi;

import static com.tbug.android.collection_demo.testspeedmodel.AlertdialogUtil.context;
import static com.tbug.android.collection_demo.testspeedmodel.ProgressDialogUtil.stopProgressDialog;
import static com.senter.support.openapi.SpeedTestOpenApi.Report_Sort_Protocol_Match_Err;

public class TestSpeedActivity extends AppCompatActivity implements View.OnClickListener{

    private Button setParams_BTN;
    private Button reTest_BTN;
    private TextView result_TV;

    public SpeedTestOpenApi.NetSpeedTestConfigBean testParam = null;
    public SpeedTestOpenApi.SpeedTestResult mspeetResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_speed);
        initView();
        initTestAPI();
    }

    /**
     * 初始化测速API
     */
    private void initTestAPI() {

        //初始化测速管家，如果已存在则不生效
        try {
            SpeedTestOpenApi.InitSpeedManager();
        } catch (Exception e) {
            e.printStackTrace();
            Logcat.log("模块异常");
        }

        //        初始化测速模块
        try {
            SpeedTestOpenApi.initSpeedModule(mUICallback);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        setParams_BTN = ((Button) findViewById(R.id.setParams));
        reTest_BTN = ((Button) findViewById(R.id.reTest));
        result_TV = ((TextView) findViewById(R.id.result));

        setParams_BTN.setOnClickListener(this);
        reTest_BTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.setParams:

                break;
            case R.id.reTest:
                try {
                    testParam = SpeedTestOpenApi.NetSpeedTestConfigBean.getParamToNetST(0, 0, "sj", "111111", "60101", 0, "123456");
                    ProgressDialogUtil.createProgressDialog(TestSpeedActivity.this, "正在网络设置");
                    try {
                        SpeedTestOpenApi.Netconfigurate netConfigParam = SpeedTestOpenApi.getParamToDHCP();
                        SpeedTestOpenApi.setNetWork(netConfigParam);// 设置网络
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private SpeedTestOpenApi.UICallback mUICallback = new SpeedTestOpenApi.UICallback() {
        @Override
        public void moduleInitStateReport(int i) {

        }

        @Override//测速相关回调
        public void speedTestResultReport(int i, SpeedTestOpenApi.SpeedTestResult speedTestResult) {

        }

        @Override//测速模块属性报告回调
        public void speedModulePropertyReport(SpeedTestOpenApi.SpeedModuleProperty speedModuleProperty) {
            Logcat.log("----初始化成功----");
        }

        @Override//是否可以掉电回调
        public void powerOffReport() {

        }

        @Override//底层相关状态的报告回调
        public void ReportTest(int i, String s) {
            switch (i) {
                case SpeedTestOpenApi.Report_JSONErr:

                    runOnUiThread(new Runnable() {
                        public void run() {
                            stopProgressDialog();
                            Logcat.log("----Report_JSONErr----");

                        }
                    });
                    break;
                case SpeedTestOpenApi.Report_NetSet_Result_Success://网络设置成功，成功后下发测试指令
                    stopProgressDialog();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // 发送命令
                            sendSpeedTestOrder();
                            Logcat.log("----Report_NetSet_Result_Success----");
                        }
                    });
                    break;
                case SpeedTestOpenApi.Report_NetSet_Result_Fail://网络设置设置失败，弹出提示框，告知用户

                    runOnUiThread(new Runnable() {
                        public void run() {
                            stopProgressDialog();
                            Logcat.log("----Report_NetSet_Result_Fail----");
                        }
                    });
                    break;
                case SpeedTestOpenApi.Report_Sort_ModulErr://超时未能返回
                    runOnUiThread(new Runnable() {
                        public void run() {
                            stopProgressDialog();
                            Logcat.log("----Report_Sort_ModulErr----");
                        }
                    });

                    break;
                case Report_Sort_Protocol_Match_Err://这里是因为给多个地区订制测速程序 ，防止底层程序拷贝出错，做的检查与提示
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Logcat.log("----Report_Sort_Protocol_Match_Err----");

                        }
                    });
                    break;

                case SpeedTestOpenApi.SoftWare_Must_UPGRADE://这里是检测到底层程序版本号比SDK使用通讯协议版本号高，SDK无法兼容底层程序做的提示
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Logcat.log("----SoftWare_Must_UPGRADE----");

                        }
                    });
                    break;
            }
        }
    };

    /**
     * 发送测速指令
     */
    public void sendSpeedTestOrder() {
/*        speedtesttime = 0;

        mViews.resetUi();//界面其它控件初始化*/

        try {
            SpeedTestOpenApi.startSpeedTest(testParam);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.key_param_err_order_send_false, Toast.LENGTH_LONG).show();
            return;
        }
//        shadeControlFLAG = 3;
        //TODO
//        createProgressDialog(context, "正在测速...", myProgressDialogBackBtLisenter);
    }
}
