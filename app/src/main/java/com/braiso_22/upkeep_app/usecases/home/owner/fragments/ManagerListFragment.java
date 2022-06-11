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
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.creation.UserCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.UserAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.utils.UserTypes;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerListFragment extends Fragment {
    ViewModel vm;
    Owner owner;

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
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), UserCreationActivity.class);
                intent.putExtra("userType", UserTypes.MANAGER);
                intent.putExtra("owner", owner);
                startActivity(intent);
                finish();
            }
            private void finish() {
                getActivity().finish();
            }
        });

    }

    private void inflateRecycler(RecyclerView recycler) {
        if (owner != null && !owner.getLogin().equals("brais")) {
            vm.getManagerByOwner(owner.getId()).observe(this.getActivity(), operators -> {
                List<User> users = new ArrayList<>(operators);
                recycler.setAdapter(new UserAdapter(users, this.getActivity(), new UserAdapter.OnUserClickListener() {
                    @Override
                    public void onUserClick(User operator) {

                    }

                    @Override
                    public void onUserLongClick(User operator, View view) {
                        showPopupMenu(operator, view);
                    }
                }));
            });
        } else {
            vm.getAllManagers().observe(this.getActivity(), operators -> {
                List<User> users = new ArrayList<>(operators);
                recycler.setAdapter(new UserAdapter(users, this.getActivity(), new UserAdapter.OnUserClickListener() {
                    @Override
                    public void onUserClick(User operator) {

                    }

                    @Override
                    public void onUserLongClick(User operator, View view) {
                        showPopupMenu(operator, view);
                    }
                }));
            });
        }
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void showPopupMenu(User manager, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteOneOption:
                    vm.deleteManager(((Manager) manager));
                    return true;
                case R.id.editOption:
                    goToUserEdit(manager);
                    return true;
                default:
                    return false;
            }
        });
        popup.show();
    }

    private void goToUserEdit(User user) {
        Intent intent = new Intent(this.getActivity(), UserCreationActivity.class);
        intent.putExtra("userType", UserTypes.MANAGER);
        intent.putExtra("user", user);
        intent.putExtra("owner", owner);
        startActivity(intent);
        this.getActivity().finish();
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}