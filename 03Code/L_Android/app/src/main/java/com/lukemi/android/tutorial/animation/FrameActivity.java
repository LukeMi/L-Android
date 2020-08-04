package com.lukemi.android.tutorial.animation;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.common.util.Logcat;

/**
 * 帧动画使用流程；
 * 此处以ImageView为例；
 * <p></>
 * 1.drawable创建跟检点为<animation-list>的xml文件<br/>
 * 2.xml子节点<item></>设置drawable属性，duration属性<br/>
 * 3.imageView设置src属性为上述的xml文件；
 * 4.动画控制，开启，
 * imageView.setImageResource(R.drawable.lottery_animlist);
 * AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
 * animationDrawable.start();<br/>
 * 5.动画控制，关闭
 * AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
 * animationDrawable.stop();<br/>
 * <p>
 * void start() - 开始播放动画
 * <p>
 * void stop() - 停止播放动画
 * <p>
 * addFrame(Drawable frame, int duration) - 添加一帧，并设置该帧显示的持续时间
 * <p>
 * void setOneShoe(boolean flag) - false为循环播放，true为仅播放一次
 * <p>
 * boolean isRunning() - 是否正在播放</p>
 */
public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView frameIV;
    private Button controlBTN;
    private Button addFrameBTN;
    private AnimationDrawable drawable;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frameIV = findViewById(R.id.frameIV);
        controlBTN = findViewById(R.id.control);
        controlBTN.setOnClickListener(this);
        addFrameBTN = findViewById(R.id.addFrame);
        addFrameBTN.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.control:
                String text = controlBTN.getText().toString();
                if ("开启动画".equals(text)) {
                    controlBTN.setText("关闭动画");
                    frameIV.setImageResource(R.drawable.frame_animation);
                    drawable = (AnimationDrawable) frameIV.getDrawable();
                    drawable.start();
                } else if ("关闭动画".equals(text)) {
                    controlBTN.setText("开启动画");
                    drawable.stop();
                }
                break;
            case R.id.addFrame:
                drawable = (AnimationDrawable) frameIV.getDrawable();
                if (++i > 2) {
                    i = 1;
                }
                int id = this.getResources().getIdentifier("pageload_icon" + i, "drawable", this.getPackageName());
                Drawable dw;
                dw = getResources().getDrawable(id);
                Logcat.log("----addFrame---->> " + "dw: " + dw + "");
                drawable.addFrame(dw, 200);
                break;
            default:
                break;
        }

    }
}
