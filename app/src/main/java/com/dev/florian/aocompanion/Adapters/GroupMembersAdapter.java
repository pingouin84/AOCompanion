package com.dev.florian.aocompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.florian.aocompanion.Adapters.Holders.GroupMembersHolder;
import com.dev.florian.aocompanion.Class.Player;
import com.dev.florian.aocompanion.R;

import java.util.List;


/**
 * Created by flori on 28/02/2016.
 */

public class GroupMembersAdapter extends RecyclerView.Adapter<GroupMembersHolder> {

    List<Player> list;

    public GroupMembersAdapter(List<Player> list) {
        this.list = list;
    }

    @Override
    public GroupMembersHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_group_members,viewGroup,false);
        return new GroupMembersHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupMembersHolder holder, int position) {
        Player player = list.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}