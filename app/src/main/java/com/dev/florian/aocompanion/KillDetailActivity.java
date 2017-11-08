package com.dev.florian.aocompanion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.florian.aocompanion.Adapters.GroupMembersAdapter;
import com.dev.florian.aocompanion.Adapters.ItemKillAdapter;
import com.dev.florian.aocompanion.Adapters.KillAdapter;
import com.dev.florian.aocompanion.Adapters.ParticipantsAdapter;
import com.dev.florian.aocompanion.Class.Equipment;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KillDetailActivity extends AppCompatActivity {

    public static String ARG_EVENT_ID = "ARG_EVENT_ID";

    private Kill kill;

    private ItemKillAdapter itemKillAdapter;
    private ParticipantsAdapter participantsAdapter;
    private GroupMembersAdapter groupMembersAdapter;
    private KillAdapter playersFeudAdapter,guildFeudAdapter;

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;

    @BindView(R.id.ll_general)
    LinearLayout ll_general;
    @BindView(R.id.ll_killer)
    LinearLayout ll_killer;
    @BindView(R.id.ll_victim)
    LinearLayout ll_victim;
    @BindView(R.id.ll_gear)
    LinearLayout ll_gear;
    @BindView(R.id.ll_player_feud)
    LinearLayout ll_player_feud;
    @BindView(R.id.ll_guild_feud)
    LinearLayout ll_guild_feud;
    @BindView(R.id.ll_inventory)
    LinearLayout ll_inventory;
    @BindView(R.id.ll_participants)
    LinearLayout ll_participants;
    @BindView(R.id.ll_groupMembers)
    LinearLayout ll_groupMembers;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.label_killer_victim)
    TextView label_killer_victim;
    @BindView(R.id.label_killer_victim_gear)
    TextView label_killer_victim_gear;
    @BindView(R.id.textView_name_killer)
    TextView textView_name_killer;
    @BindView(R.id.textView_guild_killer)
    TextView textView_guild_killer;
    @BindView(R.id.textView_fame_killer)
    TextView textView_fame_killer;
    @BindView(R.id.textView_averageItemPower_killer)
    TextView textView_averageItemPower_killer;
    @BindView(R.id.textView_averageItemPower_victim)
    TextView textView_averageItemPower_victim;
    @BindView(R.id.textView_damage_killer)
    TextView textView_damage_killer;
    @BindView(R.id.textView_name_victim)
    TextView textView_name_victim;
    @BindView(R.id.textView_guild_victim)
    TextView textView_guild_victim;
    @BindView(R.id.textView_timestamp)
    TextView textView_timestamp;
    @BindView(R.id.textView_totalVictimKillFame)
    TextView textView_totalVictimKillFame;

    @BindView(R.id.imageView_armor)
    ImageView imageView_armor;
    @BindView(R.id.imageView_bag)
    ImageView imageView_bag;
    @BindView(R.id.imageView_cap)
    ImageView imageView_cap;
    @BindView(R.id.imageView_food)
    ImageView imageView_food;
    @BindView(R.id.imageView_head)
    ImageView imageView_head;
    @BindView(R.id.imageView_mainhand)
    ImageView imageView_mainhand;
    @BindView(R.id.imageView_offhand)
    ImageView imageView_offhand;
    @BindView(R.id.imageView_potion)
    ImageView imageView_potion;
    @BindView(R.id.imageView_mount)
    ImageView imageView_mount;
    @BindView(R.id.imageView_shoes)
    ImageView imageView_shoes;

    @BindView(R.id.rv_inventory)
    RecyclerView rv_inventory;
    @BindView(R.id.rv_participants)
    RecyclerView rv_participants;
    @BindView(R.id.rv_groupMembers)
    RecyclerView rv_groupMembers;
    @BindView(R.id.rv_players_feud)
    RecyclerView rv_players_feud;
    @BindView(R.id.rv_guild_feud)
    RecyclerView rv_guild_feud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        setTitle("KILL ID: #"+getIntent().getIntExtra("ARG_EVENT_ID",0));

        VisibilityGone();
        VisibilityGeneral();

        rv_inventory.setLayoutManager(new GridLayoutManager(this,4));
        rv_participants.setLayoutManager(new LinearLayoutManager(this));
        rv_groupMembers.setLayoutManager(new LinearLayoutManager(this));
        rv_players_feud.setLayoutManager(new LinearLayoutManager(this));
        rv_guild_feud.setLayoutManager(new LinearLayoutManager(this));

        tab_layout.addOnTabSelectedListener(tab_layout_selected);

        thread (getIntent().getIntExtra("ARG_EVENT_ID",0));
    }

    TabLayout.OnTabSelectedListener tab_layout_selected = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            String param = "";
            VisibilityGone();
            switch (tab.getPosition()){
                case 0 :
                    VisibilityGeneral(); break;
                case 1 :
                    VisibilityKiller(); break;
                case 2 :
                    VisibilityVictim(); break;
                case 3 :
                    ll_player_feud.setVisibility(View.VISIBLE); break;
                case 4 :
                    ll_guild_feud.setVisibility(View.VISIBLE); break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private void VisibilityVictim() {
        ll_victim.setVisibility(View.VISIBLE);
        ll_gear.setVisibility(View.VISIBLE);
        ll_inventory.setVisibility(View.VISIBLE);
        label_killer_victim_gear.setText("VICTIM'S GEAR");
        afficherPlayer(kill.getVictim());
    }

    private void VisibilityKiller() {
        ll_killer.setVisibility(View.VISIBLE);
        ll_gear.setVisibility(View.VISIBLE);
        ll_participants.setVisibility(View.VISIBLE);
        ll_groupMembers.setVisibility(View.VISIBLE);
        textView_damage_killer.setVisibility(View.VISIBLE);
        label_killer_victim_gear.setText("KILLER'S GEAR");
        afficherPlayer(kill.getKiller());
    }

    private void VisibilityGeneral() {
        ll_general.setVisibility(View.VISIBLE);
        ll_killer.setVisibility(View.VISIBLE);
        ll_victim.setVisibility(View.VISIBLE);
        textView_averageItemPower_killer.setVisibility(View.VISIBLE);
        textView_averageItemPower_victim.setVisibility(View.VISIBLE);
    }

    private void VisibilityGone() {
        ll_general.setVisibility(View.GONE);
        ll_killer.setVisibility(View.GONE);
        ll_victim.setVisibility(View.GONE);
        ll_gear.setVisibility(View.GONE);
        ll_guild_feud.setVisibility(View.GONE);
        ll_player_feud.setVisibility(View.GONE);
        ll_inventory.setVisibility(View.GONE);
        ll_participants.setVisibility(View.GONE);
        ll_groupMembers.setVisibility(View.GONE);
        textView_averageItemPower_killer.setVisibility(View.GONE);
        textView_averageItemPower_victim.setVisibility(View.GONE);
        textView_damage_killer.setVisibility(View.GONE);
    }

    private void thread (Integer param) {
        progressBar.setVisibility(View.VISIBLE);

        Thread thread = new Thread();
        Integer[] parametre = new Integer[]{param};
        thread.execute(parametre);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            //stopActivityTask();  // finish() here.
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    class Thread extends AsyncTask<Integer, Integer, Boolean> {
        private String code;
        private Kill kill = new Kill();
        private List<Kill> players_feud = new ArrayList<>();
        private List<Kill> guild_feud = new ArrayList<>();

        @Override
        protected Boolean doInBackground(Integer... params) {
            AlbionOnline ao = new AlbionOnline();
            kill = ao.getKill(params[0]);
            if (kill != null) {
                players_feud = ao.getPlayersFeud(kill.getKiller().getId(),kill.getVictim().getId());
                if (players_feud.size() > 0) {
                    guild_feud = ao.getGuildFeud(kill.getKiller().getGuildId(), kill.getVictim().getGuildId());
                    if (guild_feud.size() > 0)
                        return true;
                }
            }

            return false;
        }

        protected void onPostExecute (Boolean resultat){
            afficher(resultat, kill, players_feud,guild_feud);
        }
    }

    void afficher (Boolean resultat,Kill kill, List<Kill> players_feud, List<Kill> guild_feud) {
        this.kill = kill;
        if (resultat) {

            textView_timestamp.setText(kill.getTimeStamp());
            textView_totalVictimKillFame.setText(String.valueOf(kill.getTotalVictimKillFame()));

            Player player = kill.getKiller();
            textView_name_killer.setText(player.getName());
            textView_guild_killer.setText(player.getFullGuild());
            textView_fame_killer.setText(String.valueOf(player.getKillFame()));
            textView_averageItemPower_killer.setText(String.valueOf(player.getAverageItemPower()));

            player = kill.getVictim();
            textView_name_victim.setText(player.getName());
            textView_guild_victim.setText(player.getFullGuild());
            textView_averageItemPower_victim.setText(String.valueOf(player.getAverageItemPower()));

            if (kill.getNumberOfParticipants() > 0) {
                participantsAdapter = new ParticipantsAdapter(kill.getParticipants());
                rv_participants.setAdapter(participantsAdapter);
            }

            if (kill.getGroupMemberCount() > 0) {
                groupMembersAdapter = new GroupMembersAdapter(kill.getGroupMembers());
                rv_groupMembers.setAdapter(groupMembersAdapter);
            }

            if (players_feud.size()>0) {
                playersFeudAdapter = new KillAdapter(players_feud);
                rv_players_feud.setAdapter(playersFeudAdapter);
            }

            if (guild_feud.size()>0) {
                guildFeudAdapter = new KillAdapter(guild_feud);
                rv_guild_feud.setAdapter(guildFeudAdapter);
            }

            afficherPlayer(kill.getKiller());
        }
        progressBar.setVisibility(View.GONE);
    }

    void afficherPlayer (Player player) {
        textView_damage_killer.setText(String.valueOf(player.getDamage())+"%");
        Equipment equipment = player.getEquipment();
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getArmor().getType()+"?quality="+equipment.getArmor().getQuality()).into(imageView_armor);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getBag().getType()+"?quality="+equipment.getBag().getQuality()).into(imageView_bag);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getCape().getType()+"?quality="+equipment.getCape().getQuality()).into(imageView_cap);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getFood().getType()+"?quality="+equipment.getFood().getQuality()).into(imageView_food);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getHead().getType()+"?quality="+equipment.getHead().getQuality()).into(imageView_head);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getMainHand().getType()+"?quality="+equipment.getMainHand().getQuality()).into(imageView_mainhand);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getOffHand().getType()+"?quality="+equipment.getOffHand().getQuality()).into(imageView_offhand);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getMount().getType()+"?quality="+equipment.getMount().getQuality()).into(imageView_mount);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getPotion().getType()+"?quality="+equipment.getPotion().getQuality()).into(imageView_potion);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getShoes().getType()+"?quality="+equipment.getShoes().getQuality()).into(imageView_shoes);

        itemKillAdapter = new ItemKillAdapter(player.getInventory());
        rv_inventory.setAdapter(itemKillAdapter);
    }
}
