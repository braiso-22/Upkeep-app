package com.braiso_22.upkeep_app.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import com.braiso_22.upkeep_app.databinding.ActivityOwnerLoginBinding;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.utils.Encrypter;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OwnerLoginActivity extends AppCompatActivity {

    ActivityOwnerLoginBinding binding;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOwnerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModel(getApplication());

        List<User> users = new ArrayList<>();
        viewModel.getAllOwners().observe(this, users::addAll);

        binding.loginOwnerRegisterbutton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginOwnerEmailInput, binding.loginOwnerPasswordInput)) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkUserExists(users, binding.loginOwnerEmailInput.getText().toString())) {
                Toast.makeText(this, "No se puede registrar con ese usuario", Toast.LENGTH_SHORT).show();
                return;
            }


            try {
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
                String encryptedPassword = Encrypter.encrypt(binding.loginOwnerPasswordInput.getText().toString());
                Owner owner = new Owner(binding.loginOwnerEmailInput.getText().toString(), encryptedPassword);
                viewModel.insert(owner);
                startOwnerActivity();
            } catch (Exception e) {
                Log.e("RegisterEncryptError", e.getMessage());
                Toast.makeText(this, "Error al encriptar la contraseña, vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
        });

        binding.loginOwnerLoginButton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginOwnerEmailInput, binding.loginOwnerPasswordInput)) {
                Toast.makeText(this, "Empty fields founds", Toast.LENGTH_SHORT).show();
                return;
            }
            String encryptedPassword = "";
            try {
                encryptedPassword = Encrypter.encrypt(binding.loginOwnerPasswordInput.getText().toString());
            } catch (Exception e) {
                Log.e("LoginEncryptError", e.getMessage());
                Toast.makeText(this, "Error al comprobar la contraseña, vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
            if (!checkPassword(users, binding.loginOwnerEmailInput.getText().toString(),
                    encryptedPassword)) {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                return;
            }
            startOwnerActivity();


        });
    }

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