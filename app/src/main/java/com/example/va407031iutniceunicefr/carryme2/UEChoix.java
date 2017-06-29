package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maxpc.carryme2.R;

/**
 * Created by MaxPC on 13/03/2016.
 */
public class UEChoix extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.ue_choix);

        final Button bqcm = (Button) findViewById(R.id.qcm);
        bqcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UEChoix.this, QCM.class);
                startActivity(intent);
            }
        });

        final Button bdoc = (Button) findViewById(R.id.doc);
        bdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UEChoix.this, Documentation.class);
                startActivity(intent);
            }
        });

        final Button accueil = (Button) findViewById(R.id.quitter);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(UEChoix.this, MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }
}
