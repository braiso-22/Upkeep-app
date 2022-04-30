package com.braiso_22.upkeep_app.usecases.home.owner.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.FleetAdapter;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class FleetsListFragment extends Fragment {

    public FleetsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fleets_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = this.getView().findViewById(R.id.recyclerView);

        ViewModel vm = new ViewModel(this.getActivity().getApplication());
        vm.getAllFleets().observe(this.getActivity(), fleets -> {
            recycler.setAdapter(new FleetAdapter(fleets, this.getActivity()));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }
}