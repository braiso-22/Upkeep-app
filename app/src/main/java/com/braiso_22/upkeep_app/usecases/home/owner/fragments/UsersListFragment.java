package com.braiso_22.upkeep_app.usecases.home.owner.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;

public class UsersListFragment extends Fragment {

    Owner owner;

    public UsersListFragment() {
        // Required empty public constructor
    }

    // on create view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    // on view created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwitchCompat switchCompat = view.findViewById(R.id.switch1);
        FragmentManager manager = this.getChildFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.setReorderingAllowed(true);
        ManagerListFragment fragment = new ManagerListFragment();
        fragment.setOwner(owner);

        ft.add(R.id.usersListFragmentContainer, fragment).commit();

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean operatorChecked = ((SwitchCompat) v).isChecked();
                FragmentTransaction ft = manager.beginTransaction();

                Fragment fragment;
                if (operatorChecked) {
                    fragment = new OperatorListFragment();
                    ((OperatorListFragment) fragment).setOwner(owner);
                } else {
                    fragment = new ManagerListFragment();
                    ((ManagerListFragment) fragment).setOwner(owner);
                }
                ft.replace(R.id.usersListFragmentContainer, fragment).commit();
            }
        });
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}