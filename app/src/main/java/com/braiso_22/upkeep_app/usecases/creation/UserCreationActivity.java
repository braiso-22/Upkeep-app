package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityUserCreationBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.NotOwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.utils.UserTypes;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserCreationActivity extends AppCompatActivity {

    ActivityUserCreationBinding binding;
    ViewModel vm;
    UserTypes userType;
    Bundle extras;
    User user;
    Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModel(getApplication());
        extras = getIntent().getExtras();
        userType = (UserTypes) extras.getSerializable("userType");
        if (extras.containsKey("owner")) {
            owner = (Owner) extras.getSerializable("owner");
        }
        if (extras.containsKey("user")) {
            user = (User) extras.getSerializable("user");

            binding.userNameEditText.setText(user.getName());
            binding.userEmailEditText.setText(user.getEmail());
            binding.userSurnameEditText.setText(user.getSurnames());
            binding.userCodeEditText.setText(user.getCode());
            binding.userLoginEditText.setText(user.getLogin());
            binding.userIdentificationEditText.setText(user.getIdentification());
            if (user instanceof Manager) {
                binding.userServiceEditText.setText(String.valueOf(((Manager) user).getService()));
            }
            if (user instanceof Operator) {
                binding.userServiceEditText.setText(String.valueOf(((Operator) user).getService()));
            }
            if (user instanceof Owner) {
                // set EditText invisible
                binding.userServiceEditText.setText("1");
                binding.userServiceEditText.setVisibility(View.GONE);
            }
        }
        onClickButtons();
    }

    private void onClickButtons() {
        binding.createUserCreationButton.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(binding.userLoginEditText, binding.userNameEditText,
                    binding.userSurnameEditText, binding.userEmailEditText,
                    binding.userIdentificationEditText, binding.userCodeEditText,
                    binding.userServiceEditText) &&
                    TextUtils.checkNumeric(binding.userServiceEditText)) {

                if (extras.containsKey("user")) {
                    updateUser();
                } else {
                    insertUser();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }

        });
        binding.cancelUserCreationButton.setOnClickListener(v -> {
            if (owner != null) {
                goToOwnerHome();
            } else {
                goToNotOwnerHome();
            }
        });
    }

    private void insertUser() {
        User user = getUserWithType();
        if (user instanceof Manager) {
            vm.insert((Manager) user);
        } else if (user instanceof Operator) {
            vm.insert((Operator) user);
        } else {
            vm.insert((Owner) user);
        }
        goToOwnerHome();
    }

    private void updateUser() {
        User user = getUserWithType();
        user.setId(this.user.getId());
        user.setPassword(this.user.getPassword());

        if (user instanceof Manager) {
            ((Manager) user).setOwner(((Manager) this.user).getOwner());
            vm.update((Manager) user);
        } else if (user instanceof Operator) {
            ((Operator) user).setOwner(((Operator) this.user).getId());
            vm.update((Operator) user);
        } else {
            vm.update((Owner) user);
        }
        this.user = user;
        if (owner != null) {
            goToOwnerHome();
        } else {
            goToNotOwnerHome();
        }
    }

    private User getUserWithType() {
        String login = binding.userLoginEditText.getText().toString();
        String name = binding.userNameEditText.getText().toString();
        String surnames = binding.userSurnameEditText.getText().toString();
        String email = binding.userEmailEditText.getText().toString();
        String identification = binding.userIdentificationEditText.getText().toString();
        String code = binding.userCodeEditText.getText().toString();
        int service = Integer.valueOf(binding.userServiceEditText.getText().toString());
        int ownerId;
        switch (userType) {
            case MANAGER:
                ownerId = owner == null ? ((Manager) user).getOwner() : owner.getId();
                return new Manager(login, code, identification, name, surnames, email, service, ownerId);
            case OPERATOR:
                ownerId = owner == null ? ((Operator) user).getOwner() : owner.getId();
                return new Operator(login, code, identification, name, surnames, email, service, ownerId);
            case OWNER:
                return new Owner(login, code, identification, name, surnames, email);
            default:
                break;
        }
        return null;
    }

    private void goToNotOwnerHome() {
        Intent intent = new Intent(this, NotOwnerHomeActivity.class);
        intent.putExtra("user", user);
        finish();
        startActivity(intent);
    }

    private void goToOwnerHome() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        intent.putExtra("user", owner);
        startActivity(intent);
        finish();
    }
}