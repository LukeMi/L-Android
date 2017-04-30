package com.lukemi.myandroid.baidu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.lukemi.myandroid.util.BDLocationUtil;


public class BDLocActivity extends AppCompatActivity implements View.OnClickListener, BDLocationUtil.LocationCallBack {

    private TextView content;
    private ProgressDialog pDialog = null;
    private BDLocationUtil bdLocationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_baidu_loc);
        initViews();
    }

    @Override
    protected void onStop() {
        bdLocationUtil.stop();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "常见问题说明").setIcon(android.R.drawable.btn_minus);
        menu.add(0, 1, 0, "菜单一").setIcon(android.R.drawable.btn_minus);
        menu.add(0, 2, 0, "菜单二").setIcon(android.R.drawable.btn_minus);
        menu.add(0, 3, 0, "菜单三").setIcon(android.R.drawable.btn_minus);
//        return super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Class<?> targetClass = null;
        switch (item.getItemId()) {
            case 0:
                targetClass = BDLocQuestionActivity.class;
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(BDLocActivity.this, targetClass));
        }
        return true;
    }

    /**
     * 初始化控件
     */
    private void initViews() {

        content = ((TextView) findViewById(com.lukemi.myandroid.R.id.content));
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        findViewById(com.lukemi.myandroid.R.id.title).setBackgroundColor(getResources().getColor(com.lukemi.myandroid.R.color.transparent));
        findViewById(com.lukemi.myandroid.R.id.startLoc).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.stopLoc).setOnClickListener(this);

        bdLocationUtil = new BDLocationUtil(this, this);
    }

    public void showPDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("定位中，请稍候");
        pDialog.setInverseBackgroundForced(false);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.startLoc:
                showPDialog();
                bdLocationUtil.start();
                break;
            case com.lukemi.myandroid.R.id.stopLoc:
                bdLocationUtil.stop();
                break;
        }
    }


    @Override
    public void getBDLocation(BDLocation location) {
        final StringBuffer sb = new StringBuffer(256);
        int locType = location.getLocType();
        String hintInfo = null;

        if (locType == 61) {
            hintInfo = "GPS定位结果，GPS定位成功。";

        } else if (locType == 62) {
            hintInfo = "无法获取有效定位依据，定位失败，请检查运营商网络或者WiFi网络是否正常开启，尝试重新请求定位。";

        } else if (locType == 63) {
            hintInfo = "网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。";

        } else if (locType == 65) {
            hintInfo = "定位缓存的结果。";

        } else if (locType == 66) {
            hintInfo = "离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。";

        } else if (locType == 67) {
            hintInfo = "离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。";

        } else if (locType == 68) {
            hintInfo = " 网络连接失败时，查找本地离线定位时对应的返回结果。";

        } else if (locType == 161) {
            hintInfo = "网络定位结果，网络定位成功。";

        } else if (locType == 162) {
            hintInfo = "请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件。";

        } else if (locType == 167) {
            hintInfo = "服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。";

        } else if (locType == 502) {
            hintInfo = "AK参数错误，请按照说明文档重新申请AK。";

        } else if (locType == 505) {
            hintInfo = "AK不存在或者非法，请按照说明文档重新申请AK。";

        } else if (locType == 601) {
            hintInfo = "AK服务被开发者自己禁用，请按照说明文档重新申请AK。";

        } else if (locType == 602) {
            hintInfo = "key mcode不匹配，您的AK配置过程中安全码设置有问题，请确保：SHA1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请AK。";

        } else if (locType > 501 && locType < 700) {
            hintInfo = "AK验证失败，请按照说明文档重新申请AK。";

        }
        /**
         * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
         * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
         */
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nhintInfo : ");
        sb.append(hintInfo);
        sb.append("\nlocType : ");// 定位类型
        sb.append(location.getLocType());
        sb.append("\nlocType description : ");// *****对应的定位类型说明*****
        sb.append(location.getLocTypeDescription());
        sb.append("\nlatitude : ");// 纬度
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");// 经度
        sb.append(location.getLongitude());
        sb.append("\nradius : ");// 半径
        sb.append(location.getRadius());
        sb.append("\nCountryCode : ");// 国家码
        sb.append(location.getCountryCode());
        sb.append("\nCountry : ");// 国家名称
        sb.append(location.getCountry());
        sb.append("\ncitycode : ");// 城市编码
        sb.append(location.getCityCode());
        sb.append("\ncity : ");// 城市
        sb.append(location.getCity());
        sb.append("\nDistrict : ");// 区
        sb.append(location.getDistrict());
        sb.append("\nStreet : ");// 街道
        sb.append(location.getStreet());
        sb.append("\naddr : ");// 地址信息
        sb.append(location.getAddrStr());
        sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
        sb.append(location.getUserIndoorState());
        sb.append("\nDirection(not all devices have value): ");
        sb.append(location.getDirection());// 方向
        sb.append("\nlocationdescribe: ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        sb.append("\nPoi: ");// POI信息
        if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
            for (int i = 0; i < location.getPoiList().size(); i++) {
                Poi poi = (Poi) location.getPoiList().get(i);
                sb.append(poi.getName() + ";");
            }
        }
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 速度 单位：km/h
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());// 卫星数目
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 海拔高度 单位：米
            sb.append("\ngps status : ");
            sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            // 运营商信息
            if (location.hasAltitude()) {// *****如果有海拔高度*****
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
            }
            sb.append("\noperationers : ");// 运营商信息
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                content.setGravity(Gravity.START);
                content.setText(sb.toString());
                pDialog.dismiss();
            }
        });
    }
}

