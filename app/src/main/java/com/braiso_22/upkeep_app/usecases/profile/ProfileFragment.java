package com.braiso_22.upkeep_app.usecases.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.FragmentProfileBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.creation.UserCreationActivity;
import com.braiso_22.upkeep_app.utils.UserTypes;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    User user;
    Owner owner;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setUserViewData(user);
        binding.toolbar2.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_edit:
                    goToUserEdit(user);
                    break;
                case R.id.logout:
                    SharedPreferences preferences = getActivity().getSharedPreferences("savedUser", getActivity().MODE_PRIVATE);
                    preferences.edit().clear().apply();
                    getActivity().finish();
                    break;
                default:
                    break;
            }

            return false;
        });
    }

    private void setUserViewData(User user) {
        binding.userNameTextView.setText(user.getName());
        binding.userSurnameTextView.setText(user.getSurnames());
        binding.userEmailTextView.setText(user.getEmail());
        binding.userIdentificationTextView.setText(user.getIdentification());
    }

    private void goToUserEdit(User user) {
        Intent intent = new Intent(this.getActivity(), UserCreationActivity.class);
        intent.putExtra("userType", getUserType(user));
        intent.putExtra("user", user);
        intent.putExtra("owner", owner);

        startActivity(intent);
        getActivity().finish();
    }

    private UserTypes getUserType(User user) {
        if (user instanceof Operator) {
            return UserTypes.OPERATOR;
        } else if (user instanceof Owner) {
            return UserTypes.OWNER;
        } else if (user instanceof Manager) {
            return UserTypes.MANAGER;
        } else {
            return UserTypes.ADMIN;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}