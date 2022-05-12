package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Upkeep;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.UpkeepAdapter;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UpkeepListFragment extends Fragment {

    public UpkeepListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upkeep_list, container, false);
    }

    // onViewCreated
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.upkeepsRecyclerView);

        ViewModel viewModel = new ViewModel(getActivity().getApplication());
        viewModel.getAllUpkeeps().observe(this.getActivity(), upkeeps -> {
            recyclerView.setAdapter(new UpkeepAdapter(upkeeps, this.getActivity(), new UpkeepAdapter.OnUpkeepClickListener() {
                @Override
                public void onUpkeepClick(Upkeep upkeep) {
                    goToTaskList(upkeep);
                }
            }));

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    public void goToTaskList(Upkeep upkeep) {
        TaskListFragment taskListFragment = new TaskListFragment();
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, taskListFragment).commit();
    }
}