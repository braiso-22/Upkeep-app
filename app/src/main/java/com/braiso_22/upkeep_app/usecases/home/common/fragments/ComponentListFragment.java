package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.ComponentAdapter;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.ServiceAdapter;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class ComponentListFragment extends Fragment {

    // Empty Constructor
    public ComponentListFragment() {
    }

    // OnCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_component_list, container, false);
    }

    // OnViewCreated with ViewModel Observer for adapter
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = this.getView().findViewById(R.id.componentRecyclerView);

        ViewModel vm = new ViewModel(this.getActivity().getApplication());
        vm.getAllComponents().observe(this.getActivity(), components -> {
            recycler.setAdapter(new ComponentAdapter(components, this.getActivity(), new ComponentAdapter.OnComponentClickListener() {
                @Override
                public void onComponentClick(Component component) {

                }
            }));
        });

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

}