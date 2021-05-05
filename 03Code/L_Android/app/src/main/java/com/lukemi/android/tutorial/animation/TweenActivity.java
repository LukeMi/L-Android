package com.lukemi.android.tutorial.animation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TweenActivity extends AppCompatActivity {

    private final int MSG_REPEAT = 1 << 1;
    @BindView(R.id.alpha_img_TweenAct)
    ImageView alphaImg;
    @BindView(R.id.alpha_TweenAct)
    Button alpha_TweenActBTN;
    @BindView(R.id.rotate_img_TweenAct)
    ImageView rotateImg;
    @BindView(R.id.scale_img_TweenAct)
    ImageView scaleImg;
    @BindView(R.id.translate_img_TweenAct)
    ImageView translateImg;
    @BindView(R.id.xml_alpha_img_TweenAct)
    ImageView xml_alphaImg;
    @BindView(R.id.xml_rotate_img_TweenAct)
    ImageView xml_rotateImg;
    @BindView(R.id.xml_scale_img_TweenAct)
    ImageView xml_scaleImg;
    @BindView(R.id.xml_translate_img_TweenAct)
    ImageView xml_translateImg;
    @BindView(R.id.iv_xml_t_s)
    ImageView iv_xml_t_s;
    @BindView(R.id.iv_xml_t_heart)
    ImageView iv_xml_t_heart;
    @BindView(R.id.xml_t_heart)
    View xml_t_heart;
    private AlphaAnimation alphaAnimation;
    private int repeatCount;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case MSG_REPEAT:
                    repeatCount++;
                    Logcat.log("------alpha_Animation------" + repeatCount + "次");
                    if (repeatCount == 3) {
                        alphaImg.clearAnimation();
                        repeatCount = 0;
                    }
                    break;
            }
        }
    };
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

    }

    @OnClick({R.id.alpha_TweenAct, R.id.rotate_TweenAct, R.id.scale_TweenAct,
            R.id.translate_TweenAct, R.id.xml_alpha_TweenAct, R.id.xml_rotate_TweenAct,
            R.id.xml_scale_TweenAct, R.id.xml_translate_TweenAct, R.id.scale_img_TweenAct,
            R.id.xml_t_s, R.id.iv_xml_t_heart})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.alpha_TweenAct:
                alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(3000);
                alphaAnimation.setRepeatCount(-1);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        alpha_TweenActBTN.setClickable(false);
                        Toast.makeText(TweenActivity.this, "渐变动画开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        alpha_TweenActBTN.setClickable(true);
                        Toast toast = Toast.makeText(TweenActivity.this, "渐变动画结束", Toast.LENGTH_SHORT);
                        toast.setView(getLayoutInflater().inflate(R.layout.view_toast, null));
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        mHandler.sendEmptyMessage(MSG_REPEAT);
                    }


                });
                alphaAnimation.setRepeatCount(-1);
                alphaImg.startAnimation(alphaAnimation);
                break;
            case R.id.rotate_TweenAct:
                RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(3000);
//                rotateAnimation.setInterpolator(new LinearInterpolator());//匀速度
//                rotateAnimation.setInterpolator(new DecelerateInterpolator());//减度效果
                rotateAnimation.setInterpolator(new AccelerateInterpolator());//加度效果
                rotateAnimation.setRepeatCount(2);
                rotateAnimation.setRepeatMode(Animation.REVERSE);
                rotateImg.startAnimation(rotateAnimation);
                break;
            case R.id.scale_TweenAct:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(3000);
                scaleAnimation.setFillAfter(true);
                scaleImg.startAnimation(scaleAnimation);
                break;
            case R.id.translate_TweenAct:
                Toast.makeText(this, "translate_TweenAct", Toast.LENGTH_SHORT).show();
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 1, 0, 1);
                translateAnimation.setDuration(3000);
                translateImg.setAnimation(translateAnimation);
                break;

            case R.id.xml_alpha_TweenAct:
                Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                xml_alphaImg.startAnimation(alphaAnimation);
                break;
            case R.id.xml_rotate_TweenAct:
                Animation ra = AnimationUtils.loadAnimation(this, R.anim.alpha);

                xml_rotateImg.startAnimation(ra);
                break;
            case R.id.xml_scale_TweenAct:
                xml_scaleImg.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
                break;
            case R.id.xml_translate_TweenAct:
                xml_translateImg.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
                break;
            case R.id.scale_img_TweenAct:
                Intent intent = new Intent();
                intent.setClass(this, ScaleActivity.class);
                startActivity(intent);
                break;
            case R.id.xml_t_s:
                iv_xml_t_s.startAnimation(AnimationUtils.loadAnimation(this, R.anim.set_a_s));
                break;
            case R.id.iv_xml_t_heart:
                heart();
                break;
            default:
                break;
        }
    }

    private void heart() {
        iv_xml_t_heart.setSelected(flag = !flag);
        heartAnimation();
    }

    private void heartAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setFillAfter(false);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (animation == scaleAnimation ){
                    Logcat.log("animation == scaleAnimation");
                }
                Logcat.log("animation onAnimationStart");
                xml_t_heart.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                xml_t_heart.setEnabled(true);
                Logcat.log("animation onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Logcat.log("animation onAnimationRepeat");
            }
        });
        iv_xml_t_heart.startAnimation(scaleAnimation);
    }
}
