package com.lukemi.android.tutorial.util;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

/**
 * 百度定位的二次封装
 * Created by mzchen on 2017/2/23.
 */

public class BDLocationUtil {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public Context context;
    public LocationCallBack locationCallBack;

    /**
     * 创建对象和初始化
     *
     * @param context
     * @param locationCallBack
     */
    public BDLocationUtil(Context context, LocationCallBack locationCallBack) {
        this.context = context;
        this.locationCallBack = locationCallBack;
        initBaiduLocSDK();
    }

    /**
     * 初始化百度定位SDK
     */
    private void initBaiduLocSDK() {
        //声明LocationClient类
        mLocationClient = new LocationClient(context.getApplicationContext());
        initLocation();
    }

    public void start() {
        if (mLocationClient != null && myListener != null) {
            //注册监听函数
            mLocationClient.registerLocationListener(myListener);
            //开始定位
            mLocationClient.start();
        }
    }

    /**
     * 停止定位
     */
    public void stop() {
        if (mLocationClient != null && myListener != null) {
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient.stop();
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    /**
     * 实现接口
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                //获取定位结果
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

                if (locationCallBack != null) {
                    locationCallBack.getBDLocation(location);
                } else {
                    try {
                        throw new Exception("LocationCallBack must't be null");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    /**
     * 封装的接口回调
     */
    public interface LocationCallBack {
        /**
         * 耗时操作
         */
        void getBDLocation(BDLocation location);
    }

}
