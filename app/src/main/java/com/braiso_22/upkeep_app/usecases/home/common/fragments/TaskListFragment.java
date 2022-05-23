package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Task;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.TaskAdapter;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class TaskListFragment extends Fragment {
    ViewModel vm;

    // Constructor
    public TaskListFragment() {
        // Required empty public constructor
    }

    // onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    // onViewCreated
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recyclerView = view.findViewById(R.id.tasksRecyclerView);
        inflateRecycler(recyclerView);
    }

    /**
     * Get tasks from database with live data and set it to the recycler view
     *
     * @param recyclerView
     */
    private void inflateRecycler(RecyclerView recyclerView) {
        vm.getAllTasks().observe(this.getActivity(), tasks -> {
            recyclerView.setAdapter(new TaskAdapter(tasks, this.getActivity(), new TaskAdapter.OnTaskClickListener() {
                @Override
                public void onTaskClick(Task task) {
                    goToStore(task);
                }
            }));
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void goToStore(Task task) {
        StoreListFragment storeListFragment = new StoreListFragment();
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, storeListFragment).addToBackStack(null).commit();
    }
}