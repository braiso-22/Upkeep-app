package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityUserCreationBinding;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.utils.UserTypes;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UserCreationActivity extends AppCompatActivity {

    ActivityUserCreationBinding binding;
    ViewModel vm;
    UserTypes userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModel(getApplication());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userType = (UserTypes) extras.getSerializable("userType");
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
                saveUser();
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }

        });
        binding.cancelUserCreationButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void saveUser() {
        String login = binding.userLoginEditText.getText().toString();
        String name = binding.userNameEditText.getText().toString();
        String surnames = binding.userSurnameEditText.getText().toString();
        String email = binding.userEmailEditText.getText().toString();
        String identification = binding.userIdentificationEditText.getText().toString();
        String code = binding.userCodeEditText.getText().toString();
        int service = Integer.valueOf(binding.userServiceEditText.getText().toString());

        switch (userType) {
            case MANAGER:
                Manager manager = new Manager(login, name, surnames, email, identification, code, service);
                vm.insert(manager);
                break;
            case OPERATOR:
                Operator operator = new Operator(login, name, surnames, email, identification, code, service);
                vm.insert(operator);
                break;
            default:
                break;
        }
        finish();
    }
}