package com.dev.florian.aocompanion.Adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.NewsDetailActivity;
import com.dev.florian.aocompanion.Class.News;
import com.dev.florian.aocompanion.R;
import com.squareup.picasso.Picasso;


/**
 * Created by flori on 28/02/2016.
 */

public class NewsHolder extends RecyclerView.ViewHolder{

    private TextView titre,date,resume;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public NewsHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        titre = itemView.findViewById(R.id.titre);
        imageView = itemView.findViewById(R.id.image);
        date = itemView.findViewById(R.id.date);
        resume = itemView.findViewById(R.id.resume);

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final News news){
        titre.setText(news.getTitre());
        Picasso.with(itemView.getContext()).load("https:"+news.getImageUrl())
                .into(imageView);
        date.setText(news.getDate());
        resume.setText(news.getResume());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.ARG_NEWS, news.getUrl());
                intent.putExtra(NewsDetailActivity.ARG_TITLE, news.getTitre());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}