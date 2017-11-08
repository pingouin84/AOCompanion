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

import com.dev.florian.aocompanion.Adapters.KillAdapter;
import com.dev.florian.aocompanion.Class.Kill;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KillBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private KillAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView ;
    @BindView(R.id.tab_pvp_kills)
    TabLayout tabLayout_kills;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public KillBoardFragment() {
        // Required empty public constructor
    }

     public static KillBoardFragment newInstance(String param1, String param2) {
        KillBoardFragment fragment = new KillBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kill_board, container, false);

        getActivity().setTitle("KILLBOARD");

        ButterKnife.bind(this, view);


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        tabLayout_kills.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String param = "";
                if (tab.getPosition() == 0)
                    param = AlbionOnline.TOP_PVP_KILLS;
                if (tab.getPosition() == 1)
                    param = AlbionOnline.RECENT_KILLS;
                thread (param);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        thread (AlbionOnline.TOP_PVP_KILLS);

        return view;
    }

    private void thread (String param) {
        progressBar.setVisibility(View.VISIBLE);

        List<Kill> killList = new ArrayList<>();
        adapter = new KillAdapter(killList);
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
    }

    class Thread extends AsyncTask<String, Integer, Boolean> {
        private String code;
        private List<Kill> killList = new ArrayList<>();

        @Override
        protected Boolean doInBackground(String... strings) {
            AlbionOnline ao = new AlbionOnline();
            killList = ao.getKillList(strings[0]);
            if (killList !=null && killList.size()>0)
                return true;
            else
                return false;
        }

        protected void onPostExecute (Boolean resultat){
            afficher(resultat, killList);
        }
    }

    void afficher (Boolean resultat,List<Kill> killList) {
        //this.killList = killList;
        if (resultat) {
            adapter = new KillAdapter(killList);
            recyclerView.setAdapter(adapter);
        }
        progressBar.setVisibility(View.GONE);
    }
}
