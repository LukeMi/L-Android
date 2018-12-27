package com.lukemi.android.tutorial.volum;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VolumeActivity extends AbsBaseActivity implements MediaContact.ViewControl {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.pb_volume)
    ProgressBar pbVolume;
    @BindView(R.id.pb_process)
    ProgressBar pb;

    private AudioManager mAudioManager;
    private Uri musicUri;
    private MediaPlayer mediaPlayer;
    private int streamMaxVolume;
    private int streamVolume;

    private int streamType = AudioManager.STREAM_MUSIC;
    private String path;
    private MediaPresenter mediaPresenter;
    private boolean isStoped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_volume;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAudioManager = ((AudioManager) getSystemService(AUDIO_SERVICE));
        streamMaxVolume = mAudioManager.getStreamMaxVolume(streamType);
        streamVolume = mAudioManager.getStreamVolume(streamType);
//        path = "/storage/emulated/0/Music/xhqg.mp3";
        path = "file:///android_asset/xhqg.mp3";
        musicUri = Uri.parse(path);
        mediaPlayer = MediaPlayer.create(this, R.raw.xhqg);
    }


    @Override
    protected void initView() {
        pbVolume.setMax(streamMaxVolume);
        pbVolume.setProgress(streamVolume);
        mediaPresenter = new MediaPresenter();
        mediaPresenter.bindPlayer(this);
    }

    @OnClick({R.id.btn_play, R.id.btn_pause, R.id.btn_stop, R.id.btn_volume_up, R.id.btn_volume_down, R.id.btn_volume_calm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                start();
                break;
            case R.id.btn_pause:
                pause();
                break;
            case R.id.btn_stop:
                stop();
                break;
            case R.id.btn_volume_up:
                volumeUp();
                break;
            case R.id.btn_volume_down:
                volumeDown();
                break;
            case R.id.btn_volume_calm:
                calm();
                break;
        }
    }

    private static final int WHAT_MSG_PLAY = 0x0000001;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case WHAT_MSG_PLAY:
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition();
                        int duration = mediaPlayer.getDuration();
                        pb.setMax(duration);
                        pb.setProgress(currentPosition);
                    }
                    handler.sendEmptyMessageDelayed(WHAT_MSG_PLAY, 1000);
                    break;
            }
        }
    };

    private void start() {
        try {
            if (mediaPlayer != null) {
                if (isStoped) {
                    mediaPlayer.reset();
//                    mediaPlayer = MediaPlayer.create(this, R.raw.xhqg);
                    mediaPlayer = MediaPlayer.create(this, musicUri);
                }
                isStoped = false;
                mediaPresenter.setMediaPlay(mediaPlayer);
                startMusicService();
                mediaPlayer.start();
                handler.sendEmptyMessage(WHAT_MSG_PLAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startMusicService() {
        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(Constants.Action.ACTION_START_FOREGROUND);
        intent.putExtra("MusicBean", new MusicBean("西海情歌", "西海在哪里"));
        startService(intent);
    }

    private void pause() {
        isStoped = false;

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {

        if (handler.hasMessages(WHAT_MSG_PLAY)) {
            handler.removeMessages(WHAT_MSG_PLAY);
        }

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void stop() {
        isStoped = true;

        pb.setProgress(0);
        if (handler.hasMessages(WHAT_MSG_PLAY)) {
            handler.removeMessages(WHAT_MSG_PLAY);
        }

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

    }

    private void volumeUp() {
        mAudioManager.adjustStreamVolume(streamType,
                AudioManager.ADJUST_RAISE,//调低声音
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);//对应UI
        int curVolume = mAudioManager.getStreamVolume(streamType);
        pbVolume.setProgress(curVolume);
    }

    private void volumeDown() {
        mAudioManager.adjustStreamVolume(streamType,
                AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);//调低声音
        int curVolume = mAudioManager.getStreamVolume(streamType);
        pbVolume.setProgress(curVolume);
    }

    boolean calm = false;

    private void calm() {
        calm = !calm;
        mAudioManager.adjustStreamVolume(streamType, calm ? AudioManager.ADJUST_MUTE : AudioManager.ADJUST_TOGGLE_MUTE, AudioManager.FLAG_VIBRATE);
    }

    @Override
    public MediaPlayer callMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void onPlayError(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "onPlayError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayPrepare(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "onPlayPrepare", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayComplete(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "onPlayComplete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayInfo(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "onPlayInfo", Toast.LENGTH_SHORT).show();
    }
}
