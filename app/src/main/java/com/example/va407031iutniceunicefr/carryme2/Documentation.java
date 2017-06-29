package com.example.va407031iutniceunicefr.carryme2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.net.ConnectivityManager;

import com.example.maxpc.carryme2.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Created by MaxPC on 15/03/2016.
 */
public class Documentation extends Activity {

    private WebView wikipedia;
    String url= "https://fr.wikipedia.org/wiki/Union_europ%C3%A9enne";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setContentView(R.layout.documentation);

        wikipedia = (WebView) findViewById(R.id.wiki);
        wikipedia.setWebViewClient(new MyWebViewClient());
        wikipedia.getSettings().setJavaScriptEnabled(true);
        wikipedia.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);



        wikipedia.getSettings().setJavaScriptEnabled(true);
        if(isConnectingToInternet(getApplicationContext())){
            wikipedia.loadUrl(url);
        }
        else{
            Toast.makeText(getApplicationContext(), "Pas de connexion internet", Toast.LENGTH_LONG).show();
            final Button testCo = (Button) findViewById(R.id.retry);
            testCo.setVisibility(View.VISIBLE);
            testCo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(Documentation.this, Documentation.class);
                    //startActivity(intent);
                    wikipedia.loadUrl(url);
                    if(isConnectingToInternet(getApplicationContext())){
                        testCo.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        final Button accueil = (Button) findViewById(R.id.quitter);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Documentation.this, UEChoix.class);
                //startActivity(intent);
                finish();
            }
        });

        //final Button testCo = (Button) findViewById(R.id.quitter);

    }
    private boolean isConnectingToInternet(Context applicationContext){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            Toast.makeText(getApplicationContext(), "Pas de connexion internet", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
