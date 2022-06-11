package com.braiso_22.upkeep_app.usecases.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.login.NotOwnerLoginActivity;
import com.braiso_22.upkeep_app.usecases.login.OwnerLoginActivity;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoardingActivity extends AppCompatActivity {

    // Buttons
    public Button ownerButton;
    public Button employeeButton;
    public Button adminButton;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        viewModel = new ViewModel(getApplication());
        setButtons();
    }

    public void setButtons(){
        ownerButton = findViewById(R.id.onBoardingOwnerButton);
        employeeButton = findViewById(R.id.onBoardingEmployeeButton);
        adminButton = findViewById(R.id.onBoardingAdminButton);

        ownerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, OwnerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, NotOwnerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOwnerWithAdmin();
            }
        });
    }

    private void goToOwnerWithAdmin() {
        viewModel.getOwnerByLogin("brais").observe(this, owner -> {
            if (owner != null) {
                Intent intent = new Intent(OnBoardingActivity.this, OwnerHomeActivity.class);
                intent.putExtra("user", owner);
                startActivity(intent);
                finish();
            }
        });
    }
}