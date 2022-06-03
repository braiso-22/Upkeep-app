package com.braiso_22.upkeep_app.usecases.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.termsandconditions.TermsAndConditionsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.Theme_Upkeepapp);

        Intent intent = new Intent(this, TermsAndConditionsActivity.class);
        startActivity(intent);
        finish();

    }
}