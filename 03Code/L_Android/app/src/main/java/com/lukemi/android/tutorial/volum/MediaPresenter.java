package com.lukemi.android.tutorial.volum;

import android.media.MediaPlayer;

public class MediaPresenter implements MediaContact.ControlPresenter {
    private MediaContact.ViewControl viewControl;
    private MediaPlayer mediaPlayer;

    public void bindPlayer(MediaContact.ViewControl viewControl) {
        this.viewControl = viewControl;
        MediaPlayer mediaPlayer = viewControl.callMediaPlayer();
        this.mediaPlayer = mediaPlayer;
        setPlayerListener(mediaPlayer);
    }

    public void setMediaPlay(MediaPlayer mediaPlayer) {
        setPlayerListener(mediaPlayer);
    }

    private void setPlayerListener(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnErrorListener(this);
        }
    }

    public boolean havePlayer() {
        return mediaPlayer != null;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        viewControl.onPlayError(mediaPlayer);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        viewControl.onPlayPrepare(mediaPlayer);
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        viewControl.onPlayInfo(mediaPlayer);
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        viewControl.onPlayComplete(mediaPlayer);
    }
}
