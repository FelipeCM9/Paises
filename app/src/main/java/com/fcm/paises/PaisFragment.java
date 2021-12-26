package com.fcm.paises;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fcm.paises.placeholder.PlaceholderContent;


public class PaisFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;


    public PaisFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PaisFragment newInstance(int columnCount) {
        PaisFragment fragment = new PaisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paises_list, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            String tipoVisualizacion = prefs.getString("list_preference_1", "Listado");
            if (tipoVisualizacion.equals("Listado")) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            }
            boolean useDivider = prefs.getBoolean("switch_preference_1", true);
            if(useDivider) {
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        LinearLayout.VERTICAL);
                recyclerView.addItemDecoration(verticalDecoration);
            }
            PlaceholderContent placeholderContent = new PlaceholderContent(getResources(),
                    getContext().getPackageName());
            recyclerView.setAdapter(new PaisRecyclerViewAdapter(PlaceholderContent.PAISES) );
        }
        return view;
    }
}