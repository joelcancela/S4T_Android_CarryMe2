package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ListView;

import com.example.maxpc.carryme2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;


public class Credits extends Activity {

    private AsyncHttpClient clientHttp = new AsyncHttpClient();





   // String responseBody = "<menu><item><id>1</id><name>Margherita</name><cost>155</cost><description>Single cheese topping</description></item><item><id>2</id><name>Double Cheese Margherita</name><cost>225</cost><description>Loaded with Extra Cheese</description></item><item><id>3</id><name>Fresh Veggie</name><cost>110</cost><description>Oninon and Crisp capsicum</description></item><item><id>4</id><name>Peppy Paneer</name><cost>155</cost><description>Paneer, Crisp capsicum and Red pepper</description></item><item><id>5</id><name>Mexican Green Wave</name><cost>445</cost><description>Onion, Crip capsicum, Tomato with mexican herb</description></item><item><id>6</id><name>Deluxe Veggie</name><cost>190</cost><description>Onion, crisp capsicum, Mashroom,Corn</description></item><item><id>7</id><name>Barbeque Chicken</name><cost>200</cost><description>Onion and Barbeque Chicken</description></item><item><id>8</id><name>Spicy Chicken</name><cost>285</cost><description>Red Pepper and Hot'n spicy chicken</description></item><item><id>9</id><name>Keema Do Pyaza</name><cost>330</cost><description>Onion, Double keema and Jalapeno</description></item><item><id>10</id><name>Chicken Golden Delight</name><cost>490</cost><description>Golden corn, Double Barbeque and Cheese</description></item></menu>";

    private ArrayList<PersonCredit> pizzas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
        String url = "https://joelcancela.ddns.net/api/carryMe2/credits";
        String finalUrl = url;
        final ProgressDialog progressDialog = ProgressDialog.show(Credits.this, "Chargement","Connexion en cours...", true, true, null);
        clientHttp.get(Credits.this, finalUrl, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                try {
                    //Chargement du fichier XML et construction de la liste de pizza
                    parseResponse(s);
                    //affichage des infos
                    setUpList();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de lecture des données");
                } catch (IOException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de connexion lors de la lecture des données");
                }
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                showErrorMessage("Vérifiez votre connexion internet");
            }
        });

      /*  //Chargement du fichier XML et construction de la liste de pizza
        try {
            parseResponse(responseBody);

            //affichage de la liste
            setUpList();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            showErrorMessage("Erreur de lecture des données");
        } catch (IOException e) {
            e.printStackTrace();
            showErrorMessage("Erreur de connexion lors de la lecture des données");
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.*/
    }

    public void open(View view){
        String url = "http://api.androidhive.info/pizza/?format=xml";
        String finalUrl = url;
        final ProgressDialog progressDialog = ProgressDialog.show(Credits.this, "Chargement","Connexion en cours...", true, true, null);
        clientHttp.get(Credits.this, finalUrl, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                try {
                    //Chargement du fichier XML et construction de la liste de pizza
                    parseResponse(s);
                    //affichage des infos
                    setUpList();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de lecture des données");
                } catch (IOException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de connexion lors de la lecture des données");
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                showErrorMessage("Erreur de lecture des données dans failure");
            }

           /* @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                showErrorMessage("Erreur de lecture des données dans failure");
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                try {
                    //Chargement du fichier XML et construction de la liste de pizza
                    parseResponse(responseBody);
                    //affichage des infos
                    setUpList();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de lecture des données");
                } catch (IOException e) {
                    e.printStackTrace();
                    showErrorMessage("Erreur de connexion lors de la lecture des données");
                }
            }*/


        });}

    private void setUpList() {
        //Création et initialisation de l'Adapter pour les personnes
        PersonAdapter adapter = new PersonAdapter(this, pizzas);

        //Récupération du composant ListView
        ListView list = (ListView) findViewById(R.id.listView);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

        //Ecoute des évènements sur la liste


    }

    private void parseResponse(String responseBody) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(new StringReader(responseBody));
        parser.require(XmlPullParser.START_DOCUMENT, null, null);
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, null, "menu");
        pizzas = new ArrayList<PersonCredit>();
        int eventType = parser.getEventType();


        while (parser.nextTag() == XmlPullParser.START_TAG) {
            parser.nextTag();
            PersonCredit pizza = new PersonCredit(parser);
            pizzas.add(pizza);


        }

        parser.require(XmlPullParser.END_TAG, null, "menu");
        parser.next();
        parser.require(XmlPullParser.END_DOCUMENT, null, null);


        Collections.sort(pizzas);
    }

    public void showErrorMessage(String m) {
        new AlertDialog.Builder(Credits.this).setTitle("Erreur")
                .setMessage(m).setNeutralButton("OK", null).show();

    }


    public void setUpInfos(){
       //showErrorMessage("Sa passe");



    }


}