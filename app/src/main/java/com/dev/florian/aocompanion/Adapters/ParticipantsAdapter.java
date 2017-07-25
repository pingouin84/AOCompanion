package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.KillHolder;
import com.dev.florian.aocompanion.Adapters.Holders.ParticipantsHolder;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.Player;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsHolder> {

    List<Player> list;

    public ParticipantsAdapter(List<Player> list) {
        this.list = list;
    }

    @Override
    public ParticipantsHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_participants,viewGroup,false);
        return new ParticipantsHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantsHolder holder, int position) {
        Player player = list.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}