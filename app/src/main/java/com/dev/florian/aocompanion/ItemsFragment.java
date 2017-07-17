package com.dev.florian.aocompanion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dev.florian.aocompanion.Adapters.PvpKillsAdapter;
import com.dev.florian.aocompanion.Class.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @Bind(R.id.spinner_category)
    Spinner category ;
    @Bind(R.id.spinner_tier)
    Spinner tier;

    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
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
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        getActivity().setTitle("Items");

        ButterKnife.bind(this, view);

        List<Category> messages = new ArrayList<>();
        messages.add(new Category("Tier1","Tier 1"));
        messages.add(new Category("Tier2","Tier 2"));
        messages.add(new Category("Tier3","Tier 3"));
        messages.add(new Category("Tier4","Tier 4"));
        ArrayAdapter <Category> arrayadapter = new ArrayAdapter<Category>( getContext(),android.R.layout.simple_spinner_item,messages);
        //arrayadapter.setDropDownViewResource(R.layout.textview);
        tier.setAdapter(arrayadapter);

        tier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Category mSelected = (Category) parent.getItemAtPosition(pos);
                //Log.i("Id:", mSelected.getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //Log.i("Message", "Nothing is selected");

            }


        });




        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.ao_tier, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //tier.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
