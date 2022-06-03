package com.braiso_22.upkeep_app.usecases.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.HomeActivity;
import com.braiso_22.upkeep_app.usecases.login.NotOwnerLoginActivity;
import com.braiso_22.upkeep_app.usecases.login.OwnerLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoardingActivity extends AppCompatActivity {

    // Buttons
    public Button ownerButton;
    public Button employeeButton;
    public Button adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
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
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, NotOwnerLoginActivity.class);
                startActivity(intent);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}