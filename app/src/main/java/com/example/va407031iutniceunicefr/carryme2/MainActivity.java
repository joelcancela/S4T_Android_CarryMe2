package com.example.va407031iutniceunicefr.carryme2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maxpc.carryme2.R;

public class MainActivity extends AppCompatActivity {
    OPMediaPlayer player = OPMediaPlayer.getInstance();


    public void playSound(int vol){
        if(player.mp==null)
            player.mp = MediaPlayer.create(getApplicationContext(), R.raw.happy);
        float volume = (float) (1 - (Math.log(100 - vol) / Math.log(100)));
        player.mp.setVolume(volume, volume);
        player.mp.setLooping(true);
        player.mp.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.accueil);
        listeners();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.accueil);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        playSound(sharedPref.getInt("volume", 0));
        listeners();



    }

    private void listeners() {
        final Button bcmtJouer = (Button) findViewById(R.id.cmtJouer);
        bcmtJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CmtJouerActivity.class);
                startActivity(intent);
            }
        });

        final Button breglages =(Button) findViewById(R.id.reglages);
        breglages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReglagesActivity.class);
                startActivity(intent);
            }
        });

        final Button ue_choix =(Button) findViewById(R.id.union);
        ue_choix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UEChoix.class);
                startActivity(intent);
            }
        });

        final Button calcul_choix =(Button) findViewById(R.id.calc);
        calcul_choix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculsChoix.class);
                startActivity(intent);
            }
        });

        final Button credits =(Button) findViewById(R.id.credits);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Credits.class);
                startActivity(intent);
            }
        });
    }
}
