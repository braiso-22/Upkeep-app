package com.braiso_22.upkeep_app.usecases.home.owner.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.FleetAdapter;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class FleetsListFragment extends Fragment {

    ViewModel vm;

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
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.fleetsRecyclerView);

        inflateRecycler(recycler);
        Toolbar toolbar = (Toolbar) this.getView().findViewById(R.id.fleetsToolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteAllOption:
                    vm.deleteAllFleets();
                    break;
            }
            return true;
        });
    }

    /**
     * Get fleets from database with live data and set it to the recycler view
     *
     * @param recycler
     */
    private void inflateRecycler(RecyclerView recycler) {

        vm.getAllFleets().observe(this.getActivity(), fleets -> {
            recycler.setAdapter(new FleetAdapter(fleets, this.getActivity(), new FleetAdapter.OnFleetClickListener() {
                @Override
                public void onFleetClick(Fleet fleet) {
                    goToBoatList(fleet);
                }
            }));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    /**
     * Change to the BoatListFragment
     */
    private void goToBoatList(Fleet fleet) {
        BoatsListFragment fragment = new BoatsListFragment();
        //fragment.setFleet(fleet);
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();
    }
}