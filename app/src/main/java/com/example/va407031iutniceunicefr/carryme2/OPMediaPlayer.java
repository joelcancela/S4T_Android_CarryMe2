package com.example.va407031iutniceunicefr.carryme2;

import android.media.MediaPlayer;

/**
 * Created by Joel CANCELA on 24/03/2016.
 */
public class OPMediaPlayer {
    MediaPlayer mp;
    private static volatile OPMediaPlayer instance = null;
    private OPMediaPlayer() { }

    public static OPMediaPlayer getInstance() {
        if (instance == null) {
            synchronized (OPMediaPlayer.class) {
                if (instance == null) {
                    instance = new OPMediaPlayer();
                }
            }
        }

        return instance;
    }
}