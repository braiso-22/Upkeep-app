package com.braiso_22.upkeep_app.usecases.home.owner.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.usecases.creation.FleetCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.FleetAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class FleetsListFragment extends Fragment {

    ViewModel vm;
    Owner owner;

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

        Toolbar toolbar = this.getView().findViewById(R.id.fleetsToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllFleets();
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), FleetCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get fleets from database with live data and set it to the recycler view
     *
     * @param recycler
     */
    private void inflateRecycler(RecyclerView recycler) {
        if (owner == null) {
            vm.getAllFleets().observe(this.getActivity(), fleets -> {
                if (getActivity() != null)
                    recycler.setAdapter(new FleetAdapter(fleets, this.getActivity(), new FleetAdapter.OnFleetClickListener() {
                        @Override
                        public void onFleetClick(Fleet fleet) {
                            goToBoatList(fleet);
                        }

                        @Override
                        public void onFleetLongClick(Fleet fleet, View view) {
                            showPopup(fleet, view);
                        }
                    }));
            });
        } else {
            vm.getFleetsByOwner(owner.getId()).observe(this.getActivity(), fleets -> {
                if (getActivity() != null)
                    recycler.setAdapter(new FleetAdapter(fleets, this.getActivity(), new FleetAdapter.OnFleetClickListener() {
                        @Override
                        public void onFleetClick(Fleet fleet) {
                            goToBoatList(fleet);
                        }

                        @Override
                        public void onFleetLongClick(Fleet fleet, View view) {
                            showPopup(fleet, view);
                        }
                    }));
            });
        }

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void goToBoatList(Fleet fleet) {
        BoatsListFragment fragment = new BoatsListFragment();
        fragment.setFleet(fleet);
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();
    }

    private void showPopup(Fleet fleet, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteOneOption:
                    vm.deleteFleet(fleet);
                    return true;
                case R.id.editOption:
                    goToFleetEdit(fleet);
                default:
                    return false;
            }
        });
        popup.show();
    }

    private void goToFleetEdit(Fleet fleet) {
        Intent intent = new Intent(this.getActivity(), FleetCreationActivity.class);
        intent.putExtra("fleet", fleet);
        startActivity(intent);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


}