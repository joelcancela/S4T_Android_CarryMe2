package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.maxpc.carryme2.R;

/**
 * Created by MaxPC on 13/03/2016.
 */
public class CalculsChoix extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.calcul_choix);
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.calculLayout, new CalculMental()).commit();
        }
    }
}
