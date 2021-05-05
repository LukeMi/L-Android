package com.lukemi.android.tutorial.api.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static android.location.LocationManager.GPS_PROVIDER;

public class GpsActivity extends AbsBaseActivity {

    private String TAG = GpsActivity.class.getSimpleName();

    private static final int LOCATION_PER_REQUEST_CODE = 0x000001;

    @BindView(R.id.tv_loc_add)
    TextView tvLocAdd;
    private LocationManager locationManager;

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                double altitude = location.getAltitude();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                String showText = "longitude : " + longitude
                        + "\n" + "latitude : " + latitude
                        + "\n" + "altitude : " + altitude;
                tvLocAdd.setText(showText);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.i(TAG, "onStatusChanged : " + s);
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.i(TAG, "onProviderEnabled : " + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.i(TAG, "onProviderDisabled : " + s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PER_REQUEST_CODE) {
            boolean pe = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    pe = false;
                    break;
                }
            }
            if (pe) {
                requestLocation();
            }
        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_gps;
    }

    @Override
    protected void onDestroy() {
        if (mLocationListener != null) {
            mLocationListener = null;
        }
        super.onDestroy();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        locationManager = ((LocationManager) getSystemService(Context.LOCATION_SERVICE));
    }

    @OnClick(R.id.btn_loc)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_loc:
                loc();
                break;
        }
    }

    private void loc() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
                        , LOCATION_PER_REQUEST_CODE);
            }
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        locationManager.addGpsStatusListener(new GpsStatus.Listener() {
//            @Override
//            public void onGpsStatusChanged(int i) {
//
//            }
//        });


        locationManager.requestLocationUpdates(GPS_PROVIDER, 100000L, 100f, mLocationListener);


    }
}
