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
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.UserAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ManagerListFragment extends Fragment {
    ViewModel vm;
    // constructor
    public ManagerListFragment() {
        // Required empty public constructor
    }

    // onViewCreated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_list, container, false);
    }

    // onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.managerRecyclerView);
        inflateRecycler(recycler);

        Toolbar toolbar = this.getParentFragment().getView().findViewById(R.id.usersToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllManagers();
            }
        }, new CRUDToolbarMenu.CreateMethod(){
            @Override
            public void create() {

            }
        });

    }
    private void inflateRecycler(RecyclerView recycler) {
        vm.getAllManagers().observe(this.getActivity(), managers -> {
            List<User> users = new ArrayList<>(managers);
            recycler.setAdapter(new UserAdapter(users, this.getActivity(), new UserAdapter.OnUserClickListener() {
                @Override
                public void onUserClick(User manager) {

                }
            }));
        });
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

}