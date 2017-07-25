package com.dev.florian.aocompanion.Adapters.Holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.Player;
import com.dev.florian.aocompanion.KillDetailActivity;
import com.dev.florian.aocompanion.R;


/**
 * Created by flori on 28/02/2016.
 */

public class ParticipantsHolder extends RecyclerView.ViewHolder{

    private TextView name,guilde,damage;

    public ParticipantsHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView_name);
        guilde = itemView.findViewById(R.id.textView_guild);
        damage = itemView.findViewById(R.id.textView_damage);
    }

    public void bind(final Player player){
        name.setText(player.getName());
        guilde.setText(player.getFullGuild());
        damage.setText(player.getDamage()+"%");
    }
}