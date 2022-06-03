package com.braiso_22.upkeep_app.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.braiso_22.upkeep_app.databinding.ActivityNotOwnerLoginBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.NotOwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.utils.Encrypter;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class NotOwnerLoginActivity extends AppCompatActivity {
    ActivityNotOwnerLoginBinding binding;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotOwnerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModel(getApplication());

        List<User> users = populateUsersArray();

        binding.loginNotOwnerRegisterbutton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginNotOwnerEmailInput, binding.loginNotOwnerPasswordInput)) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!checkUserNotRegistered(users, binding.loginNotOwnerEmailInput.getText().toString())) {
                Toast.makeText(this, "No se puede registrar con ese usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
                String encryptedPassword = Encrypter.encrypt(binding.loginNotOwnerPasswordInput.getText().toString());
                User user = getUserWithLogin(users, binding.loginNotOwnerEmailInput.getText().toString());
                user.setPassword(encryptedPassword);
                boolean isManager = user instanceof Manager;
                if (isManager) {
                    viewModel.update((Manager) user);
                } else {
                    viewModel.update((Operator) user);
                }
                startNotOwnerActivity();
            } catch (Exception e) {
                Log.e("RegisterEncryptError", e.getMessage());
                Toast.makeText(this, "Error al encriptar la contraseña, vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
        });

        binding.loginNotOwnerLoginButton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginNotOwnerEmailInput, binding.loginNotOwnerPasswordInput)) {
                Toast.makeText(this, "Empty fields founds", Toast.LENGTH_SHORT).show();
                return;
            }
            String encryptedPassword = "";
            try {
                encryptedPassword = Encrypter.encrypt(binding.loginNotOwnerPasswordInput.getText().toString());
            } catch (Exception e) {
                Log.e("LoginEncryptError", e.getMessage());
                Toast.makeText(this, "Error al comprobar la contraseña, vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
            if (!checkPassword(users, binding.loginNotOwnerEmailInput.getText().toString(),
                    encryptedPassword)) {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                return;
            }
            startNotOwnerActivity();
        });
    }

    // usuarios manager y operator

    private List<User> populateUsersArray() {
        List<User> users = new ArrayList<>();
        viewModel.getAllManagers().observe(this, users::addAll);
        viewModel.getAllOperators().observe(this, users::addAll);
        return users;
    }

    private boolean checkUserNotRegistered(List<User> users, String username) {
        boolean exists = false;
        for (User user : users) {
            if (user.getLogin().equals(username) && user.getPassword() == null) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private User getUserWithLogin(List<User> users, String username) {
        User user = null;
        for (User u : users) {
            if (u.getLogin().equals(username)) {
                user = u;
                break;
            }
        }
        return user;
    }

    private boolean checkPassword(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void startNotOwnerActivity() {
        Intent intent = new Intent(this, NotOwnerHomeActivity.class);
        startActivity(intent);
        finish();
    }
}