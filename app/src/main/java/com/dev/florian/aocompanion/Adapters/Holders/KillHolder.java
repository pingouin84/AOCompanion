package com.dev.florian.aocompanion.Adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.KillDetailActivity;
import com.dev.florian.aocompanion.R;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * Created by flori on 28/02/2016.
 */

public class KillHolder extends RecyclerView.ViewHolder{

    private TextView time,killer,victim,fame;
    private ImageView numberOfParticipants;

    public KillHolder(View itemView) {
        super(itemView);

        time = itemView.findViewById(R.id.timeStamp);
        killer = itemView.findViewById(R.id.killer);
        victim = itemView.findViewById(R.id.victim);
        numberOfParticipants = itemView.findViewById(R.id.numberOfParticipants);
        fame = itemView.findViewById(R.id.totalVictimKillFame);

    }

    public void bind(final Kill kill){
        time.setText(kill.getTimeStamp());
        killer.setText(kill.getKiller().getFullName());
        victim.setText(kill.getVictim().getFullName());
        if (kill.getNumberOfParticipants()>1)
            numberOfParticipants.setImageResource(R.drawable.kill);
        else
            numberOfParticipants.setImageResource(R.drawable.kill_solo);
        fame.setText(String.valueOf(kill.getTotalVictimKillFame()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), KillDetailActivity.class);
            intent.putExtra(KillDetailActivity.ARG_EVENT_ID, kill.getEventId());
            itemView.getContext().startActivity(intent);
            }
        });
    }
}