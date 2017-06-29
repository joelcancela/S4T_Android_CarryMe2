package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.maxpc.carryme2.R;

/**
 * Created by MaxPC on 13/03/2016.
 */
public class ReglagesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.reglages);
        initVolumeControl();
    }

    private void initVolumeControl() {
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int vol = sharedPref.getInt("volume", 0);
        ImageView im = (ImageView)findViewById(R.id.imageView);
        if(vol==0){
            im.setImageResource(R.drawable.muted);
        }

        SeekBar volumeSeekbar = (SeekBar) findViewById(R.id.volumebar);
        volumeSeekbar.setProgress(vol);
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPref.edit() ;
                editor.putInt("volume",progress);
                editor.commit();
                float volume = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                OPMediaPlayer.getInstance().mp.setVolume(volume,volume);
                ImageView im = (ImageView)findViewById(R.id.imageView);
                if(progress==0){
                    im.setImageResource(R.drawable.muted);
                }
                else{
                    im.setImageResource(R.drawable.notmuted);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}