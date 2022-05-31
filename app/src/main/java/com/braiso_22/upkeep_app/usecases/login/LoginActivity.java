package com.braiso_22.upkeep_app.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import com.braiso_22.upkeep_app.databinding.ActivityLoginBinding;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.owner.OwnerHomeActivity;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModel(getApplication());

        List<User> users = new ArrayList<>();
        viewModel.getAllOwners().observe(this, users::addAll);

        binding.loginRegisterbutton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginEmailInput, binding.loginPasswordInput)) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkUserExists(users, binding.loginEmailInput.getText().toString())) {
                Toast.makeText(this, "No se puede registrar con ese usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
            Owner owner = new Owner(binding.loginEmailInput.getText().toString(), binding.loginPasswordInput.getText().toString());
            viewModel.insert(owner);
            startOwnerActivity();

        });

        binding.loginLoginButton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginEmailInput, binding.loginPasswordInput)) {
                Toast.makeText(this, "Empty fields founds", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!checkPassword(users, binding.loginEmailInput.getText().toString(),
                    binding.loginPasswordInput.getText().toString())) {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startOwnerActivity();
        });
    }

    // usuarios manager y operator
    /*
    private List<User> populateUsersArray() {
        List<User> users = new ArrayList<>();
        viewModel.getAllManagers().observe(this, users::addAll);
        viewModel.getAllOperators().observe(this, users::addAll);
        return users;
    }*/

    private boolean checkUserExists(List<User> users, String username) {
        boolean exists = false;
        for (User user : users) {
            if (user.getLogin().equals(username)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean checkPassword(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void startOwnerActivity() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        startActivity(intent);
        finish();
    }
}