package com.example.va407031iutniceunicefr.carryme2;

/**
 * Created by ml403608@iutnice.unice.fr on 29/02/16.
 */

        import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;




public class PersonCredit implements Comparable<PersonCredit> {
private int id;
private String name;
private String sname;
private String nickname;
private String description;

    public String getName(){return name;}
    public String getDescription(){return description;}
    public void setName(String n){name=n;}
    public void setDescription(String d){description=d;}
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PersonCredit(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();

        while ( !(parser.getName().equals("item"))) {
            if(eventType==XmlPullParser.START_TAG) {

                if (parser.getName().equals("id")){
                    parser.next();
                    id = Integer.parseInt(parser.getText());
                    parser.next(); // fin de balise
                }

                if (parser.getName().equals("name")){
                    parser.next();
                    name = parser.getText();
                    parser.next(); // fin de balise
                }


                if (parser.getName().equals("sname")){
                    parser.next();
                   sname = parser.getText();
                    parser.next(); // fin de balise
                }

                if (parser.getName().equals("nickname")){
                    parser.next();
                    nickname = parser.getText();
                    parser.next(); // fin de balise
                }

                if (parser.getName().equals("description")){
                    parser.next();
                    description = parser.getText();
                    parser.next(); // fin de balise
                }

            }

            parser.nextTag(); // balise suivante

        }/*
        parser.require(XmlPullParser.START_TAG, null, "item");
        parser.next();

        parser.require(XmlPullParser.START_TAG, null, "name");
        parser.next();
        name=parser.getText();
        parser.next();
        parser.require(XmlPullParser.END_TAG, null, "name");
        parser.nextTag();

        parser.require(XmlPullParser.START_TAG, null, "cost");
        parser.next();
        cost=parser.getText();
        parser.next();
        parser.require(XmlPullParser.END_TAG, null, "cost");
        parser.nextTag();

        parser.require(XmlPullParser.START_TAG, null, "description");
        parser.next();
        description=parser.getText();
        parser.next();
        parser.require(XmlPullParser.END_TAG, null, "description");
        parser.nextTag();

        parser.require(XmlPullParser.END_TAG, null, "item");*/
    }

    @Override
    public int compareTo(PersonCredit another) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if(id < another.id){
            return BEFORE;
        }
        if(id > another.id){
            return AFTER;
        }
        return EQUAL;
    }

    @Override
    public String toString() {
        return "PersonCredit{" +
                ", id='" + id + '\'' +
                ", nom='" + name + '\'' +
                ", prenom='" + sname + '\'' +
                ", surnom='" + nickname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}