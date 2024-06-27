package com.example.calculmental;

import android.app.Application;
import android.media.MediaPlayer;

public class MediaPlayerManager extends Application {
    public static MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.aliensong);
        mediaPlayer.setLooping(true);
    }
}
