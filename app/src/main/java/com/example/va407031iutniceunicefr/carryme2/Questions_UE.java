package com.example.va407031iutniceunicefr.carryme2;

/**
 * Created by Adrien on 24/03/2016.
 */
public class Questions_UE {

    private int ID;
    private String Question;
    private String RepJuste;
    private String Rep1;
    private String Rep2;
    private String Rep3;

    public Questions_UE(){
        ID=0;
        Question="";
        Rep1="";
        Rep2="";
        Rep3="";
        RepJuste="";
    }

    public Questions_UE(String quest, String choix1, String choix2, String choix3, String choixjuste) {
        Question = quest;
        Rep1 = choix1;
        Rep2 = choix2;
        Rep3 = choix3;
        RepJuste = choixjuste;
    }

    public int getID(){
        return ID;
    }

    public String getQuestion(){
        return Question;
    }

    public String getRep1(){
        return Rep1;
    }

    public String getRep2(){
        return Rep2;
    }

    public String getRep3(){
        return Rep3;
    }

    public String getRepJuste(){
        return RepJuste;
    }

    public void setID(int id)
    {
        ID=id;
    }

    public void setQuestion(String quest) {
        Question = quest;
    }
    public void setRep1(String choix1) {
        Rep1 = choix1;
    }
    public void setRep2(String choix2) {
        Rep2 = choix2;
    }
    public void setRep3(String choix3) {
        Rep3 = choix3;
    }
    public void setRepJuste(String choixjuste) {
        RepJuste = choixjuste;
    }


}

