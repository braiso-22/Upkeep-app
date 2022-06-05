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
    Owner user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOwnerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModel(getApplication());
        binding.loginOwnerEmailInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && !TextUtils.areFieldsEmpty(binding.loginOwnerEmailInput)) {
                viewModel.getOwnerByLogin(binding.loginOwnerEmailInput.getText().toString())
                        .observe(this, owner -> {
                            user = owner;
                        });
            }
        });


        binding.loginOwnerRegisterbutton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginOwnerEmailInput, binding.loginOwnerPasswordInput)) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String cipheredPassword = Encrypter.encrypt(binding.loginOwnerPasswordInput.getText().toString());
                Owner user = new Owner(binding.loginOwnerEmailInput.getText().toString(),
                        cipheredPassword);
                if (isUserRegistered(user)) {
                    Toast.makeText(this, "No se puede registrar con este usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.insert(user);
                goToHome();
            } catch (Exception e) {
                Toast.makeText(this, "Error en el cifrado de contraseñas", Toast.LENGTH_SHORT).show();
            }

        });

        binding.loginOwnerLoginButton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginOwnerEmailInput, binding.loginOwnerPasswordInput)) {
                Toast.makeText(this, "Empty fields founds", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String cipheredPassword = Encrypter.encrypt(binding.loginOwnerPasswordInput.getText().toString());
                if (checkPassword(cipheredPassword)) {
                    goToHome();
                } else {
                    Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error en el cifrado de contraseñas", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private boolean isUserRegistered(User user) {
        return this.user != null && this.user.equals(user);
    }

    private boolean checkPassword(String password) {
        return this.user != null && this.user.getPassword().equals(password);
    }

    private void goToHome() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        viewModel.getOwnerByLogin(binding.loginOwnerEmailInput.getText().toString())
                .observe(this, owner -> {
                    user = owner;
                    if(user!=null){
                        intent.putExtra("user", user);
                        user=null;
                        startActivity(intent);
                        finish();
                    }

                });
    }
}