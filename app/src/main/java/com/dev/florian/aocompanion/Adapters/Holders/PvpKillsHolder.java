package com.dev.florian.aocompanion.Adapters.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.PvpKills;
import com.dev.florian.aocompanion.R;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * Created by flori on 28/02/2016.
 */

public class PvpKillsHolder extends RecyclerView.ViewHolder{

    private TextView time,killer,victim,fame;
    private ImageView type;

    //itemView est la vue correspondante à 1 cellule
    public PvpKillsHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        time = itemView.findViewById(R.id.timeStamp);
        killer = itemView.findViewById(R.id.killer);
        victim = itemView.findViewById(R.id.victim);
        type = itemView.findViewById(R.id.numberOfParticipants);
        fame = itemView.findViewById(R.id.totalVictimKillFame);

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final PvpKills pvpKills){
        //LocalDate date = LocalDate.parse("");
        //Date date = Date.valueOf(pvpKills.getTimeStamp());
        //Date timestamp = Date.valueOf(pvpKills.getTimeStamp().replace("T"," ").replace("Z"," "));
        Timestamp timestamp = Timestamp.valueOf(pvpKills.getTimeStamp().replace("T"," ").replace("Z"," "));
        //Forma
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        time.setText(formatter.format(new Date(timestamp.getTime())));
        killer.setText(pvpKills.getKiller().getFullName());
        victim.setText(pvpKills.getVictim().getFullName());
        if (pvpKills.getNumberOfParticipants()>1)
            type.setImageResource(R.drawable.kill);
        else
            type.setImageResource(R.drawable.kill_solo);
        fame.setText(pvpKills.getTotalVictimKillFame().toString());

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), DetailNewsActivity.class);
            intent.putExtra(DetailNewsActivity.ARG_URL, pvpKills.getUrl());
            itemView.getContext().startActivity(intent);
            }
        });*/
    }
}