package com.example.va407031iutniceunicefr.carryme2;

/**
 * Created by ml403608@iutnice.unice.fr on 29/02/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maxpc.carryme2.R;

import java.util.ArrayList;

/**
 * Created by Rosa on 25/02/2015.
 */
public class PersonAdapter extends BaseAdapter {



    private ArrayList<PersonCredit> mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public PersonAdapter(Context context, ArrayList<PersonCredit> aListP) {
        mContext = context;
        mListP = aListP;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return mListP.size();
    }

    public Object getItem(int position) {
        return mListP.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.person, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView nomcomplet = (TextView)layoutItem.findViewById(R.id.textView2);
        TextView surnom = (TextView)layoutItem.findViewById(R.id.textView);
        TextView classe = (TextView)layoutItem.findViewById(R.id.textView3);

        //(3) : Renseignement des valeurs
        nomcomplet.setText(mListP.get(position).getName()+" "+mListP.get(position).getSname());
        surnom.setText(mListP.get(position).getNickname());
        classe.setText(mListP.get(position).getDescription());

        nomcomplet.setTag(position);
        nomcomplet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                sendListener(mListP.get(position), position);

            }

        });
        //On retourne l'item créé.
        return layoutItem;
    }

    //abonnement pour click sur le nom...
    private ArrayList<PizzaAdapterListener> mListListener = new ArrayList<PizzaAdapterListener>();
    public void addListener(PizzaAdapterListener aListener) {
        mListListener.add(aListener);
    }
    private void sendListener(PersonCredit item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
            mListListener.get(i).onClickNom(item, position);
        }
    }

    /**
     * Interface pour écouter les évènements sur le nom d'une personne
     */
    public interface PizzaAdapterListener {
        public void onClickNom(PersonCredit item, int position);
    }

}

