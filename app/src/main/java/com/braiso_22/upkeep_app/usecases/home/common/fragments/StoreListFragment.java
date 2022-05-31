package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Store;
import com.braiso_22.upkeep_app.usecases.creation.StoreCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.StoreAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;


public class StoreListFragment extends Fragment {
    ViewModel vm;

    // Empty Constructor
    public StoreListFragment() {
    }

    // OnCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_list, container, false);
    }

    // OnViewCreated with ViewModel Observer for adapter
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.storeRecyclerView);
        inflateRecycler(recycler);
        Toolbar toolbar = this.getView().findViewById(R.id.storeToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllStores();
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), StoreCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get stores from database with live data and set it to the recycler view
     *
     * @param recycler
     */
    private void inflateRecycler(RecyclerView recycler) {
        vm.getAllStores().observe(this.getActivity(), stores -> {
            recycler.setAdapter(new StoreAdapter(this.getActivity(), stores, new StoreAdapter.OnStoreClickListener() {
                @Override
                public void onStoreClick(Store store) {

                }

                @Override
                public void onStoreLongClick(Store store, View view) {
                    showPopupMenu(store, view);
                }
            }));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void menuOnClick(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteAllOption:
                    vm.deleteAllFleets();
                    break;
            }
            return true;
        });
    }

    private void showPopupMenu(Store store, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteOneOption:
                    vm.deleteStore(store);
                    break;
                case R.id.editOption:
                    goToStoreEdit(store);
            }
            return true;
        });
        popup.show();
    }

    private void goToStoreEdit(Store store) {
        Intent intent = new Intent(this.getActivity(), StoreCreationActivity.class);
        intent.putExtra("store", store);
        startActivity(intent);
    }

}