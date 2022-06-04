package com.braiso_22.upkeep_app.usecases.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.braiso_22.upkeep_app.databinding.FragmentProfileBinding;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    User user;
    public ProfileFragment() {
    }
    public ProfileFragment(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUser(user);
    }

    public void setUser(User user) {
        binding.userNameTextView.setText(user.getName());
        binding.userSurnameTextView.setText(user.getSurnames());
        binding.userEmailTextView.setText(user.getEmail());
        binding.userIdentificationTextView.setText(user.getIdentification());
    }

}