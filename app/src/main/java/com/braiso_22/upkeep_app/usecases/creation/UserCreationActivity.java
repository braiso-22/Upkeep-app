package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityUserCreationBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.utils.UserTypes;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UserCreationActivity extends AppCompatActivity {

    ActivityUserCreationBinding binding;
    ViewModel vm;
    UserTypes userType;
    Bundle extras;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModel(getApplication());

        extras = getIntent().getExtras();
        userType = (UserTypes) extras.getSerializable("userType");

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
            finish();
        });
    }

    private void insertUser() {
        User user = getUserWithType();
        if (user instanceof Manager) {
            vm.insert((Manager) user);
        } else {
            vm.insert((Operator) user);
        }
        finish();
    }

    private void updateUser() {
        User user = getUserWithType();
        user.setId(this.user.getId());
        if (user instanceof Manager) {
            vm.update((Manager) user);
        } else {
            vm.update((Operator) user);
        }
        finish();
    }

    private User getUserWithType() {
        String login = binding.userLoginEditText.getText().toString();
        String name = binding.userNameEditText.getText().toString();
        String surnames = binding.userSurnameEditText.getText().toString();
        String email = binding.userEmailEditText.getText().toString();
        String identification = binding.userIdentificationEditText.getText().toString();
        String code = binding.userCodeEditText.getText().toString();
        int service = Integer.valueOf(binding.userServiceEditText.getText().toString());
        switch (userType) {
            case MANAGER:
                return new Manager(login, code, identification, name, surnames, email, service);
            case OPERATOR:
                return new Operator(login, code, identification, name, surnames, email, service);
            default:
                break;
        }
        return null;
    }
}