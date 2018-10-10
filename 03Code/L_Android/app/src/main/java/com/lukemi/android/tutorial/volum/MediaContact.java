package com.lukemi.android.tutorial.volum;

import android.media.MediaPlayer;

public class MediaContact {

    public interface ControlPresenter extends MediaPlayer.OnErrorListener
            , MediaPlayer.OnPreparedListener
            , MediaPlayer.OnInfoListener
            , MediaPlayer.OnCompletionListener {

    }

    public interface ViewControl {
        MediaPlayer callMediaPlayer();
        void onPlayError(MediaPlayer mediaPlayer);

        void onPlayPrepare(MediaPlayer mediaPlayer);

        void onPlayComplete(MediaPlayer mediaPlayer);

        void onPlayInfo(MediaPlayer mediaPlayer);

    }
}
