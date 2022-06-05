package com.braiso_22.upkeep_app.usecases.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.NotOwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.termsandconditions.TermsAndConditionsActivity;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class SplashActivity extends AppCompatActivity {
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.Theme_Upkeepapp);

        viewModel = new ViewModel(getApplication());

        selectIntent();
    }

    private void selectIntent() {
        SharedPreferences sharedPreferences = getSharedPreferences("savedUser", MODE_PRIVATE);
        if (!sharedPreferences.contains("savedUser")) {
            Intent intent = new Intent(this, TermsAndConditionsActivity.class);
            startActivity(intent);
            finish();
        } else {
            String saved = sharedPreferences.getString("savedUser", null);
            viewModel.getOwnerByLogin(saved).observe(this, owner -> {
                if (owner != null) {
                    Intent intent = new Intent(this, OwnerHomeActivity.class);
                    intent.putExtra("user", owner);
                    startActivity(intent);
                    finish();
                }
            });
            viewModel.getManagerByLogin(saved).observe(this, manager -> {
                if (manager != null) {
                    Intent intent = new Intent(this, NotOwnerHomeActivity.class);
                    intent.putExtra("user", manager);
                    startActivity(intent);
                    finish();
                }
            });
            viewModel.getOperatorByLogin(saved).observe(this, operator -> {
                if (operator != null) {
                    Intent intent = new Intent(this, NotOwnerHomeActivity.class);
                    intent.putExtra("user", operator);
                    startActivity(intent);
                    finish();
                }
            });


        }
    }
}