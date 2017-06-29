package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.maxpc.carryme2.R;

/**
 * Created by MaxPC on 13/03/2016.
 */
public class CmtJouerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.comment_jouer);
    }
}