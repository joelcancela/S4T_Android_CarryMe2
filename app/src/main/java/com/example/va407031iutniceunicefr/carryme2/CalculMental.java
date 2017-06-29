package com.example.va407031iutniceunicefr.carryme2;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxpc.carryme2.R;

/**
 * Created by MaxPC on 24/03/2016.
 */
public class CalculMental extends android.support.v4.app.Fragment {

    private Chronometer chronometer;

    private int lower = 1;//Inclus
    private int higher = 16;//Exclus
    private int higher2 = 5;//Exclus
    private View rootView;
    private int bonneReponse = 0;
    private int resul = 0;
    private int nbq = 20+1;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.calcul_mental,null);
        calcul();
        return rootView;
    }


    public void calcul() {
        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer); //Crée un chronometer
        final TextView nbr1 = (TextView) rootView.findViewById(R.id.textView);
        final TextView operande = (TextView) rootView.findViewById(R.id.textView2);
        final TextView nbr2 = (TextView) rootView.findViewById(R.id.textView3);
        final EditText resultat = (EditText) rootView.findViewById(R.id.editText);

        final Toast juste = Toast.makeText(getActivity().getApplicationContext(), "Bonne réponse !",
                Toast.LENGTH_SHORT);
        final Toast faux = Toast.makeText(getActivity().getApplicationContext(), "Mauvaise réponse.",
                Toast.LENGTH_SHORT);

        int x = 0;
        int y = 0;
        int signe = 0;


        chronometer.start();
        generateOperation(nbr1, nbr2, operande);

        resultat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == 66) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN)&& (resultat.getText().length() > 0) && (resul != Integer.parseInt(resultat.getText().toString()))){
                        faux.show();
                        resultat.setText("");
                    }


                    if ((event.getAction() == KeyEvent.ACTION_DOWN)&& (resultat.getText().length() > 0) && (resul == Integer.parseInt(resultat.getText().toString()))) {
                        juste.show();
                        bonneReponse++;
                        generateOperation(nbr1, nbr2, operande);
                        resultat.setText("");
                    }
                    if(nbq==0){
                        chronometer.stop();
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                        getActivity().setContentView(R.layout.end_calcul);
                        TextView time = (TextView)getActivity().findViewById(R.id.time);
                        time.setText(chronometer.getText());
                        resultat.setInputType(0);
                        time.startAnimation(AnimationUtils.loadAnimation(
                                getContext(), R.anim.fondu_agrandissement));
                        time.setVisibility(View.VISIBLE);
                        Button home = (Button) getActivity().findViewById(R.id.accueil);
                        final Activity theone = getActivity();
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //AREVOIR
                                //theone.setContentView(R.layout.accueil);
                                getActivity().getSupportFragmentManager().popBackStack();
                                getActivity().finish();
                                //Intent intent = new Intent(theone, MainActivity.class);
                                //startActivity(intent);
                            }
                        });


                    }
                    return true;
                }
                return false;
            }
        });


    }


    private void generateOperation(TextView a,TextView b, TextView c){
        nbq--;
        int x = (int)(Math.random() * (higher-lower)) + lower; //Premier nombre comprit entre 1 et 15
        int y = (int)(Math.random() * (higher-lower)) + lower; //Deuxieme comprit entre 1 et 15
        while(x<y){
            x = (int)(Math.random() * (higher-lower)) + lower;
        }
        int signe = (int)(Math.random() * (higher2-lower)) + lower; //Nombre compris entre 1 et 4 pour le choix de l'opérande

        a.setText(Integer.toString(x));
        b.setText(Integer.toString(y));

        switch (signe) {
            case (1):
                c.setText("+");
                resul = x + y;
                break;
            case (2):
                c.setText("-");
                resul = x - y;
                break;
            case (3):
                c.setText("*");
                resul = x * y;
                break;
            default:
                c.setText("/");
                resul = x / y;
                break;
        }

    }
}
