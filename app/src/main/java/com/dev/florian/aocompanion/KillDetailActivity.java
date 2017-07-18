package com.dev.florian.aocompanion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.florian.aocompanion.Class.Equipment;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.Player;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KillDetailActivity extends AppCompatActivity {

    public static String ARG_EVENT_ID = "ARG_EVENT_ID";

    private Kill kill;

    @Bind(R.id.tab_layout)
    TabLayout tab_layout;

    @Bind(R.id.ll_general)
    LinearLayout ll_general;
    @Bind(R.id.ll_killer)
    LinearLayout ll_killer;
    @Bind(R.id.ll_victim)
    LinearLayout ll_victim;
    @Bind(R.id.ll_gear)
    LinearLayout ll_gear;
    @Bind(R.id.ll_player_feud)
    LinearLayout ll_player_feud;
    @Bind(R.id.ll_guild_feud)
    LinearLayout ll_guild_feud;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.label_killer_victim)
    TextView label_killer_victim;
    @Bind(R.id.label_killer_victim_gear)
    TextView label_killer_victim_gear;
    @Bind(R.id.textView_name_killer)
    TextView textView_name_killer;
    @Bind(R.id.textView_guild_killer)
    TextView textView_guild_killer;
    @Bind(R.id.textView_fame_killer)
    TextView textView_fame_killer;
    @Bind(R.id.textView_averageItemPower_killer)
    TextView textView_averageItemPower_killer;
    @Bind(R.id.textView_averageItemPower_victim)
    TextView textView_averageItemPower_victim;
    @Bind(R.id.textView_damage_killer)
    TextView textView_damage_killer;
    @Bind(R.id.textView_name_victim)
    TextView textView_name_victim;
    @Bind(R.id.textView_guild_victim)
    TextView textView_guild_victim;
    @Bind(R.id.textView_timestamp)
    TextView textView_timestamp;
    @Bind(R.id.textView_totalVictimKillFame)
    TextView textView_totalVictimKillFame;

    @Bind(R.id.imageView_armor)
    ImageView imageView_armor;
    @Bind(R.id.imageView_bag)
    ImageView imageView_bag;
    @Bind(R.id.imageView_cap)
    ImageView imageView_cap;
    @Bind(R.id.imageView_food)
    ImageView imageView_food;
    @Bind(R.id.imageView_head)
    ImageView imageView_head;
    @Bind(R.id.imageView_mainhand)
    ImageView imageView_mainhand;
    @Bind(R.id.imageView_offhand)
    ImageView imageView_offhand;
    @Bind(R.id.imageView_potion)
    ImageView imageView_potion;
    @Bind(R.id.imageView_mount)
    ImageView imageView_mount;
    @Bind(R.id.imageView_shoes)
    ImageView imageView_shoes;

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
        label_killer_victim_gear.setText("VICTIM'S GEAR");
        afficherPlayer(kill.getVictim());
    }

    private void VisibilityKiller() {
        ll_killer.setVisibility(View.VISIBLE);
        ll_gear.setVisibility(View.VISIBLE);
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

        @Override
        protected Boolean doInBackground(Integer... params) {
            AlbionOnline ao = new AlbionOnline();
            kill = ao.getKill(params[0]);
            if (kill != null)
                return true;
            else
                return false;
        }

        protected void onPostExecute (Boolean resultat){
            afficher(resultat, kill);
        }
    }

    void afficher (Boolean resultat,Kill kill) {
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

            afficherPlayer(kill.getKiller());
        }
        //progressBar.setVisibility(View.GONE);
    }

    void afficherPlayer (Player player) {
        textView_damage_killer.setText(String.valueOf(player.getDamage())+"%");
        Equipment equipment = player.getEquipment();
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getArmor().getType()).into(imageView_armor);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getBag().getType()).into(imageView_bag);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getCape().getType()).into(imageView_cap);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getFood().getType()).into(imageView_food);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getHead().getType()).into(imageView_head);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getMainHand().getType()).into(imageView_mainhand);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getOffHand().getType()).into(imageView_offhand);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getMount().getType()).into(imageView_mount);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getPotion().getType()).into(imageView_potion);
        Picasso.with(this).load("https://gameinfo.albiononline.com/api/gameinfo/items/"+equipment.getShoes().getType()).into(imageView_shoes);
    }
}
