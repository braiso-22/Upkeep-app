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
import com.braiso_22.upkeep_app.model.vo.Upkeep;
import com.braiso_22.upkeep_app.usecases.creation.UpkeepCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.UpkeepAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UpkeepListFragment extends Fragment {
    ViewModel vm;

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
        vm = new ViewModel(getActivity().getApplication());
        RecyclerView recyclerView = view.findViewById(R.id.upkeepsRecyclerView);
        inflateRecycler(recyclerView);
        Toolbar toolbar = view.findViewById(R.id.upkeepsToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllUpkeeps();
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), UpkeepCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get tasks from database with live data and set it to the recycler view
     *
     * @param recyclerView
     */
    private void inflateRecycler(RecyclerView recyclerView) {
        vm.getAllUpkeeps().observe(this.getActivity(), upkeeps -> {
            recyclerView.setAdapter(new UpkeepAdapter(upkeeps, this.getActivity(), new UpkeepAdapter.OnUpkeepClickListener() {
                @Override
                public void onUpkeepClick(Upkeep upkeep) {
                    goToTaskList(upkeep);
                }

                @Override
                public void onUpkeepLongClick(Upkeep upkeep, View view) {
                    showPopupMenu(upkeep, view);
                }
            }));

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    public void goToTaskList(Upkeep upkeep) {
        TaskListFragment taskListFragment = new TaskListFragment();
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, taskListFragment).addToBackStack(null).commit();
    }

    private void showPopupMenu(Upkeep upkeep, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteOneOption:
                    vm.deleteUpkeep(upkeep);
                    return true;
                case R.id.editOption:
                    goToUpkeepEdit(upkeep);
                    return true;
                default:
                    return false;
            }
        });
        popup.show();

    }
    private  void goToUpkeepEdit(Upkeep upkeep){
        Intent intent = new Intent(this.getActivity(), UpkeepCreationActivity.class);
        intent.putExtra("upkeep", upkeep);
        startActivity(intent);
    }


}