package com.braiso_22.upkeep_app.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.braiso_22.upkeep_app.databinding.ActivityNotOwnerLoginBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
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
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotOwnerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModel(getApplication());
        binding.loginNotOwnerEmailInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && !TextUtils.areFieldsEmpty(binding.loginNotOwnerEmailInput)) {
                viewModel.getManagerByLogin(binding.loginNotOwnerEmailInput.getText().toString())
                        .observe(this, manager -> {
                            if (user == null) {
                                user = manager;
                            }
                        });
                viewModel.getOperatorByLogin(binding.loginNotOwnerEmailInput.getText().toString())
                        .observe(this, operator -> {
                            if (user == null) {
                                user = operator;
                            }
                        });
            }
        });

        binding.loginNotOwnerRegisterButton.setOnClickListener(v -> {
            if (TextUtils.areFieldsEmpty(binding.loginNotOwnerEmailInput, binding.loginNotOwnerPasswordInput)) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isUserRegistered(user)) {
                Toast.makeText(this, "No se puede registrar con este usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String cipheredPassword = Encrypter.encrypt(binding.loginNotOwnerPasswordInput.getText().toString());
                user.setPassword(cipheredPassword);
                if (user instanceof Manager) {
                    viewModel.update((Manager) user);
                } else if (user instanceof Operator) {
                    viewModel.update((Operator) user);
                }
                goToHome();
            } catch (Exception e) {
                Toast.makeText(this, "Error en el cifrado de contraseñas", Toast.LENGTH_SHORT).show();
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
                if (!checkPassword(encryptedPassword)) {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                goToHome();
            } catch (Exception e) {
                Log.e("LoginEncryptError", e.getMessage());
                Toast.makeText(this, "Error al comprobar la contraseña, vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isUserRegistered(User user) {
        return this.user != null && this.user.equals(user) && this.user.getPassword() == null;
    }

    private boolean checkPassword(String password) {
        return this.user != null && this.user.getPassword().equals(password);
    }

    private void goToHome() {
        Intent intent = new Intent(this, NotOwnerHomeActivity.class);
        viewModel.getManagerByLogin(binding.loginNotOwnerEmailInput.getText().toString())
                .observe(this, manager -> {
                    if (user == null) {
                        user = manager;

                    } else {
                        intent.putExtra("user", user);
                        SharedPreferences sharedPreferences = getSharedPreferences("savedUser", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("savedUser", user.getLogin());
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }


                });
        viewModel.getOperatorByLogin(binding.loginNotOwnerEmailInput.getText().toString())
                .observe(this, operator -> {
                    int counter = 0;

                    if (user == null) {
                        user = operator;
                    } else if (counter == 0) {
                        counter++;
                        if (counter == 1) {
                            intent.putExtra("user", user);
                            SharedPreferences sharedPreferences = getSharedPreferences("savedUser", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("savedUser", user.getLogin());
                            editor.apply();
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}