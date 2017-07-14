package com.dev.florian.aocompanion.Adapters.Holders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.DetailNewsActivity;
import com.dev.florian.aocompanion.News;
import com.dev.florian.aocompanion.R;
import com.squareup.picasso.Picasso;


/**
 * Created by flori on 28/02/2016.
 */

public class NewsHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private TextView textViewView2;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public NewsHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = itemView.findViewById(R.id.titre);
        textViewView2 = itemView.findViewById(R.id.article);
        imageView = itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final News news){
        textViewView.setText(news.getTitre());
        textViewView2.setText(news.getArticle());
        Picasso.with(itemView.getContext()).load("https:"+news.getImageUrl())
                .into(imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), DetailNewsActivity.class);
            intent.putExtra(DetailNewsActivity.ARG_URL, news.getUrl());
            itemView.getContext().startActivity(intent);
            }
        });
    }
}