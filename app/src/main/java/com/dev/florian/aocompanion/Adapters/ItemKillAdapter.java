package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.ItemKillHolder;
import com.dev.florian.aocompanion.Adapters.Holders.NewsHolder;
import com.dev.florian.aocompanion.Class.Item;
import com.dev.florian.aocompanion.Class.News;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class ItemKillAdapter extends RecyclerView.Adapter<ItemKillHolder> {

    List<Item> list;

    //ajouter un constructeur prenant en entrée une liste
    public ItemKillAdapter(List<Item> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ItemKillHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_pvp_kills_item,viewGroup,false);
        return new ItemKillHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(ItemKillHolder holder, int position) {
        Item item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}