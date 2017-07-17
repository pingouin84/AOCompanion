package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.NewsHolder;
import com.dev.florian.aocompanion.Adapters.Holders.PvpKillsHolder;
import com.dev.florian.aocompanion.Class.News;
import com.dev.florian.aocompanion.Class.PvpKills;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class PvpKillsAdapter extends RecyclerView.Adapter<PvpKillsHolder> {

    List<PvpKills> list;

    //ajouter un constructeur prenant en entrée une liste
    public PvpKillsAdapter(List<PvpKills> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public PvpKillsHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_pvp_kills,viewGroup,false);
        return new PvpKillsHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(PvpKillsHolder pvpKillsHolder, int position) {
        PvpKills pvpKills = list.get(position);
        pvpKillsHolder.bind(pvpKills);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}