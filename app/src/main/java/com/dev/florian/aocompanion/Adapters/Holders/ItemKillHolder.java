package com.dev.florian.aocompanion.Adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.Item;
import com.dev.florian.aocompanion.Class.News;
import com.dev.florian.aocompanion.NewsDetailActivity;
import com.dev.florian.aocompanion.R;
import com.squareup.picasso.Picasso;


/**
 * Created by flori on 28/02/2016.
 */

public class ItemKillHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public ItemKillHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Item item){
        Picasso.with(itemView.getContext()).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+item.getType()+"?quality="+item.getQuality())
                .into(imageView);

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.ARG_NEWS, news.getUrl());
                intent.putExtra(NewsDetailActivity.ARG_TITLE, news.getTitre());
                itemView.getContext().startActivity(intent);
            }
        });*/
    }
}