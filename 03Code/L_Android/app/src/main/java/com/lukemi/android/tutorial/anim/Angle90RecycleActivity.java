package com.lukemi.android.tutorial.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Angle90RecycleActivity extends AppCompatActivity {

    private final String TAG = Angle90RecycleActivity.class.getSimpleName();

    private ImageView iv90;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle90_recycle);
        iv90 = findViewById(R.id.iv90);
        initInterval();
    }

    private void initInterval() {
        disposable = Observable.interval(1250, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    KLog.d(TAG, aLong);

                    int l = (int) (aLong % 4);
                    int angle = l * 90;
                    iv90.setRotation(angle);
//                    start(angle);
                });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void start(int fromDegree) {
        RotateAnimation animation = new RotateAnimation(fromDegree, fromDegree + 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(250);
        animation.setInterpolator(new AccelerateInterpolator());
        iv90.clearAnimation();
        iv90.setAnimation(animation);
    }
}
