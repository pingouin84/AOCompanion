package com.dev.florian.aocompanion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dev.florian.aocompanion.Class.Item;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.Player;

public class ItemDetailActivity extends AppCompatActivity {

    public static String ARG_ITEM_NAME = "ARG_ITEM_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Thread thread = new Thread();
        String[] parametre = new String[]{getIntent().getStringExtra("ARG_ITEM_NAME")};
        thread.execute(parametre);
    }

    class Thread extends AsyncTask<String, Integer, Boolean> {
        private Item item = new Item();

        @Override
        protected Boolean doInBackground(String... params) {
            AlbionOnline ao = new AlbionOnline();
            item = ao.getItem(params[0]);
            if (item != null)
                return true;
            else
                return false;
        }

        protected void onPostExecute (Boolean resultat){
            afficher(resultat, item);
        }
    }

    void afficher (Boolean resultat, Item item) {

    }
}
