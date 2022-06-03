package com.braiso_22.upkeep_app.usecases.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.FleetsListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.UsersListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OwnerHomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.users:
                lastFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainerView, new UsersListFragment());
                ft.commit();
                break;
            case R.id.actual_table:
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                if (lastFragment != null && !lastFragment.equals(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView))) {
                    ft2.replace(R.id.fragmentContainerView, lastFragment);
                } else {
                    ft2.replace(R.id.fragmentContainerView, new FleetsListFragment());
                }
                ft2.commit();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.actual_table) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}