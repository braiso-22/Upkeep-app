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
import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.usecases.creation.BoatCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.BoatAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class BoatsListFragment extends Fragment {
    ViewModel vm;

    public BoatsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boats_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.boatsRecyclerView);
        inflateRecycler(recycler);
        Toolbar toolbar = this.getView().findViewById(R.id.boatToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllBoats();
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), BoatCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inflateRecycler(RecyclerView recycler) {
        vm.getAllBoats().observe(this.getActivity(), boats -> {
            recycler.setAdapter(new BoatAdapter(boats, this.getActivity(), new BoatAdapter.OnBoatClickListener() {
                @Override
                public void onBoatClick(Boat boat) {
                    goToServicesList(boat);
                }

                @Override
                public void onBoatLongClick(Boat boat, View view) {
                    showPopupMenu(boat, view);
                }
            }));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void showPopupMenu(Boat boat, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.editOption:
                    goToBoatCreation(boat);
                    break;
                case R.id.deleteOneOption:
                    //vm.deleteBoat(boat);
                    break;
            }
            return true;
        });
        popup.show();
    }

    private void goToBoatCreation(Boat boat) {
        Intent intent = new Intent(this.getActivity(), BoatCreationActivity.class);
        intent.putExtra("boat", boat);
        startActivity(intent);
    }

    // method to change the fragment to services list fragment
    public void goToServicesList(Boat boat) {
        ServicesListFragment fragment = new ServicesListFragment();
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();
    }
}