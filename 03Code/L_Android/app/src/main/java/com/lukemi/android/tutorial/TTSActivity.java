package com.lukemi.android.tutorial;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.common.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author mzchen
 * @date 2019/6/18 11:47
 * @des 语音合成Demo
 * @mail chenmingzhiji@163.com
 */
public class TTSActivity extends AppCompatActivity {
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null && tts.isSpeaking()) {
            tts.stop();
        }
    }

    @OnClick({R.id.btn_init, R.id.btn_play, R.id.btn_stop, R.id.btn_destroy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_init:
                if (tts == null) {
                    tts = new TextToSpeech(getApplicationContext(), status -> Logcat.log("status : " + status), getLocalClassName());
                    tts.setSpeechRate(4f);
                }
                break;
            case R.id.btn_play:
                if (tts != null) {
                    tts.speak("接到实时单，请及时抢单，1S", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    ToastUtil.show_makeText(getApplication(), "u must have the TextToSpeech instance", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btn_stop:
                if (tts != null && tts.isSpeaking()) {
                    tts.stop();
                }
                break;
            case R.id.btn_destroy:
                if (tts != null && tts.isSpeaking()) {
                    tts.stop();
                }
                tts = null;
                break;
            default:
                break;
        }
    }
}
