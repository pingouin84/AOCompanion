package com.dev.florian.aocompanion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class DetailNewsActivity extends AppCompatActivity {

    public static final String ARG_NEWS = "NEWS";
    public static final String ARG_TITLE = "TITLE";

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getIntent().getStringExtra(DetailNewsActivity.ARG_TITLE));

        webview = (WebView)findViewById(R.id.webView);

        Thread thread = new Thread();
        String[] parametre = new String[]{getIntent().getStringExtra(DetailNewsActivity.ARG_NEWS)};
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

    class Thread extends AsyncTask<String, Integer, Boolean> {
        private String code;

        @Override
        protected Boolean doInBackground(String... strings) {
            AlbionOnline ao = new AlbionOnline();
            code = ao.getNewsArticle(strings[0]);
            if (code.length() > 0)
                return true;
            else
                return false;
        }

        protected void onPostExecute(Boolean resultat) {
            afficherArticle(code);
        }
    }

    private void afficherArticle (String code) {
        webview.loadDataWithBaseURL(AlbionOnline.domaine, code, "text/html", "utf-8", null);
    }
}
