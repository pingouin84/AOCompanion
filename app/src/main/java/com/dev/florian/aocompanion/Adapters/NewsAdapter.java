package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.NewsHolder;
import com.dev.florian.aocompanion.News;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    List<News> list;

    //ajouter un constructeur prenant en entrée une liste
    public NewsAdapter(List<News> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public NewsHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_news,viewGroup,false);
        return new NewsHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(NewsHolder newsHolder, int position) {
        News news = list.get(position);
        newsHolder.bind(news);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}