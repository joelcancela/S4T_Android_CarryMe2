package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxpc.carryme2.R;

import org.w3c.dom.Text;

import static java.lang.Thread.sleep;

/**
 * Created by Adrien on 24/03/2016.
 */
public class QCM extends Activity {

    private int questmin = 0;//inclus
    private int questmax = 29+1;//exclus

    private int randomQuestMin = 0;
    private int randomQuestMax = 4;
    protected int nbquestions=10;

    int bonneReponse = 0;
    TextView questionView;
    Button reponse1;
    Button reponse2;
    Button reponse3;
    Button reponse4;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.ue_qcm);
        questionView = (TextView) findViewById(R.id.question);
        reponse1 = (Button) findViewById(R.id.choix1);
        reponse2 = (Button) findViewById(R.id.choix2);
        reponse3 = (Button) findViewById(R.id.choix3);
        reponse4 = (Button) findViewById(R.id.choix4);
        QCMHelper mesQuestionsList = new QCMHelper();
        mesQuestionsList.addQuestion();
            genererQuestion(mesQuestionsList);
    }


    public void refreshbgquestions(){
        reponse1.setBackgroundResource(android.R.drawable.btn_default);
        reponse2.setBackgroundResource(android.R.drawable.btn_default);
        reponse3.setBackgroundResource(android.R.drawable.btn_default);
        reponse4.setBackgroundResource(android.R.drawable.btn_default);
        reponse1.setEnabled(true);
        reponse2.setEnabled(true);
        reponse3.setEnabled(true);
        reponse4.setEnabled(true);
    }

    public void nextQuestion(final QCMHelper qcm){
        reponse1.setEnabled(false);
        reponse2.setEnabled(false);
        reponse3.setEnabled(false);
        reponse4.setEnabled(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                refreshbgquestions();
                nbquestions--;
                if (nbquestions > 0) {
                    genererQuestion(qcm);
                }
                if (nbquestions == 0) {
                    int score = bonneReponse;
                    setContentView(R.layout.end_qcm);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                    TextView Score = (TextView) findViewById(R.id.score);
                    Score.setText(Integer.toString(score));
                    Score.startAnimation(AnimationUtils.loadAnimation(
                            QCM.this, R.anim.fondu_agrandissement));
                    Score.setVisibility(View.VISIBLE);
                    final Button home =(Button) findViewById(R.id.accueil);
                    home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            }
        }, 1000);
    }


    public void genererQuestion(QCMHelper mesQuestionsList){


        //Récupère un ArrayList de question
            questmax=mesQuestionsList.size();

            int x = (int) (Math.random() * (questmax - questmin)) + questmin;


            //Récupère une question au hasard
            Questions_UE q1 = (Questions_UE) mesQuestionsList.get(x);
            mesQuestionsList.remove(x);
            questmax=mesQuestionsList.size();
            //Pas retake meme question


            //Récupère la question, et les 4 réponse
            String question = q1.getQuestion();
            String rep1 = q1.getRep1();
            String rep2 = q1.getRep2();
            String rep3 = q1.getRep3();
            String repJuste = q1.getRepJuste();
            questionView.setText(question);


            final int y = (int) (Math.random() * (randomQuestMax - randomQuestMin)) + randomQuestMin;
            //Affiche la question et les réponses
            switch (y) {
                case (0):
                    reponse1.setText(repJuste); //0
                    reponse2.setText(rep1); //1
                    reponse3.setText(rep2); //2
                    reponse4.setText(rep3); //3
                    break;
                case (1):
                    reponse1.setText(rep1); //0
                    reponse2.setText(repJuste); //1
                    reponse3.setText(rep2); //2
                    reponse4.setText(rep3); //3
                    break;
                case (2):
                    reponse1.setText(rep1); //0
                    reponse2.setText(rep2); //1
                    reponse3.setText(repJuste); //2
                    reponse4.setText(rep3); //3
                    break;
                case (3):
                    reponse1.setText(rep1); //0
                    reponse2.setText(rep2); //1
                    reponse3.setText(rep3); //2
                    reponse4.setText(repJuste); //3
                    break;
            }
        qcmQuest(y,repJuste,mesQuestionsList);
        }

    public void qcmQuest(final int y, final String repJuste, final QCMHelper qcm) {



            reponse1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(y==0){
                        reponse1.setBackgroundColor(Color.GREEN);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==1){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.GREEN);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==2){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.GREEN);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if (y==3){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.GREEN);
                    }

                    if ((reponse1.getText().toString()).equals(repJuste) )
                        bonneReponse++;
                    nextQuestion(qcm);
                }
            });

            reponse2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(y==0){
                        reponse1.setBackgroundColor(Color.GREEN);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==1){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.GREEN);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==2){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.GREEN);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==3){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.GREEN);
                    }
                    if ((reponse2.getText().toString()).equals(repJuste))
                        bonneReponse++;
                    nextQuestion(qcm);
                }
            });

            reponse3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(y==0){
                        reponse1.setBackgroundColor(Color.GREEN);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==1){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.GREEN);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==2){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.GREEN);
                        reponse4.setBackgroundColor(Color.RED);
                    }
                    else if(y==3){
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.GREEN);
                    }
                    if ((reponse3.getText().toString()).equals(repJuste))
                        bonneReponse++;

                    nextQuestion(qcm);
                }
            });

            reponse4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (y == 0) {
                        reponse1.setBackgroundColor(Color.GREEN);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    } else if (y == 1) {
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.GREEN);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.RED);
                    } else if (y == 2) {
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.GREEN);
                        reponse4.setBackgroundColor(Color.RED);
                    } else if (y == 3) {
                        reponse1.setBackgroundColor(Color.RED);
                        reponse2.setBackgroundColor(Color.RED);
                        reponse3.setBackgroundColor(Color.RED);
                        reponse4.setBackgroundColor(Color.GREEN);
                    }
                    if ((reponse4.getText().toString()).equals(repJuste))
                        bonneReponse++;
                    nextQuestion(qcm);
                }
            });
    }
}