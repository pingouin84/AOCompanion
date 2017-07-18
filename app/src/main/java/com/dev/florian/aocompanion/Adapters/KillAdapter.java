package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.KillHolder;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class KillAdapter extends RecyclerView.Adapter<KillHolder> {

    List<Kill> list;

    //ajouter un constructeur prenant en entrée une liste
    public KillAdapter(List<Kill> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public KillHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_pvp_kills,viewGroup,false);
        return new KillHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(KillHolder killHolder, int position) {
        Kill kill = list.get(position);
        killHolder.bind(kill);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}