package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Store;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.ComponentAdapter;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.StoreAdapter;
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
            }));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }
}