package com.dev.florian.aocompanion.Adapters.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.Player;
import com.dev.florian.aocompanion.R;


/**
 * Created by flori on 28/02/2016.
 */

public class GroupMembersHolder extends RecyclerView.ViewHolder{

    private TextView name,guilde,fame;

    public GroupMembersHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView_name);
        guilde = itemView.findViewById(R.id.textView_guild);
        fame = itemView.findViewById(R.id.textView_fame);
    }

    public void bind(final Player player){
        name.setText(player.getName());
        guilde.setText(player.getFullGuild());
        fame.setText(String.valueOf(player.getKillFame()));
    }
}