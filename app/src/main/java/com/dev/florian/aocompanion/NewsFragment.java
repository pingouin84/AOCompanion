package com.dev.florian.aocompanion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dev.florian.aocompanion.Adapters.NewsAdapter;
import com.dev.florian.aocompanion.Class.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment {

    private NewsAdapter adapter;

    @BindView(R.id.news_recycler_view)
    RecyclerView recyclerView ;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout ;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        getActivity().setTitle("Actualités");

        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String param = "";
                if (tab.getPosition() == 0)
                    param = AlbionOnline.NEWS_DEV;
                if (tab.getPosition() == 1)
                    param = AlbionOnline.NEWS_COM;
                thread (param);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        thread (AlbionOnline.NEWS_COM);

        return view;
    }

    private void thread (String param) {
        progressBar.setVisibility(View.VISIBLE);

        List<News>newsList = new ArrayList<>();
        adapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);

        Thread thread = new Thread();
        String[] parametre = new String[]{param};
        thread.execute(parametre);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    class Thread extends AsyncTask<String, Integer, Boolean> {
        private String code;
        private List<News> newsList = new ArrayList<>();

        @Override
        protected Boolean doInBackground(String... strings) {
            AlbionOnline ao = new AlbionOnline();
            newsList = ao.getNewsList(strings[0]);
            if (newsList != null && newsList.size()>0)
                return true;
            else
                return false;
        }

        protected void onPostExecute (Boolean resultat){
            afficher(resultat,newsList);
        }
    }

    void afficher (Boolean resultat,List<News> newsList) {
        if (resultat) {
            adapter = new NewsAdapter(newsList);
            recyclerView.setAdapter(adapter);
        }
        progressBar.setVisibility(View.GONE);
    }
}
