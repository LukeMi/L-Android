package com.lukemi.android.tutorial.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Angle90RecycleActivity extends AppCompatActivity {

    @BindView(R.id.iv90)
    ImageView iv90;

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle90_recycle);
        ButterKnife.bind(this);
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    long l = aLong % 4;
                    long angle = l * 90;
                    iv90.setRotation(angle);
                });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
