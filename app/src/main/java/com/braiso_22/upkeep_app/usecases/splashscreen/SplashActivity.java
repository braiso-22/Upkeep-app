package com.braiso_22.upkeep_app.usecases.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.owner.OwnerHomeActivity;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, OwnerHomeActivity.class);
        startActivity(intent);
        finish();

    }
}